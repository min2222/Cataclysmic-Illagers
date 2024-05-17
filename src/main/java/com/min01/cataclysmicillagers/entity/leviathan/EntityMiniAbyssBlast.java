package com.min01.cataclysmicillagers.entity.leviathan;

import com.github.L_Ender.cataclysm.entity.projectile.Mini_Abyss_Blast_Entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class EntityMiniAbyssBlast extends Mini_Abyss_Blast_Entity
{
    public EntityMiniAbyssBlast(EntityType<? extends Mini_Abyss_Blast_Entity> type, Level world)
    {
    	super(type, world);
    }
    
	public EntityMiniAbyssBlast(EntityType<? extends Mini_Abyss_Blast_Entity> type, Level world, LivingEntity caster, double x, double y, double z, float yaw, float pitch, int duration, float direction)
	{
		super(type, world, caster, x, y, z, yaw, pitch, duration, direction);
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.caster != null)
		{
            if(this.caster instanceof EntityFlyingTidalClaw claw)
            {
            	if(!claw.isAlive())
            	{
            		this.discard();
            	}
            	if(!claw.level.isClientSide && claw.getTarget() == null)
            	{
            		this.discard();
            	}
                this.updatePos();
            }
		}
		else
		{
        	this.discard();
		}
	}
	
    private void updatePos()
    {
    	this.setYaw((float) ((this.caster.yHeadRot + this.getBeamDirection()) * Math.PI / 180.0D));
        this.setPitch((float) (-this.caster.getXRot() * Math.PI / 180.0D));
        this.setPos(this.caster.getX(), this.caster.getY() + 0.125F, this.caster.getZ());
    }
}
