package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.SmokerBlock;
import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class SmokerFeature extends Feature<NoFeatureConfig> {

	private Mutable npos = new Mutable();
	
	public SmokerFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (canPlaceAt(world, pos) && world.getBlockState(pos).isAir()) {
			generate(world, random, pos);
			return true;
		} else return false;
	}

	private boolean canPlaceAt(IWorld world, BlockPos pos)
	{
		return BlocksHelper.isNetherGround(world.getBlockState(pos.down()));
	}
	
	private void generate(IWorld world, Random random, BlockPos pos) {
		BlockState top = BNBlocks.SMOKER.get().getDefaultState();
		BlockState middle = BNBlocks.SMOKER.get().getDefaultState().with(SmokerBlock.SHAPE, TripleShape.MIDDLE);
		BlockState bottom = BNBlocks.SMOKER.get().getDefaultState().with(SmokerBlock.SHAPE, TripleShape.BOTTOM);
		for (int i = 0; i < 8; i++)
		{
			int x = pos.getX() + (int) (random.nextGaussian() * 2);
			int z = pos.getZ() + (int) (random.nextGaussian() * 2);
			int y = pos.getY() + random.nextInt(6);
			for (int j = 0; j < 6; j++)
			{
				npos.setPos(x, y - j, z);
				if (world.isAirBlock(npos) && canPlaceAt(world, npos))
				{
					int h = random.nextInt(5);
					BlocksHelper.setWithoutUpdate(world, npos, bottom);
					for (int n = 1; n < h; n++)
					{
						BlockPos up = npos.up(n);
						if (world.isAirBlock(up.up()))
							BlocksHelper.setWithoutUpdate(world, up, middle);
						else
						{
							BlocksHelper.setWithoutUpdate(world, up, top);
							return;
						}
					}	
					BlocksHelper.setWithoutUpdate(world, npos.up(h), top);
					break;
				}
			}
		}
	}
	
}
