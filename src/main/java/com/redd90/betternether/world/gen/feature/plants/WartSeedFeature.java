package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.world.gen.feature.ScatteredFeature;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class WartSeedFeature extends ScatteredFeature {

	public WartSeedFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
		this.blockstate = BNBlocks.WART_SEED.get().getDefaultState();
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean canPlaceAt(IWorld world, BlockPos pos) {
		return blockstate.getBlock().isValidPosition(blockstate, world, pos) && world.isAirBlock(pos);
	}
	
}
