package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BloomingVineFeature extends VineFeature {

	public BloomingVineFeature(Codec<NoFeatureConfig> codec) {
		super(BNBlocks.BLOOMING_VINE.get(), codec);
	}

}