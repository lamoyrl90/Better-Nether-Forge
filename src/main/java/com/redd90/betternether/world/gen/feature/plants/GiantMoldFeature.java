package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.RedMushroomCapBlock;
import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;
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

public class GiantMoldFeature extends Feature<NoFeatureConfig> {

	Mutable npos = new Mutable();
	
	public GiantMoldFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public GiantMoldFeature() {
		this(NoFeatureConfig.field_236558_a_);
	}

	public void generate(IWorld world, BlockPos pos, Random random)
	{
		Block under;
		if (world.getBlockState(pos.down()).getBlock() == BNBlocks.NETHER_MYCELIUM.get())
		{
			for (int i = 0; i < 10; i++)
			{
				int x = pos.getX() + (int) (random.nextGaussian() * 2);
				int z = pos.getZ() + (int) (random.nextGaussian() * 2);
				int y = pos.getY() + random.nextInt(6);
				for (int j = 0; j < 16; j++)
				{
					npos.setPos(x, y - j, z);
					under = world.getBlockState(npos.down()).getBlock();
					if (under == BNBlocks.NETHER_MYCELIUM.get())
					{
						grow(world, npos, random);
					}
				}
			}
		}
	}

	public void grow(IWorld world, BlockPos pos, Random random)
	{
		int size = 2 + random.nextInt(6);
		for (int y = 1; y <= size; y++)
			if (!world.isAirBlock(pos.up(y)))
			{
				if (y == 1)
					return;
				size = y - 1;
				break;
			}
		BlockState middle = BNBlocks.GIANT_MOLD.get().getDefaultState().with(RedMushroomCapBlock.SHAPE, TripleShape.MIDDLE);
		for (int y = 1; y < size; y++)
			BlocksHelper.setWithoutUpdate(world, pos.up(y), middle);
		BlocksHelper.setWithoutUpdate(world, pos.up(size), BNBlocks.GIANT_MOLD.get().getDefaultState().with(RedMushroomCapBlock.SHAPE, TripleShape.TOP));
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.GIANT_MOLD.get().getDefaultState().with(RedMushroomCapBlock.SHAPE, TripleShape.BOTTOM));
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (world.getBlockState(pos.down()).getBlock() == BNBlocks.NETHER_MYCELIUM.get()) {
			generate(world, pos, random);
			return true;}
		else return false;
	}
}
