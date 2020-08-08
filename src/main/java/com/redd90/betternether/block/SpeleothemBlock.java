package com.redd90.betternether.block;

import javax.annotation.Nullable;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class SpeleothemBlock extends BNBlock {
	public static final IntegerProperty SIZE = IntegerProperty.create("size", 0, 7);
	private static final Mutable POS = new Mutable();
	private static final VoxelShape[] SHAPES;
	
	public SpeleothemBlock(AbstractBlock.Properties properties)
	{
		super(properties);
		this.properties.notSolid();
		this.setDefaultState(getStateContainer().getBaseState().with(SIZE, 0));
	}
	
	@Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
        stateManager.add(SIZE);
    }
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPES[state.get(SIZE)];
	}
	
	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack)
	{
		if (world.getBlockState(pos.down()).getBlock() instanceof SpeleothemBlock)
		{
			POS.setX(pos.getX());
			POS.setZ(pos.getZ());
			for (int i = 1; i < 8; i++)
			{
				POS.setY(pos.getY() - i);
				if (world.getBlockState(POS).getBlock() instanceof SpeleothemBlock)
				{
					BlockState state2 = world.getBlockState(POS);
					int size = state2.get(SIZE);
					if (size < i)
					{
						world.setBlockState(POS, state2.with(SIZE, i));
					}
					else
						break;
				}
				else
					break;
			}
		}
		if (world.getBlockState(pos.up()).getBlock() instanceof SpeleothemBlock)
		{
			POS.setX(pos.getX());
			POS.setZ(pos.getZ());
			for (int i = 1; i < 8; i++)
			{
				POS.setY(pos.getY() + i);
				if (world.getBlockState(POS).getBlock() instanceof SpeleothemBlock)
				{
					BlockState state2 = world.getBlockState(POS);
					int size = state2.get(SIZE);
					if (size < i)
					{
						world.setBlockState(POS, state2.with(SIZE, i));
					}
					else
						break;
				}
				else
					break;
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState aboveState = world.getBlockState(pos.up());
		BlockState belowState = world.getBlockState(pos.down());
		
		if(belowState.isSolid() || aboveState.isSolid()) {
			return true;
		}
		
		if(world.getBlockState(pos).isAir() && (belowState.getBlock() instanceof SpeleothemBlock || aboveState.getBlock() instanceof SpeleothemBlock)) {
			return true;
		}
		
		if (world.getBlockState(pos).getBlock() instanceof SpeleothemBlock) {
			if (!belowState.isSolid() && aboveState.getBlock() instanceof SpeleothemBlock) {
				if (aboveState.get(SIZE) >= world.getBlockState(pos).get(SIZE)) {
					return true;
				}
			}
			
			if (!aboveState.isSolid() && belowState.getBlock() instanceof SpeleothemBlock) {
				if (belowState.get(SIZE) >= world.getBlockState(pos).get(SIZE)) {
					return true;
				}
			}
		}
				
		return false;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (!isValidPosition(state, world, pos)) {
			return Blocks.AIR.getDefaultState();
		} else {
			return world.getBlockState(pos);
		}
		
	}
	
	static
	{
		SHAPES = new VoxelShape[8];
		for (int i = 0; i < 8; i++)
			SHAPES[i] = Block.makeCuboidShape(7 - i, 0, 7 - i, 9 + i, 16, 9 + i);
	}
}
