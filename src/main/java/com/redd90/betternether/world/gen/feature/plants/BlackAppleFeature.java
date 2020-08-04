package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.BNPlantBlock;
import com.redd90.betternether.registry.BNBlocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class BlackAppleFeature extends ScatteredPlantFeature {

	public BlackAppleFeature(Codec<NoFeatureConfig> codec) {
		super(BNBlocks.BLACK_APPLE.get(), BNPlantBlock.AGE, 4, codec);
	}
	
}
