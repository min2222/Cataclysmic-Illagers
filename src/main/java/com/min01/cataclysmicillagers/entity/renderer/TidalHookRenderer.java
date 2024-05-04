package com.min01.cataclysmicillagers.entity.renderer;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.cataclysm.client.model.entity.ModelTidal_Hook;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;
import com.min01.cataclysmicillagers.entity.leviathan.EntityTidalHook;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Matrix3f;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;

public class TidalHookRenderer extends EntityRenderer<EntityTidalHook>
{
	private final ModelTidal_Hook model = new ModelTidal_Hook();

	private static final ResourceLocation TEXTURE = new ResourceLocation(Cataclysm.MODID, "textures/entity/tidal_hook.png");

	private static final ResourceLocation CHAIN_TEXTURE = new ResourceLocation(Cataclysm.MODID, "textures/entity/tidal_hook_chain.png");

	private static final RenderType CHAIN_LAYER = RenderType.entitySmoothCutout(CHAIN_TEXTURE);

	public TidalHookRenderer(EntityRendererProvider.Context renderManagerIn)
	{
		super(renderManagerIn);
	}

	@Override
	public void render(EntityTidalHook entity, float yaw, float tickDelta, PoseStack matrices, MultiBufferSource provider, int light) 
	{
		matrices.pushPose();
		matrices.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(tickDelta, entity.yRotO, entity.getYRot()) - 90.0F));
		matrices.mulPose(Vector3f.ZP.rotationDegrees(Mth.lerp(tickDelta, entity.xRotO, entity.getXRot()) + 90.0F));
		VertexConsumer vertexConsumer = provider.getBuffer(this.model.renderType(getTextureLocation(entity)));
		this.model.renderToBuffer(matrices, vertexConsumer, light, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
		matrices.popPose();
		matrices.pushPose();
		Entity fromEntity = entity.getOwner();
		float x = (float) Mth.lerp(tickDelta, entity.xo, entity.getX());
		float y = (float) Mth.lerp(tickDelta, entity.yo, entity.getY());
		float z = (float) Mth.lerp(tickDelta, entity.zo, entity.getZ());
		if (fromEntity != null)
		{
			Vec3 distVec = getPositionOfPriorMob(fromEntity, tickDelta).subtract(x, y, z);
			Vec3 from = distVec;
			renderChainCube(from, tickDelta, entity.tickCount, matrices, provider, light);
		}
		matrices.popPose();
	}

	private Vec3 getPositionOfPriorMob(Entity mob, float partialTicks) 
	{
		double d4 = Mth.lerp(partialTicks, mob.xo, mob.getX());
		double d5 = Mth.lerp(partialTicks, mob.yo, mob.getY());
		double d6 = Mth.lerp(partialTicks, mob.zo, mob.getZ());
		float f3 = 0.0F;
		if (mob instanceof EntityFlyingTidalClaw) 
		{
			EntityFlyingTidalClaw claw = (EntityFlyingTidalClaw) mob;
			float f = claw.getAttackAnim(partialTicks);
			float f1 = Mth.sin(Mth.sqrt(f) * 3.1415927F);
			float f2 = Mth.lerp(partialTicks, claw.yBodyRotO, claw.yBodyRot) * 0.017453292F;
			int i = 0;
			double d0 = Mth.sin(f2);
			double d1 = Mth.cos(f2);
			double d2 = i * 0.35D;
			if ((this.entityRenderDispatcher.options == null))
			{
				double d7 = 960.0D / ((Integer) this.entityRenderDispatcher.options.fov().get()).intValue();
				Vec3 vec3 = this.entityRenderDispatcher.camera.getNearPlane().getPointOnPlane(i * 0.6F, -1.0F);
				vec3 = vec3.scale(d7);
				vec3 = vec3.yRot(f1 * 0.25F);
				vec3 = vec3.xRot(-f1 * 0.35F);
				d4 = Mth.lerp(partialTicks, claw.xo, claw.getX()) + vec3.x;
				d5 = Mth.lerp(partialTicks, claw.yo, claw.getY()) + vec3.y;
				d6 = Mth.lerp(partialTicks, claw.zo, claw.getZ()) + vec3.z;
				f3 = claw.getEyeHeight() - 0.25F;
			} 
			else 
			{
				d4 = Mth.lerp(partialTicks, claw.xo, claw.getX()) - d1 * d2 - d0 * 0.05D;
				d5 = claw.yo + claw.getEyeHeight() + (claw.getY() - claw.yo) * partialTicks - 0.45D;
				d6 = Mth.lerp(partialTicks, claw.zo, claw.getZ()) - d0 * d2 + d1 * 0.05D;
				f3 = 0.15F;
			}
		}
		return new Vec3(d4, d5 + f3, d6);
	}

	public static void renderChainCube(Vec3 from, float tickDelta, int age, PoseStack stack, MultiBufferSource provider, int light)
	{
		float lengthXY = Mth.sqrt((float) (from.x * from.x + from.z * from.z));
		float squaredLength = (float) (from.x * from.x + from.y * from.y + from.z * from.z);
		float length = Mth.sqrt(squaredLength);
		stack.pushPose();
		stack.mulPose(Vector3f.YP.rotation((float) -Math.atan2(from.z, from.x) - 1.5707964F));
		stack.mulPose(Vector3f.XP.rotation((float) -Math.atan2(lengthXY, from.y) - 1.5707964F));
		stack.mulPose(Vector3f.ZP.rotationDegrees(25.0F));
		stack.pushPose();
		stack.translate(0.015D, -0.2D, 0.0D);
		VertexConsumer vertexConsumer = provider.getBuffer(CHAIN_LAYER);
		float vertX1 = 0.0F;
		float vertY1 = 0.25F;
		float vertX2 = Mth.sin(6.2831855F) * 0.125F;
		float vertY2 = Mth.cos(6.2831855F) * 0.125F;
		float minU = 0.0F;
		float maxU = 0.1875F;
		float minV = 0.0F - (age + tickDelta) * 0.01F;
		float maxV = Mth.sqrt(squaredLength) / 8.0F - (age + tickDelta) * 0.01F;
		PoseStack.Pose entry = stack.last();
		Matrix4f matrix4f = entry.pose();
		Matrix3f matrix3f = entry.normal();
		vertexConsumer.vertex(matrix4f, vertX1, vertY1, 0.0F).color(0, 0, 0, 255).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX1, vertY1, length).color(255, 255, 255, 255).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, length).color(255, 255, 255, 255).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, 0.0F).color(0, 0, 0, 255).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		stack.popPose();
		stack.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
		stack.translate(-0.015D, -0.2D, 0.0D);
		entry = stack.last();
		matrix4f = entry.pose();
		matrix3f = entry.normal();
		vertexConsumer.vertex(matrix4f, vertX1, vertY1, 0.0F).color(0, 0, 0, 255).uv(minU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX1, vertY1, length).color(255, 255, 255, 255).uv(minU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, length).color(255, 255, 255, 255).uv(maxU, maxV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		vertexConsumer.vertex(matrix4f, vertX2, vertY2, 0.0F).color(0, 0, 0, 255).uv(maxU, minV).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(light).normal(matrix3f, 0.0F, -1.0F, 0.0F).endVertex();
		stack.popPose();
	}

	@Override
	public ResourceLocation getTextureLocation(EntityTidalHook entity) 
	{
		return TEXTURE;
	}
}
