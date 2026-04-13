package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EggingHandler {

    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();
        if (!entity.level().isClientSide && entity.tickCount % 20 == 0) {
            if (entity.getAttribute(ModAttributes.EGGING.get()) != null) {
                double eggingLvl = entity.getAttribute(ModAttributes.EGGING.get()).getValue();
                if (eggingLvl > 0) {
                    if (entity.getRandom().nextFloat() <= 0.01 * eggingLvl) {
                        ItemEntity egg = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), Items.EGG.getDefaultInstance());
                        entity.level().addFreshEntity(egg);
                    }
                }
            }
        }
    }
}
