package com.min01.cataclysmicillagers.event;

import com.github.L_Ender.cataclysm.client.render.entity.RendererMini_Abyss_Blast;
import com.github.L_Ender.cataclysm.client.render.entity.RendererTidal_Tentacle;
import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.IllagerEntities;
import com.min01.cataclysmicillagers.entity.model.ModelAbyssalMaster;
import com.min01.cataclysmicillagers.entity.model.ModelDesertMonarch;
import com.min01.cataclysmicillagers.entity.renderer.AbyssalMasterRenderer;
import com.min01.cataclysmicillagers.entity.renderer.DesertMonarchRenderer;
import com.min01.cataclysmicillagers.entity.renderer.FlyingTidalClawRenderer;
import com.min01.cataclysmicillagers.entity.renderer.TidalHookRenderer;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CataclysmicIllagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler 
{
    @SubscribeEvent
    public static void onRegisterEntityRenderers(EntityRenderersEvent.RegisterRenderers event)
    {
    	event.registerEntityRenderer(IllagerEntities.ABYSSAL_MASTER.get(), AbyssalMasterRenderer::new);
    	event.registerEntityRenderer(IllagerEntities.FLYING_TIDAL_CLAW.get(), FlyingTidalClawRenderer::new);
    	event.registerEntityRenderer(IllagerEntities.TIDAL_HOOK.get(), TidalHookRenderer::new);
    	event.registerEntityRenderer(IllagerEntities.MINI_ABYSS_BLAST.get(), RendererMini_Abyss_Blast::new);
    	event.registerEntityRenderer(IllagerEntities.TIDAL_TENTACLE.get(), RendererTidal_Tentacle::new);
    	
    	event.registerEntityRenderer(IllagerEntities.DESERT_MONARCH.get(), DesertMonarchRenderer::new);
    }
    
    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
    	event.registerLayerDefinition(ModelAbyssalMaster.LAYER_LOCATION, ModelAbyssalMaster::createBodyLayer);
    	event.registerLayerDefinition(ModelDesertMonarch.LAYER_LOCATION, ModelDesertMonarch::createBodyLayer);
    }
}
