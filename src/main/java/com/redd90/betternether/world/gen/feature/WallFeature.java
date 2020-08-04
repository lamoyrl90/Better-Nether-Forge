package com.redd90.betternether.world.gen.feature;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class WallFeature extends Feature<NoFeatureConfig> {

	private static final Direction[] DIRECTIONS = HorizontalBlock.HORIZONTAL_FACING.getAllowedValues().toArray(new Direction[] {});
	private static final Direction[] SHUFFLED = new Direction[DIRECTIONS.length];
	protected Block plantBlock;
	
	
	public WallFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
		this.plantBlock = null;
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen,
			Random rand, BlockPos pos, NoFeatureConfig config) {
		if (isValidPosition(world, pos)) {
			generate(world, pos, rand);
			return true;
		} else return false;
	}

	public void generate(IWorld world, BlockPos pos, Random random)
	{
		BlockState state = getStateForPlacement(world, pos, random);
		if (state != null)
		BlocksHelper.setWithoutUpdate(world, pos, state);
	}
	
	@SuppressWarnings("deprecation")
	public boolean isValidPosition(IWorld world, BlockPos pos) {
		return world.isAirBlock(pos) && this.plantBlock.isValidPosition(plantBlock.getDefaultState(), world, pos);
	}
	
	private BlockState getStateForPlacement(IWorld world, BlockPos pos, Random random)
	{
		BlockState blockState = plantBlock.getDefaultState();
		shuffle(random);
		for(int i = 0; i < 4; i++) 
		{
			Direction direction = SHUFFLED[i];
			Direction direction2 = direction.getOpposite();
			blockState = blockState.with(HorizontalBlock.HORIZONTAL_FACING, direction2);
			if (blockState.isValidPosition(world, pos))
			{
				return blockState;
			}
		}
		return null;
	}
	
	private void shuffle(Random random)
	{
		for (int i = 0; i < 4; i++)
			SHUFFLED[i] = DIRECTIONS[i];
		for (int i = 0; i < 4; i++)
		{
			int i2 = random.nextInt(4);
			Direction d = SHUFFLED[i2];
			SHUFFLED[i2] = SHUFFLED[i];
			SHUFFLED[i] = d;
		}
	}
	
}
