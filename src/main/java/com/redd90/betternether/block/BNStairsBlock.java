package com.redd90.betternether.block;

import java.util.function.Supplier;

import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNStairsBlock extends StairsBlock implements IRenderTypeable {

	public BNStairsBlock(Supplier<BlockState> state, Properties properties) {
		super(state, properties);
	}

	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	
	
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
