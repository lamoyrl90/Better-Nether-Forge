package com.redd90.betternether.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class RubeusConeBlock extends BNBlock {

	private static final VoxelShape SHAPE = Block.makeCuboidShape(3, 3, 3, 13, 16, 13);
	
	public RubeusConeBlock() {
		super(BNBlockProperties.stalagnate());
		this.properties.notSolid();
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPE;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return world.getBlockState(pos.up()).isSolidSide(world, pos.up(), Direction.DOWN);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (isValidPosition(state, world, pos))
			return state;
		else
			return Blocks.AIR.getDefaultState();
	}
	
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 15;
    }
	
}
