package net.hubert.rupecs_emblems.event;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.event.custom.ItemiumSpawnEvent;
import net.hubert.rupecs_emblems.item.ModItems;
import net.hubert.rupecs_emblems.item.custom.ItemiumItem;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityStruckByLightningEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.TradeWithVillagerEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.event.level.NoteBlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.rumpertt.rupecs_elytras.event.custom.ElytraBehaviorEvent;

import javax.swing.text.html.HTML;
import java.awt.print.Paper;
import java.util.stream.StreamSupport;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ItemiumSpawnEvents {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        float chance = (float) (1+event.player.getAttributeValue(ModAttributes.LUCKY.get()));
        if (event.phase == TickEvent.Phase.END && !event.player.level().isClientSide()) {
            var level = (ServerLevel) event.player.level();
            BlockPos pos = event.player.blockPosition();
            Player player = event.player;
            long timeOfDay = level.getDayTime() % 24000;
            // Every 10 seconds
            if (level.getBlockState(pos).is(Blocks.BUBBLE_COLUMN)) {
                if (level.random.nextFloat() < 0.005f * chance) { // 0.5% chance every 1t
                    MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                            (ItemiumItem) ModItems.BULBULIUM.get(), new Vec3(
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5),
                            player, event.player.level(),
                                    new ItemEntity(
                                            level,
                                            pos.getX() + 0.5,
                                            pos.getY() + 0.5,
                                            pos.getZ() + 0.5,
                                            ModItems.BULBULIUM.get().getDefaultInstance()
                                    )
                            ));
                }
            }
            if (player.isOnFire()) {
                if (level.random.nextFloat() < 0.001f * chance) { // 0.1% chance every 1t
                    MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                            (ItemiumItem) ModItems.BURNING_CLOTH.get(), new Vec3(
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5),
                            player,
                            level,new ItemEntity(
                            level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            ModItems.BURNING_CLOTH.get().getDefaultInstance()
                    )));

                }
            }
            if (level.getBiome(pos).is(Biomes.DESERT) && player.isInWall() && (timeOfDay <= 13000 || timeOfDay >= 23000)) {
                if (level.random.nextFloat() < 0.003f * chance) { // 0.3% chance every 1t
                    MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                            (ItemiumItem) ModItems.SUNLIGHT_SHARD.get(), new Vec3(
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5),
                            player,
                            level,new ItemEntity(
                            level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            ModItems.SUNLIGHT_SHARD.get().getDefaultInstance()
                    )));
                }
            }
        }
    }
    @SubscribeEvent
    public static void onBlockBreak(BlockEvent.BreakEvent event){
        if (!(event.getPlayer() != null && !event.getPlayer().level().isClientSide() && !event.getPlayer().isCreative())) return;
        float chance = (float) (1+event.getPlayer().getAttributeValue(ModAttributes.LUCKY.get()));
        Player player = event.getPlayer();
        BlockPos pos = event.getPos();
        ServerLevel level = (ServerLevel) player.level();
        if (isShieldedFossilDropableFrom(event.getState())){
            if (player.isCreative())return;
            if (level.random.nextFloat() < getChanceForShieldedFossil(event.getState())*chance){
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.SHIELDED_FOSSIL.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.SHIELDED_FOSSIL.get().getDefaultInstance()
                )));
            }
        }if (event.getState().is(BlockTags.LOGS)){
            if (player.isCreative())return;
            if (level.random.nextFloat() < 0.02*chance){
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.BONSAI.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.BONSAI.get().getDefaultInstance()
                )));
            }
        }
        if (event.getState().is(Tags.Blocks.ORES_QUARTZ)){
            if (player.isCreative())return;
            if (level.random.nextFloat() < 0.02*chance){
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.THE_EARTH.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.THE_EARTH.get().getDefaultInstance()
                )));
            }
        }
    }

    private static boolean isShieldedFossilDropableFrom(BlockState state) {
        return state.is(BlockTags.DIRT) || state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(BlockTags.BASE_STONE_NETHER) || state.is(Tags.Blocks.STONE) || state.is(Tags.Blocks.ORES);
    }

    private static float getChanceForShieldedFossil(BlockState state) {
        if (state.is(BlockTags.BASE_STONE_OVERWORLD) || state.is(BlockTags.BASE_STONE_NETHER) || state.is(Tags.Blocks.STONE)){
            return 0.01f;
        } else if (state.is(Tags.Blocks.ORES)){
            if (state.is(BlockTags.DIAMOND_ORES)){
                return 0.02f;
            } else return 0.014f;
        }
        return 0.006f;
    }

    @SubscribeEvent
    public static void onFinishUseItem(LivingEntityUseItemEvent.Finish event){
        if (!(event.getEntity() != null && !event.getEntity().level().isClientSide()) || !(event.getEntity() instanceof Player player)) return;
        float chance = (float) (1+player.getAttributeValue(ModAttributes.LUCKY.get()));
        BlockPos pos = player.blockPosition();
        ServerLevel level = (ServerLevel) player.level();
        FoodProperties foodProperties = event.getItem().getFoodProperties(player);
        if (player.level().isThundering() || player.level().getBlockState(player.blockPosition().below()).is(Blocks.CACTUS)){
            if (foodProperties != null && foodProperties.isMeat()) {
                if (level.random.nextFloat() < 0.12*chance) {
                    MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                            (ItemiumItem) ModItems.REFERENCIUM.get(), new Vec3(
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5),
                            player,
                            level,new ItemEntity(
                            level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            ModItems.REFERENCIUM.get().getDefaultInstance()
                    )));
                }
            }
        }
        if (foodProperties != null &&  event.getItem().is(Items.BREAD) && player.isPassenger() && player.getVehicle() instanceof Boat){
            if (level.random.nextFloat() < 0.06*chance) {
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.BREAD_PIECE.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.BREAD_PIECE.get().getDefaultInstance()
                )));
            }
        }
        if (foodProperties != null && player.blockPosition().getY() > 128){
            if (level.random.nextFloat() < 0.1*chance) {
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.MOON_FRAGMENT.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.MOON_FRAGMENT.get().getDefaultInstance()
                )));
            }
        }

    }
    @SubscribeEvent
    public static void onUseItem(LivingEntityUseItemEvent.Start event) {
        if (!(event.getEntity() != null && !event.getEntity().level().isClientSide()) || !(event.getEntity() instanceof Player player)) return;
        float chance = (float) (1+player.getAttributeValue(ModAttributes.LUCKY.get()));
        BlockPos pos = player.blockPosition();
        ServerLevel level = (ServerLevel) player.level();
        if (event.getItem().is(Items.GOAT_HORN)){
            if (level.random.nextFloat() < 0.3*chance) {
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.HALF_NOTE.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.HALF_NOTE.get().getDefaultInstance()
                )));
            }
        }
    }

    @SubscribeEvent
    public static void onBoneMeal(BonemealEvent event) {
        // Skip if client-side
        if (event.getLevel().isClientSide()) {
            return;
        }

        BlockPos pos = event.getPos();
        ServerLevel level = (ServerLevel) event.getLevel();
        BlockState state = level.getBlockState(pos);

        // Check if the block can actually be bonemealed (this is what makes it consume the bonemeal)
        if (!state.is(BlockTags.BEE_GROWABLES) && !(state.getBlock() instanceof BonemealableBlock boneMealable
                && boneMealable.isValidBonemealTarget(level, pos, state, level.isClientSide))) {
            return;
        }

        float chance = (float) (1+event.getEntity().getAttributeValue(ModAttributes.LUCKY.get()));

        // 5% chance to drop a Verdant Core
        if (level.random.nextFloat() < 0.05f*chance) {
            MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                    (ItemiumItem) ModItems.VERDANT_CORE.get(), new Vec3(
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5),
                    event.getEntity(),
                    level,new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    ModItems.VERDANT_CORE.get().getDefaultInstance()
            )));
        }
    }
    @SubscribeEvent
    public static void onPlayerInteract(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        ItemStack heldItem = event.getItemStack();
        float chance = (float) (1+player.getAttributeValue(ModAttributes.LUCKY.get()));
        if (!level.isClientSide && heldItem.getItem() instanceof AxeItem) {
            BlockState currentState = level.getBlockState(pos);
            if (AxeItem.getAxeStrippingState(currentState) == null) return;
            Block stripped = AxeItem.getAxeStrippingState(currentState).getBlock();

            BlockState strippedState = stripped.defaultBlockState()
                    .setValue(RotatedPillarBlock.AXIS, currentState.getValue(RotatedPillarBlock.AXIS));

            level.scheduleTick(pos, currentState.getBlock(), 1);

            if (level.random.nextFloat() < 0.03*chance) {
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.BONSAI.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,
                        new ItemEntity(
                                level,
                                pos.getX() + 0.5,
                                pos.getY() + 0.5,
                                pos.getZ() + 0.5,
                                ModItems.BONSAI.get().getDefaultInstance()
                        )));
            }
        }
    }
    @SubscribeEvent
    public static void onEntityHurt(LivingHurtEvent event) {
        Level level = event.getEntity().level();
        BlockPos pos = event.getEntity().getOnPos();
        if (event.getEntity() instanceof Player player) {
            float chance = (float) (1+player.getAttributeValue(ModAttributes.LUCKY.get()));
            boolean hasIronArmor = StreamSupport.stream(player.getArmorSlots().spliterator(), false)
                    .anyMatch(itemStack ->
                            itemStack.is(Items.IRON_HELMET) ||
                                    itemStack.is(Items.IRON_CHESTPLATE) ||
                                    itemStack.is(Items.IRON_LEGGINGS) ||
                                    itemStack.is(Items.IRON_BOOTS)
                    );

            if (hasIronArmor) {
                if (level.random.nextFloat() < 0.06*chance) {
                    MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                            (ItemiumItem) ModItems.IRON_PLATING.get(), new Vec3(
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5),
                            player,
                            level,new ItemEntity(
                            level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            ModItems.IRON_PLATING.get().getDefaultInstance()
                    )));
                }
            }
            if (event.getSource() == player.damageSources().drown()){
                if (level.random.nextFloat() < 0.06*chance) {
                    MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                            (ItemiumItem) ModItems.GLASS_LUNG.get(), new Vec3(
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5),
                            player,
                            level,new ItemEntity(
                            level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            ModItems.GLASS_LUNG.get().getDefaultInstance()
                    )));
                }
            }
            if (event.getSource() == player.damageSources().freeze()){
                if (level.random.nextFloat() < 0.06*chance) {
                    MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                            (ItemiumItem) ModItems.FRACTURED_ICICLE.get(), new Vec3(
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5),
                            player,
                            level,new ItemEntity(
                            level,
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            ModItems.FRACTURED_ICICLE.get().getDefaultInstance()
                    )));
                }
            }
        }
        if (event.getEntity() instanceof Squid){
            if (event.getSource().getEntity() instanceof Player player){
                float chance = (float) (1+player.getAttributeValue(ModAttributes.LUCKY.get()));
                if (player.getMainHandItem().is(Items.PAPER)) {
                    if (level.random.nextFloat() < 0.1*chance) {
                        MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                                (ItemiumItem) ModItems.JACK_OF_SPADES.get(), new Vec3(
                                pos.getX() + 0.5,
                                pos.getY() + 0.5,
                                pos.getZ() + 0.5),
                                player,
                                level,new ItemEntity(
                                level,
                                pos.getX() + 0.5,
                                pos.getY() + 0.5,
                                pos.getZ() + 0.5,
                                ModItems.JACK_OF_SPADES.get().getDefaultInstance()
                        )));
                        player.getMainHandItem().shrink(1);

                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onPlaceBlock(BlockEvent.EntityPlaceEvent event){
        Block block = event.getPlacedBlock().getBlock();
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        if (!(event.getEntity() instanceof  Player player))return;

        float chance = (float) (1+player.getAttributeValue(ModAttributes.LUCKY.get()));
        if (block.defaultBlockState().is(Blocks.TORCH) && (event.getPlacedAgainst().is(Blocks.POLISHED_DIORITE) || event.getPlacedAgainst().is(Blocks.BEDROCK))){
            if (level.random.nextFloat() < 0.005*chance) {
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.THE_TORCH.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.THE_TORCH.get().getDefaultInstance()
                )));

            }
        }
        if (block.defaultBlockState().is(Blocks.LIGHTNING_ROD) && event.getPlacedAgainst().is(Blocks.COPPER_BLOCK) && event.getEntity().getBlockStateOn().is(Blocks.COAL_BLOCK)){
            if (level.random.nextFloat() < 0.01*chance) {
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.BATTERY.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level, new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.BATTERY.get().getDefaultInstance()
                )));
            }

        }
    }
    @SubscribeEvent
    public static void onLivingDie(LivingDeathEvent event){
        Level level = event.getEntity().level();
        BlockPos pos = event.getEntity().blockPosition();
        if (!(event.getSource().getEntity() instanceof Player player))return;

        float chance = (float) (1+player.getAttributeValue(ModAttributes.LUCKY.get()));
        if ((event.getEntity() instanceof Animal animal && animal.isInWater()) ||(event.getEntity() instanceof WaterAnimal waterAnimal && waterAnimal.isInWater())){
            if (level.random.nextFloat() < 0.18*chance) {
                MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                        (ItemiumItem) ModItems.BENT_FIN.get(), new Vec3(
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5),
                        player,
                        level,new ItemEntity(
                        level,
                        pos.getX() + 0.5,
                        pos.getY() + 0.5,
                        pos.getZ() + 0.5,
                        ModItems.BENT_FIN.get().getDefaultInstance()
                )));
            }
        }
    }
    @SubscribeEvent
    public static void onVillagerTrade(TradeWithVillagerEvent event){
        Level level = event.getEntity().level();
        BlockPos pos = event.getAbstractVillager().getOnPos();
        float chance = (float) (1+event.getEntity().getAttributeValue(ModAttributes.LUCKY.get()));
        if (level.random.nextFloat() < 0.2*chance) {
            MinecraftForge.EVENT_BUS.post(new ItemiumSpawnEvent(
                    (ItemiumItem) ModItems.COIN_NECKLACE.get(), new Vec3(
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5),
                    event.getEntity(),
                    level,new ItemEntity(
                    level,
                    pos.getX() + 0.5,
                    pos.getY() + 0.5,
                    pos.getZ() + 0.5,
                    ModItems.COIN_NECKLACE.get().getDefaultInstance()
            )));
        }
    }

    @SubscribeEvent
    public static void onSpawnItemiumEvent(ItemiumSpawnEvent event) {
        ItemEntity entity = event.getItemiumEntity().copy();
        entity.setGlowingTag(true);


        event.getLevel().addFreshEntity(entity);
        if (event.getLevel().random.nextFloat() < 0.15){
            ItemEntity enthereal_mass = new ItemEntity(event.getLevel(), event.getPosition().x, event.getPosition().y, event.getPosition().z, ModItems.ENTHEREAL_MASS.get().getDefaultInstance());
            enthereal_mass.setGlowingTag(true);
            enthereal_mass.setNoPickUpDelay();
            event.getLevel().addFreshEntity(enthereal_mass);
        }
        event.getLevel().playSound(
                null, // all nearby players hear it
                event.getPosition().x, event.getPosition().y, event.getPosition().z,
                SoundEvents.AMETHYST_CLUSTER_BREAK,
                SoundSource.MASTER,
                1f, 1f
        );

    }



}
