package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.BNPlantBlock;
import com.redd90.betternether.registry.BNBlocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FeatherFernFeature extends ScatteredPlantFeature {

	public FeatherFernFeature(Codec<NoFeatureConfig> codec) {
		super(BNBlocks.FEATHER_FERN.get(), BNPlantBlock.AGE, 4, codec);
	}
	
}
