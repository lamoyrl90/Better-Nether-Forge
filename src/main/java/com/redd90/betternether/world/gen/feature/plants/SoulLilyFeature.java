package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.SoulLilyBlock;
import com.redd90.betternether.block.SoulLilyBlock.SoulLilyShape;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class SoulLilyFeature extends Feature<NoFeatureConfig> {
	
	public SoulLilyFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	Mutable npos = new Mutable();
	
	
	public boolean generate(IWorld world, BlockPos pos, Random random)
	{
		Block under;
		if (world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND)
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
						if (under == Blocks.SOUL_SAND && world.isAirBlock(npos))
						{
							growTree(world, npos, random);
							return true;
						}
					}
					else
						break;
				}
			}
		}
		return false;
	}
	
	private void growTree(IWorld world, BlockPos pos, Random random)
	{
		if (world.getBlockState(pos.down()).getBlock() == Blocks.SOUL_SAND)
		{
			if (world.isAirBlock(pos.up()))
			{
				if (world.isAirBlock(pos.up(2)) && isAirSides(world, pos.up(2)))
				{
					growBig(world, pos);
				}
				else
					growMedium(world, pos);
			}
			else
				growSmall(world, pos);
		}
	}
	
	public void growSmall(IWorld world, BlockPos pos)
	{
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.SOUL_LILY.get().getDefaultState());
	}
	
	public void growMedium(IWorld world, BlockPos pos)
	{
		BlocksHelper.setWithoutUpdate(world, pos,
				BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.MEDIUM_BOTTOM));
		BlocksHelper.setWithoutUpdate(world, pos.up(),
				BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.MEDIUM_TOP));
	}
	
	public void growBig(IWorld world, BlockPos pos)
	{
		BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.BIG_BOTTOM));
		BlocksHelper.setWithoutUpdate(world, pos.up(),
				BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.BIG_MIDDLE));
		BlockPos up = pos.up(2);
		BlocksHelper.setWithoutUpdate(world, up,
				BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.BIG_TOP_CENTER));
		BlocksHelper.setWithoutUpdate(world, up.north(), BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.BIG_TOP_SIDE_S));
		BlocksHelper.setWithoutUpdate(world, up.south(),
				BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.BIG_TOP_SIDE_N));
		BlocksHelper.setWithoutUpdate(world, up.east(),
				BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.BIG_TOP_SIDE_W));
		BlocksHelper.setWithoutUpdate(world, up.west(),
				BNBlocks.SOUL_LILY.get()
				.getDefaultState()
				.with(SoulLilyBlock.SHAPE, SoulLilyShape.BIG_TOP_SIDE_E));
	}
	
	private boolean isAirSides(IWorld world, BlockPos pos)
	{
		return world.isAirBlock(pos.north()) && world.isAirBlock(pos.south()) && world.isAirBlock(pos.east()) && world.isAirBlock(pos.west());
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager p_230362_2_, ChunkGenerator p_230362_3_,
			Random random, BlockPos pos, NoFeatureConfig p_230362_6_) {
		return generate(world, pos, random);
	}
}
