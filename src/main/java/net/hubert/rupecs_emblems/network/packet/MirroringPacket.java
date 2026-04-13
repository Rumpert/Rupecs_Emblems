package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class MirroringPacket {

    public MirroringPacket() {

    }
    public MirroringPacket(FriendlyByteBuf buf) {

    }
    public void toBytes(FriendlyByteBuf buf) {

    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null || !(player.getAttribute(ModAttributes.MIRRORING.get()) != null && player.getAttribute(ModAttributes.MIRRORING.get()).getValue() > 0)) return;


            double reach = player.getAttribute(ModAttributes.MIRRORING.get()).getValue() * 50; // Adjust reach distance if needed
            Vec3 eyePos = player.getEyePosition();
            Vec3 lookVec = player.getLookAngle();
            Vec3 reachVec = eyePos.add(lookVec.scale(reach));

            AABB aabb = player.getBoundingBox().expandTowards(lookVec.scale(reach)).inflate(1.5D, 1.5D, 1.5D);
            List<Mob> mobs = player.level().getEntitiesOfClass(Mob.class, aabb, mob -> mob != null && mob.isAlive());

            Mob closestMob = null;
            double closestDistance = Double.MAX_VALUE;

            for (Mob mob : mobs) {
                AABB mobBox = mob.getBoundingBox();
                Optional<Vec3> optionalHit = mobBox.clip(eyePos, reachVec);
                if (optionalHit.isPresent()) {
                    double distance = eyePos.distanceTo(optionalHit.get());
                    if (distance < closestDistance) {
                        closestDistance = distance;
                        closestMob = mob;
                    }
                }
            }

            if (closestMob != null) {
                Vec3 playerPos = player.position();
                Vec3 mobPos = closestMob.position();

                player.teleportTo(mobPos.x, mobPos.y, mobPos.z);
                player.resetFallDistance();
                closestMob.teleportTo(playerPos.x, playerPos.y, playerPos.z);
                closestMob.resetFallDistance();


                CooldownManager.addCooldown(player.getUUID(), "Mirroring", 200,
                        new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/mirroring.png"));

                for (int i = 0; i<7; i++){
                    player.serverLevel().sendParticles(ParticleTypes.END_ROD, player.getX(), player.getY()+1, player.getZ(),3,0.5,0.5, 0.5,0.04);
                    player.serverLevel().sendParticles(ParticleTypes.END_ROD, closestMob.getX(), closestMob.getY()+1, closestMob.getZ(),3,0.5,0.5, 0.5,0.04);
                }


            }



        });
        return true;
    }


}
