package com.min01.cataclysmicillagers.entity;

import javax.annotation.Nullable;

import net.minecraft.commands.arguments.EntityAnchorArgument.Anchor;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.AbstractIllager;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public abstract class CataclysmSpellCasterIllager extends AbstractIllager 
{
	private static final EntityDataAccessor<Byte> DATA_SPELL_CASTING_ID = SynchedEntityData.defineId(CataclysmSpellCasterIllager.class, EntityDataSerializers.BYTE);
	protected int spellCastingTickCount;
	private CataclysmSpellCasterIllager.CataclysmIllagerSpell currentSpell = CataclysmSpellCasterIllager.CataclysmIllagerSpell.NONE;

	protected CataclysmSpellCasterIllager(EntityType<? extends CataclysmSpellCasterIllager> p_33724_, Level p_33725_)
	{
		super(p_33724_, p_33725_);
	}

	@Override
	protected void defineSynchedData() 
	{
		super.defineSynchedData();
		this.entityData.define(DATA_SPELL_CASTING_ID, (byte) 0);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag p_33732_) 
	{
		super.readAdditionalSaveData(p_33732_);
		this.spellCastingTickCount = p_33732_.getInt("SpellTicks");
	}

	@Override
	public void addAdditionalSaveData(CompoundTag p_33734_)
	{
		super.addAdditionalSaveData(p_33734_);
		p_33734_.putInt("SpellTicks", this.spellCastingTickCount);
	}

	@Override
	public AbstractIllager.IllagerArmPose getArmPose()
	{
		if (this.isCastingSpell()) 
		{
			return AbstractIllager.IllagerArmPose.SPELLCASTING;
		} 
		else
		{
			return this.isCelebrating() ? AbstractIllager.IllagerArmPose.CELEBRATING : AbstractIllager.IllagerArmPose.CROSSED;
		}
	}

	
	public boolean isCastingSpell()
	{
		if (this.level.isClientSide)
		{
			return this.entityData.get(DATA_SPELL_CASTING_ID) > 0;
		} 
		else 
		{
			return this.spellCastingTickCount > 0;
		}
	}

	public void setIsCastingSpell(CataclysmSpellCasterIllager.CataclysmIllagerSpell p_33728_)
	{
		this.currentSpell = p_33728_;
		this.entityData.set(DATA_SPELL_CASTING_ID, (byte) p_33728_.id);
	}

	protected CataclysmSpellCasterIllager.CataclysmIllagerSpell getCurrentSpell()
	{
		return !this.level.isClientSide ? this.currentSpell : CataclysmSpellCasterIllager.CataclysmIllagerSpell.byId(this.entityData.get(DATA_SPELL_CASTING_ID));
	}

	@Override
	protected void customServerAiStep() 
	{
		super.customServerAiStep();
		if (this.spellCastingTickCount > 0)
		{
			--this.spellCastingTickCount;
		}
	}

	@Override
	public void tick() 
	{
		super.tick();
		if (this.level.isClientSide && this.isCastingSpell())
		{
			CataclysmSpellCasterIllager.CataclysmIllagerSpell spellcasterillager$illagerspell = this.getCurrentSpell();
			Vec3 color = Vec3.fromRGB24(spellcasterillager$illagerspell.spellColor);
			float f = this.yBodyRot * ((float) Math.PI / 180F) + Mth.cos((float) this.tickCount * 0.6662F) * 0.25F;
			float f1 = Mth.cos(f);
			float f2 = Mth.sin(f);
			this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + (double) f1 * 0.6D, this.getY() + 1.8D, this.getZ() + (double) f2 * 0.6D, color.x, color.y, color.z);
			this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() - (double) f1 * 0.6D, this.getY() + 1.8D, this.getZ() - (double) f2 * 0.6D, color.x, color.y, color.z);
		}
		
		if(this.getTarget() != null)
		{
			this.lookAt(Anchor.EYES, this.getTarget().getEyePosition());
		}
	}

	protected int getSpellCastingTime() 
	{
		return this.spellCastingTickCount;
	}

	protected abstract SoundEvent getCastingSoundEvent();

	public static enum CataclysmIllagerSpell implements net.minecraftforge.common.IExtensibleEnum
	{
		NONE(0, 0),
		ABYSS_BLAST_PORTAL(1, 3411567),
		FLYING_TIDAL_CLAW(2, 3411567);
		final int id;
		final int spellColor;

		private CataclysmIllagerSpell(int id, int color) 
		{
			this.id = id;
			this.spellColor = color;
		}

		public static CataclysmIllagerSpell byId(int p_33759_) 
		{
			for(CataclysmIllagerSpell spellcasterillager$illagerspell : values()) 
			{
				if (p_33759_ == spellcasterillager$illagerspell.id)
				{
					return spellcasterillager$illagerspell;
				}
			}
			return NONE;
		}
		
		public static CataclysmIllagerSpell create(String name, int id, int color)
		{
			throw new IllegalStateException("Enum not extended");
		}
	}

	public abstract class CataclysmSpellCasterUseSpellGoal extends Goal 
	{
		protected int attackWarmupDelay;
		protected int nextAttackTickCount;

		@Override
		public boolean canUse()
		{
			LivingEntity livingentity = CataclysmSpellCasterIllager.this.getTarget();
			if (livingentity != null && livingentity.isAlive())
			{
				if (CataclysmSpellCasterIllager.this.isCastingSpell())
				{
					return false;
				} 
				else
				{
					return CataclysmSpellCasterIllager.this.tickCount >= this.nextAttackTickCount;
				}
			}
			else 
			{
				return false;
			}
		}

		@Override
		public boolean canContinueToUse()
		{
			LivingEntity livingentity = CataclysmSpellCasterIllager.this.getTarget();
			return livingentity != null && livingentity.isAlive() && this.attackWarmupDelay > 0;
		}

		@Override
		public void start() 
		{
			this.attackWarmupDelay = this.adjustedTickDelay(this.getCastWarmupTime());
			CataclysmSpellCasterIllager.this.spellCastingTickCount = this.getCastingTime();
			this.nextAttackTickCount = CataclysmSpellCasterIllager.this.tickCount + this.getCastingInterval();
			SoundEvent soundevent = this.getSpellPrepareSound();
			if (soundevent != null) 
			{
				CataclysmSpellCasterIllager.this.playSound(soundevent, 1.0F, 1.0F);
			}

			CataclysmSpellCasterIllager.this.setIsCastingSpell(this.getSpell());
		}

		@Override
		public void tick() 
		{
			--this.attackWarmupDelay;
			if (this.attackWarmupDelay == 0)
			{
				this.performSpellCasting();
				CataclysmSpellCasterIllager.this.playSound(CataclysmSpellCasterIllager.this.getCastingSoundEvent(), 1.0F, 1.0F);
			}
		}

		@Override
		public void stop() 
		{
			CataclysmSpellCasterIllager.this.setIsCastingSpell(CataclysmIllagerSpell.NONE);
		}
		
		protected abstract void performSpellCasting();

		protected int getCastWarmupTime()
		{
			return 20;
		}

		protected abstract int getCastingTime();

		protected abstract int getCastingInterval();

		@Nullable
		protected abstract SoundEvent getSpellPrepareSound();

		protected abstract CataclysmSpellCasterIllager.CataclysmIllagerSpell getSpell();
	}
}