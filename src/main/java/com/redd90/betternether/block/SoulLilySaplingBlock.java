package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.item.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class SoulLilySaplingBlock extends BNSaplingBlock {
	public SoulLilySaplingBlock()
	{
		super(BNBlocks.SOUL_LILY.get(), DyeColor.ORANGE);
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		BlockState ground = world.getBlockState(pos.down());
		return BlocksHelper.isSoulSand(ground) || ground.getBlock() == BNBlocks.SOUL_FARMLAND.get();
	}
}
