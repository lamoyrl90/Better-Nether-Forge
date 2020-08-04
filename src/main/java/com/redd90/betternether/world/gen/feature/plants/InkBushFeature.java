package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.BNPlantBlock;
import com.redd90.betternether.registry.BNBlocks;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class InkBushFeature extends ScatteredPlantFeature {

	public InkBushFeature(Codec<NoFeatureConfig> codec) {
		super(BNBlocks.INK_BUSH.get(), BNPlantBlock.AGE, 4, codec);
	}
	
}
