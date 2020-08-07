package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.LumabusVineBlock;
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

public class LumabusVineFeature extends Feature<NoFeatureConfig> {

	public LumabusVineFeature(Codec<NoFeatureConfig> p_i231953_1_) {
		super(p_i231953_1_);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		if (world.getBlockState(pos.up()).isSolid()) {
			generate(world, pos, random);
			return true;
		} return false;
	}
	
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		int h = random.nextInt(19) + 5;
		int h2 = BlocksHelper.downRay(world, pos, h);
		h2 -= 2;
		
		if (h2 < 3)
			return;
		
		BlockState vineState = BNBlocks.LUMABUS_VINE.get().getDefaultState().with(LumabusVineBlock.SHAPE, TripleShape.MIDDLE);
		
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.LUMABUS_VINE.get().getDefaultState());
		
		for (int y = 1; y < h2; y++)
			BlocksHelper.setWithoutUpdate(world, pos.down(y), vineState);
		
		BlocksHelper.setWithoutUpdate(world, pos.down(h2), BNBlocks.LUMABUS_VINE.get().getDefaultState().with(LumabusVineBlock.SHAPE, TripleShape.BOTTOM));
	}

}
