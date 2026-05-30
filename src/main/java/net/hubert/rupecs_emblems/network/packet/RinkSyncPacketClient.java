package net.hubert.rupecs_emblems.network.packet;

import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RinkSyncPacketClient {

    public static void handle(int entityId, boolean rinkActive) {

        Level level = Minecraft.getInstance().level;
        if (level == null) return;

        Entity entity = level.getEntity(entityId);

        if (entity instanceof Player targetPlayer) {

            if (rinkActive) {
                targetPlayer.getTags().add("rink_active");
            } else {
                targetPlayer.getTags().remove("rink_active");
            }

            System.out.println(
                "Client: Set rink_active to " +
                rinkActive +
                " for player " +
                targetPlayer.getName().getString()
            );
        }
    }
}