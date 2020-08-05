package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
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

public class EyeFeature extends Feature<NoFeatureConfig> implements IGrowableFeature {
	
	
	public EyeFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public void generate(IWorld world, BlockPos pos, Random random)
	{
		int h = random.nextInt(19) + 5;
		int h2 = BlocksHelper.downRay(world, pos, h);
		
		if (h2 < 5)
			return;
		
		h2 -= 1;
		
		BlockState vineState = BNBlocks.EYE_VINE.get().getDefaultState();
		BlockState eyeState = random.nextBoolean() ? BNBlocks.EYEBALL.get().getDefaultState() : BNBlocks.EYEBALL_SMALL.get().getDefaultState();
		
		for (int y = 0; y < h2; y++)
			BlocksHelper.setWithoutUpdate(world, pos.down(y), vineState);
		
		BlocksHelper.setWithoutUpdate(world, pos.down(h2), eyeState);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (BNBlocks.EYE_SEED.get().isValidPosition(BNBlocks.EYE_SEED.get().getDefaultState(), world, pos)) {
			generate(world, pos, random);
			return true;
		} return false;
	}
}
