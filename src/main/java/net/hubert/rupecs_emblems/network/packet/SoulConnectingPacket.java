package net.hubert.rupecs_emblems.network.packet;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.attribute.ModAttributes;
import net.hubert.rupecs_emblems.attribute.attributeHandlers.custom.SoulConnectionHandlerClient;
import net.hubert.rupecs_emblems.client.ClientSoulConnectionParticleHandler;
import net.hubert.rupecs_emblems.util.CooldownManager;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class SoulConnectingPacket {
    private boolean connect = true;

    public SoulConnectingPacket(boolean connect) {
        this.connect = connect;
    }
    public SoulConnectingPacket(FriendlyByteBuf buf) {
        this.connect = buf.readBoolean();
    }
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.connect);
    }
    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            ServerPlayer player = context.getSender();
            if (player == null || !(player.getAttribute(ModAttributes.SOUL_CONNECTOR.get()) != null && player.getAttribute(ModAttributes.SOUL_CONNECTOR.get()).getValue() > 0)) return;
            if (this.connect) {

                double reach = 50; // Adjust reach distance if needed
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
                    CooldownManager.addCooldown(player.getUUID(), "SoulConnection", 200,
                            new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/ability/soul_connection.png"));
                    ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.putIfAbsent(player, new ArrayList<>());
                    if (!ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.isEmpty()
                            && ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.containsKey(player)) {
                        if (ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.get(player).size() < player.getAttribute(ModAttributes.SOUL_CONNECTOR.get()).getValue()) {
                            List<LivingEntity> mobsToAdd = new ArrayList<>();
                            if (!ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.get(player).contains(closestMob)) {

                                mobsToAdd = ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.get(player);
                            }
                            mobsToAdd.add(closestMob);


                            ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.put(player, mobsToAdd);
                        } else {
                            List<LivingEntity> mobsToAdd = new ArrayList<>();
                            if (!ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.get(player).contains(closestMob)) {

                                mobsToAdd = ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.get(player);
                            }
                            mobsToAdd.remove(0);
                            mobsToAdd.add(closestMob);


                            ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.put(player, mobsToAdd);
                        }
                    }
                }
            } else {
                ClientSoulConnectionParticleHandler.soulConnectionEntitiesWithParticlesToSpawn.clear();
            }

        });
        return true;
    }


}
