package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.BNPlantBlock;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class NetherWartFeature extends ScatteredPlantFeature {

	public NetherWartFeature(Codec<NoFeatureConfig> codec) {
		super(Blocks.NETHER_WART, BNPlantBlock.AGE, 4, codec);
	}
	
}
