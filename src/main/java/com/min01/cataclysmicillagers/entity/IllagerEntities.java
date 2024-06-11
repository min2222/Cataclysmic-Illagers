package com.min01.cataclysmicillagers.entity;

import com.min01.cataclysmicillagers.CataclysmicIllagers;
import com.min01.cataclysmicillagers.entity.leviathan.EntityAbyssalMaster;
import com.min01.cataclysmicillagers.entity.leviathan.EntityFlyingTidalClaw;
import com.min01.cataclysmicillagers.entity.leviathan.EntityMiniAbyssBlast;
import com.min01.cataclysmicillagers.entity.leviathan.EntityTidalHook;
import com.min01.cataclysmicillagers.entity.leviathan.EntityTidalTentacle;
import com.min01.cataclysmicillagers.entity.remnant.EntityDesertMonarch;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class IllagerEntities
{
	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, CataclysmicIllagers.MODID);

	public static final RegistryObject<EntityType<EntityAbyssalMaster>> ABYSSAL_MASTER = registerEntity("abyssal_master", createBuilder(EntityAbyssalMaster::new, MobCategory.MONSTER).sized(0.6F, 1.95F));
	public static final RegistryObject<EntityType<EntityFlyingTidalClaw>> FLYING_TIDAL_CLAW = registerEntity("flying_tidal_claw", EntityType.Builder.<EntityFlyingTidalClaw>of(EntityFlyingTidalClaw::new, MobCategory.MONSTER).sized(0.5F, 0.5F));
	public static final RegistryObject<EntityType<EntityTidalHook>> TIDAL_HOOK = registerEntity("tidal_hook", EntityType.Builder.<EntityTidalHook>of(EntityTidalHook::new, MobCategory.MISC).sized(0.5F, 0.5F));
	public static final RegistryObject<EntityType<EntityMiniAbyssBlast>> MINI_ABYSS_BLAST = registerEntity("mini_abyss_blast", EntityType.Builder.<EntityMiniAbyssBlast>of(EntityMiniAbyssBlast::new, MobCategory.MISC).sized(0.1F, 0.1F));
	public static final RegistryObject<EntityType<EntityTidalTentacle>> TIDAL_TENTACLE = registerEntity("tidal_tentacle", EntityType.Builder.<EntityTidalTentacle>of(EntityTidalTentacle::new, MobCategory.MISC).sized(0.1F, 0.1F));

	public static final RegistryObject<EntityType<EntityDesertMonarch>> DESERT_MONARCH = registerEntity("desert_monarch", createBuilder(EntityDesertMonarch::new, MobCategory.MONSTER).sized(0.6F, 2.25F));
	
	public static <T extends Entity> EntityType.Builder<T> createBuilder(EntityType.EntityFactory<T> factory, MobCategory category)
	{
		return EntityType.Builder.<T>of(factory, category);
	}
	
	public static <T extends Entity> RegistryObject<EntityType<T>> registerEntity(String name, EntityType.Builder<T> builder) 
	{
		return ENTITY_TYPES.register(name, () -> builder.build(new ResourceLocation(CataclysmicIllagers.MODID, name).toString()));
	}
}
