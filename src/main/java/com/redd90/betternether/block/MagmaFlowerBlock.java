package com.redd90.betternether.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;

public class MagmaFlowerBlock extends BNPlantBlock {

	private static final VoxelShape SHAPE = Block.makeCuboidShape(1, 0, 1, 15, 12, 15);
	
	public MagmaFlowerBlock()
	{
		super(MaterialColor.ORANGE_TERRACOTTA, true);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).getBlock() == Blocks.MAGMA_BLOCK;
	}

}
