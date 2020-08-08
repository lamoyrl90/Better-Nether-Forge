package com.redd90.betternether;


import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber (modid = BetterNether.MODID, bus = EventBusSubscriber.Bus.MOD)
public class BNConfig {

	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final CommonConfig COMMON;
	
	public static Boolean NetherSpawn;
	public static Integer GlobalPlantCount;
	public static Integer GlobalDecorationCount;
	public static Float WallFactor;
	public static Float ScatteredStructureFrequency;
	public static Float LavaPyramidFrequency;
	public static Integer CitySeparation;
	//public static Float GhastHiveFrequency;
	
	static {
		final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	
	public static void bakeConfig() {
		NetherSpawn = COMMON.NetherSpawn.get();
		GlobalPlantCount = COMMON.GlobalPlantCount.get();
		GlobalDecorationCount = COMMON.GlobalDecorationCount.get();
		WallFactor = COMMON.WallFactor.get();
		ScatteredStructureFrequency = COMMON.ScatteredStructureFrequency.get();
		LavaPyramidFrequency = COMMON.LavaPyramidFrequency.get();
		CitySeparation = COMMON.CitySeparation.get();
	}
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
		if (event.getConfig().getSpec() == BNConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}
	
	public static class CommonConfig{
		public final ForgeConfigSpec.BooleanValue NetherSpawn;
		public final ConfigValue<Integer> GlobalPlantCount;
		public final ConfigValue<Integer> GlobalDecorationCount;
		public final ConfigValue<Float> WallFactor;
		public final ConfigValue<Float> ScatteredStructureFrequency;
		public final ConfigValue<Float> LavaPyramidFrequency;
		public final ConfigValue<Integer> CitySeparation;
		
		public CommonConfig(ForgeConfigSpec.Builder builder) {
			NetherSpawn = builder
					.translation(BetterNether.MODID + ".config.NetherSpawn")
					.define("Spawn in Nether", true);
			
			GlobalPlantCount = builder
					.translation(BetterNether.MODID + ".config.GlobalPlantCount")
					.define("Global Plant Count", 128);
			
			GlobalDecorationCount = builder
					.translation(BetterNether.MODID + ".config.GlobalDecorationCount")
					.define("Global Decoration Count", 64);
			
			WallFactor = builder
					.translation(BetterNether.MODID + ".config.WallFactor")
					.define("Extra factor for wall features", 4.0f);
			
			ScatteredStructureFrequency = builder
					.translation(BetterNether.MODID + ".config.ScatteredStructureFrequency")
					.define("Scattered Structure Frequency", 0.08f);

			LavaPyramidFrequency = builder
					.translation(BetterNether.MODID + ".config.LavaPyramidFrequency")
					.define("Lava Pyramid Frequency", 0.01f);
			
			CitySeparation = builder
					.translation(BetterNether.MODID + ".config.LavaPyramidFrequency")
					.define("Separation of cities", 64);

		}
	}
	
}
