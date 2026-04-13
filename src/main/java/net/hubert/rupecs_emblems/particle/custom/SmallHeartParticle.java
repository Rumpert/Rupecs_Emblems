// File: SmallHeartParticle.java
package net.hubert.rupecs_emblems.particle.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class SmallHeartParticle extends TextureSheetParticle {

    protected SmallHeartParticle(ClientLevel world, double x, double y, double z,
                                 SpriteSet spriteSet, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.setSpriteFromAge(spriteSet);
        this.lifetime = 4; // 20 ticks = 1 second
        this.gravity = 0.0F; // No gravity pulling it up or down
        this.xd = motionX;
        this.yd = motionY + 0.04;
        this.zd = motionZ;
        this.quadSize = 0.2F; // Small size (vanilla heart is about 0.5F)
    }


    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public @Nullable Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel, double v, double v1, double v2,
                                                 double v3, double v4, double v5) {
            return new SmallHeartParticle(clientLevel, v, v1 ,v2, this.spriteSet, v3, v4, v5);
        }
    }
}
