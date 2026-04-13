package net.hubert.rupecs_emblems.attribute.attributeHandlers.custom;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.command.gamerule.ModGameRules;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.MoonAsteroidEntity;
import net.hubert.rupecs_emblems.network.PacketHandler;
import net.hubert.rupecs_emblems.network.packet.MoonWrathPacket;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, value = Dist.CLIENT)
public class MoonWrathHandlerClient {
    public static final KeyMapping MOON_WRATH_KEY = Keybinds.INSTANCE.secondaryEmblemAbility;
    @SubscribeEvent
    public static void onLivingTick(LivingEvent.LivingTickEvent event) {
        Minecraft mc = Minecraft.getInstance();
        Player player = mc.player;
        if (player == null || player != event.getEntity()) return;

        var moonWrathAttr = player.getAttribute(ModAttributes.MOON_WRATH.get());
        if (moonWrathAttr == null || moonWrathAttr.getValue() <= 0) return;

        UUID playerUUID = player.getUUID();
        var cooldownEntry = CooldownManager.getCooldownEntries(playerUUID).get("MoonWrath");

        boolean hasCooldown = cooldownEntry != null && cooldownEntry.getTicks() > 0;

        if (MOON_WRATH_KEY.isDown() && !hasCooldown) {
            PacketHandler.sendToServer(new MoonWrathPacket());

            CooldownManager.addCooldown(player.getUUID(), "MoonWrath", 200,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/moon_wrath.png"));
        }

    }

}
