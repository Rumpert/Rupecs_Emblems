package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.providers.EmblemEffectProvider;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class HealingHandler {

    @SubscribeEvent
    public static void onLivingTickNature(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide && entity.tickCount % 60 == 0) {
            if (entity.getAttribute(ModAttributes.NATURE_HEALING.get()) != null) {
                double healingBonus = entity.getAttribute(ModAttributes.NATURE_HEALING.get()).getValue();
                if (healingBonus > 0) {
                    BlockPos belowPos = entity.blockPosition().below();
                    if (entity.level().getBlockState(belowPos).is(ModTags.Blocks.COUNTED_AS_A_PLANT_BLOCK)
                            || entity.level().getBlockState(belowPos).is(BlockTags.LEAVES)) {
                        entity.heal((float) healingBonus);
                    }
                }
            }
        }
        if (!entity.level().isClientSide && entity.tickCount % 20 == 0) {
            if (entity.getAttribute(ModAttributes.PHOTOSYNTHESIS.get()) != null) {
                double healingBonus = entity.getAttribute(ModAttributes.PHOTOSYNTHESIS.get()).getValue();
                if (healingBonus > 0) {
                    BlockPos pos = entity.blockPosition().above(2);
                    long timeOfDay = entity.level().getDayTime() % 24000;
                    if (((entity.level().canSeeSky(pos) && ((timeOfDay <= 13000 || timeOfDay >= 23000)||entity.getAttributeValue(ModAttributes.LUNARSYNTHESIS.get())>0))
                            || EmblemEffectProvider.IS_FORCED_DAY )
                            && entity.getMaxHealth() / 2 > entity.getHealth()) {
                        entity.heal((float) healingBonus);

                    }
                }
            }

        }
    }
}
