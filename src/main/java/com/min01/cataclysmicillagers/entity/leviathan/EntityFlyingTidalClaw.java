package com.min01.cataclysmicillagers.entity.leviathan;

import com.github.L_Ender.cataclysm.entity.projectile.Mini_Abyss_Blast_Entity;
import com.github.L_Ender.cataclysm.entity.projectile.Tidal_Tentacle_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.github.L_Ender.cataclysm.init.ModEntities;
import com.min01.cataclysmicillagers.entity.AbstractOwnableMonster;
import com.min01.cataclysmicillagers.entity.IllagerEntities;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.fluids.FluidType;

public class EntityFlyingTidalClaw extends AbstractOwnableMonster<EntityAbyssalMaster>
{
	private ClawType type;
	private static final EntityDataAccessor<Integer> TICK = SynchedEntityData.defineId(EntityFlyingTidalClaw.class, EntityDataSerializers.INT);
	
	public EntityFlyingTidalClaw(EntityType<? extends Monster> p_19870_, Level p_19871_) 
	{
		super(p_19870_, p_19871_);
        this.setNoGravity(true);
        this.moveControl = new FlyingMoveControl(this, 20, false);
	}
	
	public EntityFlyingTidalClaw(EntityType<? extends Monster> p_19870_, Level p_19871_, EntityAbyssalMaster owner)
	{
		this(p_19870_, p_19871_);
		this.setOwner(owner);
	}
	
	@Override
	protected void registerGoals() 
	{
		this.goalSelector.addGoal(4, new EntityFlyingTidalClaw.ClawAttackGoal(this));
		this.goalSelector.addGoal(4, new WaterAvoidingRandomFlyingGoal(this, 0.65));
		this.targetSelector.addGoal(2, (new NearestAttackableTargetGoal<>(this, Player.class, true)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(3, (new NearestAttackableTargetGoal<>(this, AbstractVillager.class, false)).setUnseenMemoryTicks(300));
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, false));
	}
	
    public static AttributeSupplier.Builder createAttributes()
    {
        return Mob.createMobAttributes()
    			.add(Attributes.MAX_HEALTH, 10.0D)
    			.add(Attributes.FLYING_SPEED, 0.65D)
    			.add(Attributes.FOLLOW_RANGE, 20);
    }
    
    @Override
    protected void defineSynchedData()
    {
    	super.defineSynchedData();
    	this.entityData.define(TICK, 40);
    }
	
	@Override
	protected boolean shouldDespawnInPeaceful()
	{
		return true;
	}
	
	@Override
	public boolean canDrownInFluidType(FluidType type) 
	{
		return false;
	}
	
	@Override
	public boolean isPushedByFluid(FluidType type) 
	{
		return false;
	}
	
    @Override
    public boolean isPushable()
    {
        return false;
    }

    @Override
    protected void doPush(Entity p_20971_) 
    {
    	
    }
    
    @Override
    protected void pushEntities() 
    {
    	
    }
	
