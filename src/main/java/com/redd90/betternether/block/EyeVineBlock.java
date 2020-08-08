package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class EyeVineBlock extends BNBlock {

	protected static final VoxelShape SHAPE = Block.makeCuboidShape(4, 0, 4, 12, 16, 12);
	
	public EyeVineBlock() {
		super(BNBlockProperties.vine(MaterialColor.RED));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}

	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		return SHAPE;
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
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		Block up = world.getBlockState(pos.up()).getBlock();
		Block down = world.getBlockState(pos.down()).getBlock();
		if (up != this && up != Blocks.NETHERRACK)
			return Blocks.AIR.getDefaultState();
		else if (down != this && !(down instanceof EyeBaseBlock))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}
	
	@Override
	public ItemStack getItem(IBlockReader world, BlockPos pos, BlockState state)
	{
		return new ItemStack(BNBlocks.EYE_SEED.get());
	}
}
