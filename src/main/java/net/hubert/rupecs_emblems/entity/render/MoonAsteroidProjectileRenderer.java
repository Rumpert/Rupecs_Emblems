package net.hubert.rupecs_emblems.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.entity.custom.MoonAsteroidEntity;
import net.hubert.rupecs_emblems.entity.layer.ModModelLayers;
import net.hubert.rupecs_emblems.entity.model.MoonAsteroidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

import static net.minecraft.client.renderer.entity.LivingEntityRenderer.getOverlayCoords;

public class MoonAsteroidProjectileRenderer extends EntityRenderer<MoonAsteroidEntity> {
    private final MoonAsteroidModel<MoonAsteroidEntity> model;

    public MoonAsteroidProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new MoonAsteroidModel<>(context.bakeLayer(ModModelLayers.MOON_ASTEROID_LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(MoonAsteroidEntity entity) {
        return new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/entity/moon_asteroid.png");
    }

    @Override
    public void render(MoonAsteroidEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        poseStack.translate(0.0D, 0.0D, 0.0D); // center it if needed
        var vertexConsumer = buffer.getBuffer(model.renderType(getTextureLocation(entity)));
        model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY,1,1,1,1);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}
