package com.min01.cataclysmicillagers.event;

import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.IllagerEntities;
import com.min01.cataclysmicillagers.entity.leviathan.EntityAbyssalMaster;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;
import com.min01.cataclysmicillagers.entity.remnant.EntityDesertMonarch;
import com.min01.cataclysmicillagers.item.IllagerItems;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CataclysmicIllagers.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler
{
    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) 
    {
    	event.put(IllagerEntities.ABYSSAL_MASTER.get(), EntityAbyssalMaster.createAttributes().build());
    	event.put(IllagerEntities.FLYING_TIDAL_CLAW.get(), EntityFlyingTidalClaw.createAttributes().build());
    	
    	event.put(IllagerEntities.DESERT_MONARCH.get(), EntityDesertMonarch.createAttributes().build());
    }
    
    @SubscribeEvent
    public static void onCreativeTabBuild(BuildCreativeModeTabContentsEvent event) 
    {
        if(event.getTabKey() == CreativeModeTabs.SPAWN_EGGS) 
        {
        	event.accept(IllagerItems.ABYSSAL_MASTER_SPAWN_EGG.get());
        }
    }
}
