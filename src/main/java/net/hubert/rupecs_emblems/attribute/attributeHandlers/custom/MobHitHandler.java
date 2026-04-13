package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.ClientLightningParticleHandler;
import net.hubert.rupecs_emblems.client.ClientSoulConnectionParticleHandler;
import net.hubert.rupecs_emblems.effect.ModEffects;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.DamagingNoteProjectile;
import net.hubert.rupecs_emblems.entity.custom.HealingNoteProjectile;
import net.hubert.rupecs_emblems.entity.custom.OwnableLightningBolt;
import net.hubert.rupecs_emblems.util.AttributeHelper;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.TickEvent.ServerTickEvent;
import net.minecraftforge.event.entity.ProjectileImpactEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MobHitHandler {

    // List to keep track of pending modifier removals
    private static final List<ModifierRemovalTask> removalTasks = new ArrayList<>();
    public static final Map<LivingEntity, Integer> frozenEntities = new HashMap<>();
    public static final Map<LivingEntity, Integer> electrocutedEntities = new HashMap<>();
    public static final Map<LivingEntity, Integer> drowningEntities = new HashMap<>();
    public static final Map<LivingEntity, Integer> tidalEntities = new HashMap<>();
    public static final Map<LivingEntity, LivingEntity> markedTargets = new HashMap<>();
    public static final Map<LivingEntity, Integer> markedTimers = new HashMap<>();
    private static final Map<LivingEntity, LivingEntity> lightningParticleSourceTargets = new ConcurrentHashMap<>();
    private static final Map<UUID, Integer> hitCountForCrit = new HashMap<>();
    private static final Map<UUID, Integer> hitCount = new HashMap<>();
    private static final Map<UUID, Integer> damagedCount = new HashMap<>();
    private static final Map<UUID, Integer> critCount = new HashMap<>();

    // A simple data class for a removal task
    private static class ModifierRemovalTask {
        public final LivingEntity entity;
        public final AttributeModifier modifier;
        public int ticksLeft;

        public ModifierRemovalTask(LivingEntity entity, AttributeModifier modifier, int ticksLeft) {
            this.entity = entity;
            this.modifier = modifier;
            this.ticksLeft = ticksLeft;
        }
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        DamageSource source = event.getSource();
        // Check if the source is a player
        if (source.getEntity() instanceof Player) {
            Player player = (Player) source.getEntity();

            hitCount.putIfAbsent(player.getUUID(), 0);
            hitCount.computeIfPresent(player.getUUID(), (p, c) -> c+1);

            if (player.getAttribute(ModAttributes.MOON_FAVOR.get()) != null) {
                // Check that the target mob has the gravity attribute we want to modify
                if (event.getEntity().getAttribute(ForgeMod.ENTITY_GRAVITY.get()) != null) {
                    double favorValue = player.getAttribute(ModAttributes.MOON_FAVOR.get()).getValue();
                    AttributeModifier moonFavorModifier = new AttributeModifier(
                            "Moon favor",
                            -1 * favorValue,
                            AttributeModifier.Operation.ADDITION
                    );
                    LivingEntity target = event.getEntity();
                    // Apply the modifier transiently
                    target.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).addTransientModifier(moonFavorModifier);
                    // Schedule removal of the modifier after 20 ticks (1 second)
                    removalTasks.add(new ModifierRemovalTask(target, moonFavorModifier, 20));
                }
            }
            if (player.getAttribute(ModAttributes.FIRE_FIST.get()) != null && source.type() == player.damageSources().playerAttack(player).type()) {
                event.getEntity().setSecondsOnFire((int) player.getAttribute(ModAttributes.FIRE_FIST.get()).getValue());
            }
            if (player.getAttribute(ModAttributes.FROST_FIST.get()) != null
                    && player.getAttribute(ModAttributes.FROST_FIST.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                frozenEntities.put(event.getEntity(), ((int) player.getAttribute(ModAttributes.FROST_FIST.get()).getValue() * 20) + 10);
            }
            if (player.getAttribute(ModAttributes.ELECTRO_FIST.get()) != null
                    && player.getAttribute(ModAttributes.ELECTRO_FIST.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                electrocutedEntities.put(event.getEntity(), ((int) player.getAttribute(ModAttributes.ELECTRO_FIST.get()).getValue() * 20) + 10);
            }
            if (player.getAttribute(ModAttributes.BUBBLE_FIST.get()) != null
                    && player.getAttribute(ModAttributes.BUBBLE_FIST.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                drowningEntities.put(event.getEntity(), ((int) player.getAttribute(ModAttributes.BUBBLE_FIST.get()).getValue() * 20) + 10);
            }
            if (player.getAttribute(ModAttributes.BLEEDING_FIST.get()) != null
                    && player.getAttribute(ModAttributes.BLEEDING_FIST.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                event.getEntity().addEffect(new MobEffectInstance(ModEffects.BLEEDING.get(), 400, (int) AttributeHelper.getValueIfNotNull(player, ModAttributes.BLEEDING_FIST.get())));
            }
            if (player.getAttribute(ModAttributes.WITHER_FIST.get()) != null
                    && player.getAttribute(ModAttributes.WITHER_FIST.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 100, (int) AttributeHelper.getValueIfNotNull(player, ModAttributes.WITHER_FIST.get())));
            }
            if (player.getAttribute(ModAttributes.TIDAL_FIST.get()) != null
                    && player.getAttribute(ModAttributes.TIDAL_FIST.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                tidalEntities.put(event.getEntity(), ((int) player.getAttribute(ModAttributes.TIDAL_FIST.get()).getValue() * 20) + 10);
            }
            if (player.getAttribute(ModAttributes.SCHADENFREUDE.get()) != null
                    && player.getAttribute(ModAttributes.SCHADENFREUDE.get()).getValue() > 0
                    && source.getEntity() == player) {

                markedTargets.put(event.getEntity(), player); // target -> attacker
                markedTimers.put(event.getEntity(), 100);     // duration (ticks)
            }
            if (player.getAttribute(ModAttributes.LIGHTNING_ASPECT.get()) != null
                    && player.getAttribute(ModAttributes.LIGHTNING_ASPECT.get()).getValue() > 0
                    && source.getEntity() == player) {
                OwnableLightningBolt lightningBolt = new OwnableLightningBolt(ModEntities.OWNABLE_LIGHTNING_BOLT.get(), player.level());
                lightningBolt.setPos(event.getEntity().position());
                lightningBolt.setDamage((float) (player.getAttribute(ModAttributes.LIGHTNING_ASPECT.get()).getValue()*2));
                lightningBolt.setOwner(player);
                if (player instanceof ServerPlayer serverPlayer) lightningBolt.setCause(serverPlayer);
                player.level().addFreshEntity(lightningBolt);
            }
            if (player.getAttribute(ModAttributes.MUDDY_FIST.get()) != null
                    && player.getAttribute(ModAttributes.MUDDY_FIST.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, (int) AttributeHelper.getValueIfNotNull(player, ModAttributes.MUDDY_FIST.get())));
            }
            // NEW: STATIC attribute - zap enemies with lightning that bounces up to 2 times
            if (player.getAttribute(ModAttributes.STATIC.get()) != null
                    && player.getAttribute(ModAttributes.STATIC.get()).getValue() > 0
                    && source.type() == player.damageSources().playerAttack(player).type()) {
                if (event.getEntity() instanceof LivingEntity) {
                    LivingEntity target = (LivingEntity) event.getEntity();
                    List<LivingEntity> alreadyZapped = new ArrayList<>();
                    alreadyZapped.add(target);
                    zapAndBounce(player.level(), player, target, (int) player.getAttributeValue(ModAttributes.STATIC.get()), alreadyZapped, 3);
                    lightningParticleSourceTargets.put(player, target);

                }
            }

            if (player.getAttributeValue(ModAttributes.UNTUNED.get()) >0 && !event.getSource().isIndirect() && player.getRandom().nextFloat() < 0.5) {
                DamagingNoteProjectile entityToAdd = new DamagingNoteProjectile(ModEntities.DAMAGING_NOTE.get(), player.level(), ((float) player.getAttributeValue(ModAttributes.UNTUNED.get())), true, false);
                entityToAdd.setPos(player.position());
                RandomSource random = player.getRandom();
                Vec3 randomDir = new Vec3(
                        (random.nextDouble() - 0.5) * 0.3,  // X: random between -0.15 and +0.15
                        0.1 + random.nextDouble() * 0.1,    // Y: random upward between 0.2–0.4
                        (random.nextDouble() - 0.5) * 0.3   // Z: random between -0.15 and +0.15
                );
                entityToAdd.addDeltaMovement(randomDir);
                entityToAdd.setTarget(event.getEntity());
                entityToAdd.setOwner(player);
                player.level().addFreshEntity(entityToAdd);
            }
            if (player.getAttributeValue(ModAttributes.TUNED.get()) >0 && !event.getSource().isIndirect() && hitCount.getOrDefault(player.getUUID(), 1) % 3 == 0) {
                HealingNoteProjectile entityToAdd = new HealingNoteProjectile(ModEntities.HEALING_NOTE.get(), player.level(), ((float) player.getAttributeValue(ModAttributes.TUNED.get())), true);
                entityToAdd.setPos(player.position());
                RandomSource random = player.getRandom();
                Vec3 randomDir = new Vec3(
                        (random.nextDouble() - 0.5) * 0.3,  // X: random between -0.15 and +0.15
                        0.1 + random.nextDouble() * 0.1,    // Y: random upward between 0.2–0.4
                        (random.nextDouble() - 0.5) * 0.3   // Z: random between -0.15 and +0.15
                );
                entityToAdd.addDeltaMovement(randomDir);
                entityToAdd.setOwner(player);
                player.level().addFreshEntity(entityToAdd);
            }

        }
        if (event.getEntity() instanceof Player player) {

            damagedCount.putIfAbsent(player.getUUID(), 0);
            damagedCount.computeIfPresent(player.getUUID(), (p, c) -> c+1);
            if (player.getAttribute(ModAttributes.FRAGILE_BODY.get()) != null) {
                event.setAmount((float) (event.getAmount() + player.getAttribute(ModAttributes.FRAGILE_BODY.get()).getValue()));
            }
            if (player.getAttribute(ModAttributes.DAMAGE_REFLECTION.get()) != null && player.getAttribute(ModAttributes.DAMAGE_REFLECTION.get()).getValue() > 0 && event.getSource().getEntity() != player) {
                if (event.getSource().getEntity() == null || !event.getSource().getEntity().isAlive()) return;
                event.getSource().getEntity().hurt(player.damageSources().magic(), (float) (event.getAmount() * (player.getAttribute(ModAttributes.DAMAGE_REFLECTION.get()).getValue())));
            }
            if (player.getAttribute(ModAttributes.THORNS.get()) != null && player.getAttribute(ModAttributes.THORNS.get()).getValue() > 0 && event.getSource().getEntity() != player) {
                if (event.getSource().getEntity() == null || !event.getSource().getEntity().isAlive()) return;
                event.getSource().getEntity().hurt(player.damageSources().playerAttack(player), (float) (player.getAttribute(ModAttributes.THORNS.get()).getValue()));
            }
            if (player.getAttribute(ModAttributes.SNEAKY_DEFENCE.get()) != null && player.getAttribute(ModAttributes.SNEAKY_DEFENCE.get()).getValue() > 0) {
                if (player.isCrouching()){
                    event.setAmount((float) (Math.max(1, event.getAmount() - (event.getAmount() * 0.1 * AttributeHelper.getValueIfNotNull(player, ModAttributes.SNEAKY_DEFENCE.get())))));
                }
            }
            if (player.getAttribute(ModAttributes.SOUL_CONNECTOR.get()) != null
                    && player.getAttribute(ModAttributes.SOUL_CONNECTOR.get()).getValue() > 0
                    && event.getSource().getEntity() != player){
                if (ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.keySet().contains(player)){
                    float amountToHurtPlayer = event.getAmount();
                    float amountToHurtEntity = amountToHurtPlayer / 2 / ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.get(player).size();
                    for (LivingEntity entityToHurt : ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.get(player)){
                        entityToHurt.hurt(player.damageSources().magic(), amountToHurtEntity);
                        amountToHurtPlayer -= amountToHurtEntity;
                    }
                    event.setAmount(amountToHurtPlayer);
                }
            }
            if (player.getAttribute(ModAttributes.FEATHER_FEET.get()) != null && source.type() == player.damageSources().fall().type()) {
                event.setAmount((float) (event.getAmount() * player.getAttribute(ModAttributes.FEATHER_FEET.get()).getValue()));
            }
            if (player.getAttribute(ModAttributes.MOON_FEET.get()) != null &&
                    source.type() == player.damageSources().fall().type() && AttributeHelper.getValueIfNotNull(player, ModAttributes.MOON_FEET.get()) > 0) {
                player.resetFallDistance();
                event.setCanceled(true);

                // Cancel visual feedback like screen shake / red damage flash
                player.hurtDuration = 0;
                player.hurtTime = 0;
                player.hurtMarked = false;


            }
            if (player.getAttribute(ModAttributes.CONDUCTOR.get()) != null &&
                    player.getAttribute(ModAttributes.CONDUCTOR.get()).getValue() > 0 &&
                    event.getSource().getEntity() instanceof LivingEntity livingSource) {
                zapAndBounce(player.level(), player, livingSource, 0, new ArrayList<>(), player.getAttribute(ModAttributes.CONDUCTOR.get()).getValue());
            }
            if (player.getAttribute(ModAttributes.ANTHEM.get()) != null &&
                    player.getAttributeValue(ModAttributes.ANTHEM.get()) > 0 && damagedCount.getOrDefault(player.getUUID(), 0) %2 == 0) {
                for (int i = 0; i < 3; i++) {
                    HealingNoteProjectile entityToAdd = new HealingNoteProjectile(ModEntities.HEALING_NOTE.get(), player.level(), ((float) player.getAttributeValue(ModAttributes.ANTHEM.get())), true);
                    entityToAdd.setPos(player.position());
                    RandomSource random = player.getRandom();
                    Vec3 randomDir = new Vec3(
                            (random.nextDouble() - 0.5) * 0.3,  // X: random between -0.15 and +0.15
                            0.1 + random.nextDouble() * 0.1,    // Y: random upward between 0.2–0.4
                            (random.nextDouble() - 0.5) * 0.3   // Z: random between -0.15 and +0.15
                    );
                    entityToAdd.addDeltaMovement(randomDir);
                    entityToAdd.setOwner(player);
                    player.level().addFreshEntity(entityToAdd);
                }
            }
            if (player.getAttribute(ModAttributes.I_ANTHEM.get()) != null &&
                    player.getAttributeValue(ModAttributes.I_ANTHEM.get()) > 0 && damagedCount.getOrDefault(player.getUUID(), 0) %2 == 0) {
                for (int i = 0; i < Math.random() * 10; i++) {
                    HealingNoteProjectile entityToAdd = new HealingNoteProjectile(ModEntities.HEALING_NOTE.get(), player.level(), ((float) player.getAttributeValue(ModAttributes.I_ANTHEM.get())), true);
                    entityToAdd.setPos(player.position());
                    RandomSource random = player.getRandom();
                    Vec3 randomDir = new Vec3(
                            (random.nextDouble() - 0.5) * 0.3,  // X: random between -0.15 and +0.15
                            0.1 + random.nextDouble() * 0.1,    // Y: random upward between 0.2–0.4
                            (random.nextDouble() - 0.5) * 0.3   // Z: random between -0.15 and +0.15
                    );
                    entityToAdd.addDeltaMovement(randomDir);
                    entityToAdd.setOwner(player);
                    player.level().addFreshEntity(entityToAdd);
                }
            }

        }
    }
    @SubscribeEvent
    public static void onProjectileHit(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof EntityHitResult hitResult) {
            if (hitResult.getEntity() instanceof Player player) {

                if (player.getAttribute(ModAttributes.PAPER.get()) != null &&
                        player.getAttributeValue(ModAttributes.PAPER.get()) > 0 &&
                        player.level().getRandom().nextFloat() < 0.4f) {
                    event.getProjectile().discard();

                }
            }
        }
    }
    @SubscribeEvent
    public static void onCrit(CriticalHitEvent event){
        Player player = event.getEntity();
        if (event.isVanillaCritical() && !player.level().isClientSide){
            critCount.putIfAbsent(player.getUUID(), 0);
            critCount.computeIfPresent(player.getUUID(), (p, c) -> c+1);
            System.out.println(critCount);
        }
        if (player.getAttributeValue(ModAttributes.SONG.get()) > 0){
            if (event.isVanillaCritical() && critCount.getOrDefault(player.getUUID(), 1) %2 == 0) {
                System.out.println(critCount);
                HealingNoteProjectile entityToAdd = new HealingNoteProjectile(ModEntities.HEALING_NOTE.get(), player.level(), ((float) player.getAttributeValue(ModAttributes.SONG.get())), true);
                entityToAdd.setPos(player.position());
                RandomSource random = player.getRandom();
                Vec3 randomDir = new Vec3(
                        (random.nextDouble() - 0.5) * 0.3,  // X: random between -0.15 and +0.15
                        0.1 + random.nextDouble() * 0.1,    // Y: random upward between 0.2–0.4
                        (random.nextDouble() - 0.5) * 0.3   // Z: random between -0.15 and +0.15
                );
                entityToAdd.addDeltaMovement(randomDir);
                entityToAdd.setOwner(player);
                player.level().addFreshEntity(entityToAdd);
            }
        }
        if (player.getAttributeValue(ModAttributes.BEAT.get()) > 0){
                hitCountForCrit.putIfAbsent(player.getUUID(), 0);
                hitCountForCrit.computeIfPresent(player.getUUID(), (p, c) -> c+1);
                if (player.getRandom().nextFloat() < 0.005 * player.getAttributeValue(ModAttributes.BEAT.get()) * (hitCountForCrit.getOrDefault(player.getUUID(), 1) * hitCountForCrit.getOrDefault(player.getUUID(), 1))) {
                    if (event.getOldDamageModifier() != 1.5F) {
                        event.setDamageModifier(event.getOldDamageModifier() + 0.5f);
                        event.setResult(Event.Result.ALLOW);
                        hitCountForCrit.computeIfPresent(player.getUUID(), (p, c) -> 0);
                    }
                }
        }
    }

    @SubscribeEvent
    public static void onEntityDie(LivingDeathEvent event) {
        DamageSource source = event.getSource();
        if (source == null) return;

        Entity directEntity = source.getDirectEntity(); // e.g. fireball
        Entity trueSource = source.getEntity();         // attacker (may be Player)

        if (trueSource instanceof Player player) {
            double value = AttributeHelper.getValueIfNotNull(player, ModAttributes.SCHADENFREUDE.get());
            if (value > 0) {
                int amplifier = (int) value;

                applyOrIncreaseEffect(player, MobEffects.MOVEMENT_SPEED, amplifier);
                applyOrIncreaseEffect(player, MobEffects.DAMAGE_BOOST, amplifier);
            }
        }
        LivingEntity target = event.getEntity();


        if (markedTargets.containsKey(target) && markedTargets.get(target) instanceof Player player) {
            double value = AttributeHelper.getValueIfNotNull(player, ModAttributes.SCHADENFREUDE.get());
            if (value > 0) {
                int amplifier = (int) value;

                applyOrIncreaseEffect(player, MobEffects.MOVEMENT_SPEED, amplifier);
                applyOrIncreaseEffect(player, MobEffects.DAMAGE_BOOST, amplifier);
            }
        }


        // Clean up tracking
        markedTargets.remove(target);
        markedTimers.remove(target);



    }
    @SubscribeEvent
    public static void onLivingDrops(LivingDropsEvent event){

        if (event.getSource().getEntity() instanceof Player player) {
            double value = AttributeHelper.getValueIfNotNull(player, ModAttributes.SKIN_TRANSPLANTER.get());
            if (value > 0) {
                if (event.getEntity() instanceof Cow cow) {
                    ServerLevel level = (ServerLevel) cow.level();

                    // Instead of cow's loot, use rabbit's loot table
                    ResourceLocation rabbitLootId = EntityType.RABBIT.getDefaultLootTable();
                    LootTable lootTable = level.getServer().getLootData().getLootTable(rabbitLootId);

                    LootParams.Builder builder = new LootParams.Builder(level)
                            .withParameter(LootContextParams.THIS_ENTITY, cow) // use cow as "this entity" so drops appear at its position
                            .withParameter(LootContextParams.ORIGIN, cow.position())
                            .withOptionalParameter(LootContextParams.DAMAGE_SOURCE, event.getSource())
                            .withOptionalParameter(LootContextParams.KILLER_ENTITY, player);

                    LootParams params = builder.create(LootContextParamSets.ENTITY);

                    // Generate rabbit loot
                    List<ItemStack> loot = lootTable.getRandomItems(params);
                    Collection<ItemEntity> drops = new ArrayList<>();
                    for (ItemStack stack : loot) {
                        drops.add(new ItemEntity(
                                cow.level(),
                                cow.getX(),
                                cow.getY(),
                                cow.getZ(),
                                stack
                        ));
                    }

                    event.getDrops().clear();
                    event.getDrops().addAll(drops);
                } else if (event.getEntity() instanceof Rabbit cow) {
                    ServerLevel level = (ServerLevel) cow.level();

                    // Instead of cow's loot, use rabbit's loot table
                    ResourceLocation rabbitLootId = EntityType.COW.getDefaultLootTable();
                    LootTable lootTable = level.getServer().getLootData().getLootTable(rabbitLootId);

                    LootParams.Builder builder = new LootParams.Builder(level)
                            .withParameter(LootContextParams.THIS_ENTITY, cow) // use cow as "this entity" so drops appear at its position
                            .withParameter(LootContextParams.ORIGIN, cow.position())
                            .withOptionalParameter(LootContextParams.DAMAGE_SOURCE, event.getSource())
                            .withOptionalParameter(LootContextParams.KILLER_ENTITY, player);

                    LootParams params = builder.create(LootContextParamSets.ENTITY);

                    // Generate rabbit loot
                    List<ItemStack> loot = lootTable.getRandomItems(params);
                    Collection<ItemEntity> drops = new ArrayList<>();
                    for (ItemStack stack : loot) {
                        drops.add(new ItemEntity(
                                cow.level(),
                                cow.getX(),
                                cow.getY(),
                                cow.getZ(),
                                stack
                        ));
                    }

                    event.getDrops().clear();
                    event.getDrops().addAll(drops);
                }
            }
        }
        if (event.getSource().getEntity() instanceof Player player) {
            if (player.getAttributeValue(ModAttributes.SMOKING.get()) <= 0) return;
            Collection<ItemEntity> drops = new ArrayList<>();
            Iterator<ItemEntity> iterator = event.getDrops().iterator();
            while (iterator.hasNext()) {
                ItemEntity item = iterator.next();

                if (isSmokerable(event.getEntity().level(), item.getItem())) {

                    drops.add(new ItemEntity(
                            player.level(),
                            event.getEntity().getX(),
                            event.getEntity().getY(),
                            event.getEntity().getZ(),
                            getSmoke(player.level(), new SimpleContainer(item.getItem())).copyWithCount(item.getItem().getCount())
                    ));

                    iterator.remove(); // safe remove
                }
            }
            event.getDrops().clear();
            event.getDrops().addAll(drops);
        }
        if (event.getSource().getEntity() instanceof Player player && (event.getEntity() instanceof Animal || event.getEntity() instanceof WaterAnimal)) {
            double value = AttributeHelper.getValueIfNotNull(player, ModAttributes.BUTCHER_BLESSING.get());
            if (value > 0) {
                int amplifier = (int) value;
                if (player.getRandom().nextFloat() < 0.2 + (0.05 * amplifier)) {
                    for (ItemEntity item : event.getDrops()) {
                        ItemEntity copy = item.copy();
                        copy.setPos(event.getEntity().position());
                        event.getEntity().level().addFreshEntity(copy);
                    }

                }
            }

        }
    }
    private static void applyOrIncreaseEffect(Player player, MobEffect effect, int baseAmplifier) {
        MobEffectInstance existing = player.getEffect(effect);
        if (existing == null) {
            player.addEffect(new MobEffectInstance(effect, 100, baseAmplifier));
        } else {
            int increasedAmp = player.getRandom().nextFloat()<0.2 ? existing.getAmplifier() + 1 : existing.getAmplifier();
            player.addEffect(new MobEffectInstance(effect, 100, increasedAmp));
        }
    }

    @SubscribeEvent
    public static void onServerTick(ServerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Iterator<ModifierRemovalTask> iterator = removalTasks.iterator();
            while (iterator.hasNext()) {
                ModifierRemovalTask task = iterator.next();
                task.ticksLeft--;
                if (task.ticksLeft <= 0) {
                    if (task.entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()) != null) {
                        task.entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).removeModifier(task.modifier);
                    }
                    iterator.remove();
                }
            }
            Iterator<Map.Entry<LivingEntity, Integer>> iterator_f = frozenEntities.entrySet().iterator();
            while (iterator_f.hasNext()) {
                Map.Entry<LivingEntity, Integer> entry = iterator_f.next();
                LivingEntity entity = entry.getKey();
                int remainingTicks = entry.getValue();

                if (!entity.isAlive()) {
                    iterator_f.remove();
                    continue;
                }

                // Apply freeze damage every second (20 ticks)
                if (remainingTicks > 0 && entity.tickCount % 20 == 0) {
                    entity.invulnerableTime = 0;
                    entity.hurt(entity.damageSources().freeze(), 1.0f);
                }

                if (remainingTicks > 0) {
                    entry.setValue(remainingTicks - 1);
                } else {
                    iterator_f.remove();
                }
            }
            Iterator<Map.Entry<LivingEntity, Integer>> iterator_e = electrocutedEntities.entrySet().iterator();
            while (iterator_e.hasNext()) {
                Map.Entry<LivingEntity, Integer> entry = iterator_e.next();
                LivingEntity entity = entry.getKey();
                int remainingTicks = entry.getValue();

                if (!entity.isAlive()) {
                    iterator_e.remove();
                    continue;
                }

                // Apply freeze damage every second (20 ticks)
                if (remainingTicks > 0 && entity.tickCount % 20 == 0) {
                    entity.invulnerableTime = 0;
                    entity.hurt(entity.damageSources().lightningBolt(), 1.0f);

                    AABB searchBox = entity.getBoundingBox().inflate(3);
                    List<LivingEntity> nearbyEnemies = entity.level().getEntitiesOfClass(LivingEntity.class, searchBox,
                            e -> e != entity && e.isAlive() && !(e instanceof Player) && (e instanceof Monster || e.getClass() == entity.getClass()));
                    if (!nearbyEnemies.isEmpty()) {
                        int randomIndex = ThreadLocalRandom.current().nextInt(nearbyEnemies.size());
                        LivingEntity nextTarget = nearbyEnemies.get(randomIndex);

                        // Continue the chain, using target as the new source.
                        zapAndBounce(entity.level(), entity, nextTarget, 0, new ArrayList<>(), 3);
                    }
                }

                if (remainingTicks > 0) {
                    entry.setValue(remainingTicks - 1);
                } else {
                    iterator_e.remove();
                }
            }
            Iterator<Map.Entry<LivingEntity, Integer>> iterator_d = drowningEntities.entrySet().iterator();
            while (iterator_d.hasNext()) {
                Map.Entry<LivingEntity, Integer> entry = iterator_d.next();
                LivingEntity entity = entry.getKey();
                int remainingTicks = entry.getValue();

                if (!entity.isAlive()) {
                    iterator_d.remove();
                    continue;
                }

                // Apply freeze damage every second (20 ticks)
                if (remainingTicks > 0 && entity.tickCount % 20 == 0) {
                    entity.invulnerableTime = 0;
                    entity.hurt(entity.damageSources().drown(), 1.0f);
                }

                if (remainingTicks > 0) {
                    entry.setValue(remainingTicks - 1);
                } else {
                    iterator_d.remove();
                }
            }
            Iterator<Map.Entry<LivingEntity, Integer>> iterator_t = tidalEntities.entrySet().iterator();
            while (iterator_t.hasNext()) {
                Map.Entry<LivingEntity, Integer> entry = iterator_t.next();
                LivingEntity entity = entry.getKey();
                int remainingTicks = entry.getValue();

                if (!entity.isAlive()) {
                    iterator_t.remove();
                    continue;
                }

                // Apply freeze damage every second (20 ticks)
                if (remainingTicks > 0) {
                    MobControlHandler.getNearbyMobs(entity, 2).forEach(mob -> {
                        mob.addDeltaMovement(entity.position().add(mob.position().reverse()).normalize().scale(0.05));
                        if (mob.getClass().equals(entity.getClass())) {
                            if (mob.getAttribute(ForgeMod.ENTITY_GRAVITY.get()) != null && entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()) != null) {
                                double favorValue = entity.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue();
                                AttributeModifier moonFavorModifier = new AttributeModifier(
                                        "Tidal Moon Favor",
                                        -mob.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).getValue() + favorValue,
                                        AttributeModifier.Operation.ADDITION
                                );
                                // Apply the modifier transiently
                                mob.getAttribute(ForgeMod.ENTITY_GRAVITY.get()).addTransientModifier(moonFavorModifier);
                                // Schedule removal of the modifier after 20 ticks (1 second)
                                removalTasks.add(new ModifierRemovalTask(mob, moonFavorModifier, 20));
                            }
                        }
                    });
                }


                if (remainingTicks > 0) {
                    entry.setValue(remainingTicks - 1);
                } else {
                    iterator_t.remove();
                }
            }
            Iterator<Map.Entry<LivingEntity, Integer>> iterator_m = markedTimers.entrySet().iterator();
            while (iterator_m.hasNext()) {
                Map.Entry<LivingEntity, Integer> entry = iterator_m.next();
                int remaining = entry.getValue() - 1;
                if (remaining <= 0) {
                    markedTargets.remove(entry.getKey());
                    iterator_m.remove();
                } else {
                    entry.setValue(remaining);
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        // Client-side particles only
        if (frozenEntities.containsKey(entity)) {
            if (entity.tickCount % 5 == 0) {
                spawnFreezeParticles(entity);
            }
        }
        if (electrocutedEntities.containsKey(entity)) {
            if (entity.tickCount % 5 == 0) {
                spawnElectrocutedParticles(entity);
            }
        }
        if (drowningEntities.containsKey(entity)) {
            if (entity.tickCount % 5 == 0) {
                spawnDrowningParticles(entity);
            }
        }
    }

    // Modified to use ThreadLocalRandom.current() instead of entity.getRandom()
    private static void spawnFreezeParticles(LivingEntity entity) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;
        for (int i = 0; i < 5; i++) {
            double offsetX = (ThreadLocalRandom.current().nextDouble() - 0.5) * entity.getBbWidth();
            double offsetY = ThreadLocalRandom.current().nextDouble() * entity.getBbHeight();
            double offsetZ = (ThreadLocalRandom.current().nextDouble() - 0.5) * entity.getBbWidth();

            serverLevel.sendParticles(ParticleTypes.SNOWFLAKE,
                    entity.getX() + offsetX,
                    entity.getY() + offsetY,
                    entity.getZ() + offsetZ,
                    1, 0, 0.05, 0,0);
        }
    }
    private static void spawnElectrocutedParticles(LivingEntity entity) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;
        for (int i = 0; i < 3; i++) {
            double offsetX = (ThreadLocalRandom.current().nextDouble() - 0.5) * entity.getBbWidth();
            double offsetY = ThreadLocalRandom.current().nextDouble() * entity.getBbHeight();
            double offsetZ = (ThreadLocalRandom.current().nextDouble() - 0.5) * entity.getBbWidth();

            serverLevel.sendParticles(ParticleTypes.ELECTRIC_SPARK,
                    entity.getX() + offsetX,
                    entity.getY() + offsetY,
                    entity.getZ() + offsetZ,
                    1, 0, 0.05, 0,0);
        }
    }
    private static void spawnDrowningParticles(LivingEntity entity) {
        if (!(entity.level() instanceof ServerLevel serverLevel)) return;
        for (int i = 0; i < 3; i++) {
            double offsetX = (ThreadLocalRandom.current().nextDouble() - 0.5) * entity.getBbWidth();
            double offsetY = ThreadLocalRandom.current().nextDouble() * entity.getBbHeight();
            double offsetZ = (ThreadLocalRandom.current().nextDouble() - 0.5) * entity.getBbWidth();

            serverLevel.sendParticles(ParticleTypes.BUBBLE_POP,
                    entity.getX() + offsetX,
                    entity.getY() + offsetY,
                    entity.getZ() + offsetZ,
                    1, 0, 0.05, 0,0);
            serverLevel.sendParticles(ParticleTypes.BUBBLE,
                    entity.getX() + offsetX,
                    entity.getY() + offsetY,
                    entity.getZ() + offsetZ,
                    1, 0, 0.05, 0,0);
        }
    }

    public static Map<LivingEntity, LivingEntity> getLightningParticleSourceTargets() {
        return lightningParticleSourceTargets;
    }




    public static void zapAndBounce(Level world, LivingEntity source, LivingEntity target, int bounceCount, List<LivingEntity> alreadyZapped, double bounceRadius){
        zapAndBounce(world, source, target, bounceCount, alreadyZapped, bounceRadius, 1);
    }
    public static void zapAndBounce(Level world, LivingEntity source, LivingEntity target, int bounceCount, List<LivingEntity> alreadyZapped, double bounceRadius, float damage) {
        // Instead of sending a network packet, spawn the lightning particles immediately.
        ClientLightningParticleHandler.lightningParticleSourceTargets.put(source,target);

        if (!target.level().isClientSide()) { // Process damage and bouncing on the server.
            target.hurt(target.damageSources().generic(), damage);
            target.hurtDuration = 0;
            target.hurtMarked = false;
            target.hurtTime = 0;
            target.invulnerableTime = 0;

            if (bounceCount > 0) {
                AABB searchBox = target.getBoundingBox().inflate(bounceRadius);
                List<LivingEntity> nearbyEnemies = world.getEntitiesOfClass(LivingEntity.class, searchBox,
                        e -> e != target && e.isAlive() && !(e instanceof Player) && !alreadyZapped.contains(e)
                                && (e instanceof Monster || e.getClass() == target.getClass()));

                if (!nearbyEnemies.isEmpty()) {
                    int randomIndex = ThreadLocalRandom.current().nextInt(nearbyEnemies.size());
                    LivingEntity nextTarget = nearbyEnemies.get(randomIndex);
                    alreadyZapped.add(nextTarget);

                    // Continue the chain, using target as the new source.
                    zapAndBounce(world, target, nextTarget, bounceCount - 1, alreadyZapped, bounceRadius);
                }
            }
        }
    }
    public static boolean isSmokerable(Level level, ItemStack stack) {
        if (stack.isEmpty()) return false;

        RecipeManager manager = level.getRecipeManager();
        return manager.getRecipesFor(RecipeType.SMOKING, new SimpleContainer(stack), level)
                .stream()
                .anyMatch(r -> r.getIngredients().get(0).test(stack));
    }
    public static ItemStack getSmoke(Level level, Container container) {
        SmokingRecipe recipe = level.getRecipeManager()
                .getRecipeFor(RecipeType.SMOKING, container, level)
                .orElse(null);

        if (recipe != null) {
            return recipe.getResultItem(RegistryAccess.EMPTY);
        }
        return ItemStack.EMPTY;
    }
}
