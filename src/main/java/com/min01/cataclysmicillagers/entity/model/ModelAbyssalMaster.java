package com.min01.cataclysmicillagers.entity.model;

import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.leviathan.EntityAbyssalMaster;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

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

public class ModelAbyssalMaster extends EntityModel<EntityAbyssalMaster> implements ArmedModel, HeadedModel
{
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(CataclysmicIllagers.MODID, "abyssal_master"), "main");
	private final ModelPart nose;
	private final ModelPart hod;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart arms;
	private final ModelPart left_arm;
	private final ModelPart right_arm;
	private final ModelPart left_leg;
	private final ModelPart right_leg;

	public ModelAbyssalMaster(ModelPart root)
	{
		this.nose = root.getChild("nose");
		this.hod = root.getChild("hod");
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.arms = root.getChild("arms");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
		this.left_leg = root.getChild("left_leg");
		this.right_leg = root.getChild("right_leg");
	}

	public static LayerDefinition createBodyLayer() 
	{
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		partdefinition.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

		PartDefinition hod = partdefinition.addOrReplaceChild("hod", CubeListBuilder.create().texOffs(60, 22).addBox(-4.5281F, -2.7064F, -1.5719F, 10.0F, 4.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(56, -4).addBox(4.4719F, -8.9064F, 4.4281F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(32, 65).addBox(-4.5281F, -5.7064F, -1.5719F, 10.0F, 6.0F, 8.0F, new CubeDeformation(-0.1F)), PartPose.offset(-0.4719F, -0.0936F, -0.4281F));

		hod.addOrReplaceChild("head_r1", CubeListBuilder.create().texOffs(75, 12).mirror().addBox(-5.0F, 0.0F, 0.0F, 10.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.4719F, -2.7064F, 6.4281F, 0.4363F, 0.0F, 0.0F));

		hod.addOrReplaceChild("head_r2", CubeListBuilder.create().texOffs(71, 15).mirror().addBox(-3.0F, 0.0F, -3.5F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-4.5281F, -2.7064F, 5.9281F, 0.0F, 0.0F, 0.4363F));

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(28, 44).addBox(-8.0F, -8.0F, -4.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(28, 44).mirror().addBox(4.0F, -8.0F, -4.0F, 4.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(56, -4).addBox(-4.0F, -9.0F, 4.0F, 0.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(4.0F, -8.0F, 0.0F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).mirror().addBox(-8.0F, -8.0F, 0.0F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		head.addOrReplaceChild("head_r3", CubeListBuilder.create().texOffs(71, 15).addBox(0.0F, 0.0F, -3.5F, 3.0F, 0.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -2.8F, 5.5F, 0.0F, 0.0F, -0.4363F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 58).mirror().addBox(0.0F, -4.5F, -3.7F, 0.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(3.2F, 10.5F, -3.3F, 0.0F, 1.2217F, 0.0F));

		body.addOrReplaceChild("body_r2", CubeListBuilder.create().texOffs(0, 58).addBox(0.0F, -4.5F, -3.7F, 0.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(0, 58).addBox(0.0F, -4.5F, -3.7F, 0.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.2F, 10.5F, -3.3F, 0.0F, -1.2217F, 0.0F));

		body.addOrReplaceChild("fin", CubeListBuilder.create().texOffs(38, -3).addBox(0.0F, -21.0F, 3.0F, 0.0F, 13.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 3.5F, 0.3F));

		PartDefinition arms_rotation = arms.addOrReplaceChild("arms_rotation", CubeListBuilder.create().texOffs(44, 22).addBox(-8.0F, 0.0F, -2.05F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(40, 38).addBox(-4.0F, 4.0F, -2.05F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 0.05F, -0.7505F, 0.0F, 0.0F));

		arms_rotation.addOrReplaceChild("arms_flipped", CubeListBuilder.create().texOffs(44, 22).mirror().addBox(4.0F, -24.0F, -2.05F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 14).mirror().addBox(8.0F, -24.0F, -0.05F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 14).addBox(-12.0F, -24.0F, -0.05F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(40, 46).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false)
		.texOffs(52, 14).mirror().addBox(3.0F, -1.5F, 0.3F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(40, 46).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(52, 14).addBox(-7.0F, -1.5F, 0.3F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, 2.0F, 0.0F));

		partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, 0.0F));

		partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(EntityAbyssalMaster entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch)
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
		
		boolean flag = entity.getArmPose() == AbstractIllager.IllagerArmPose.CROSSED;
		this.arms.visible = flag;
		this.left_arm.visible = !flag;
		this.right_arm.visible = !flag;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha)
	{
		nose.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		hod.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		arms.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
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