package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BlackVineFeature extends VineFeature {

	public BlackVineFeature(Codec<NoFeatureConfig> codec) {
		super(BNBlocks.BLACK_VINE.get(), codec);
	}

}