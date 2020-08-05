package com.redd90.betternether.compatibility;

import com.redd90.betternether.registry.BNBiomes;

import biomesoplenty.api.enums.BOPClimates;

public class BiomesoPlenty {
	public static void registerBiomesToBOP() {
		BOPClimates.NETHER.addBiome(5, BNBiomes.FUNGAL_WOODLANDS.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.OLD_FUNGIWOODS.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.GRAVEL_DESERT.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.HADEAN_JUNGLE.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.SWELTERING_SWAMPLAND.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.TORRID_TERRACES.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.NETHER_GRASSLANDS.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.OBSIDIAN_CHASM.get());	
	}
}
