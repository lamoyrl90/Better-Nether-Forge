package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.StalagnateBlock;
import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class StalagnateFeature extends Feature<NoFeatureConfig> {
	
	public static final int MAX_LENGTH = 25; // 27
	public static final int MIN_LENGTH = 3; // 5
		
	public StalagnateFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public void generate(IWorld world, BlockPos pos, Random random)
	{
		int length = BlocksHelper.upRay(world, pos, MAX_LENGTH);
		if (length > MIN_LENGTH && BlocksHelper.isNetherrack(world.getBlockState(pos.up(length + 1))))
		{
			BlockState bottom = BNBlocks.STALAGNATE.get().getDefaultState().with(StalagnateBlock.SHAPE, TripleShape.BOTTOM);
			BlockState middle = BNBlocks.STALAGNATE.get().getDefaultState();
			BlockState top = BNBlocks.STALAGNATE.get().getDefaultState().with(StalagnateBlock.SHAPE, TripleShape.TOP);
			
			BlocksHelper.setWithoutUpdate(world, pos, bottom);
			BlocksHelper.setWithoutUpdate(world, pos.up(length), top);
			for (int y = 1; y < length; y++)
				BlocksHelper.setWithoutUpdate(world, pos.up(y), middle);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (BNBlocks.STALAGNATE.get().isValidPosition(BNBlocks.STALAGNATE.get().getDefaultState(), world, pos)){
			generate(world, pos, random);
			return true;
		} return false;
	}
}
