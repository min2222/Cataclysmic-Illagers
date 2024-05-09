package com.min01.cataclysmicillagers;

import com.min01.cataclysmicillagers.config.IllagerConfig;
import com.min01.cataclysmicillagers.entity.IllagerEntities;
import com.min01.cataclysmicillagers.item.IllagerItems;
import com.min01.cataclysmicillagers.sound.IllagerSounds;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod(CataclysmicIllagers.MODID)
public class CataclysmicIllagers
{
	public static final String MODID = "cataclysmicillagers";
	
	public CataclysmicIllagers() 
	{
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		IllagerEntities.ENTITY_TYPES.register(bus);
		IllagerItems.ITEMS.register(bus);
		IllagerSounds.SOUNDS.register(bus);
		
		IllagerConfig.loadConfig(IllagerConfig.CONFIG, FMLPaths.CONFIGDIR.get().resolve("cataclysmic-illagers.toml").toString());
	}
}
