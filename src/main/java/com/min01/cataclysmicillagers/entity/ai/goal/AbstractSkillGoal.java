package com.min01.cataclysmicillagers.entity.ai.goal;

import com.min01.cataclysmicillagers.entity.AbstractOwnableMonster;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public abstract class AbstractSkillGoal<T extends AbstractOwnableMonster<? extends LivingEntity>> extends Goal
{
	protected int skillWarmupDelay;
	protected int nextSkillTickCount;
	
	public AbstractSkillGoal() 
	{
		
	}
	
    @Override
    public boolean canUse() 
    {
    	LivingEntity livingentity = this.getMob().getTarget();
    	if (livingentity != null && livingentity.isAlive()) 
    	{
    		if (this.getMob().isUsingSkill())
    		{
    			return false;
    		}
    		else 
    		{
    			return this.getMob().tickCount >= this.nextSkillTickCount && this.additionalStartCondition();
    		}
    	}
    	else 
    	{
    		return false;
    	}
    }
    
    public boolean additionalStartCondition()
    {
    	return true;
    }
    
    @Override
    public boolean canContinueToUse() 
    {
    	LivingEntity livingentity = this.getMob().getTarget();
    	return livingentity != null && livingentity.isAlive() && this.getMob().skillUsingTickCount > 0;
    }
    
    @Override
    public void start()
    {
    	this.getMob().setAggressive(true);
    	this.skillWarmupDelay = this.adjustedTickDelay(this.getSkillWarmupTime());
    	this.getMob().skillUsingTickCount = this.getSkillUsingTime();
    	this.nextSkillTickCount = this.getMob().tickCount + this.getSkillUsingInterval();
    	
    	this.getMob().setIsUsingSkill(true);
    }
    
	@Override
	public void stop()
	{
		this.getMob().setAggressive(false);
    	this.getMob().setIsUsingSkill(false);
	}
	
    @Override
    public void tick() 
    {
    	--this.skillWarmupDelay;
    	if (this.skillWarmupDelay == 0) 
    	{
    		this.performSkill();
    	}
    }

    protected abstract void performSkill();

    //wait specific tick before use skill
    protected int getSkillWarmupTime()
    {
    	return 20;
    }
    
    protected abstract int getSkillUsingTime();

    protected abstract int getSkillUsingInterval();
    
    public abstract T getMob();
}
