package com.min01.cataclysmicillagers.entity;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.min01.cataclysmicillagers.util.IllagerUtil;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public abstract class AbstractOwnableMonster<T extends LivingEntity> extends Monster
{
	public static final EntityDataAccessor<Optional<UUID>> OWNER_UUID = SynchedEntityData.defineId(AbstractOwnableMonster.class, EntityDataSerializers.OPTIONAL_UUID);
	public static final EntityDataAccessor<Boolean> IS_USING_SKILL = SynchedEntityData.defineId(AbstractOwnableMonster.class, EntityDataSerializers.BOOLEAN);
	
	public int skillUsingTickCount;
	
	public AbstractOwnableMonster(EntityType<? extends Monster> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
	}
	
	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(OWNER_UUID, Optional.empty());
		this.entityData.define(IS_USING_SKILL, false);
	}
	
	public void setIsUsingSkill(boolean value) 
	{
		this.entityData.set(IS_USING_SKILL, value);
	}
	
	protected int getSkillUsingTime()
	{
		return this.skillUsingTickCount;
	}
	
	public boolean isUsingSkill() 
	{
		return this.skillUsingTickCount > 0 || this.entityData.get(IS_USING_SKILL);
	}
	
	@Override
	protected void customServerAiStep() 
	{
		super.customServerAiStep();
		if(this.skillUsingTickCount > 0)
		{
			--this.skillUsingTickCount;
		}
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_) 
	{
		super.addAdditionalSaveData(p_37265_);
		if (this.entityData.get(OWNER_UUID).isPresent())
		{
			p_37265_.putUUID("Owner", this.entityData.get(OWNER_UUID).get());
		}
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		super.readAdditionalSaveData(p_37262_);
		if (p_37262_.hasUUID("Owner")) 
		{
			this.entityData.set(OWNER_UUID, Optional.of(p_37262_.getUUID("Owner")));
		}
	}
	
	public void setOwner(@Nullable T p_37263_)
	{
		if (p_37263_ != null)
		{
			this.entityData.set(OWNER_UUID, Optional.of(p_37263_.getUUID()));
		}
	}
	
	@Nullable
	public T getOwner() 
	{
		if(this.entityData.get(OWNER_UUID).isPresent()) 
		{
			return IllagerUtil.getEntityByUUID(this.level, this.entityData.get(OWNER_UUID).get());
		}
		return null;
	}
}