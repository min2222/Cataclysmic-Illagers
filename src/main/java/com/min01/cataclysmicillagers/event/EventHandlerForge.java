package com.min01.cataclysmicillagers.event;

import com.github.L_Ender.cataclysm.entity.projectile.Mini_Abyss_Blast_Entity;
import com.github.L_Ender.cataclysm.world.data.CMWorldData;
import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.config.IllagerConfig;
import com.min01.cataclysmicillagers.entity.IllagerEntities;
import com.min01.cataclysmicillagers.entity.leviathan.EntityAbyssalMaster;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;
import com.min01.cataclysmicillagers.util.IllagerUtil;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.raid.Raid.RaiderType;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
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
	public static void onLivingTick(LivingTickEvent event)
	{
		Entity entity = event.getEntity();
		if(entity instanceof AbstractVillager villager)
		{
			villager.goalSelector.addGoal(1, new AvoidEntityGoal<>(villager, EntityAbyssalMaster.class, 8.0F, 0.5D, 0.5D));
		}
	}
	
	@SubscribeEvent
	public static void onLevelLoad(LevelEvent.Load event)
	{
        CMWorldData worldData = CMWorldData.get((Level) event.getLevel(), Level.OVERWORLD);
        if(worldData != null) 
        {
        	if(worldData.isLeviathanDefeatedOnce())
        	{
            	RaiderType.create("ABYSSAL_MASTER", IllagerEntities.ABYSSAL_MASTER.get(), IllagerUtil.getWaveCount(IllagerConfig.abyssalMasterWaveCount.get()));
        	}
        }
	}
}
