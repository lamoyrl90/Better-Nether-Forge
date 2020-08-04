package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.world.gen.feature.WallFeature;

import net.minecraft.world.gen.feature.NoFeatureConfig;

public class WallMossFeature extends WallFeature {

	public WallMossFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
		this.plantBlock = BNBlocks.WALL_MOSS.get();
	}

}
