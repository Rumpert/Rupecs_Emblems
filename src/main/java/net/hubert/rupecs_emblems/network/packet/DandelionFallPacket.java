package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class DandelionFallPacket {

    public DandelionFallPacket() {

    }
    public DandelionFallPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null || !(player.getAttribute(ModAttributes.DANDELION_FALL.get()) != null && player.getAttribute(ModAttributes.DANDELION_FALL.get()).getValue() > 0)) return;
            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, (int) player.getAttribute(ModAttributes.DANDELION_FALL.get()).getValue() * 20, 1));
            CooldownManager.addCooldown(player.getUUID(), "DandelionFall", 60,
                    new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/dandelion_fall.png"));






        });
        return true;
    }


}
