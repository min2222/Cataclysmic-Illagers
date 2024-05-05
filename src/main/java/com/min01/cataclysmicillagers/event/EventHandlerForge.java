package com.min01.cataclysmicillagers.event;

import com.github.L_Ender.cataclysm.entity.projectile.Mini_Abyss_Blast_Entity;
import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
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
}
