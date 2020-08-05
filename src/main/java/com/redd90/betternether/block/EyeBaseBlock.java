package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;

public class EyeBaseBlock extends BNBlock {
	
	public EyeBaseBlock(Block.Properties settings)
	{
		super(settings);
	}
	
	public boolean canCreatureSpawn(BlockState state, IBlockReader view, BlockPos pos, PlacementType type, EntityType<?> entitytype)
	{
		return false;
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		BlockPos blockPos = pos.up();
		Block up = world.getBlockState(blockPos).getBlock();
		if (up != BNBlocks.EYE_VINE.get() && up != Blocks.NETHERRACK)
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
