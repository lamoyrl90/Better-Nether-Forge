package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.JellyfishMushroomBlock;
import com.redd90.betternether.block.shapes.TripleShape;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class JellyfishMushroomFeature extends Feature<NoFeatureConfig> {

	public JellyfishMushroomFeature(Codec<NoFeatureConfig> p_i231953_1_) {
		super(p_i231953_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		if (world.getBlockState(pos.down()).getBlock() == BNBlocks.MUSHROOM_GRASS.get()) {
			generate(world, pos, random);
			return true;
		} return false;
	}

	Mutable npos = new Mutable();
	
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		Block under;
		if (world.getBlockState(pos.down()).getBlock() == BNBlocks.MUSHROOM_GRASS.get())
		{
			for (int i = 0; i < 10; i++)
			{
				int x = pos.getX() + (int) (random.nextGaussian() * 2);
				int z = pos.getZ() + (int) (random.nextGaussian() * 2);
				int y = pos.getY() + random.nextInt(6);
				for (int j = 0; j < 6; j++)
				{
					npos.setPos(x, y - j, z);
					if (npos.getY() > 31)
					{
						under = world.getBlockState(npos.down()).getBlock();
						if (under == BNBlocks.MUSHROOM_GRASS.get() && world.isAirBlock(npos))
						{
							grow(world, npos, random);
						}
					}
					else
						break;
				}
			}
		}
	}

	public void grow(IWorld world, BlockPos pos, Random random)
	{
		if (random.nextBoolean() && world.isAirBlock(pos.up()))
			growMedium(world, pos);
		else
			growSmall(world, pos);
	}
	
	public void growSmall(IWorld world, BlockPos pos)
	{
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.JELLYFISH_MUSHROOM.get().getDefaultState().with(JellyfishMushroomBlock.SHAPE, TripleShape.BOTTOM));
	}
	
	public void growMedium(IWorld world, BlockPos pos)
	{
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.JELLYFISH_MUSHROOM.get().getDefaultState().with(JellyfishMushroomBlock.SHAPE, TripleShape.MIDDLE));
		BlocksHelper.setWithoutUpdate(world, pos.up(), BNBlocks.JELLYFISH_MUSHROOM.get().getDefaultState().with(JellyfishMushroomBlock.SHAPE, TripleShape.TOP));
	}
}
