package com.min01.cataclysmicillagers.entity.model;

import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.remnant.EntityDesertMonarch;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.AbstractIllager.IllagerArmPose;

public class ModelDesertMonarch extends EntityModel<EntityDesertMonarch> implements ArmedModel, HeadedModel
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CataclysmicIllagers.MODID, "desert_monarch"), "main");
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public ModelDesertMonarch(ModelPart root)
	{
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(56, 44).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.5F))
		.texOffs(72, 57).addBox(4.0F, -16.0F, -0.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(72, 57).mirror().addBox(-8.0F, -16.0F, -0.5F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(72, 69).addBox(4.0F, -16.0F, 3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(72, 69).mirror().addBox(-8.0F, -16.0F, 3.5F, 4.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(90, 8).addBox(-2.0F, -2.0F, -6.0F, 4.0F, 4.0F, 15.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0872F, 1.9962F, 0.48F, 0.0F, 0.0F));

		head.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(65, 32).addBox(-2.5F, -2.0F, -8.0F, 5.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -4.0F, 0.0873F, 0.0F, 0.0F));

		head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(84, 27).mirror().addBox(-2.0F, -2.0F, -4.5F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.8F, -10.7F, -0.5F, 0.0873F, -0.0873F, 0.0873F));

		head.addOrReplaceChild("head_r4", CubeListBuilder.create().texOffs(84, 27).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8F, -10.7F, -0.5F, 0.0873F, 0.0873F, -0.0873F));

		head.addOrReplaceChild("head_r5", CubeListBuilder.create().texOffs(90, 56).mirror().addBox(-2.5F, -2.0F, -8.5F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, -2.0F, 3.5F, 0.0886F, 0.1739F, 0.0154F));

		head.addOrReplaceChild("head_r6", CubeListBuilder.create().texOffs(90, 56).addBox(-0.5F, -2.0F, -8.5F, 3.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, -2.0F, 3.5F, 0.0886F, -0.1739F, -0.0154F));

		head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(85, 96).addBox(-4.0F, 10.0F, -3.0F, 8.0F, 11.0F, 6.0F, new CubeDeformation(0.5F))
		.texOffs(64, 78).addBox(-5.0F, 0.0F, -4.1F, 10.0F, 10.0F, 8.0F, new CubeDeformation(0.25F))
		.texOffs(90, 69).addBox(0.0F, 0.0F, 3.9F, 0.0F, 10.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(75, 101).addBox(-2.0F, -2.0F, -0.5F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, -4.6F, 0.0F, 0.0F, 0.7854F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(40, 62).mirror().addBox(-1.5F, 3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(54, 72).mirror().addBox(3.5F, 1.0F, 0.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(5.0F, 2.0F, 0.0F, 0.0F, -0.0873F, -0.0436F));

		left_arm.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(0, 95).mirror().addBox(-5.25F, -3.0F, -3.5F, 9.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(8, 113).mirror().addBox(-5.25F, -3.0F, -4.5F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.1F)).mirror(false)
		.texOffs(0, 114).mirror().addBox(3.75F, -6.0F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 108).mirror().addBox(-5.25F, -9.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(0, 78).mirror().addBox(-2.25F, -5.0F, -3.5F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.25F, -1.0F, 0.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition wand = left_arm.addOrReplaceChild("wand", CubeListBuilder.create().texOffs(116, 117).mirror().addBox(-0.4308F, 0.0F, -25.0F, 2.0F, 2.0F, 60.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(181, 117).addBox(0.6F, -7.0F, -45.0F, 0.0F, 16.0F, 37.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.7154F, 7.3496F, 0.4469F, 0.0873F, 0.0F, 0.0F));

		wand.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(116, 180).mirror().addBox(2.0F, -20.0F, -45.0F, 0.0F, 16.0F, 37.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-11.4308F, -1.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 62).addBox(-3.5F, 3.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(54, 72).addBox(-6.5F, 1.0F, 0.0F, 3.0F, 7.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 2.0F, 0.0F, -0.0435F, 0.0038F, 0.1308F));

		right_arm.addOrReplaceChild("mini_golden_greatsword", CubeListBuilder.create().texOffs(95, 106).addBox(-0.5F, -3.0F, -23.3F, 1.0F, 6.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(100, 113).addBox(-1.0F, -4.0F, -5.3F, 2.0F, 8.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(84, 113).addBox(-1.0F, -1.0F, -2.3F, 2.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 8.0F, 0.0F, 0.0873F, 0.0F, 0.0F));

		right_arm.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(0, 95).addBox(-3.75F, -3.0F, -3.5F, 9.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 114).addBox(-7.75F, -6.0F, 0.0F, 4.0F, 9.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(8, 113).addBox(-3.75F, -3.0F, -4.5F, 9.0F, 6.0F, 9.0F, new CubeDeformation(0.1F))
		.texOffs(0, 108).addBox(-3.75F, -9.0F, 0.0F, 9.0F, 6.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 78).addBox(-3.75F, -5.0F, -3.5F, 6.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.25F, -1.0F, 0.0F, 0.0F, 0.0F, -0.1309F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void setupAnim(EntityDesertMonarch entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
	{
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);
		if(this.riding) 
		{
			this.right_arm.xRot = (-(float)Math.PI / 5F);
			this.right_arm.yRot = 0.0F;
			this.right_arm.zRot = 0.0F;
			this.left_arm.xRot = (-(float)Math.PI / 5F);
			this.left_arm.yRot = 0.0F;
			this.left_arm.zRot = 0.0F;
			this.right_leg.xRot = -1.4137167F;
			this.right_leg.yRot = ((float)Math.PI / 10F);
			this.right_leg.zRot = 0.07853982F;
			this.left_leg.xRot = -1.4137167F;
			this.left_leg.yRot = (-(float)Math.PI / 10F);
			this.left_leg.zRot = -0.07853982F;
		}
		else 
		{
			this.right_arm.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F;
			this.right_arm.yRot = 0.0F;
			this.right_arm.zRot = 0.0F;
			this.left_arm.xRot = Mth.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F;
			this.left_arm.yRot = 0.0F;
			this.left_arm.zRot = 0.0F;
			this.right_leg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount * 0.5F;
			this.right_leg.yRot = 0.0F;
			this.right_leg.zRot = 0.0F;
			this.left_leg.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount * 0.5F;
			this.left_leg.yRot = 0.0F;
			this.left_leg.zRot = 0.0F;
		}
		
		if(entity.getArmPose() == IllagerArmPose.SPELLCASTING)
		{
	        this.right_arm.z = 0.0F;
	        this.right_arm.x = -5.0F;
	        this.left_arm.z = 0.0F;
	        this.left_arm.x = 5.0F;
	        this.right_arm.xRot = Mth.cos(ageInTicks * 0.6662F) * 0.25F;
	        this.left_arm.xRot = Mth.cos(ageInTicks * 0.6662F) * 0.25F;
	        this.right_arm.zRot = 2.3561945F;
	        this.left_arm.zRot = -2.3561945F;
	        this.right_arm.yRot = 0.0F;
	        this.left_arm.yRot = 0.0F;
		}
		
		if(entity.getArmPose() == AbstractIllager.IllagerArmPose.ATTACKING)
		{
			AnimationUtils.swingWeaponDown(this.right_arm, this.left_arm, entity, this.attackTime, ageInTicks);
		}
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) 
	{
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_arm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		left_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		right_leg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
	
	@Override
	public ModelPart getHead()
	{
		return this.head;
	}

	@Override
	public void translateToHand(HumanoidArm p_102108_, PoseStack p_102109_)
	{
		this.getArm(p_102108_).translateAndRotate(p_102109_);
	}
	
	private ModelPart getArm(HumanoidArm p_102923_)
	{
		return p_102923_ == HumanoidArm.LEFT ? this.left_arm : this.right_arm;
	}
}