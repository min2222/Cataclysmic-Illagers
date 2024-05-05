package com.min01.cataclysmicillagers.entity.leviathan;

import com.github.L_Ender.cataclysm.init.ModEntities;
import com.github.L_Ender.cataclysm.init.ModSounds;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class EntityTidalHook extends AbstractArrow 
{
	private static final EntityDataAccessor<Integer> HOOKED_ENTITY_ID = SynchedEntityData.defineId(EntityTidalHook.class, EntityDataSerializers.INT);

	private double maxRange = 0.0D;

	private double maxSpeed = 0.0D;
	
	private Entity hookedEntity;
	
	private boolean isPulling = false;

	public EntityTidalHook(EntityType<? extends AbstractArrow> type, LivingEntity owner, Level world) 
	{
		super(type, owner, world);
		setNoGravity(true);
		setBaseDamage(0.0D);
	}

	public EntityTidalHook(Level world, double x, double y, double z) 
	{
		super(ModEntities.TIDAL_HOOK.get(), x, y, z, world);
		setNoGravity(true);
		setBaseDamage(0.0D);
	}

	public EntityTidalHook(EntityType<EntityTidalHook> tidal_hook_entityEntityType, Level level)
	{
		super(tidal_hook_entityEntityType, level);
		setNoGravity(true);
		setBaseDamage(0.0D);
	}

	@Override
	protected void defineSynchedData()
	{
		super.defineSynchedData();
		this.entityData.define(HOOKED_ENTITY_ID, Integer.valueOf(0));
	}

	@Override
	public void tick()
	{
		super.tick();
		Entity entity = getOwner();
		if (entity instanceof LivingEntity) 
		{
			LivingEntity owner = (LivingEntity) entity;
			
			if(owner != null)
			{
				if (this.isPulling && this.tickCount % 3 == 0)
				{
					this.level.playSound(null, getOwner().blockPosition(), ModSounds.TIDAL_HOOK_LOOP.get(), SoundSource.PLAYERS, 0.4F, 1.0F);
				}
				
				if (!this.level.isClientSide) 
				{
					if (owner.isDeadOrDying() || owner.distanceTo(this) > this.maxRange || this.distanceTo(owner) <= 2)
					{
						this.discard();
					}
					
					if (this.hookedEntity != null)
					{
						if (this.hookedEntity.isRemoved()) 
						{
							this.hookedEntity = null;
							this.discard();
						}
						else 
						{
							moveTo(this.hookedEntity.getX(), this.hookedEntity.getY(0.8D), this.hookedEntity.getZ());
						}
						
						if (this.isPulling && this.hookedEntity != null) 
						{
							double brakeZone = 6.0D * this.maxSpeed / 10.0D;
							double pullSpeed = this.maxSpeed / 6.0D;
							Vec3 distance = owner.position().subtract(this.hookedEntity.position().add(0.0D, (this.hookedEntity.getBbHeight() / 2.0F), 0.0D));
							Vec3 motion = distance.normalize().scale((distance.length() < brakeZone) ? (pullSpeed * distance.length() / brakeZone) : pullSpeed);
							
							if (Math.abs(distance.y) < 0.1D)
							{
								motion = new Vec3(motion.x, 0.0D, motion.z);
							}
							
							if ((new Vec3(distance.x, 0.0D, distance.z)).length() < (new Vec3((this.hookedEntity.getBbWidth() / 2.0F), 0.0D, (this.hookedEntity.getBbWidth() / 2.0F))).length() / 1.4D)
							{
								motion = new Vec3(0.0D, motion.y, 0.0D);
							}
							
							this.hookedEntity.setDeltaMovement(motion);
							this.hookedEntity.hurtMarked = true;
						}
					}
				}
			}
		}
	}

	@Override
	public boolean shouldRenderAtSqrDistance(double distance)
	{
		return true;
	}

	@Override
	protected float getWaterInertia()
	{
		return 1.0F;
	}

	@Override
	protected ItemStack getPickupItem() 
	{
		return ItemStack.EMPTY;
	}

	@Override
	protected SoundEvent getDefaultHitGroundSoundEvent() 
	{
		return (SoundEvent) ModSounds.TIDAL_HOOK_HIT.get();
	}
	
	@Override
	protected void onHitBlock(BlockHitResult p_36755_)
	{
		super.onHitBlock(p_36755_);
		this.discard();
	}

	@Override
	protected void onHitEntity(EntityHitResult entityHitResult)
	{
		if (!this.level.isClientSide) 
		{
			Entity entity = this.getOwner();
			if (entity instanceof LivingEntity owner)
			{
				Entity hitEntity = entityHitResult.getEntity();
				if (!owner.isAlliedTo(hitEntity) && hitEntity != owner && hitEntity instanceof LivingEntity living && this.hookedEntity == null) 
				{
					this.hookedEntity = living;
					this.getEntityData().set(HOOKED_ENTITY_ID, this.hookedEntity.getId() + 1);
					this.isPulling = true;
				}
			}
		}
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) 
	{
		super.readAdditionalSaveData(tag);
		this.maxRange = tag.getDouble("maxRange");
		this.maxSpeed = tag.getDouble("maxSpeed");
	    this.isPulling = tag.getBoolean("isPulling");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag)
	{
		super.addAdditionalSaveData(tag);
		tag.putDouble("maxRange", this.maxRange);
		tag.putDouble("maxSpeed", this.maxSpeed);
	    tag.putBoolean("isPulling", this.isPulling);
	}

	public void setProperties(double maxRange, double maxVelocity, float pitch, float yaw, float roll, float modifierZ) 
	{
		float f = 0.017453292F;
		float x = -Mth.sin(yaw * f) * Mth.cos(pitch * f);
		float y = -Mth.sin((pitch + roll) * f);
		float z = Mth.cos(yaw * f) * Mth.cos(pitch * f);
		shoot(x, y, z, modifierZ, 0.0F);
		this.maxRange = maxRange;
		this.maxSpeed = maxVelocity;
	}
}