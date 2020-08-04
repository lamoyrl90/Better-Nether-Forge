package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.BNPlantBlock;
import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MagmaFlowerFeature extends ScatteredPlantFeature {

	public MagmaFlowerFeature(Codec<NoFeatureConfig> codec) {
		super(BNBlocks.MAGMA_FLOWER.get(), BNPlantBlock.AGE, 3, codec);
	}

}
