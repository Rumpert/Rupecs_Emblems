package net.hubert.rupecs_emblems.particle;

import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, Rupecs_Emblems.MOD_ID);

    public static final RegistryObject<SimpleParticleType> SMALL_HEART_PARTICLES =
            PARTICLE_TYPES.register("small_heart", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> WITHERER_PARTICLES =
            PARTICLE_TYPES.register("witherer", () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> GREEN_HEART_PARTICLES =
            PARTICLE_TYPES.register("green_heart", () -> new SimpleParticleType(true));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }

}
