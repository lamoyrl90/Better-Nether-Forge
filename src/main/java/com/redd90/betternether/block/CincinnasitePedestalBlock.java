package com.redd90.betternether.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class CincinnasitePedestalBlock extends BNBlock {

	private static final VoxelShape SHAPE = Block.makeCuboidShape(2, 0, 2, 14, 16, 14);
	
	public CincinnasitePedestalBlock(Properties properties) {
		super(properties.notSolid());
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}
}
