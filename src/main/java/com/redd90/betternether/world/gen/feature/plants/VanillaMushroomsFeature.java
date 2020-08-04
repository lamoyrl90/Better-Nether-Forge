package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class VanillaMushroomsFeature extends Feature<NoFeatureConfig> {
	
	public VanillaMushroomsFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	private Mutable npos = new Mutable();
	
	private boolean canPlaceAt(IWorld world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).getBlock() == BNBlocks.NETHER_MYCELIUM.get();
	}
	

	public void generate(IWorld world, BlockPos pos, Random random)
	{
		if (canPlaceAt(world, pos))
		{
			BlockState state = random.nextBoolean() ? Blocks.RED_MUSHROOM.getDefaultState() : Blocks.BROWN_MUSHROOM.getDefaultState();
			for (int i = 0; i < 16; i++)
			{
				int x = pos.getX() + (int) (random.nextGaussian() * 4);
				int z = pos.getZ() + (int) (random.nextGaussian() * 4);
				int y = pos.getY() + random.nextInt(8);
				for (int j = 0; j < 8; j++)
				{
					npos.setPos(x, y - j, z);
					if (world.isAirBlock(npos) && canPlaceAt(world, npos))
					{
						BlocksHelper.setWithoutUpdate(world, npos, state);
					}
				}
			}
		}
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if(canPlaceAt(world, pos)) {
			generate(world, pos, random);
			return true;
		} else return false;
	}
}
