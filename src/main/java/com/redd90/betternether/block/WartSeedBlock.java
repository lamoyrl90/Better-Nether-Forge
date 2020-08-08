package com.redd90.betternether.block;

import java.util.EnumMap;
import java.util.Random;

import com.google.common.collect.Maps;
import com.redd90.betternether.util.BlocksHelper;
import com.redd90.betternether.world.gen.feature.plants.WartTreeFeature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.IGrowable;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

public class WartSeedBlock extends BNBlock implements IGrowable {

	private static final EnumMap<Direction, VoxelShape> BOUNDING_SHAPES = Maps.newEnumMap(Direction.class);
	private static final WartTreeFeature STRUCTURE = new WartTreeFeature(NoFeatureConfig.field_236558_a_);
	public static final DirectionProperty FACING = BlockStateProperties.FACING;
	
	static
	{
		BOUNDING_SHAPES.put(Direction.UP, VoxelShapes.create(0.25, 0.0, 0.25, 0.75, 0.5, 0.75));
		BOUNDING_SHAPES.put(Direction.DOWN, VoxelShapes.create(0.25, 0.5, 0.25, 0.75, 1.0, 0.75));
		BOUNDING_SHAPES.put(Direction.NORTH, VoxelShapes.create(0.25, 0.25, 0.5, 0.75, 0.75, 1.0));
		BOUNDING_SHAPES.put(Direction.SOUTH, VoxelShapes.create(0.25, 0.25, 0.0, 0.75, 0.75, 0.5));
		BOUNDING_SHAPES.put(Direction.WEST, VoxelShapes.create(0.5, 0.25, 0.25, 1.0, 0.75, 0.75));
		BOUNDING_SHAPES.put(Direction.EAST, VoxelShapes.create(0.0, 0.25, 0.25, 0.5, 0.75, 0.75));
	}
	
	public WartSeedBlock()
	{
		super(BNBlockProperties.netherWart());
		this.properties.tickRandomly().notSolid();
		this.setDefaultState(getStateContainer().getBaseState().with(FACING, Direction.UP));
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
	public BlockState getStateForPlacement(BlockItemUseContext ctx)
	{
		BlockState blockState = this.getDefaultState();
		IWorldReader worldView = ctx.getWorld();
		BlockPos blockPos = ctx.getPos();
		Direction[] directions = ctx.getNearestLookingDirections();
		for(int i = 0; i < directions.length; ++i) 
		{
			Direction direction = directions[i];
			Direction direction2 = direction.getOpposite();
			blockState = (BlockState) blockState.with(FACING, direction2);
			if (blockState.isValidPosition(worldView, blockPos))
			{
				return blockState;
			}
		}
		return null;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		Direction direction = (Direction) state.get(FACING);
		BlockPos blockPos = pos.offset(direction.getOpposite());
		return hasEnoughSolidSide(world, blockPos, direction) || world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND;
	}

	@Override
	public boolean canGrow(IBlockReader world, BlockPos pos, BlockState state, boolean isClient)
	{
		Direction direction = (Direction) state.get(FACING);
		return direction == Direction.UP && world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND;
	}

	@Override
	public boolean canUseBonemeal(World world, Random random, BlockPos pos, BlockState state)
	{
		return random.nextInt(16) == 0;
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state)
	{
		STRUCTURE.grow(world, pos, random);
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
		if (!isValidPosition(state, world, pos))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}

}
