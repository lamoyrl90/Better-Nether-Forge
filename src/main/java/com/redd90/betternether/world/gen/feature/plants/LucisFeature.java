package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.LucisMushroomBlock;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class LucisFeature extends Feature<NoFeatureConfig> {

	public LucisFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public LucisFeature() {
		this(NoFeatureConfig.field_236558_a_);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if(canGenerate(world,pos)) {
			generate(world,pos,random);
			return true;
		} else return false;
	}
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		if (canGenerate(world, pos))
		{
			BlockState center = BNBlocks.LUCIS_MUSHROOM.get().getDefaultState().with(LucisMushroomBlock.SHAPE, LucisMushroomBlock.EnumShape.CENTER);
			BlockState side = BNBlocks.LUCIS_MUSHROOM.get().getDefaultState().with(LucisMushroomBlock.SHAPE, LucisMushroomBlock.EnumShape.SIDE);
			BlockState corner = BNBlocks.LUCIS_MUSHROOM.get().getDefaultState().with(LucisMushroomBlock.SHAPE, LucisMushroomBlock.EnumShape.CORNER);

			if (random.nextInt(3) == 0)
			{
				if (canReplace(world.getBlockState(pos)))
					BlocksHelper.setWithoutUpdate(world, pos, center);
				if (canReplace(world.getBlockState(pos.north())))
					BlocksHelper.setWithoutUpdate(world, pos.north(), side.with(LucisMushroomBlock.FACING, Direction.NORTH));
				if (canReplace(world.getBlockState(pos.south())))
					BlocksHelper.setWithoutUpdate(world, pos.south(), side.with(LucisMushroomBlock.FACING, Direction.SOUTH));
				if (canReplace(world.getBlockState(pos.east())))
					BlocksHelper.setWithoutUpdate(world, pos.east(), side.with(LucisMushroomBlock.FACING, Direction.EAST));
				if (canReplace(world.getBlockState(pos.west())))
					BlocksHelper.setWithoutUpdate(world, pos.west(), side.with(LucisMushroomBlock.FACING, Direction.WEST));

				if (canReplace(world.getBlockState(pos.north().east())))
					BlocksHelper.setWithoutUpdate(world, pos.north().east(), corner.with(LucisMushroomBlock.FACING, Direction.SOUTH));
				if (canReplace(world.getBlockState(pos.north().west())))
					BlocksHelper.setWithoutUpdate(world, pos.north().west(), corner.with(LucisMushroomBlock.FACING, Direction.EAST));
				if (canReplace(world.getBlockState(pos.south().east())))
					BlocksHelper.setWithoutUpdate(world, pos.south().east(), corner.with(LucisMushroomBlock.FACING, Direction.WEST));
				if (canReplace(world.getBlockState(pos.south().west())))
					BlocksHelper.setWithoutUpdate(world, pos.south().west(), corner.with(LucisMushroomBlock.FACING, Direction.NORTH));
			}
			else
			{
				boolean offset = false;
				if (BlocksHelper.isNetherrack(world.getBlockState(pos.north())))
				{
					pos = pos.north();
					offset = true;
				}
				else if (BlocksHelper.isNetherrack(world.getBlockState(pos.east())))
				{
					pos = pos.east();
					offset = true;
				}
				if (!offset)
					BlocksHelper.setWithoutUpdate(world, pos, corner.with(LucisMushroomBlock.FACING, Direction.SOUTH));
				if (canReplace(world.getBlockState(pos.west())))
					BlocksHelper.setWithoutUpdate(world, pos.west(), corner.with(LucisMushroomBlock.FACING, Direction.EAST));
				if (canReplace(world.getBlockState(pos.south())))
					BlocksHelper.setWithoutUpdate(world, pos.south(), corner.with(LucisMushroomBlock.FACING, Direction.WEST));
				if (canReplace(world.getBlockState(pos.south().west())))
					BlocksHelper.setWithoutUpdate(world, pos.south().west(), corner.with(LucisMushroomBlock.FACING, Direction.NORTH));
			}
		}
	}

	private boolean canReplace(BlockState state)
	{
		Block b = state.getBlock();
		return b == Blocks.AIR || b == BNBlocks.LUCIS_SPORE.get();
	}

	private boolean canGenerate(IWorld world, BlockPos pos)
	{
		for (Direction dir: HorizontalBlock.HORIZONTAL_FACING.getAllowedValues())
			if (BlocksHelper.isNetherrack(world.getBlockState(pos.offset(dir))))
				return true;
		return false;
	}
}
