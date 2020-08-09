package com.redd90.betternether.world.gen.feature.plants;

import com.mojang.serialization.Codec;
import com.redd90.betternether.world.gen.feature.ScatteredFeature;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class WarpedRootsFeature extends ScatteredFeature {

	public WarpedRootsFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
		this.blockstate = Blocks.WARPED_ROOTS.getDefaultState();
	}

	public boolean canPlaceAt(IWorld world, BlockPos pos)
	{
		return blockstate.isValidPosition(world, pos);
	}
	
}
