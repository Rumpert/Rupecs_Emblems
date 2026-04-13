package net.hubert.rupecs_emblems.event;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.client.Keybinds;
import net.hubert.rupecs_emblems.entity.ModEntities;
import net.hubert.rupecs_emblems.entity.custom.TameableBee;
import net.hubert.rupecs_emblems.entity.custom.TameablePufferfish;
import net.hubert.rupecs_emblems.entity.layer.ModModelLayers;
import net.hubert.rupecs_emblems.entity.model.MoonAsteroidModel;
import net.hubert.rupecs_emblems.particle.ModParticles;
import net.hubert.rupecs_emblems.particle.custom.GreenHeartParticle;
import net.hubert.rupecs_emblems.particle.custom.SmallHeartParticle;
import net.hubert.rupecs_emblems.particle.custom.WithererParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Rupecs_Emblems.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.TAMEABLE_PUFFERFISH.get(), TameablePufferfish.createAttributes().build());
        event.put(ModEntities.TAMEABLE_BEE.get(), TameableBee.createAttributes().build());
    }

    @SubscribeEvent
    public static void registerParticleProvider(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ModParticles.SMALL_HEART_PARTICLES.get(), SmallHeartParticle.Provider::new);
        event.registerSpriteSet(ModParticles.GREEN_HEART_PARTICLES.get(), GreenHeartParticle.Provider::new);
        event.registerSpriteSet(ModParticles.WITHERER_PARTICLES.get(), WithererParticle.Provider::new);
    }

}
