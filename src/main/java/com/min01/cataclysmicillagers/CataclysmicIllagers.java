package com.min01.cataclysmicillagers;

import com.min01.cataclysmicillagers.entity.IllagerEntities;
import com.min01.cataclysmicillagers.item.IllagerItems;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(CataclysmicIllagers.MODID)
public class CataclysmicIllagers
{
	public static final String MODID = "cataclysmicillagers";
	
	public CataclysmicIllagers() 
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IllagerEntities.ENTITY_TYPES.register(bus);
		IllagerItems.ITEMS.register(bus);
	}
}
