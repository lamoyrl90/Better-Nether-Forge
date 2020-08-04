package com.redd90.betternether.world.gen.feature.plants;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

@SuppressWarnings("deprecation")
public abstract class ScatteredPlantFeature extends Feature<NoFeatureConfig> {

	private static Mutable npos = new Mutable();
	protected Block plantBlock;
	private final Property<Integer> ageProp;
	private final int maxAge;
	
	public ScatteredPlantFeature(Block plantIn, Property<Integer> ageProp, int maxAge, Codec<NoFeatureConfig> codec) {
		super(codec);
		this.plantBlock = plantIn;
		this.ageProp = ageProp;
		this.maxAge = maxAge;
	}

	//Generate
	
	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator chunkGenerator,
			Random rand, BlockPos pos, NoFeatureConfig config) {
		return shouldGenerate(worldIn, rand, pos, ageProp, maxAge);
	}

	
	private boolean isValidPosition(IWorld world, BlockPos pos) {
		return plantBlock.isValidPosition(plantBlock.getDefaultState(), world, pos);
	}
	
	public boolean shouldGenerate(IWorld world, Random rand, BlockPos pos, Property<Integer> ageProp, int maxAge) {
		if (!isValidPosition(world, pos) || !world.getBlockState(pos).isAir()) {
			//BetterNether.LOGGER.debug("Could not place " + plantBlock.getRegistryName().getPath() + " at " + pos.toString());
			return false;
			
		} else {
			generateFeatures(world, ageProp, maxAge, pos, rand);
			//BetterNether.LOGGER.debug("Placed " + plantBlock.getRegistryName().getPath() + " at " + pos.toString());
			return true;
		}
	}
	
	private void generateFeatures(IWorld world, Property<Integer> ageProp, int maxAge, BlockPos pos, Random rand) {
		BlockState state = plantBlock.getDefaultState();
		int rndState = rand.nextInt(2);
		for (int i = 0; i < 20; i++)
		{
			int x = pos.getX() + (int) (rand.nextGaussian() * 4);
			int z = pos.getZ() + (int) (rand.nextGaussian() * 4);
			if (((x + z + rndState) & 1) == 0)
			{
				if (rand.nextBoolean())
				{
					x += rand.nextBoolean() ? 1 : -1;
				}
				else
				{
					z += rand.nextBoolean() ? 1 : -1;
				}
			}
			int y = pos.getY() + rand.nextInt(8);
			for (int j = 0; j < 8; j++)
			{
				npos.setPos(x, y - j, z);
				if (world.isAirBlock(npos) && isValidPosition(world, npos))
				{
					if (ageProp != null)
						BlocksHelper.setWithoutUpdate(world, npos, state.with(ageProp, rand.nextInt(maxAge)));
					else
						BlocksHelper.setWithoutUpdate(world, npos, state);
					break;
				}
			}
		}
	}
	
}
