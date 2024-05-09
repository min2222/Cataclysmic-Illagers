package com.min01.cataclysmicillagers.config;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class IllagerConfig 
{
    private static ForgeConfigSpec.Builder BUILDER;
    public static ForgeConfigSpec CONFIG;

	public static ConfigValue<List<? extends Integer>> abyssalMasterWaveCount;
    
    public static void loadConfig(ForgeConfigSpec config, String path) 
    {
        CommentedFileConfig file = CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        file.load();
        config.setConfig(file);
    }
    
    static 
    {
    	BUILDER = new ForgeConfigSpec.Builder();
    	IllagerConfig.init(IllagerConfig.BUILDER);
    	CONFIG = IllagerConfig.BUILDER.build();
    }
	
    public static void init(ForgeConfigSpec.Builder config) 
    {
    	config.push("Raid Settings");
        abyssalMasterWaveCount = config.comment("number of abyssal master spawned for each wave").define("abyssalMasterWaveCount", Arrays.asList(new Integer[] {0, 0, 0, 0, 1, 0, 0, 2}), s -> s instanceof Integer);
        config.pop();
    }
}
