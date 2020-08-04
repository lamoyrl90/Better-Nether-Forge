package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.world.gen.feature.WallFeature;

import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BrownMushroomWallFeature extends WallFeature {

	public BrownMushroomWallFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
		this.plantBlock = BNBlocks.WALL_MUSHROOM_BROWN.get();
	}

}
