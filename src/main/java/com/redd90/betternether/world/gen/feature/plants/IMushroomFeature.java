package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public interface IMushroomFeature {
	public void grow(IWorld world, BlockPos pos, Random random);
}
