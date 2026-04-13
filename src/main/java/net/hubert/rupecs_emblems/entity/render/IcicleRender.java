package net.hubert.rupecs_emblems.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.entity.custom.IcicleProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class IcicleRender extends EntityRenderer<IcicleProjectile> {

    private static final ResourceLocation ICICLE_TEXTURE = new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/entity/icicle.png");

    public IcicleRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(IcicleProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // Rotate to always face the camera (billboard effect)
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F)); // Fix texture flipping

        // Get the vertex consumer
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(ICICLE_TEXTURE));

        // Define size (similar to snowball)
        float size = 0.3F;
        renderQuad(poseStack, vertexConsumer, packedLight, size);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(IcicleProjectile entity) {
        return ICICLE_TEXTURE;
    }

    private void renderQuad(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, float size) {
        // Define UV coordinates for a full texture render
        float minU = 0.0F, maxU = 1.0F;
        float minV = 0.0F, maxV = 1.0F;

        // Define vertex positions (flat square)
        float halfSize = size / 2.0F;
        poseStack.pushPose();
        PoseStack.Pose entry = poseStack.last();

        vertexConsumer.vertex(entry.pose(), -halfSize, -halfSize, 0.0F).color(255, 255, 255, 255)
                .uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(entry.normal(), 0, 1, 0).endVertex();
        vertexConsumer.vertex(entry.pose(), halfSize, -halfSize, 0.0F).color(255, 255, 255, 255)
                .uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(entry.normal(), 0, 1, 0).endVertex();
        vertexConsumer.vertex(entry.pose(), halfSize, halfSize, 0.0F).color(255, 255, 255, 255)
                .uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(entry.normal(), 0, 1, 0).endVertex();
        vertexConsumer.vertex(entry.pose(), -halfSize, halfSize, 0.0F).color(255, 255, 255, 255)
                .uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(packedLight).normal(entry.normal(), 0, 1, 0).endVertex();

        poseStack.popPose();
    }
}
