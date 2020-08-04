package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

public class StalagnateSaplingBlock extends BNSaplingBlock {

	protected static final VoxelShape SHAPE_TOP = Block.makeCuboidShape(4, 6, 4, 12, 16, 12);
	protected static final VoxelShape SHAPE_BOTTOM = Block.makeCuboidShape(4, 0, 4, 12, 12, 12);
	public static final BooleanProperty TOP = BooleanProperty.create("top");
	
	public StalagnateSaplingBlock() {
		super(BNBlocks.STALAGNATE.get(), MaterialColor.CYAN_TERRACOTTA);
		this.setDefaultState(getStateContainer().getBaseState().with(TOP, true));
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
        stateManager.add(TOP);
    }
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState blockState = this.getDefaultState();
		if (ctx.getFace() == Direction.DOWN)
			return blockState;
		else if (ctx.getFace() == Direction.UP)
			return blockState.with(TOP, false);
		else
			return null;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return BlocksHelper.isNetherGround(world.getBlockState(pos.down())) || BlocksHelper.isNetherGround(world.getBlockState(pos.up()));
	}
	
	public VoxelShape getOutlineShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return state.get(TOP).booleanValue() ? SHAPE_TOP : SHAPE_BOTTOM;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (state.get(TOP).booleanValue())
		{
			if (BlocksHelper.isNetherGround(world.getBlockState(pos.up())))
				return state;
			else
				return Blocks.AIR.getDefaultState();
		}
		else
		{
			if (BlocksHelper.isNetherGround(world.getBlockState(pos.down())))
				return state;
			else
				return Blocks.AIR.getDefaultState();
		}
	}
	
}
