package com.redd90.betternether;


import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.config.ModConfig;

@EventBusSubscriber (modid = BetterNether.MODID, bus = EventBusSubscriber.Bus.MOD)
public class BNConfig {

	
	public static final ForgeConfigSpec COMMON_SPEC;
	public static final CommonConfig COMMON;
	
	public static boolean NetherSpawn;
	
	static {
		final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		COMMON_SPEC = specPair.getRight();
		COMMON = specPair.getLeft();
	}
	
	public static void bakeConfig() {
		NetherSpawn = COMMON.NetherSpawn.get();
	}
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
		if (event.getConfig().getSpec() == BNConfig.COMMON_SPEC) {
			bakeConfig();
		}
	}
	
	public static class CommonConfig{
		public final ForgeConfigSpec.BooleanValue NetherSpawn;
		
		public CommonConfig(ForgeConfigSpec.Builder builder) {
			NetherSpawn = builder
					.translation(BetterNether.MODID + ".config.NetherSpawn")
					.define("Spawn in the Nether instead of the Overworld", true);
		}
	}
	
}
