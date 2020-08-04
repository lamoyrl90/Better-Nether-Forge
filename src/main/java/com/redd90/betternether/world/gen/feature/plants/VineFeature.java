package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.BNVineBlock;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public abstract class VineFeature extends Feature<NoFeatureConfig> {

	private Mutable blockPos = new Mutable();
	
	private Block block;
	
	public VineFeature(Block block, Codec<NoFeatureConfig> codec) {
		super(codec);
		this.block = block;
	}

	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator chunkGenerator,
			Random rand, BlockPos pos, NoFeatureConfig config) {
		return shouldGenerate(worldIn, rand, pos);
	}

	public boolean shouldGenerate(IWorld world, Random random, BlockPos pos) {
		if (this.isValidPosition(world, pos)) {
			generate(world, pos, random);
			return true;
		} else {
			return false;
		}
	}
	
	@SuppressWarnings("deprecation")
	private boolean isValidPosition(IWorld world, BlockPos pos) {
		if (this.block.isValidPosition(this.block.getDefaultState(), world, pos) && world.getBlockState(pos).isAir()
				&& !(world.getBlockState(pos.up()).getBlock() instanceof BNVineBlock)) {
			return true;
		} else return false;
	}
	
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		int h = BlocksHelper.downRay(world, pos, 25);
		if (h < 2)
			return;
		h = random.nextInt(h) + 1;
		
		BlockState bottom = block.getDefaultState().with(BNVineBlock.BOTTOM, true).with(BNVineBlock.AGE, random.nextInt(15));
		BlockState middle = block.getDefaultState().with(BNVineBlock.BOTTOM, false);
		
		blockPos.setPos(pos);
		for (int y = 0; y < h; y++)
		{
			blockPos.setY(pos.getY() - y);
			if (world.isAirBlock(blockPos.down()))
				BlocksHelper.setWithoutUpdate(world, blockPos, middle);
			else
			{
				BlocksHelper.setWithoutUpdate(world, blockPos, bottom);
				return;
			}
		}
		BlocksHelper.setWithoutUpdate(world, blockPos.down(), bottom);
	}
	
}
