package com.min01.cataclysmicillagers.entity.remnant;

import com.min01.cataclysmicillagers.entity.CataclysmSpellCasterIllager;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class EntityDesertMonarch extends CataclysmSpellCasterIllager
{
	public EntityDesertMonarch(EntityType<? extends CataclysmSpellCasterIllager> p_33724_, Level p_33725_) 
	{
		super(p_33724_, p_33725_);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
	}
	
	public static AttributeSupplier.Builder createAttributes() 
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MOVEMENT_SPEED, 0.55D)
				.add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MAX_HEALTH, 80.0D)
				.add(Attributes.ARMOR, 25.0D)
				.add(Attributes.ARMOR_TOUGHNESS, 25.0D);
	}
	
	@Override
	public double getRandomStrollSpeed() 
	{
		return 0.45D;
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return SoundEvents.VINDICATOR_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return SoundEvents.VINDICATOR_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_32654_)
	{
		return SoundEvents.VINDICATOR_HURT;
	}

	@Override
	protected SoundEvent getCastingSoundEvent() 
	{
		return SoundEvents.EVOKER_CAST_SPELL;
	}

	@Override
	public SoundEvent getCelebrateSound()
	{
		return SoundEvents.VINDICATOR_CELEBRATE;
	}
}
