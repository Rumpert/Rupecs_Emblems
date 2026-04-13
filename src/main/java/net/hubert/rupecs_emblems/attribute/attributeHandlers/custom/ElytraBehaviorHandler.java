package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.util.AttributeHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.Event;

import java.lang.reflect.Method;

import static net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.MobControlHandler.getNearbyMobs;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID)
public class ElytraBehaviorHandler {

    private static boolean isRupecsElytrasLoaded = false;
    private static Class<?> elytraEventClass = null;

    static {
        isRupecsElytrasLoaded = ModList.get().isLoaded("rupecs_elytras");
        if (isRupecsElytrasLoaded) {
            try {
                elytraEventClass = Class.forName("net.rumpertt.rupecs_elytras.event.custom.ElytraBehaviorEvent");
            } catch (ClassNotFoundException e) {
                Rupecs_Emblems.LOGGER.warn("Rupecs Elytras is loaded but ElytraBehaviorEvent class not found!");
                isRupecsElytrasLoaded = false;
            }
        }
    }

    @SubscribeEvent
    public static void onElytraBehavior(Event event) {
        if (!isRupecsElytrasLoaded || elytraEventClass == null) return;

        // Check if this is the correct event type using reflection
        if (!elytraEventClass.isInstance(event)) return;

        try {
            // Use reflection to access event methods
            Method getPlayerMethod = elytraEventClass.getMethod("getPlayer");
            Method getActionMethod = elytraEventClass.getMethod("getAction");

            Player player = (Player) getPlayerMethod.invoke(event);
            Enum<?> action = (Enum<?>) getActionMethod.invoke(event);

            if (player.level().isClientSide) return;

            // Get action enum values using reflection
            Class<?> actionClass = Class.forName("net.rumpertt.rupecs_elytras.event.custom.ElytraBehaviorEvent$Action");
            Enum<?> startFlying = Enum.valueOf((Class<Enum>) actionClass, "START_FLYING");
            Enum<?> onFlight = Enum.valueOf((Class<Enum>) actionClass, "ON_FLIGHT");

            if (action == startFlying) {
                handleStartFlying(player);
            } else if (action == onFlight) {
                handleOnFlight(player);
            }

        } catch (Exception e) {
            Rupecs_Emblems.LOGGER.error("Failed to handle ElytraBehaviorEvent", e);
        }
    }

    private static void handleStartFlying(Player player) {
        if (AttributeHelper.getValueIfNotNull(player, ModAttributes.PHOENIX.get()) > 0) {
            getNearbyMobs(player, 6).forEach(mob -> {
                if (!mob.isAlliedTo(player) && (mob.isAggressive() || (mob.getTarget() != null && mob.getTarget().is(player)))) {
                    mob.setSecondsOnFire((int) AttributeHelper.getValueIfNotNull(player, ModAttributes.PHOENIX.get()) * 3);
                }
            });
        }
    }

    private static void handleOnFlight(Player player) {
        if (AttributeHelper.getValueIfNotNull(player, ModAttributes.PHOENIX_FLIGHT.get()) > 0) {
            getNearbyMobs(player, 3).forEach(mob -> {
                if (!mob.isAlliedTo(player) && (mob.isAggressive() || (mob.getTarget() != null && mob.getTarget().is(player)))) {
                    mob.setSecondsOnFire((int) AttributeHelper.getValueIfNotNull(player, ModAttributes.PHOENIX_FLIGHT.get()));
                }
            });
        }
    }
}