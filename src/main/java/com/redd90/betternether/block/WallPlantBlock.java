package com.redd90.betternether.block;

import java.util.EnumMap;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WallPlantBlock extends BNBlock {
	private static final EnumMap<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(ImmutableMap.of(
			Direction.NORTH, Block.makeCuboidShape(2, 2, 10, 14, 14, 16),
			Direction.SOUTH, Block.makeCuboidShape(2, 2, 0, 14, 14, 6),
			Direction.WEST, Block.makeCuboidShape(10, 2, 2, 16, 14, 14),
			Direction.EAST, Block.makeCuboidShape(0, 2, 2, 6, 14, 14)));
	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	
	public WallPlantBlock(MaterialColor color)
	{
		super(BNBlockProperties.plant(color, false));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(FACING);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return BOUNDING_SHAPES.get(state.get(FACING));
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		Direction direction = (Direction) state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		BlockState blockState = world.getBlockState(blockPos);
		return blockState.isSolidSide(world, blockPos, direction);
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
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState blockState = this.getDefaultState();
		IWorldReader worldView = ctx.getWorld();
		BlockPos blockPos = ctx.getPos();
		Direction[] directions = ctx.getNearestLookingDirections();
		for(int i = 0; i < directions.length; ++i) 
		{
			Direction direction = directions[i];
			if (direction.getAxis().isHorizontal())
			{
				Direction direction2 = direction.getOpposite();
				blockState = blockState.with(FACING, direction2);
				if (blockState.isValidPosition(worldView, blockPos))
				{
					return blockState;
				}
			}
		}
		return null;
	}
}
