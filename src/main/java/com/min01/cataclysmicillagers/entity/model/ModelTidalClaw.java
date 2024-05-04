package com.min01.cataclysmicillagers.entity.model;

import com.github.L_Ender.lionfishapi.client.model.tools.AdvancedEntityModel;
import com.github.L_Ender.lionfishapi.client.model.tools.AdvancedModelBox;
import com.github.L_Ender.lionfishapi.client.model.tools.BasicModelPart;
import com.google.common.collect.ImmutableList;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

public class ModelTidalClaw extends AdvancedEntityModel<EntityFlyingTidalClaw> 
{
	private final AdvancedModelBox root;

	private final AdvancedModelBox handguard;

	private final AdvancedModelBox jaw;

	private final AdvancedModelBox right_jaw;

	private final AdvancedModelBox left_jaw;

	private final AdvancedModelBox upper_fin;

	private final AdvancedModelBox lower_fin;

	public ModelTidalClaw()
	{
		this.texWidth = 64;
		this.texHeight = 64;
		this.root = new AdvancedModelBox(this);
		this.root.setRotationPoint(0.0F, 21.0F, 0.0F);
		this.root.setTextureOffset(0, 9).addBox(-3.0F, -3.0F, 0.0F, 6.0F, 6.0F, 3.0F, 0.01F, false);
		this.handguard = new AdvancedModelBox(this);
		this.handguard.setRotationPoint(-3.0F, 0.0F, 1.5F);
		this.root.addChild((BasicModelPart) this.handguard);
		setRotationAngle(this.handguard, 0.0F, -0.1745F, 0.0F);
		this.handguard.setTextureOffset(13, 13).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 4.0F, 6.0F, 0.0F, false);
		this.jaw = new AdvancedModelBox(this);
		this.jaw.setRotationPoint(0.0F, 3.0F, 0.0F);
		this.root.addChild((BasicModelPart) this.jaw);
		this.jaw.setTextureOffset(23, 0).addBox(-2.0F, -5.5F, -2.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);
		this.jaw.setTextureOffset(23, 0).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 0.0F, 2.0F, 0.0F, false);
		this.right_jaw = new AdvancedModelBox(this);
		this.right_jaw.setRotationPoint(-1.5F, -3.0F, 0.0F);
		this.jaw.addChild((BasicModelPart) this.right_jaw);
		setRotationAngle(this.right_jaw, 0.0F, 0.2618F, 0.0F);
		this.right_jaw.setTextureOffset(11, 24).addBox(-2.0F, -3.0F, -3.0F, 2.0F, 6.0F, 3.0F, 0.0F, false);
		this.right_jaw.setTextureOffset(30, 19).addBox(-2.0F, -2.0F, -4.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		this.right_jaw.setTextureOffset(24, 9).addBox(0.0F, -3.0F, -3.0F, 2.0F, 6.0F, 3.0F, 0.0F, false);
		this.right_jaw.setTextureOffset(0, 29).addBox(0.0F, -2.0F, -4.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		this.left_jaw = new AdvancedModelBox(this);
		this.left_jaw.setRotationPoint(1.95F, -3.0F, 0.4F);
		this.jaw.addChild((BasicModelPart) this.left_jaw);
		setRotationAngle(this.left_jaw, 0.0F, -0.3491F, 0.0F);
		this.left_jaw.setTextureOffset(22, 24).addBox(0.0F, -3.0F, -3.0F, 1.0F, 6.0F, 3.0F, 0.0F, false);
		this.left_jaw.setTextureOffset(31, 3).addBox(0.0F, -2.0F, -4.0F, 1.0F, 4.0F, 1.0F, 0.0F, false);
		this.left_jaw.setTextureOffset(0, 0).addBox(-2.0F, -2.0F, -4.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		this.left_jaw.setTextureOffset(0, 19).addBox(-2.0F, -3.0F, -3.0F, 2.0F, 6.0F, 3.0F, 0.0F, false);
		this.upper_fin = new AdvancedModelBox(this);
		this.upper_fin.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.root.addChild((BasicModelPart) this.upper_fin);
		setRotationAngle(this.upper_fin, 0.48F, -0.3054F, 0.0F);
		this.upper_fin.setTextureOffset(0, 0).addBox(-5.0F, 0.0F, 0.0F, 7.0F, 0.0F, 8.0F, 0.0F, false);
		this.lower_fin = new AdvancedModelBox(this);
		this.lower_fin.setRotationPoint(0.0F, 4.0F, 0.0F);
		this.root.addChild((BasicModelPart) this.lower_fin);
		setRotationAngle(this.lower_fin, -0.48F, -0.3054F, 0.0F);
		this.lower_fin.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, 0.0F, 7.0F, 0.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(EntityFlyingTidalClaw entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) 
	{
		this.root.rotateAngleZ = 89.55F;
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		this.root.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(AdvancedModelBox advancedModelBox, float x, float y, float z)
	{
		advancedModelBox.rotateAngleX = x;
		advancedModelBox.rotateAngleY = y;
		advancedModelBox.rotateAngleZ = z;
	}

	@Override
	public Iterable<AdvancedModelBox> getAllParts()
	{
		return ImmutableList.of(this.root, this.handguard, this.jaw, this.right_jaw, this.left_jaw, this.upper_fin, this.lower_fin);
	}
	
	@Override
	public Iterable<BasicModelPart> parts()
	{
		return ImmutableList.of(this.root);
	}
}
