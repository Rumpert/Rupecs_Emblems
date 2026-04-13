package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.providers.EmblemEffectProvider;
import net.hubert.rupecs_emblems.util.AttributeHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class MotherEarthBlessingHandler {
    // Slower configuration
    private static int CHECK_RADIUS = 10;          // Smaller radius
    private static int VERTICAL_CHECK = 10;        // Only 1 block above/below
    private static final int BASE_TICK_RATE = 20;        // Start with just 1 extra tick
    private static final int MOTHER_EARTH_BOOST = 10;        // Start with just 1 extra tick
    private static boolean BONEMEAL_TOGGLED = false;

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {

        // Check every 0.2 seconds (4 ticks)
        int CHECK_INTERVAL = 4;
        if (event.phase != TickEvent.Phase.END || event.side.isClient()) return;

        Player player = event.player;
        if (player.level().isClientSide || player.isSpectator()) return;

        // Check attribute
        if (player.getAttribute(ModAttributes.MOTHER_EARTH_BLESSING.get()) == null ||
                player.getAttributeValue(ModAttributes.MOTHER_EARTH_BLESSING.get()) <= 0) {
            return;
        }

        // Slower checking interval
        if (player.level().getGameTime() % CHECK_INTERVAL != 0) return;

        // More gradual scaling - cubic root of attribute value
        double growthFactor = Math.sqrt(player.getAttributeValue(ModAttributes.MOTHER_EARTH_BLESSING.get())*MOTHER_EARTH_BOOST);
        int tickRateMultiplier = BASE_TICK_RATE + (int)growthFactor;

        acceleratePlantGrowth(player, tickRateMultiplier);
    }

    private static void acceleratePlantGrowth(Player player, int tickRateMultiplier) {
        ServerLevel level = (ServerLevel) player.level();
        BlockPos centerPos = player.blockPosition();

        // Get plants in smaller area
        List<BlockPos> plantPositions = findGrowablePlants(level, centerPos);

        // Only process a fraction of plants each time
        int plantsToProcess = Math.max(1, plantPositions.size() * 2);

        for (int i = 0; i < plantsToProcess; i++) {
            if (plantPositions.isEmpty()) break;

            BlockPos pos = plantPositions.remove(level.random.nextInt(plantPositions.size()));
            BlockState state = level.getBlockState(pos);

            // Apply ticks with decreasing probability based on multiplier
            for (int t = 0; t < tickRateMultiplier; t++) {
                state.randomTick(level, pos, level.random);
            }

            // Rare visual feedback
            if (tickRateMultiplier > 3 && level.random.nextInt(20) == 0) {
                level.levelEvent(2005, pos, 0);
            }
        }
    }

    private static List<BlockPos> findGrowablePlants(ServerLevel level, BlockPos center) {
        List<BlockPos> positions = new ArrayList<>();

        for (int x = -CHECK_RADIUS; x <= CHECK_RADIUS; x++) {
            for (int y = -VERTICAL_CHECK; y <= VERTICAL_CHECK; y++) {
                for (int z = -CHECK_RADIUS; z <= CHECK_RADIUS; z++) {
                    BlockPos pos = center.offset(x, y, z);

                    if (!level.isLoaded(pos)) continue;

                    BlockState state = level.getBlockState(pos);

                    if (state.isRandomlyTicking() && isGrowablePlant(state)) {
                        positions.add(pos);
                    }
                }
            }
        }
        return positions;
    }

    private static boolean isGrowablePlant(BlockState state) {
        Block block = state.getBlock();
        return isPlant(block) ||
                state.is(BlockTags.SAPLINGS) ||
                state.is(BlockTags.CROPS);
    }

    private static boolean isPlant(Block block) {
        return
                block instanceof StemBlock ||
                        block instanceof CocoaBlock ||
                        block instanceof CropBlock ||
                        block instanceof GrowingPlantBlock ||
                        block instanceof SugarCaneBlock ||
                        block instanceof CactusBlock ||
                        block instanceof BambooSaplingBlock ||
                        block instanceof BambooStalkBlock ||
                        block instanceof NetherWartBlock;
    }
    @SubscribeEvent
    public static void onBlockInteraction(PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide) return;

        Level level = event.getLevel();
        BlockPos pos = event.getHitVec().getBlockPos();

        if (level.getBlockState(pos).getBlock() instanceof ComposterBlock composterBlock) {
            if (event.getEntity().getAttributeValue(ModAttributes.MOTHER_EARTH_BLESSING.get()) > 0) {
                ItemStack stack = event.getItemStack();

                // Only if the item is normally compostable
                if (ComposterBlock.COMPOSTABLES.containsKey(stack.getItem())) {
                    int currentLevel = level.getBlockState(pos).getValue(ComposterBlock.LEVEL);

                    if (currentLevel < 7) { // Not full yet
                        // Always succeed, skip chance
                        level.setBlock(pos, level.getBlockState(pos).setValue(ComposterBlock.LEVEL, currentLevel + 1), 3);

                        // Shrink item unless creative
                        if (!event.getEntity().isCreative()) {
                            stack.shrink(1);
                        }

                        // Play particles + sound like vanilla
                        level.levelEvent(1500, pos, 1);

                        // Cancel vanilla handling so it doesn’t double-process
                        event.setCanceled(true);
                        event.setCancellationResult(InteractionResult.SUCCESS);
                    }
                }
            }
        }
    }
    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event){
        if (event.getEntity().level().isClientSide)return;
        if (!(event.getEntity() instanceof Player player && player.getAttributeValue(ModAttributes.MOTHER_EARTH_BLESSING.get()) > 0))return;
        for (LivingEntity livingEntity : getNearbyLivingEntities(player, CHECK_RADIUS)) {
            if (livingEntity.isBaby() && livingEntity instanceof AgeableMob ageableMob) {
                ageableMob.setAge(Math.min((int) (ageableMob.getAge() + 100 * player.getAttributeValue(ModAttributes.MOTHER_EARTH_BLESSING.get())), 0));
                ((ServerLevel)event.getEntity().level()).sendParticles(
                        ParticleTypes.HAPPY_VILLAGER,
                        livingEntity.getX(), livingEntity.getY() + 0.5, livingEntity.getZ(),
                        1, 0.1, 0.1, 0.1, 0.1
                );
                System.out.println(ageableMob.getAge());
            }
        }
    }

    public static List<LivingEntity> getNearbyLivingEntities(Player player, double range) {
        AABB area = new AABB(
                player.getX() - range, player.getY() - 2, player.getZ() - range,
                player.getX() + range, player.getY() + 3, player.getZ() + range
        );

        return player.level().getEntitiesOfClass(LivingEntity.class, area, LivingEntity::isAlive);
    }

    @SubscribeEvent
    public static void handleBonemealPassive(LivingEvent.LivingTickEvent event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity() instanceof Player player)) return;

        double blessing = player.getAttributeValue(ModAttributes.MOTHER_EARTH_BLESSING.get());
        if (blessing <= 0) return;

        // Run every 200 ticks (~10 seconds)
        if (player.tickCount % 200 != 0 || !BONEMEAL_TOGGLED) return;
        if (player.isCrouching()) return;

        Level level = player.level();
        BlockPos below = player.blockPosition().below();

        // Only affect blocks that can accept bonemeal
        if (level.getBlockState(below).getBlock() instanceof BonemealableBlock bonemealable) {
            if (bonemealable.isValidBonemealTarget(level, below, level.getBlockState(below), level.isClientSide)) {
                bonemealable.performBonemeal(
                        (ServerLevel) level,
                        level.getRandom(),
                        below,
                        level.getBlockState(below)
                );

                // Play particles and sound like vanilla
                level.levelEvent(2005, below, 0);
            }
        }
    }
    @SubscribeEvent
    public static void handleGrassPassive(LivingEvent.LivingTickEvent event) {
        if (event.getEntity().level().isClientSide) return;
        if (!(event.getEntity() instanceof Player player)) return;

        double blessing = player.getAttributeValue(ModAttributes.MOTHER_EARTH_BLESSING.get());
        if (blessing <= 0) return;

        if (player.isCrouching()) return;

        Level level = player.level();
        BlockPos below = player.blockPosition().below();

        // Only affect blocks that can accept bonemeal
        if (level.getBlockState(below).is(Blocks.DIRT)) {
            level.setBlock(below, Blocks.GRASS_BLOCK.defaultBlockState(), 3);
        }
    }

    public static void setBonemealToggled(boolean bonemealToggled) {
        BONEMEAL_TOGGLED = bonemealToggled;
    }

    public static boolean isBonemealToggled() {
        return BONEMEAL_TOGGLED;
    }
}