package com.min01.cataclysmicillagers.entity.renderer;

import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.leviathan.EntityAbyssalMaster;
import com.min01.cataclysmicillagers.entity.model.ModelAbyssalMaster;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class AbyssalMasterRenderer extends MobRenderer<EntityAbyssalMaster, ModelAbyssalMaster>
{
	public AbyssalMasterRenderer(Context p_174304_) 
	{
		super(p_174304_, new ModelAbyssalMaster(p_174304_.bakeLayer(ModelAbyssalMaster.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityAbyssalMaster p_115812_)
	{
		return new ResourceLocation(CataclysmicIllagers.MODID, "textures/entity/abyssal_master.png");
	}
}
