package com.redd90.betternether.compatibility;

import com.redd90.betternether.registry.BNBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.block.Block;

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
		BOPClimates.NETHER.addBiome(5, BNBiomes.BONE_REEF.get());	
		BOPClimates.NETHER.addBiome(5, BNBiomes.OLD_WARPED_WOODS.get());
		BOPClimates.NETHER.addBiome(5, BNBiomes.WART_FOREST.get());		
		BOPClimates.NETHER.addBiome(5, BNBiomes.SOUL_PLAINS.get());		
		BOPClimates.NETHER.addBiome(5, BNBiomes.CRIMSON_GLOWING_WOODS.get());		
		
		//BOPBiomes.undergrowth.get().addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(BNEntities.FIREFLY.get(), 50, 2, 4));
	
	}
	
	public static Block getBurningBlossom() {
		return BOPBlocks.burning_blossom;
	}
}
