package com.min01.cataclysmicillagers.entity.leviathan;

import com.github.L_Ender.cataclysm.entity.projectile.Tidal_Tentacle_Entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class EntityTidalTentacle extends Tidal_Tentacle_Entity
{
	public EntityTidalTentacle(EntityType<?> type, Level level)
	{
		super(type, level);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.getCreatorEntity() != null)
		{
			Entity entity = this.getCreatorEntity();
			if(entity instanceof EntityFlyingTidalClaw claw)
			{
				if(!claw.isAlive() || claw.getTarget() == null)
				{
					this.discard();
				}
			}
		}
	}
}
