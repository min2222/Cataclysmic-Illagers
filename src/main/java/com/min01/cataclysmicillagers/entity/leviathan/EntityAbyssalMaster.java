package com.min01.cataclysmicillagers.entity.leviathan;

import java.util.ArrayList;
import java.util.List;

import com.github.L_Ender.cataclysm.entity.AnimationMonster.BossMonsters.The_Leviathan.Abyss_Blast_Portal_Entity;
import com.github.L_Ender.cataclysm.init.ModEffect;
import com.min01.cataclysmicillagers.entity.CataclysmSpellCasterIllager;
import com.min01.cataclysmicillagers.entity.IllagerEntities;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw.ClawType;
import com.min01.cataclysmicillagers.sound.IllagerSounds;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.scores.PlayerTeam;

public class EntityAbyssalMaster extends CataclysmSpellCasterIllager
{
	public List<EntityFlyingTidalClaw> list = new ArrayList<>();
	
	public EntityAbyssalMaster(EntityType<? extends CataclysmSpellCasterIllager> p_33724_, Level p_33725_) 
	{
		super(p_33724_, p_33725_);
	}
	
	@Override
	protected void registerGoals() 
	{
		super.registerGoals();
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 0.6D, 1.0D));
		this.goalSelector.addGoal(4, new EntityAbyssalMaster.AbyssalMasterSummonClawsGoal());
		this.goalSelector.addGoal(4, new EntityAbyssalMaster.AbyssalMasterLaserGoal());
	}
	
	public static AttributeSupplier.Builder createAttributes() 
	{
		return Monster.createMonsterAttributes()
				.add(Attributes.MOVEMENT_SPEED, 0.5D)
				.add(Attributes.FOLLOW_RANGE, 40.0D)
				.add(Attributes.MAX_HEALTH, 50.0D)
				.add(Attributes.ARMOR, 10.0D);
	}
	
	@Override
	public boolean isAlliedTo(Entity p_20355_) 
	{
		if(p_20355_ instanceof EntityFlyingTidalClaw claw)
		{
			return this.list.contains(claw);
		}
		return super.isAlliedTo(p_20355_);
	}
	
	@Override
	public int getAmbientSoundInterval() 
	{
		return 40;
	}
	
	@Override
	protected SoundEvent getAmbientSound() 
	{
		return IllagerSounds.ABYSSAL_MASTER_AMBIENT.get();
	}

	@Override
	protected SoundEvent getDeathSound() 
	{
		return IllagerSounds.ABYSSAL_MASTER_DEATH.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource p_32654_)
	{
		return IllagerSounds.ABYSSAL_MASTER_HURT.get();
	}

	@Override
	protected SoundEvent getCastingSoundEvent() 
	{
		return SoundEvents.EVOKER_CAST_SPELL;
	}
	
	@Override
	public boolean canBeAffected(MobEffectInstance p_21197_) 
	{
		return p_21197_.getEffect() != ModEffect.EFFECTABYSSAL_BURN.get() && p_21197_.getEffect() != ModEffect.EFFECTABYSSAL_CURSE.get() && p_21197_.getEffect() != ModEffect.EFFECTABYSSAL_FEAR.get();
	}

	@Override
	public SoundEvent getCelebrateSound() 
	{
		return SoundEvents.EVOKER_CELEBRATE;
	}
	
	class AbyssalMasterSummonClawsGoal extends CataclysmSpellCasterIllager.CataclysmSpellCasterUseSpellGoal 
	{
		private final TargetingConditions clawCountTargeting = TargetingConditions.forNonCombat().range(16.0D).ignoreLineOfSight().ignoreInvisibilityTesting();

		@Override
		public boolean canUse() 
		{
			if(!super.canUse())
			{
				return false;
			}
			else 
			{
				int i = EntityAbyssalMaster.this.level.getNearbyEntities(Vex.class, this.clawCountTargeting, EntityAbyssalMaster.this, EntityAbyssalMaster.this.getBoundingBox().inflate(16.0D)).size();
				return EntityAbyssalMaster.this.random.nextInt(8) + 1 > i;
			}
		}

		@Override
		protected int getCastingTime() 
		{
			return 100;
		}

		@Override
		protected int getCastingInterval() 
		{
			return 340;
		}

		@Override
		protected void performSpellCasting() 
		{
			ServerLevel serverlevel = (ServerLevel)EntityAbyssalMaster.this.level;

			for(int i = 0; i < 3; ++i)
			{
				BlockPos blockpos = EntityAbyssalMaster.this.blockPosition().offset(-2 + EntityAbyssalMaster.this.random.nextInt(5), 1, -2 + EntityAbyssalMaster.this.random.nextInt(5));
				EntityFlyingTidalClaw claw = new EntityFlyingTidalClaw(IllagerEntities.FLYING_TIDAL_CLAW.get(), EntityAbyssalMaster.this.level, EntityAbyssalMaster.this);
				claw.moveTo(blockpos, 0.0F, 0.0F);
				switch(i)
				{
				case 0 :
					claw.setClawType(ClawType.LASER);
					break;
				case 1 : 
					claw.setClawType(ClawType.TENTACLE);
					break;
				case 2 : 
					claw.setClawType(ClawType.HOOK);
					break;
				}
				claw.finalizeSpawn(serverlevel, EntityAbyssalMaster.this.level.getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, (SpawnGroupData)null, (CompoundTag)null);
				if(EntityAbyssalMaster.this.getTeam() != null)
				{
					PlayerTeam team = EntityAbyssalMaster.this.getServer().getScoreboard().getPlayerTeam(EntityAbyssalMaster.this.getTeam().getName());
					EntityAbyssalMaster.this.getServer().getScoreboard().addPlayerToTeam(claw.getStringUUID(), team);
				}
				EntityAbyssalMaster.this.list.add(claw);
				serverlevel.addFreshEntityWithPassengers(claw);
			}
		}
		
		@Override
		protected SoundEvent getSpellPrepareSound() 
		{
			return SoundEvents.EVOKER_PREPARE_SUMMON;
		}

		@Override
		protected CataclysmSpellCasterIllager.CataclysmIllagerSpell getSpell()
		{
			return CataclysmSpellCasterIllager.CataclysmIllagerSpell.FLYING_TIDAL_CLAW;
		}
	}
	
	class AbyssalMasterLaserGoal extends CataclysmSpellCasterIllager.CataclysmSpellCasterUseSpellGoal 
	{
		@Override
		protected int getCastingTime()
		{
			return 40;
		}

		@Override
		protected int getCastingInterval() 
		{
			return 200;
		}

		@Override
		protected void performSpellCasting()
		{
			LivingEntity livingentity = EntityAbyssalMaster.this.getTarget();
			double d0 = Math.min(livingentity.getY(), EntityAbyssalMaster.this.getY());
			double d1 = Math.max(livingentity.getY(), EntityAbyssalMaster.this.getY()) + 1.0D;
			float f = (float) Mth.atan2(livingentity.getZ() - EntityAbyssalMaster.this.getZ(), livingentity.getX() - EntityAbyssalMaster.this.getX());
			if(EntityAbyssalMaster.this.distanceToSqr(livingentity) < 9.0D)
			{
				for(int i = 0; i < 5; ++i)
				{
					float f1 = f + (float) i * (float) Math.PI * 0.4F;
					this.createSpellEntity(EntityAbyssalMaster.this.getX() + (double) Mth.cos(f1) * 1.5D, EntityAbyssalMaster.this.getZ() + (double) Mth.sin(f1) * 1.5D, d0, d1, f1, 0);
				}

				for(int k = 0; k < 8; ++k) 
				{
					float f2 = f + (float) k * (float) Math.PI * 2.0F / 8.0F + 3.2566371F;
					this.createSpellEntity(EntityAbyssalMaster.this.getX() + (double) Mth.cos(f2) * 2.5D, EntityAbyssalMaster.this.getZ() + (double) Mth.sin(f2) * 2.5D, d0, d1, f2, 3);
				}
			}
			else 
			{
				for(int l = 0; l < 40; ++l) 
				{
					double d2 = 3.25D * (double) (l + 1);
					int j = 1 * l;
					this.createSpellEntity(EntityAbyssalMaster.this.getX() + (double) Mth.cos(f) * d2, EntityAbyssalMaster.this.getZ() + (double) Mth.sin(f) * d2, d0, d1, f, j);
				}
			}
		}

		private void createSpellEntity(double p_32673_, double p_32674_, double p_32675_, double p_32676_, float p_32677_, int p_32678_)
		{
			BlockPos blockpos = new BlockPos(p_32673_, p_32676_, p_32674_);
			boolean flag = false;
			double d0 = 0.0D;

			do
			{
				BlockPos blockpos1 = blockpos.below();
				BlockState blockstate = EntityAbyssalMaster.this.level.getBlockState(blockpos1);
				if(blockstate.isFaceSturdy(EntityAbyssalMaster.this.level, blockpos1, Direction.UP)) 
				{
					if(!EntityAbyssalMaster.this.level.isEmptyBlock(blockpos))
					{
						BlockState blockstate1 = EntityAbyssalMaster.this.level.getBlockState(blockpos);
						VoxelShape voxelshape = blockstate1.getCollisionShape(EntityAbyssalMaster.this.level, blockpos);
						if(!voxelshape.isEmpty()) 
						{
							d0 = voxelshape.max(Direction.Axis.Y);
						}
					}

					flag = true;
					break;
				}

				blockpos = blockpos.below();
			} 
			while(blockpos.getY() >= Mth.floor(p_32675_) - 1);

			if(flag) 
			{
				Abyss_Blast_Portal_Entity portal = new Abyss_Blast_Portal_Entity(EntityAbyssalMaster.this.level, p_32673_, (double) blockpos.getY() + d0, p_32674_, p_32677_, p_32678_, EntityAbyssalMaster.this);
				if(EntityAbyssalMaster.this.getTeam() != null)
				{
					PlayerTeam team = EntityAbyssalMaster.this.getServer().getScoreboard().getPlayerTeam(EntityAbyssalMaster.this.getTeam().getName());
					EntityAbyssalMaster.this.getServer().getScoreboard().addPlayerToTeam(portal.getStringUUID(), team);
				}
				EntityAbyssalMaster.this.level.addFreshEntity(portal);
			}
		}

		@Override
		protected SoundEvent getSpellPrepareSound() 
		{
			return SoundEvents.EVOKER_PREPARE_ATTACK;
		}

		@Override
		protected CataclysmSpellCasterIllager.CataclysmIllagerSpell getSpell() 
		{
			return CataclysmSpellCasterIllager.CataclysmIllagerSpell.ABYSS_BLAST_PORTAL;
		}
	}
}
