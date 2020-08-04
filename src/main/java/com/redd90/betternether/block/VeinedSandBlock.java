package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class VeinedSandBlock extends BNBlock {
	public VeinedSandBlock()
	{
		super(Block.Properties.create(Material.SAND, MaterialColor.BROWN)
				.sound(SoundType.SOUL_SAND)
				.hardnessAndResistance(0.5F, 0.5F));
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (world.getBlockState(pos.up()).getBlock() == BNBlocks.SOUL_VEIN.get())
			return state;
		else
			return Blocks.SOUL_SAND.getDefaultState();
	}
}
