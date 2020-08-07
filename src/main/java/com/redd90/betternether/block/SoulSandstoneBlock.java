package com.redd90.betternether.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class SoulSandstoneBlock extends BNBlock {
	public static final BooleanProperty UP = BooleanProperty.create("up");
	
	public SoulSandstoneBlock()
	{
		super(BNBlockProperties.SOUL_SANDSTONE);
		this.setDefaultState(this.getStateContainer().getBaseState().with(UP, true));
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(UP);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		return state.with(UP, world.getBlockState(pos.up()).getBlock() != this);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		return this.getDefaultState().with(UP, ctx.getWorld().getBlockState(ctx.getPos().up()).getBlock() != this);
	}
	
}
