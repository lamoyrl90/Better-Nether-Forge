package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.registry.BNBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class MushroomFirBlock extends BNBlock{
	public static final EnumProperty<MushroomFirShape> SHAPE = EnumProperty.create("shape", MushroomFirShape.class);
	
	private static final VoxelShape BOTTOM_SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 16, 12);
	private static final VoxelShape MIDDLE_SHAPE = Block.makeCuboidShape(5, 0, 5, 11, 16, 11);
	private static final VoxelShape TOP_SHAPE = Block.makeCuboidShape(6, 0, 6, 10, 16, 10);
	private static final VoxelShape SIDE_BIG_SHAPE = Block.makeCuboidShape(0.01, 0.01, 0.01, 15.99, 13, 15.99);
	private static final VoxelShape SIDE_SMALL_N_SHAPE = Block.makeCuboidShape(4, 1, 0, 12, 8, 8);
	private static final VoxelShape SIDE_SMALL_S_SHAPE = Block.makeCuboidShape(4, 1, 8, 12, 8, 16);
	private static final VoxelShape SIDE_SMALL_E_SHAPE = Block.makeCuboidShape(8, 1, 4, 16, 8, 12);
	private static final VoxelShape SIDE_SMALL_W_SHAPE= Block.makeCuboidShape(0, 1, 4, 8, 8, 12);
	private static final VoxelShape END_SHAPE = Block.makeCuboidShape(0.01, 0, 0.01, 15.99, 15.99, 15.99);
	
	public MushroomFirBlock()
	{
		super(BNBlockProperties.mushroomFir().notSolid().tickRandomly());
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(SHAPE);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		if (state.get(SHAPE) == MushroomFirShape.BOTTOM || state.get(SHAPE) == MushroomFirShape.MIDDLE || state.get(SHAPE) == MushroomFirShape.TOP){
			return true;
		} else {
			for (int i=0; i<4;i++) {
				if (world.getBlockState(pos.offset(Direction.byHorizontalIndex(i))).getBlock() == BNBlocks.MUSHROOM_FIR.get()) {
					return true;
				}
			}
			
			if (world.getBlockState(pos.down()).getBlock() == BNBlocks.MUSHROOM_FIR.get())
				return true;
		}
		
		return false;
	}
	
	@Override
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!isValidPosition(state, worldIn, pos))
			worldIn.destroyBlock(pos, true);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		switch (state.get(SHAPE))
		{
		case BOTTOM:
			return BOTTOM_SHAPE;
		case END:
			return END_SHAPE;
		case MIDDLE:
		default:
			return MIDDLE_SHAPE;
		case SIDE_BIG_E:
		case SIDE_BIG_N:
		case SIDE_BIG_S:
		case SIDE_BIG_W:
			return SIDE_BIG_SHAPE;
		case SIDE_SMALL_E:
			return SIDE_SMALL_E_SHAPE;
		case SIDE_SMALL_N:
			return SIDE_SMALL_N_SHAPE;
		case SIDE_SMALL_S:
			return SIDE_SMALL_S_SHAPE;
		case SIDE_SMALL_W:
			return SIDE_SMALL_W_SHAPE;
		case TOP:
			return TOP_SHAPE;
		}
	}
	
	public enum MushroomFirShape implements IStringSerializable
	{
		BOTTOM("bottom"),
		MIDDLE("middle"),
		TOP("top"),
		SIDE_BIG_N("side_big_n"),
		SIDE_BIG_S("side_big_s"),
		SIDE_BIG_E("side_big_e"),
		SIDE_BIG_W("side_big_w"),
		SIDE_SMALL_N("side_small_n"),
		SIDE_SMALL_S("side_small_s"),
		SIDE_SMALL_E("side_small_e"),
		SIDE_SMALL_W("side_small_w"),
		END("end");
		
		final String name;

		MushroomFirShape(String name)
		{
			this.name = name;
		}

		@Override
		public String getString()
		{
			return name;
		}

		@Override
		public String toString()
		{
			return name;
		}
	}
	
	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		MushroomFirShape shape = state.get(SHAPE);
		return shape == MushroomFirShape.BOTTOM || shape == MushroomFirShape.MIDDLE ? new ItemStack(BNBlocks.MUSHROOM_FIR_STEM.get()) : new ItemStack(BNBlocks.MUSHROOM_FIR_SAPLING.get());
	}
}
