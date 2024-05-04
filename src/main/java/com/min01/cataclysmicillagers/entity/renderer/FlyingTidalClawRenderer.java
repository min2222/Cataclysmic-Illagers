package com.min01.cataclysmicillagers.entity.renderer;

import com.github.L_Ender.cataclysm.Cataclysm;
import com.github.L_Ender.lionfishapi.client.model.tools.AdvancedEntityModel;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;
import com.min01.cataclysmicillagers.entity.model.ModelTidalClaw;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class FlyingTidalClawRenderer extends MobRenderer<EntityFlyingTidalClaw, AdvancedEntityModel<EntityFlyingTidalClaw>>
{
	public FlyingTidalClawRenderer(Context p_174008_) 
	{
		super(p_174008_, new ModelTidalClaw(), 0);
	}

	@Override
	public ResourceLocation getTextureLocation(EntityFlyingTidalClaw p_114482_) 
	{
		return new ResourceLocation(Cataclysm.MODID, "textures/item/tidal_claws.png");
	}
}
