package com.redd90.betternether;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.fml.ModLoadingContext;

public class BNConfig {
	public static class CommonConfig {
		public static final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
		public static final ForgeConfigSpec spec;
		
		public static final ForgeConfigSpec.BooleanValue netherSpawn;
		public static final ForgeConfigSpec.BooleanValue lightLevel;
		public static final ForgeConfigSpec.DoubleValue globalPlantCount;
		public static final ForgeConfigSpec.DoubleValue wallFactor;
		public static final ForgeConfigSpec.DoubleValue globalDecorCount;
		public static final ForgeConfigSpec.DoubleValue scatteredStructureCount;
		public static final ForgeConfigSpec.DoubleValue lavaPyramidCount;
		public static final ConfigValue<Integer> easyLightLevel;
		public static final ConfigValue<Integer> normalLightLevel;
		public static final ConfigValue<Integer> hardLightLevel;
		public static final ForgeConfigSpec.BooleanValue spawnCincinnasite;
		public static final ConfigValue<Integer> cincinnasiteCount;
		public static final ConfigValue<Integer> cincinnasiteSize;
		public static final ConfigValue<Integer> cincinnasiteMinY;
		public static final ConfigValue<Integer> cincinnasiteMaxY;
		public static final ForgeConfigSpec.BooleanValue spawnRuby;
		public static final ConfigValue<Integer> rubyCount;
		public static final ConfigValue<Integer> rubySize;
		public static final ConfigValue<Integer> rubyMinY;
		public static final ConfigValue<Integer> rubyMaxY;
		
		static {
			builder.push("Game Settings");
				netherSpawn = builder.comment("Spawn and respawn in the Nether").define("nether_spawn", false);
				lightLevel = builder.comment("Light level affects Nether monster spawning").define("light_level", false);
				builder.push("Light level difficulty settings - only works if light_level is true");
					easyLightLevel = builder.comment("Light level for easy spawning").defineInRange("easy_light_level", 7, 0, 15);
					normalLightLevel = builder.comment("Light level for normal spawning").defineInRange("normal_light_level", 9, 0, 15);
					hardLightLevel = builder.comment("Light level for hard spawning").defineInRange("hard_light_level", 11, 0, 15);
				builder.pop();
			builder.pop();
			
			builder.push("Worldgen Settings");
				globalPlantCount = builder.comment("Base number of plants generated. Higher == more").defineInRange("global_plant_count",  128d, 0, 10000);
				wallFactor = builder.comment("Extra factor applied to wall decorations").defineInRange("wall_factor",  4d, 0, 10000);
				globalDecorCount = builder.comment("Base number of decorations generated. Higher == more").defineInRange("global_decor_count",  64d, 0, 10000);
				scatteredStructureCount = builder.comment("Average scattered structures per chunk").defineInRange("scattered_structure_count",  0.08d, 0, 100);
				lavaPyramidCount = builder.comment("Average lava pyramids per chunk").defineInRange("lava_pyramid_count",  0.01d, 0, 100);
				builder.push("Cincinnasite Ore");
					spawnCincinnasite = builder.comment("Should cincinnasite ore generate").define("generate_cincinnasite", true);
					cincinnasiteCount = builder.comment("Number of veins per chunk").define("cincinnasite_count", 12);
					cincinnasiteSize = builder.comment("Size of cincinnasize veins").define("cincinnasite_size", 14);
					cincinnasiteMinY = builder.comment("Minimum Y level that cincinnasite generates").defineInRange("cincinnasite_min_y", 10, 0, 128);
					cincinnasiteMaxY = builder.comment("Maximum Y level that cincinnasite generates").defineInRange("cincinnasite_max_y", 108, 0, 128);
				builder.pop();
				builder.push("Ruby Ore");
					spawnRuby = builder.comment("Should ruby ore generate").define("generate_cincinnasite", true);
					rubyCount = builder.comment("Number of veins per chunk").define("ruby_count", 2);
					rubySize = builder.comment("Size of ruby veins").define("ruby_size", 8);
					rubyMinY = builder.comment("Minimum Y level that ruby generates").defineInRange("ruby_min_y", 102, 0, 128);
					rubyMaxY = builder.comment("Maximum Y level that ruby generates").defineInRange("ruby_max_y", 123, 0, 128);
				builder.pop();
			builder.pop();
			
			spec = builder.build();
		}
	}
	
	public static void registerConfig() {
		ModLoadingContext.get().registerConfig(net.minecraftforge.fml.config.ModConfig.Type.COMMON, CommonConfig.spec, "betternether.toml");
	}
}
