package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class FoodHandler {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.getAttribute(ModAttributes.UNDERFED.get()) != null) {
            if (event.phase == TickEvent.Phase.END && event.side.isServer()) {
                Player player = event.player;
                FoodData foodData = player.getFoodData();

                // Only affect survival/adventure mode
                if (!player.isCreative() && !player.isSpectator()) {
                    // Get current exhaustion level
                    float exhaustion = foodData.getExhaustionLevel();

                    // Apply multiplier
                    foodData.setExhaustion((float) (exhaustion * 1 + (0.02 * player.getAttribute(ModAttributes.UNDERFED.get()).getValue())));
                }
            }
        }
    }
}
