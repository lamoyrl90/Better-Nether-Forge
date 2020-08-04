package com.redd90.betternether.world.gen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public interface IFeature {
	public void generate(IWorld world, BlockPos pos, Random random);
}
