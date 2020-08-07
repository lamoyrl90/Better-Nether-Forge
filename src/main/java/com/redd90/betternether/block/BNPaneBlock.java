package com.redd90.betternether.block;

import net.minecraft.block.BlockState;
import net.minecraft.block.PaneBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNPaneBlock extends PaneBlock implements IRenderTypeable {
	
	private BNRenderLayer layer = BNRenderLayer.CUTOUT;
	
	public BNPaneBlock(Properties properties) {
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
