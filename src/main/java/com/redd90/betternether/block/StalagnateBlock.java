package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.IGrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class StalagnateBlock extends StemBlock implements IGrowable {

	public static final EnumProperty<TripleShape> SHAPE = EnumProperty.create("shape", TripleShape.class);
	private static final int MAX_SIZE = 25;
	
	public StalagnateBlock() {
		super(BNBlockProperties.stalagnate());
		this.properties.tickRandomly().notSolid();
		this.setDefaultState(this.stateContainer.getBaseState().with(SHAPE, TripleShape.MIDDLE));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}

	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
        stateManager.add(SHAPE);
    }
	
	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return new ItemStack(BNBlocks.STALAGNATE_STEM.get());
	}
	
	public void tick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
		if (!state.isValidPosition(worldIn, pos)) {
			worldIn.destroyBlock(pos, true);
		}
	}
		
	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
		int up = getNumStalagnateBlocksAbove(worldIn, pos);
		int down = getNumStalagnateBlocksBelow(worldIn, pos);
		return BlocksHelper.isNetherGround(worldIn.getBlockState(pos.up(up+1))) || BlocksHelper.isNetherGround(worldIn.getBlockState(pos.down(down+1)));
	}
	
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		grow(worldIn, random, pos, state);
	}
	
	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
		if (!stateIn.isValidPosition(worldIn, currentPos)) {
			worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
		}
		
		if (BlocksHelper.isNetherGround(worldIn.getBlockState(currentPos.up()))) {
			return stateIn.with(SHAPE, TripleShape.TOP);
		} else if (BlocksHelper.isNetherGround(worldIn.getBlockState(currentPos.down()))) {
			return stateIn.with(SHAPE, TripleShape.BOTTOM);
		}

		return this.getDefaultState();
	}
	
	protected int getNumStalagnateBlocksAbove(IBlockReader worldIn, BlockPos pos) {
		int i;
		for(i = 0; i < MAX_SIZE && worldIn.getBlockState(pos.up(i + 1)).isIn(BNBlocks.STALAGNATE.get()); ++i) {
		}

		return i;
	}
	
	protected int getNumStalagnateBlocksBelow(IBlockReader worldIn, BlockPos pos) {
		int i;
		for(i = 0; i < MAX_SIZE && worldIn.getBlockState(pos.down(i + 1)).isIn(BNBlocks.STALAGNATE.get()); ++i) {
		}

		return i;
	}
	
	public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
		int i = this.getNumStalagnateBlocksAbove(worldIn, pos);
		int j = this.getNumStalagnateBlocksBelow(worldIn, pos);
		return i + j + 1 < MAX_SIZE;
	}
	
	public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state) {
		return false;
	}
	
	public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
		if (canGrow(worldIn, pos, state, !worldIn.isRemote)) {
			if (worldIn.isAirBlock(pos.up())) {
				addSegment(worldIn, pos, Direction.UP);
			} else if (worldIn.isAirBlock(pos.down())) {
				addSegment(worldIn, pos, Direction.DOWN);
			}
		}
	}
	
	private void addSegment(IWorld world, BlockPos pos, Direction dir) {
		BlockPos growingPos = pos.offset(dir);
		BlockPos wallCheckPos = pos.offset(dir, 2);
		boolean up = dir == Direction.UP ? true : false;
		TripleShape shape;
		if (up && BlocksHelper.isNetherGround(world.getBlockState(wallCheckPos))) {
			shape = TripleShape.TOP;
		} else if (!up && BlocksHelper.isNetherGround(world.getBlockState(wallCheckPos))){
			shape = TripleShape.BOTTOM;
		} else {
			shape = TripleShape.MIDDLE;
		}
		
		world.setBlockState(growingPos, this.getDefaultState().with(SHAPE, shape), 3);
		
	}
}
