package com.redd90.betternether.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.FenceBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNFenceBlock extends FenceBlock implements IRenderTypeable {

	public BNFenceBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BNRenderLayer getRenderLayer() {
		return BNRenderLayer.SOLID;
	}
}
