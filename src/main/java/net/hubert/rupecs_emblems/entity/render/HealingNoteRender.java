package net.hubert.rupecs_emblems.entity.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.hubert.rupecs_emblems.Rupecs_Emblems;
import net.hubert.rupecs_emblems.entity.custom.CometShardProjectile;
import net.hubert.rupecs_emblems.entity.custom.HealingNoteProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class HealingNoteRender extends EntityRenderer<HealingNoteProjectile> {

    private static final ResourceLocation BASE_HEALING_NOTE_TEXTURE = new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/entity/healing_note_base.png");
    private static final ResourceLocation HIGH_HEALING_NOTE_TEXTURE = new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/entity/healing_note_high.png");
    private static final ResourceLocation SUPER_HEALING_NOTE_TEXTURE = new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/entity/healing_note_super.png");
    private static final ResourceLocation HYPER_HEALING_NOTE_TEXTURE = new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/entity/healing_note_hyper.png");
    private static final ResourceLocation DIVINE_HEALING_NOTE_TEXTURE = new ResourceLocation(Rupecs_Emblems.MOD_ID, "textures/entity/healing_note_divine.png");

    public HealingNoteRender(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(HealingNoteProjectile entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        // Rotate to always face the camera (billboard effect)
        poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        poseStack.mulPose(Axis.YP.rotationDegrees(180.0F)); // Fix texture flipping

        // Get the vertex consumer
        int tier = (int) (entity.getHealAmount() / 3);
        ResourceLocation texture = switch (tier) {
            case 0 -> BASE_HEALING_NOTE_TEXTURE;
            case 1 -> HIGH_HEALING_NOTE_TEXTURE;
            case 2 -> SUPER_HEALING_NOTE_TEXTURE;
            case 3 -> HYPER_HEALING_NOTE_TEXTURE;
            default -> DIVINE_HEALING_NOTE_TEXTURE;
        };

        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutout(texture));

        // Define size (similar to snowball)
        float size = 0.3F;
        renderQuad(poseStack, vertexConsumer, packedLight, size);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(HealingNoteProjectile entity) {
        int tier = (int) (entity.getHealAmount() / 3);
        return switch (tier) {
            case 0 -> BASE_HEALING_NOTE_TEXTURE;
            case 1 -> HIGH_HEALING_NOTE_TEXTURE;
            case 2 -> SUPER_HEALING_NOTE_TEXTURE;
            case 3 -> HYPER_HEALING_NOTE_TEXTURE;
            default -> DIVINE_HEALING_NOTE_TEXTURE;
        };
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
