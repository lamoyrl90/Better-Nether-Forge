package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNTags;
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

public class RubeusBushFeature extends Feature<NoFeatureConfig> {

	private static final Mutable POS = new Mutable();
	
	public RubeusBushFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (!world.isAirBlock(pos) || !world.isAirBlock(pos.up())|| !world.isAirBlock(pos.up(15)) || !world.getBlockState(pos.down()).isSolid())
			return false;
		generate(world, pos, random);
		return true;
	}

	
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		float r = random.nextFloat() * 3 + 1;
		int count = (int) r;
		
		for (int i = 0; i < count; i++)
		{
			float fr = r - i;
			int ir = (int) Math.ceil(fr);
			float r2 = fr * fr;
			
			int x1 = pos.getX() - ir;
			int x2 = pos.getX() + ir;
			int z1 = pos.getZ() - ir;
			int z2 = pos.getZ() + ir;
			
			POS.setY(pos.getY() + i);
			
			for (int x = x1; x < x2; x++)
			{
				POS.setX(x);
				int sqx = x - pos.getX();
				sqx *= sqx;
				for (int z = z1; z < z2; z++)
				{
					int sqz = z - pos.getZ();
					sqz *= sqz;
					POS.setZ(z);
					if (sqx + sqz < r2 + random.nextFloat() * r)
					{
						setIfAir(world, POS, BNBlocks.RUBEUS_LEAVES.get().getDefaultState());
					}
				}
			}
		}
		
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.RUBEUS_BARK_HALF_STRIPPED.get().getDefaultState());
		setIfAir(world, pos.up(), BNBlocks.RUBEUS_LEAVES.get().getDefaultState());
		setIfAir(world, pos.north(), BNBlocks.RUBEUS_LEAVES.get().getDefaultState());
		setIfAir(world, pos.south(), BNBlocks.RUBEUS_LEAVES.get().getDefaultState());
		setIfAir(world, pos.east(), BNBlocks.RUBEUS_LEAVES.get().getDefaultState());
		setIfAir(world, pos.west(), BNBlocks.RUBEUS_LEAVES.get().getDefaultState());
	}
	
	private void setIfAir(IWorld world, BlockPos pos, BlockState state)
	{
		if (world.isAirBlock(pos))
			BlocksHelper.setWithoutUpdate(world, pos, state);
	}
}
