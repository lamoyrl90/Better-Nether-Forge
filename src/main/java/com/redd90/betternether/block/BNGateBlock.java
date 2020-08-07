package com.redd90.betternether.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceGateBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNGateBlock extends FenceGateBlock implements IRenderTypeable {

	public BNGateBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BNRenderLayer getRenderLayer() {
		return BNRenderLayer.SOLID;
	}

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 0;
    }
}
