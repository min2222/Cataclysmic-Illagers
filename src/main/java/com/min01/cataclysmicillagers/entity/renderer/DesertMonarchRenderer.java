package com.min01.cataclysmicillagers.entity.renderer;

import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.model.ModelDesertMonarch;
import com.min01.cataclysmicillagers.entity.remnant.EntityDesertMonarch;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class DesertMonarchRenderer extends MobRenderer<EntityDesertMonarch, ModelDesertMonarch>
{
	public DesertMonarchRenderer(Context p_174304_)
	{
		super(p_174304_, new ModelDesertMonarch(p_174304_.bakeLayer(ModelDesertMonarch.LAYER_LOCATION)), 0.5F);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityDesertMonarch p_115812_)
	{
		return new ResourceLocation(CataclysmicIllagers.MODID, "textures/entity/desert_monarch.png");
	}
}
