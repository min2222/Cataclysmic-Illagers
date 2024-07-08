package com.min01.cataclysmicillagers.entity.remnant;

import com.github.L_Ender.cataclysm.entity.projectile.Ancient_Desert_Stele_Entity;
import com.min01.cataclysmicillagers.entity.CataclysmSpellCasterIllager;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

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
		this.goalSelector.addGoal(4, new EntityDesertMonarch.DesertMonarchSteleGoal());
		this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 0.45D, false));
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
	public void tick()
	{
		super.tick();
		
		if(this.getTarget() != null && !this.isCastingSpell())
		{
			this.setAggressive(true);
		}
	}
	
	@Override
	public double getRandomStrollSpeed() 
	{
		return 0.45D;
	}
	
	@Override
	public IllagerArmPose getArmPose() 
	{
		if(this.isAggressive()) 
		{
			return AbstractIllager.IllagerArmPose.ATTACKING;
		}
		return super.getArmPose();
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
	
	class DesertMonarchSteleGoal extends CataclysmSpellCasterIllager.CataclysmSpellCasterUseSpellGoal 
	{
		@Override
		public void start() 
		{
			super.start();
			EntityDesertMonarch.this.setAggressive(false);
		}
		
		@Override
		public boolean canUse() 
		{
			return super.canUse() && EntityDesertMonarch.this.distanceTo(EntityDesertMonarch.this.getTarget()) >= 6;
		}
		
		@Override
		protected void performSpellCasting()
		{
			Entity target = EntityDesertMonarch.this.getTarget();
            double d1 = target.getY();
            float f = (float) Mth.atan2(target.getZ() - EntityDesertMonarch.this.getZ(), target.getX() - EntityDesertMonarch.this.getX());

            for(int k = 0; k < 8; ++k)
            {
                float f2 = f + (float)k * (float)Math.PI * 2.0F / 8.0F + ((float) Math.PI * 2F / 5F);
                this.createSpellEntity(EntityDesertMonarch.this.getX() + (double)Mth.cos(f2) * 4.5D, EntityDesertMonarch.this.getZ() + (double)Mth.sin(f2) * 4.5D, d1, f2, 3);
            }
            
            for(int k = 0; k < 13; ++k) 
            {
                float f3 = f + (float) k * (float) Math.PI * 2.0F / 13.0F + ((float) Math.PI * 2F / 10F);
                this.createSpellEntity(EntityDesertMonarch.this.getX() + (double)Mth.cos(f3) * 6.5D, EntityDesertMonarch.this.getZ() + (double)Mth.sin(f3) * 6.5D, d1, f3, 10);
            }
            
            for(int k = 0; k < 16; ++k)
            {
                float f4 = f + (float) k * (float) Math.PI * 2.0F / 16.0F + ((float) Math.PI * 2F / 20F);
                this.createSpellEntity(EntityDesertMonarch.this.getX() + (double)Mth.cos(f4) * 8.5D, EntityDesertMonarch.this.getZ() + (double)Mth.sin(f4) * 8.5D, d1, f4, 15);
            }
            
            for(int k = 0; k < 19; ++k)
            {
                float f5 = f + (float) k * (float) Math.PI * 2.0F / 19.0F + ((float) Math.PI * 2F / 40F);
                this.createSpellEntity(EntityDesertMonarch.this.getX() + (double)Mth.cos(f5) * 10.5D, EntityDesertMonarch.this.getZ() + (double)Mth.sin(f5) * 10.5D, d1, f5, 20);
            }
            
            for(int k = 0; k < 24; ++k) 
            {
                float f6 = f + (float) k * (float) Math.PI * 2.0F / 24.0F + ((float) Math.PI * 2F / 80F);
                this.createSpellEntity(EntityDesertMonarch.this.getX() + (double)Mth.cos(f6) * 12.5D, EntityDesertMonarch.this.getZ() + (double)Mth.sin(f6) * 12.5D, d1, f6, 30);
            }
		}

		@Override
		protected int getCastingTime() 
		{
			return 40;
		}

		@Override
		protected int getCastingInterval() 
		{
			return 100;
		}

		@Override
		protected SoundEvent getSpellPrepareSound()
		{
			return SoundEvents.EVOKER_CAST_SPELL;
		}

		@Override
		protected CataclysmIllagerSpell getSpell() 
		{
			return CataclysmIllagerSpell.ANCIENT_STELE;
		}
		
		private void createSpellEntity(double posX, double posZ, double posY, float rotation, int delay)
		{
			BlockPos blockpos = BlockPos.containing(posX, posY, posZ);
            double d0 = 0.0D;
            do
            {
                BlockPos blockpos1 = blockpos.above();
                BlockState blockstate = EntityDesertMonarch.this.level.getBlockState(blockpos1);
                if(blockstate.isFaceSturdy(EntityDesertMonarch.this.level, blockpos1, Direction.DOWN))
                {
                    if(!EntityDesertMonarch.this.level.isEmptyBlock(blockpos)) 
                    {
                        BlockState blockstate1 = EntityDesertMonarch.this.level.getBlockState(blockpos);
                        VoxelShape voxelshape = blockstate1.getCollisionShape(EntityDesertMonarch.this.level, blockpos);
                        if(!voxelshape.isEmpty()) 
                        {
                            d0 = voxelshape.max(Direction.Axis.Y);
                        }
                    }

                    break;
                }

                blockpos = blockpos.above();
            }
            while(blockpos.getY() < Math.min(EntityDesertMonarch.this.level.getMaxBuildHeight(), EntityDesertMonarch.this.getBlockY() + 12));
			EntityDesertMonarch.this.level.addFreshEntity(new Ancient_Desert_Stele_Entity(EntityDesertMonarch.this.level, posX, (double)blockpos.getY() + d0 -3, posZ, rotation, delay, EntityDesertMonarch.this));
		}
	}
}
