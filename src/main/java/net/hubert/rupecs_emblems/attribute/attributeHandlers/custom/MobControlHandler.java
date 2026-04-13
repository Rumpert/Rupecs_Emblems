package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.particle.ModParticles;
import net.hubert.rupecs_emblems.providers.EmblemEffectProvider;
import net.hubert.rupecs_emblems.util.AttributeHelper;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.levelgen.structure.*;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingChangeTargetEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.*;

import static net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.MobHitHandler.zapAndBounce;

@Mod.EventBusSubscriber
public class MobControlHandler {

    @SubscribeEvent
    public static void onLivingUpdate(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.hasEffect(MobEffects.FIRE_RESISTANCE) && entity.isOnFire()){
            entity.extinguishFire();
        } else if (entity.isOnFire() && EmblemEffectProvider.IS_FORCED_RAIN && entity.getRemainingFireTicks() > 1) {
            System.out.println(entity.getRemainingFireTicks());
            if (entity.level().random.nextFloat() > 0.5f) {
                entity.setRemainingFireTicks(entity.getRemainingFireTicks() - 1);
            }
        }
    }
    @SubscribeEvent
    public static void onLivingUpdateAnimal(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Animal animal)) return;
        if (animal instanceof TamableAnimal tamable && tamable.isTame()) return;

        Player player = animal.level().getNearestPlayer(animal, 10.0);

        if (player != null && hasPositiveAttribute(player, ModAttributes.ANIMAL_TEMPTATION.get())) {
            animal.getNavigation().moveTo(player, getAttributeValue(player, ModAttributes.ANIMAL_TEMPTATION.get()));
            animal.getLookControl().setLookAt(player, 10.0F, animal.getMaxHeadXRot());
        }
    }


    // Revised Inferno logic affecting area around player
    @SubscribeEvent
    public static void onLivingUpdateInferno(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Player player)) return;
        if (!hasPositiveAttribute(player, ModAttributes.INFERNO.get())) return;

        handleInfernoDamage(player);
        spawnInfernoParticles(player);
    }

    @SubscribeEvent
    public static void onLivingUpdateYuki(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Player player)) return;
        if (!hasPositiveAttribute(player, ModAttributes.YUKI.get())) return;

        handleYukiFreeze(player);
        spawnYukiParticles(player);
    }

    @SubscribeEvent
    public static void onPiglinTarget(LivingChangeTargetEvent event) {
        if (!(event.getEntity() instanceof Piglin piglin)) return;
        if (!(event.getNewTarget() instanceof Player player)) return;

        if (player.getAttribute(ModAttributes.GOLDEN_FAVOR.get()) != null && player.getAttribute(ModAttributes.GOLDEN_FAVOR.get()).getValue() > 0) {
            // Prevent Piglin from targeting the player
            event.setCanceled(true);
        }
    }

    @SubscribeEvent
    public static void onPiglinTick(LivingEvent.LivingTickEvent event) {
        if (!(event.getEntity() instanceof Piglin piglin)) return;

        // If Piglin has a target that is a player...
        if (piglin.getTarget() instanceof Player player) {
            // ...and that player has the GOLDEN_FAVOR attribute
            if (player.getAttribute(ModAttributes.GOLDEN_FAVOR.get()) != null &&
                    player.getAttribute(ModAttributes.GOLDEN_FAVOR.get()).getValue() > 0) {

                // Instantly stop attacking the player
                piglin.setTarget(null);
                piglin.setLastHurtByMob(null);
                piglin.setLastHurtByPlayer(null);

                // Clear piglin's memory of aggression
                piglin.getBrain().eraseMemory(MemoryModuleType.ANGRY_AT);
                piglin.getBrain().eraseMemory(MemoryModuleType.ATTACK_TARGET);
            }
        }
    }




    @SubscribeEvent
    public static void onServerTick(TickEvent.ServerTickEvent event) {
        if (event.phase != TickEvent.Phase.START) return;

        event.getServer().getPlayerList().getPlayers().forEach(player -> {
            if (hasGoldenFavor(player)) {
                locateAndDisplayBastion(player);

            }
        });

    }

    private static boolean hasGoldenFavor(ServerPlayer player) {
        AttributeInstance attr = player.getAttribute(ModAttributes.GOLDEN_FAVOR.get());

        return attr != null && attr.getValue() > 0;
    }

    private static void locateAndDisplayBastion(ServerPlayer player) {
        ServerLevel level = player.serverLevel();

        // Get the registry (only needed if you want to verify structure presence)
        var structureRegistry = level.registryAccess().registryOrThrow(Registries.STRUCTURE);
        var bastionStructure = structureRegistry.get(BuiltinStructures.BASTION_REMNANT);

        if (bastionStructure == null) return;

        // Find first nearest bastion
        BlockPos firstBastion = level.findNearestMapStructure(
                ModTags.Structures.BASTION,
                player.blockPosition(),
                10000,
                false
        );

        if (firstBastion == null) return;

        // Move away from the first bastion to search for the second nearest
        Vec3 directionAway = Vec3.atCenterOf(player.blockPosition()).subtract(Vec3.atCenterOf(firstBastion)).normalize();
        BlockPos searchFrom = firstBastion.offset(
                (int) (directionAway.x * 500),  // shift 500 blocks away
                0,
                (int) (directionAway.z * 500)
        );

        BlockPos secondBastion = level.findNearestMapStructure(
                ModTags.Structures.BASTION,
                searchFrom,
                10000,
                false
        );

        if (secondBastion != null && !secondBastion.equals(firstBastion)) {
            getNearbyPiglins(level, player).forEach(piglin ->
                    showDirectionParticles(piglin, secondBastion)
            );

        }
    }

    private static List<Piglin> getNearbyPiglins(ServerLevel level, ServerPlayer player) {

        return level.getEntitiesOfClass(
                Piglin.class,
                new AABB(player.blockPosition()).inflate(10),
                piglin -> piglin.isAlive()
        );
    }
    private static void showDirectionParticles(Piglin piglin, BlockPos bastionPos) {
        ServerLevel level = (ServerLevel) piglin.level();
        Vec3 origin = piglin.position().add(0, 1.2, 0); // Piglin eye height
        Vec3 target = Vec3.atCenterOf(bastionPos);
        Vec3 direction = target.subtract(origin).normalize();


        // Create particle trail
        if (piglin.tickCount % 10 != 0) return;
        for (int i = 0; i < 8; i++) {
            Vec3 particlePos = origin.add(direction.scale(i*0.25));
            level.sendParticles(
                    ParticleTypes.HAPPY_VILLAGER,
                    particlePos.x,
                    piglin.position().y,
                    particlePos.z,
                    1,
                    0, 0, 0,
                    0.00
            );
        }
    }




    private static void handleInfernoDamage(Player player) {
        if (player.tickCount % 20 != 0) return; // Only damage every second

        double range = 3.0 *(1+((player.getAttributeValue(ModAttributes.INFERNO.get())%1)*10));
        float damage = (float) Math.floor(getAttributeValue(player, ModAttributes.INFERNO.get()));

        getNearbyMobs(player, range).forEach(mob -> {
            if (!mob.isAlliedTo(player) && (mob.isAggressive() || (mob.getTarget() != null && mob.getTarget().is(player)))) {
                mob.hurt(player.damageSources().playerAttack(player), damage);
                mob.setDeltaMovement(0, 0, 0);
            }
        });
    }


    private static void spawnInfernoParticles(Player player) {
        if (player.tickCount % 5 != 0) return; // Spawn particles every 5 ticks

        double radius = 3.0 *(1+((player.getAttributeValue(ModAttributes.INFERNO.get())%1)*10));
        int points = 32;
        double yOffset = 0.5;

        for (int i = 0; i < points; i++) {
            if (player.getRandom().nextFloat() < 0.33f) {
                double angle = Math.PI * 2 * i / points;
                double x = player.getX() + Math.cos(angle) * radius;
                double z = player.getZ() + Math.sin(angle) * radius;

                player.level().addParticle(ParticleTypes.FLAME,
                        x, player.getY() + yOffset, z,
                        (Math.random() - 0.5) * 0.05,
                        0,
                        (Math.random() - 0.5) * 0.05
                );
            }
        }
    }


    private static void handleYukiFreeze(Player player) {

        double range = 3.0 * player.getAttributeValue(ModAttributes.YUKI.get());

        getNearbyMobs(player, range).forEach(mob -> {
            if (!mob.isAlliedTo(player) && (mob.isAggressive() || (mob.getTarget() != null && mob.getTarget().is(player)))) {
                mob.setTicksFrozen(mob.getTicksFrozen()+4);
                System.out.println(mob.getTicksFrozen());
            }
        });
    }


    private static void spawnYukiParticles(Player player) {
        if (player.tickCount % 5 != 0) return; // Spawn particles every 5 ticks

        double radius = 3.0 * player.getAttributeValue(ModAttributes.YUKI.get());
        int points = 32;
        double yOffset = 0.5;
        for (int l = 0;l<6;l++) {
            for (int i = 0; i < points; i++) {
                if (player.getRandom().nextFloat() < 0.33f) {
                    double angle = Math.PI * 2 * i / points;
                    double x = player.getX() + Math.cos(angle) * (radius/l);
                    double z = player.getZ() + Math.sin(angle) * (radius/l);

                    player.level().addParticle(ParticleTypes.SNOWFLAKE,
                            x, player.getY() + yOffset, z,
                            (Math.random() - 0.5) * 0.05,
                            0,
                            (Math.random() - 0.5) * 0.05
                    );
                }
            }
        }
    }

    @SubscribeEvent
    public static void onLivingUpdateBlizzard(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Player player)) return;
        if (!hasPositiveAttribute(player, ModAttributes.BLIZZARD.get())) return;

        handleBlizzardSlow(player);
        spawnBlizzardParticles(player, getAttributeValue(player, ModAttributes.BLIZZARD.get()));
    }

    private static void handleBlizzardSlow(Player player) {
        double range = getAttributeValue(player, ModAttributes.BLIZZARD.get());
        getNearbyLivingEntities(player, range).forEach(entity -> {
            if (!entity.isAlliedTo(player) && !(entity instanceof Player)) {
                entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 20, 1, false, false, false));
            }
        });
    }


    private static void spawnBlizzardParticles(Player player, double radius) {
        if (player.tickCount % 5 != 0) return; // Spawn particles every 5 ticks

        int points = 32;
        double yOffset = 0.25;

        for (int i = 0; i < points; i++) {
            if (player.getRandom().nextFloat() < 0.33f) {
                double angle = Math.PI * 2 * i / points;
                double x = player.getX() + Math.cos(angle) * radius;
                double z = player.getZ() + Math.sin(angle) * radius;

                player.level().addParticle(ParticleTypes.SNOWFLAKE,
                        x, player.getY() + yOffset, z,
                        0,
                        0.05,
                        0
                );
            }
        }
    }
    @SubscribeEvent
    public static void onLivingUpdateSteam(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Player player)) return;
        if (!hasPositiveAttribute(player, ModAttributes.STEAM.get())) return;

        handleSteamWeakness(player);
        spawnSteamParticles(player, getAttributeValue(player, ModAttributes.STEAM.get()));
    }

    private static void handleSteamWeakness(Player player) {
        double range = getAttributeValue(player, ModAttributes.STEAM.get());
        getNearbyLivingEntities(player, range).forEach(entity -> {
            if (!entity.isAlliedTo(player) && !(entity instanceof Player)) {
                if (!entity.hasEffect(MobEffects.WEAKNESS)) {
                    entity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 20, 0, false, false, false));
                }
            }
        });
    }
    @SubscribeEvent
    public static void onLivingUpdateWither(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Player player)) return;
        if (!hasPositiveAttribute(player, ModAttributes.WITHERER.get())) return;

        handleWitherWither(player);
        spawnWitherParticles(player, 4);
    }

    private static void handleWitherWither(Player player) {
        double range = 4;
        getNearbyMobs(player, range).forEach(mob -> {
            if (!mob.isAlliedTo(player) && (mob.isAggressive() || (mob.getTarget() != null && mob.getTarget().is(player)))) {
                if (!mob.hasEffect(MobEffects.WITHER)) {
                    mob.addEffect(new MobEffectInstance(MobEffects.WITHER, 20, (int) getAttributeValue(player, ModAttributes.WITHERER.get())));
                }
            }
        });
    }


    private static void spawnSteamParticles(Player player, double radius) {
        if (player.tickCount % 5 != 0) return; // Spawn particles every 5 ticks

        int points = 32;
        double yOffset = 0.25;

        for (int i = 0; i < points; i++) {
            if (player.getRandom().nextFloat() < 0.33f) {
                double angle = Math.PI * 2 * i / points;
                double x = player.getX() + Math.cos(angle) * radius;
                double z = player.getZ() + Math.sin(angle) * radius;

                player.level().addParticle(ParticleTypes.CLOUD,
                        x, player.getY() + yOffset, z,
                        0,
                        0.05,
                        0
                );
            }
        }
    }
    @SubscribeEvent
    public static void onLivingUpdateMist(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Player player)) return;
        if (!hasPositiveAttribute(player, ModAttributes.MIST.get())) return;

        handleMistDrown(player);
        spawnMistParticles(player, AttributeHelper.getValueIfNotNull(player, ModAttributes.MIST.get()));
    }

    private static void handleMistDrown(Player player) {
        double range = AttributeHelper.getValueIfNotNull(player, ModAttributes.MIST.get());
        getNearbyMobs(player, range).forEach(mob -> {
            if (!mob.isAlliedTo(player) && (mob.isAggressive() || (mob.getTarget() != null && mob.getTarget().is(player)))) {
                MobHitHandler.drowningEntities.put(mob, 30);
            }
        });
    }


    private static void spawnMistParticles(Player player, double radius) {
        if (player.tickCount % 5 != 0) return; // Spawn particles every 5 ticks

        int points = 32;
        double yOffset = 0.25;

        for (int i = 0; i < points; i++) {
            if (player.getRandom().nextFloat() < 0.33f) {
                double angle = Math.PI * 2 * i / points;
                double x = player.getX() + Math.cos(angle) * radius;
                double z = player.getZ() + Math.sin(angle) * radius;

                player.level().addParticle(ParticleTypes.FISHING,
                        x, player.getY() + yOffset, z,
                        0,
                        0.05,
                        0
                );
            }
        }
    }
    private static void spawnWitherParticles(Player player, double radius) {
        if (player.tickCount % 5 != 0) return; // Spawn particles every 5 ticks

        int points = 32;
        double yOffset = 0.25;

        for (int i = 0; i < points; i++) {
            if (player.getRandom().nextFloat() < 0.33f) {
                double angle = Math.PI * 2 * i / points;
                double x = player.getX() + Math.cos(angle) * radius;
                double z = player.getZ() + Math.sin(angle) * radius;

                player.level().addParticle(ModParticles.WITHERER_PARTICLES.get(),
                        x, player.getY() + yOffset, z,
                        0,
                        0.05,
                        0
                );
            }
        }
    }
    @SubscribeEvent
    public static void onLivingUpdateCoiled(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        if (!(entity instanceof Player player)) return;
        if (!hasPositiveAttribute(player, ModAttributes.COILED.get())) return;
        if (entity.tickCount % Math.round(Math.max(1, 20 / getAttributeValue(player, ModAttributes.COILED.get()))) == 0) {
            handleCoiledZap(player);
        }
        spawnCoiledParticles(player, 5);
    }

    private static void handleCoiledZap(Player player) {
        double range = 5;
        if (!getNearbyMobs(player, range).isEmpty()) {
            Mob entity = getNearbyMobs(player, range).get(player.level().random.nextInt(getNearbyMobs(player, range).size()));
            if (!entity.isAlliedTo(player) && (entity.isAggressive() || (entity.getTarget() != null && entity.getTarget().is(player)))) {
                zapAndBounce(player.level(), player, entity, 0, new ArrayList<>(), range);

            }

        }
    }


    private static void spawnCoiledParticles(Player player, double radius) {
        if (player.tickCount % 5 != 0) return; // Spawn particles every 5 ticks

        int points = 32;
        double yOffset = 0.25;

        for (int i = 0; i < points; i++) {
            if (player.getRandom().nextFloat() < 0.33f) {
                double angle = Math.PI * 2 * i / points;
                double x = player.getX() + Math.cos(angle) * radius;
                double z = player.getZ() + Math.sin(angle) * radius;

                player.level().addParticle(ParticleTypes.ELECTRIC_SPARK,
                        x, player.getY() + yOffset, z,
                        0,
                        -0.05,
                        0
                );
            }
        }
    }


    public static List<LivingEntity> getNearbyLivingEntities(Player player, double range) {
        AABB area = new AABB(
                player.getX() - range, player.getY() - 2, player.getZ() - range,
                player.getX() + range, player.getY() + 3, player.getZ() + range
        );

        return player.level().getEntitiesOfClass(LivingEntity.class, area,
                mob -> !mob.isAlliedTo(player) && mob.isAlive()
        );
    }
    public static List<Mob> getNearbyMobs(Entity player, double range) {
        AABB area = new AABB(
                player.getX() - range, player.getY() - 2, player.getZ() - range,
                player.getX() + range, player.getY() + 3, player.getZ() + range
        );
        return player.level().getEntitiesOfClass(Mob.class, area,
                LivingEntity::isAlive
        );
    }

    private static boolean hasPositiveAttribute(Player player, Attribute attribute) {
        return player.getAttribute(attribute) != null &&
                player.getAttribute(attribute).getValue() > 0;
    }

    private static double getAttributeValue(Player player, Attribute attribute) {
        return hasPositiveAttribute(player, attribute) ?
                player.getAttribute(attribute).getValue() : 0;
    }
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;

        if (!(player.getAttributeValue(ModAttributes.MAGNET.get()) > 0)) return;

        List<ItemEntity> items = player.level().getEntitiesOfClass(
                ItemEntity.class,
                player.getBoundingBox().inflate(1+player.getAttributeValue(ModAttributes.MAGNET.get()))
        );

        for (ItemEntity item : items) {
            Vec3 direction = player.position().subtract(item.position()).normalize();
            if (!player.isCrouching()) {
                item.setDeltaMovement(item.getDeltaMovement().add(direction.scale(0.1)));
            }
        }
    }
}