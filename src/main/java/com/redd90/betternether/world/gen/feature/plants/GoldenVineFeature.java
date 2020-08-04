package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.world.gen.feature.NoFeatureConfig;

public class GoldenVineFeature extends VineFeature {

	public GoldenVineFeature(Codec<NoFeatureConfig> codec) {
		super(BNBlocks.GOLDEN_VINE.get(), codec);
	}

}
