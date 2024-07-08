package com.min01.cataclysmicillagers.sound;

import com.min01.cataclysmicillagers.CataclysmicIllagers;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IllagerSounds
{
	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, CataclysmicIllagers.MODID);
	
	public static final RegistryObject<SoundEvent> ABYSSAL_MASTER_AMBIENT = registerSound("abyssal_master_ambient");
	public static final RegistryObject<SoundEvent> ABYSSAL_MASTER_HURT = registerSound("abyssal_master_hurt");
	public static final RegistryObject<SoundEvent> ABYSSAL_MASTER_DEATH = registerSound("abyssal_master_death");
	
	private static RegistryObject<SoundEvent> registerSound(String name) 
	{
		return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(CataclysmicIllagers.MODID, name)));
    }
}
