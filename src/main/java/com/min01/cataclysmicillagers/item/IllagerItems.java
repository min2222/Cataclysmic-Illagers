package com.min01.cataclysmicillagers.item;

import java.util.function.Supplier;

import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.IllagerEntities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IllagerItems 
{
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, CataclysmicIllagers.MODID);
	
	public static final RegistryObject<Item> ABYSSAL_MASTER_SPAWN_EGG = registerSpawnEgg("abyssal_master_spawn_egg", () -> IllagerEntities.ABYSSAL_MASTER.get(), 9804699, 3411567);
	public static final RegistryObject<Item> DESERT_MONARCH_SPAWN_EGG = registerSpawnEgg("desert_monarch_spawn_egg", () -> IllagerEntities.DESERT_MONARCH.get(), 12159548, 2434117);
	
	public static RegistryObject<Item> registerSpawnEgg(String name, Supplier<? extends EntityType<? extends Mob>> type, int color1, int color2)
	{
		return ITEMS.register(name, () -> new ForgeSpawnEggItem(type, color1, color2, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	}
}
