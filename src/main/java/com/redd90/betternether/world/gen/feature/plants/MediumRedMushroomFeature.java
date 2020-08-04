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

public class MediumRedMushroomFeature extends Feature<NoFeatureConfig> implements IMushroomFeature {

	private static final Mutable POS = new Mutable();
	
	public MediumRedMushroomFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
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
				if (((x + z) & 1) == 0)
				{
					if (random.nextBoolean())
					{
						x += random.nextBoolean() ? 1 : -1;
					}
					else
					{
						z += random.nextBoolean() ? 1 : -1;
					}
				}
				int y = pos.getY() + random.nextInt(6);
				for (int j = 0; j < 12; j++)
				{
					POS.setPos(x, y - j, z);
					under = world.getBlockState(POS.down()).getBlock();
					if (under == BNBlocks.NETHER_MYCELIUM.get())
					{
						grow(world, POS, random);
					}
				}
			}
		}
	}

	public void grow(IWorld world, BlockPos pos, Random random)
	{
		int size = 1 + random.nextInt(4);
		for (int y = 1; y <= size; y++)
			if (!world.isAirBlock(pos.up(y)))
			{
				if (y == 1)
					return;
				size = y - 1;
				break;
			}
		BlockState middle = BNBlocks.RED_LARGE_MUSHROOM.get().getDefaultState().with(RedMushroomCapBlock.SHAPE, TripleShape.MIDDLE);
		for (int y = 1; y < size; y++)
			BlocksHelper.setWithoutUpdate(world, pos.up(y), middle);
		BlocksHelper.setWithoutUpdate(world, pos.up(size), BNBlocks.RED_LARGE_MUSHROOM.get().getDefaultState().with(RedMushroomCapBlock.SHAPE, TripleShape.TOP));
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.RED_LARGE_MUSHROOM.get().getDefaultState().with(RedMushroomCapBlock.SHAPE, TripleShape.BOTTOM));
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
