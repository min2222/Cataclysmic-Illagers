package com.min01.cataclysmicillagers.util;

import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.LevelEntityGetter;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;

public class IllagerUtil 
{
	public static int[] getWaveCount(List<? extends Integer> list)
	{
		return new int[] {list.get(0).intValue(), list.get(1).intValue(), list.get(2).intValue(), list.get(3).intValue(), list.get(4).intValue(), list.get(5).intValue(), list.get(6).intValue(), list.get(7).intValue()};
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Entity> T getEntityByUUID(Level level, UUID uuid)
	{
		Method m = ObfuscationReflectionHelper.findMethod(Level.class, "m_142646_");
		try 
		{
			LevelEntityGetter<Entity> entities = (LevelEntityGetter<Entity>) m.invoke(level);
			return (T) entities.get(uuid);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}
