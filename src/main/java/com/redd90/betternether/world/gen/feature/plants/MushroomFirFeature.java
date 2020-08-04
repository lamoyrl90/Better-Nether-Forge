package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.MushroomFirBlock;
import com.redd90.betternether.block.MushroomFirBlock.MushroomFirShape;
import com.redd90.betternether.block.NetherMyceliumBlock;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class MushroomFirFeature extends Feature<NoFeatureConfig> {

	Mutable npos = new Mutable();
	
	public MushroomFirFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}
	
	public MushroomFirFeature() {
		this(NoFeatureConfig.field_236558_a_);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (world.getBlockState(pos.down()).getBlock() == BNBlocks.NETHER_MYCELIUM.get()) {
			generate(world, pos, random);
			return true;
		} else return false;
			
	}

	public void generate(IWorld world, BlockPos pos, Random random)
	{
		if (world.getBlockState(pos.down()).getBlock() == BNBlocks.NETHER_MYCELIUM.get())
		{
			int h = 3 + random.nextInt(5);
			for (int y = 1; y < h; y++)
				if (!world.isAirBlock(pos.up(y)))
				{
					h = y;
					break;
				}
			if (h < 3)
				return;
			
			BlocksHelper.setWithoutUpdate(world, pos, BNBlocks.MUSHROOM_FIR.get()
					.getDefaultState()
					.with(MushroomFirBlock.SHAPE, MushroomFirShape.BOTTOM));
			int h2 = (h + 1) >> 1;
			h += pos.getY();
			h2 += pos.getY();
			npos.setPos(pos);
			for (int y = pos.getY() + 1; y < h2; y++)
			{
				npos.setY(y);
				BlocksHelper.setWithoutUpdate(world, npos, BNBlocks.MUSHROOM_FIR.get()
						.getDefaultState()
						.with(MushroomFirBlock.SHAPE, MushroomFirShape.MIDDLE));
			}
			for (int y = h2; y < h; y++)
			{
				npos.setY(y);
				BlocksHelper.setWithoutUpdate(world, npos, BNBlocks.MUSHROOM_FIR.get()
						.getDefaultState()
						.with(MushroomFirBlock.SHAPE, MushroomFirShape.TOP));
			}
			int h3 = (h2 + h) >> 1;
			for (int y = h2 - 1; y < h3; y++)
			{
				npos.setY(y);
				BlockPos branch;
				if (random.nextBoolean())
				{
					branch = npos.north();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_BIG_S));
				}
				if (random.nextBoolean())
				{
					branch = npos.south();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_BIG_N));
				}
				if (random.nextBoolean())
				{
					branch = npos.east();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_BIG_W));
				}
				if (random.nextBoolean())
				{
					branch = npos.west();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_BIG_E));
				}
			}
			for (int y = h3; y < h; y++)
			{
				npos.setY(y);
				BlockPos branch;
				if (random.nextBoolean())
				{
					branch = npos.north();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_SMALL_S));
				}
				if (random.nextBoolean())
				{
					branch = npos.south();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_SMALL_N));
				}
				if (random.nextBoolean())
				{
					branch = npos.east();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_SMALL_W));
				}
				if (random.nextBoolean())
				{
					branch = npos.west();
					if (world.isAirBlock(branch))
						BlocksHelper.setWithoutUpdate(world, branch, BNBlocks.MUSHROOM_FIR.get()
								.getDefaultState()
								.with(MushroomFirBlock.SHAPE, MushroomFirShape.SIDE_SMALL_E));
				}
			}
			npos.setY(h);
			if (world.isAirBlock(npos))
				BlocksHelper.setWithoutUpdate(world, npos, BNBlocks.MUSHROOM_FIR.get()
						.getDefaultState()
						.with(MushroomFirBlock.SHAPE, MushroomFirShape.END));
			
			BlocksHelper.cover(world,
					pos.down(),
					BNBlocks.NETHER_MYCELIUM.get(),
					BNBlocks.NETHER_MYCELIUM.get().getDefaultState().with(NetherMyceliumBlock.IS_BLUE, true),
					5,
					random);
		}
	}
}
