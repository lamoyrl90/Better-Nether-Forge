package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.WartSeedBlock;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class WartBushFeature extends Feature<NoFeatureConfig> {
	
	private static final Direction[] DIRS = new Direction[] {Direction.UP, Direction.NORTH, Direction.SOUTH, Direction.EAST, Direction.WEST};
		
	public WartBushFeature(Codec<NoFeatureConfig> p_i231953_1_) {
		super(p_i231953_1_);
	}

	
	
	public boolean generate(IWorld world, BlockPos pos, Random random)
	{
		if (world.isAirBlock(pos) && world.getBlockState(pos.down()).isSolid())
		{
			BlocksHelper.setWithoutUpdate(world, pos, Blocks.NETHER_WART_BLOCK.getDefaultState());
			for (Direction dir: DIRS)
				setSeed(world, pos, dir);
			return true;
		} return false;
	}
	
	private void setSeed(IWorld world, BlockPos pos, Direction dir)
	{
		BlockPos p = pos.offset(dir);
		if (world.isAirBlock(p))
			BlocksHelper.setWithoutUpdate(world, p, BNBlocks.WART_SEED.get().getDefaultState().with(WartSeedBlock.FACING, dir));
	}


	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return generate(world, pos, random);
	}
}
