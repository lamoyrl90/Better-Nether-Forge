package com.redd90.betternether.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNBlock extends Block implements IRenderTypeable {


	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	public BNBlock(Properties properties) {
		super(properties);
	}

	public void setRenderLayer(BNRenderLayer layer)
	{
		this.layer = layer;
	}
	
	@Override
	public BNRenderLayer getRenderLayer() {
		return layer;
	}

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 0;
    }
}
