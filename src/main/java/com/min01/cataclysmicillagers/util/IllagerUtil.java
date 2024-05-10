package com.min01.cataclysmicillagers.util;

import java.util.List;
import java.util.UUID;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.TransientEntitySectionManager;
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
		if(level instanceof ServerLevel serverLevel)
		{
			return (T) serverLevel.getEntity(uuid);
		}
		else if(level instanceof ClientLevel clientLevel)
		{
			TransientEntitySectionManager<Entity> entityStorage = ObfuscationReflectionHelper.getPrivateValue(ClientLevel.class, clientLevel, "f_171631_");
			return (T) entityStorage.getEntityGetter().get(uuid);
		}
		return null;
	}
}
