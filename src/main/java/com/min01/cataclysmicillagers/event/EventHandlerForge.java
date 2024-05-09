package com.min01.cataclysmicillagers.event;

import java.util.List;

import com.github.L_Ender.cataclysm.entity.projectile.Mini_Abyss_Blast_Entity;
import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.config.IllagerConfig;
import com.min01.cataclysmicillagers.entity.IllagerEntities;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.raid.Raid.RaiderType;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CataclysmicIllagers.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class EventHandlerForge
{
	@SubscribeEvent
	public static void onLevelTick(LevelTickEvent event)
	{
		if(event.level instanceof ServerLevel level)
		{
			level.getAllEntities().forEach(t -> 
			{
				if(t instanceof Mini_Abyss_Blast_Entity blast)
				{
					if(blast.caster != null && blast.caster instanceof EntityFlyingTidalClaw claw)
					{
						blast.setPos(claw.getX(), claw.getEyeY() - 0.25, claw.getZ());
					}
				}
			});
		}
	}
	
	@SubscribeEvent
	public static void onLevelLoad(LevelEvent.Load event)
	{
    	RaiderType.create("ABYSSAL_MASTER", IllagerEntities.ABYSSAL_MASTER.get(), getWaveCount(IllagerConfig.abyssalMasterWaveCount.get()));
	}
	
	public static int[] getWaveCount(List<? extends Integer> list)
	{
		return new int[] {list.get(0).intValue(), list.get(1).intValue(), list.get(2).intValue(), list.get(3).intValue(), list.get(4).intValue(), list.get(5).intValue(), list.get(6).intValue(), list.get(7).intValue()};
	}
}
