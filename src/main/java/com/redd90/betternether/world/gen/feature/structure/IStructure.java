package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public interface IStructure
{
	public boolean generate(IWorld world, BlockPos pos, Random random);
}