	@Override
	protected PathNavigation createNavigation(Level p_218342_)
	{
		FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, p_218342_);
		flyingpathnavigation.setCanOpenDoors(false);
		flyingpathnavigation.setCanFloat(true);
		flyingpathnavigation.setCanPassDoors(true);
		return flyingpathnavigation;
	}
	
	@Override
	public void travel(Vec3 p_218382_) 
	{
		if (this.isEffectiveAi() || this.isControlledByLocalInstance())
		{
			this.moveRelative(this.getSpeed(), p_218382_);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale((double)0.5));
		}
		this.calculateEntityAnimation(this, false);
	}
	
	@Override
	public boolean displayFireAnimation() 
	{
		return false;
	}
    
	@Override
	public boolean causeFallDamage(float p_218321_, float p_218322_, DamageSource p_218323_) 
	{
		return false;
	}
	
	@Override
	protected void playStepSound(BlockPos p_218364_, BlockState p_218365_)
	{
		
	}

	@Override
	protected void checkFallDamage(double p_218316_, boolean p_218317_, BlockState p_218318_, BlockPos p_218319_)
	{
		
	}
	
	@Override
	public boolean removeWhenFarAway(double p_21542_)
	{
		return false;
	}
	
	@Override
	public void addAdditionalSaveData(CompoundTag p_37265_)
	{
		super.addAdditionalSaveData(p_37265_);
		p_37265_.putInt("ClawType", this.type.ordinal());
	}
	
	@Override
	public void readAdditionalSaveData(CompoundTag p_37262_) 
	{
		super.readAdditionalSaveData(p_37262_);
		if(p_37262_.contains("ClawType"))
		{
			this.type = ClawType.values()[p_37262_.getInt("ClawType")];
		}
	}
	
	@Override
	public boolean canBeAffected(MobEffectInstance p_21197_) 
	{
		return p_21197_.getEffect() != ModEffect.EFFECTABYSSAL_BURN.get() && p_21197_.getEffect() != ModEffect.EFFECTABYSSAL_CURSE.get() && p_21197_.getEffect() != ModEffect.EFFECTABYSSAL_FEAR.get();
	}
	
	@Override
	public void tick() 
	{
		super.tick();
		if(this.getOwner() != null)
		{
			EntityAbyssalMaster owner = this.getOwner();
			if(!owner.list.contains(this))
			{
				owner.list.add(this);
			}
			if(owner.getTarget() != null)
			{
				Entity target = owner.getTarget();
	        	this.lookAt(Anchor.EYES, target.getEyePosition());
	        	
	        	if(this.type == ClawType.HOOK)
	        	{
	        		this.getAttribute(Attributes.FOLLOW_RANGE).setBaseValue(100);
	        		if(this.distanceTo(owner) > 10)
	        		{
		        		this.getNavigation().moveTo(owner.getX(), owner.getEyeY(), owner.getZ(), 0.65D);
	        		}
	        	}
	        	else
	        	{
	        		this.getNavigation().moveTo(target.getX(), target.getEyeY(), target.getZ(), 0.65D);
	        	}
	        	
				this.setTarget(owner.getTarget());
			}
			else if(!this.level.isClientSide)
			{
	        	this.lookAt(Anchor.EYES, owner.getEyePosition());
        		this.getNavigation().moveTo(owner.getX(), owner.getEyeY(), owner.getZ(), 0.65D);
			}
		}
		else
		{
			this.discard();
		}
		
		if(this.getTick() > 0)
		{
			this.setTick(this.getTick() - 1);
		}
	}
	
	@Override
	public boolean isAlliedTo(Entity p_20355_)
	{
		if(this.getOwner() != null)
		{
			if(p_20355_ == this.getOwner())
			{
				return true;
			}
			else
			{
				return this.getOwner().isAlliedTo(p_20355_);
			}
		}
		return super.isAlliedTo(p_20355_);
	}
	
	public void setTick(int tick)
	{
		this.entityData.set(TICK, tick);
	}
	
	public int getTick()
	{
		return this.entityData.get(TICK);
	}
	
	public void setClawType(ClawType type)
	{
		this.type = type;
	}
	
	public ClawType getClawType()
	{
		return this.type;
	}
	
	public static enum ClawType
	{
		HOOK,
		TENTACLE,
		LASER;
	}
	
	public static class ClawAttackGoal extends Goal
	{
		private EntityFlyingTidalClaw mob;
		
		public ClawAttackGoal(EntityFlyingTidalClaw mob)
		{
			this.mob = mob;
		}
		
		@Override
		public boolean canUse() 
		{
			Entity entity = this.mob.getTarget();
			if(entity != null)
			{
				boolean flag = this.mob.type == ClawType.HOOK ? this.mob.distanceTo(entity) >= 8 : true;
				return entity.isAlive() && this.mob.getTick() <= 0 && flag;
			}
			return false;
		}
		
		@Override
		public void start() 
		{
			super.start();
			switch(this.mob.type)
			{
			case HOOK:
		        double maxRange = this.mob.distanceTo(this.mob.getTarget()) + 5;
		        double maxSpeed = 20.0D;
		        EntityTidalHook hookshot = new EntityTidalHook(IllagerEntities.TIDAL_HOOK.get(), this.mob, this.mob.level);
		        hookshot.setProperties(maxRange, maxSpeed, this.mob.getXRot(), this.mob.getYRot(), 0.0F, 1.5F * (float)(maxSpeed / 10.0D));
		        this.mob.level.addFreshEntity(hookshot);
				break;
			case LASER:
			    float dir = 90.0F;
				Mini_Abyss_Blast_Entity beam = new Mini_Abyss_Blast_Entity(ModEntities.MINI_ABYSS_BLAST.get(), this.mob.level, this.mob, this.mob.getX(), this.mob.getEyeY() - 0.25, this.mob.getZ(), (float)((this.mob.yHeadRot + dir) * Math.PI / 180.0D), (float)(-this.mob.getXRot() * Math.PI / 180.0D), 80, dir);
				this.mob.level.addFreshEntity(beam);
				break;
			case TENTACLE:
		        Tidal_Tentacle_Entity segment = ModEntities.TIDAL_TENTACLE.get().create(this.mob.level);
		        segment.moveTo(this.mob.getX(), this.mob.getEyeY() - 0.25, this.mob.getZ(), this.mob.getYRot(), this.mob.getXRot());
		        this.mob.level.addFreshEntity(segment);
		        segment.setCreatorEntityUUID(this.mob.getUUID());
		        segment.setFromEntityID(this.mob.getId());
		        segment.setToEntityID(this.mob.getTarget().getId());
		        segment.moveTo(this.mob.getX(), this.mob.getEyeY() - 0.25, this.mob.getZ(), this.mob.getYRot(), this.mob.getXRot());
		        segment.setProgress(0.0F);
				break;
			default:
				break;
			}
			this.mob.setTick(40);
		}
	}
}
