package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class BlockInteractHandler {

    @SubscribeEvent
    public static void onBlockInteract(PlayerInteractEvent.RightClickBlock event) {

        Level level = event.getLevel();
        if (level.isClientSide) return;

        Player player = event.getEntity();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);
        ItemStack stack = event.getItemStack();

        if (!(state.getBlock() instanceof ComposterBlock)) return;

        float baseChance = ComposterBlock.COMPOSTABLES.getOrDefault(stack.getItem(), 0f);
        if (baseChance <= 0) return;

        double bonus = player.getAttributeValue(ModAttributes.NUTRITIOUS_CROPS.get()) * 0.05;
        if (bonus <= 0)return;
        float finalChance = (float) Math.min(1.0, baseChance + bonus);

        int levelValue = state.getValue(ComposterBlock.LEVEL);
        if (levelValue >= 7) return;
        BlockState newState = state;
        if (level.random.nextFloat() < finalChance) {

            newState = state.setValue(ComposterBlock.LEVEL, levelValue + 1);
            level.setBlock(pos, newState, 3);


        }

        level.playSound(
                null,
                pos,
                net.minecraft.sounds.SoundEvents.COMPOSTER_FILL_SUCCESS,
                net.minecraft.sounds.SoundSource.BLOCKS,
                1.0F,
                1.0F
        );

        level.levelEvent(1500, pos, state != newState ? 1 : 0);
        if (!player.isCreative()) {
            stack.shrink(1);
        }
        event.setCanceled(true);
    }


}
