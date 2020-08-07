package com.redd90.betternether.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.DoorBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNDoorBlock extends DoorBlock implements IRenderTypeable {

	public BNDoorBlock(Properties builder) {
		super(builder);
	}

	@Override
	public BNRenderLayer getRenderLayer() {
		return BNRenderLayer.CUTOUT;
	}

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 0;
    }
}
