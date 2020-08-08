package com.redd90.betternether.block;

import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class WillowLeavesBlock extends BNBlock {
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	public static final BooleanProperty NATURAL = BooleanProperty.create("natural");
	
	public WillowLeavesBlock()
	{
		super(BNBlockProperties.willowLeaves());
		this.setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.UP).with(NATURAL, true));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(FACING, NATURAL);
	}
	
	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		return this.getDefaultState().with(NATURAL, false);
	}
	
	@Override
	public BlockState rotate(BlockState state, Rotation rotation)
	{
		return BlocksHelper.rotateHorizontal(state, rotation, FACING);
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror)
	{
		return BlocksHelper.mirrorHorizontal(state, mirror, FACING);
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (state.get(NATURAL) && world.isAirBlock(pos.offset(state.get(FACING).getOpposite())))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}
	
	
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader view, BlockPos pos)
	{
		return true;
	}
	
	@Override
	public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos)
	{
		return VoxelShapes.empty();
	}
}
