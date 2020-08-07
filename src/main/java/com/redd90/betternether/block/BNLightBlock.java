package com.redd90.betternether.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNLightBlock extends BNBlock {

	public BNLightBlock(Properties properties) {
		super(properties);
	}

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }
}
