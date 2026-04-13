package net.hubert.rupecs_emblems.network;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.network.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

public class PacketHandler {
    public static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Rupecs_Emblems.MOD_ID, "main"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();
        INSTANCE = net;

        net.messageBuilder(PoopPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PoopPacket::new)
                .encoder(PoopPacket::toBytes)
                .consumerMainThread(PoopPacket::handle)
                .add();

        net.messageBuilder(ReturnPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ReturnPacket::new)
                .encoder(ReturnPacket::toBytes)
                .consumerMainThread(ReturnPacket::handle)
                .add();

        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(CooldownSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CooldownSyncPacket::new)
                .encoder(CooldownSyncPacket::toBytes)
                .consumerMainThread(CooldownSyncPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(RinkSyncPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(RinkSyncPacket::new)
                .encoder(RinkSyncPacket::toBytes)
                .consumerMainThread(RinkSyncPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(LightningParticlePacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(LightningParticlePacket::new)
                .encoder(LightningParticlePacket::toBytes)
                .consumerMainThread(LightningParticlePacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(GamblingPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(GamblingPacket::new)
                .encoder(GamblingPacket::toBytes)
                .consumerMainThread(GamblingPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(VanishPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(VanishPacket::new)
                .encoder(VanishPacket::toBytes)
                .consumerMainThread(VanishPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(VanishTickPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(VanishTickPacket::new)
                .encoder(VanishTickPacket::toBytes)
                .consumerMainThread(VanishTickPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(ThunderclapPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ThunderclapPacket::new)
                .encoder(ThunderclapPacket::toBytes)
                .consumerMainThread(ThunderclapPacket::handle)
                .add();

        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(TimeControlPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TimeControlPacket::new)
                .encoder(TimeControlPacket::toBytes)
                .consumerMainThread(TimeControlPacket::handle)
                .add();

        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(MirroringPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(MirroringPacket::new)
                .encoder(MirroringPacket::toBytes)
                .consumerMainThread(MirroringPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(SoulConnectingPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SoulConnectingPacket::new)
                .encoder(SoulConnectingPacket::toBytes)
                .consumerMainThread(SoulConnectingPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(MoonWrathPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(MoonWrathPacket::new)
                .encoder(MoonWrathPacket::toBytes)
                .consumerMainThread(MoonWrathPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(IciclePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(IciclePacket::new)
                .encoder(IciclePacket::toBytes)
                .consumerMainThread(IciclePacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(DandelionFallPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(DandelionFallPacket::new)
                .encoder(DandelionFallPacket::toBytes)
                .consumerMainThread(DandelionFallPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(BeeSummonPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BeeSummonPacket::new)
                .encoder(BeeSummonPacket::toBytes)
                .consumerMainThread(BeeSummonPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(PuffSummonPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PuffSummonPacket::new)
                .encoder(PuffSummonPacket::toBytes)
                .consumerMainThread(PuffSummonPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(FloristPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(FloristPacket::new)
                .encoder(FloristPacket::toBytes)
                .consumerMainThread(FloristPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(RandomPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RandomPacket::new)
                .encoder(RandomPacket::toBytes)
                .consumerMainThread(RandomPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(BonemealTogglePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(BonemealTogglePacket::new)
                .encoder(BonemealTogglePacket::toBytes)
                .consumerMainThread(BonemealTogglePacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(HealPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(HealPacket::new)
                .encoder(HealPacket::toBytes)
                .consumerMainThread(HealPacket::handle)
                .add();
        // Register CooldownSyncPacket (server → client)
        net.messageBuilder(RinkPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(RinkPacket::new)
                .encoder(RinkPacket::toBytes)
                .consumerMainThread(RinkPacket::handle)
                .add();
        net.messageBuilder(PulsePacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(PulsePacket::new)
                .encoder(PulsePacket::toBytes)
                .consumerMainThread(PulsePacket::handle)
                .add();


    }

    public static <MSG> void sendToServer(MSG msg){
        INSTANCE.sendToServer(msg);
    }

    public static <MSG> void sendToClient(MSG msg, ServerPlayer player){
        INSTANCE.send(PacketDistributor.PLAYER.with(()->player), msg);
    }
    public static <MSG> void sendToTrackingEntitiesAndSelf(MSG msg, Entity entity) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), msg);
    }
    public static <MSG> void sendToTrackingEntities(MSG msg, Entity entity) {
        INSTANCE.send(PacketDistributor.TRACKING_ENTITY.with(() -> entity), msg);
    }

}
