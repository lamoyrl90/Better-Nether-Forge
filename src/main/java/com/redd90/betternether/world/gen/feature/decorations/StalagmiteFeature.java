package com.redd90.betternether.world.gen.feature.decorations;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.SpeleothemBlock;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNTags;
import com.redd90.betternether.util.BlocksHelper;
import com.redd90.betternether.util.MHelper;

import net.minecraft.block.Block;
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

public class StalagmiteFeature extends Feature<NoFeatureConfig> {
		
	private static final Mutable POS = new Mutable();
	private Block block;
	private static Block under;
	//private Block[] ground;
	
	public StalagmiteFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}
	
	@Override
	public boolean func_230362_a_(ISeedReader worldIn, StructureManager structureManager, ChunkGenerator chunkGenerator, Random random, BlockPos pos, NoFeatureConfig featureConfig) {
		if (!invalidPosition(worldIn, pos)){
			generate(worldIn, pos, random);
			return true;
		} else return false;
	}

	public void generate(IWorld world, BlockPos pos, Random random)
	{
		if (!invalidPosition(world, pos))
		{
			for (int i = 0; i < 16; i++)
			{
				int x = pos.getX() + (int) (random.nextGaussian() * 2F);
				int z = pos.getZ() + (int) (random.nextGaussian() * 2F);
				POS.setPos(x, pos.getY(), z);
				int y = pos.getY() - BlocksHelper.downRay(world, POS, 8);
				
				if (!invalidPosition(world, POS))
				{
					block=pickMaterial(world, POS, random);
					int dx = x - pos.getX();
					int dz = z - pos.getZ();
					float dist = 4 - (float) Math.sqrt(dx * dx + dz * dz);
					if (dist < 1)
						dist = 1;
					int h = (int) (random.nextInt((int) Math.ceil(dist + 1)) + dist + random.nextInt(2));
					h = h > 8 ? 8 : h;
					POS.setY(y);
					int h2 = BlocksHelper.upRay(world, POS, h + 2) + 1;
					POS.setY(y + h2);
					boolean stalagnate = h2 <= h && BlocksHelper.isNetherrack(world.getBlockState(POS));
					if (h2 <= h)
						h = h2;
					int offset = stalagnate ? (h < 8 ? MHelper.randRange(0, 8 - h, random) : 0) : 0;
					
					
					
					if (under != null && h > 2)
					{
						POS.setY(y - 1);
						BlocksHelper.setWithoutUpdate(world, POS, under.getDefaultState());
						if (stalagnate)
						{
							POS.setY(y + h);
							BlocksHelper.setWithoutUpdate(world, POS, under.getDefaultState());
						}
					}
					for (int n = 0; n < h; n++)
					{
						POS.setY(y + n);
						if (!world.isAirBlock(POS))
							break;
						int size = stalagnate ? (Math.abs(h / 2 - n) + offset) : (h - n - 1);
						BlocksHelper.setWithoutUpdate(world, POS, block.getDefaultState().with(SpeleothemBlock.SIZE, size));
					}
						
				}
			}
		}
	}
	
	/*
	private static void generateFeatures(IWorld world, Random random, BlockPos pos, int spreadXZ, int spreadY, int maxSize) {
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

		for(int i = 0; i < (spreadXZ * spreadXZ) / 2; ++i) {
			blockpos$mutable.setPos(pos).move(MathHelper.nextInt(random, -spreadXZ, spreadXZ), MathHelper.nextInt(random, -spreadY, spreadY), MathHelper.nextInt(random, -spreadXZ, spreadXZ));
			if (searchForPlacement(world, blockpos$mutable) && !invalidPosition(world, blockpos$mutable)) {
				int j = MathHelper.nextInt(random, 1, maxSize);
				
				placeColumn(world, random, blockpos$mutable, j);
			}
		}

	}

	@SuppressWarnings("deprecation")
	private static boolean searchForPlacement(IWorld world, BlockPos.Mutable pos) {
		while(true) {
			pos.move(0, -1, 0);
			if (World.isOutsideBuildHeight(pos)) {
				return false;
			}

			if (!world.getBlockState(pos).isAir()) {
				break;
			}
		}

		pos.move(0, 1, 0);
		return true;
	}

	public static void placeColumn(IWorld world, Random random, BlockPos.Mutable mutPos, int size) {
		BlockState material = pickMaterial(world, mutPos, random);
		int j = size-1;
		
		if(material.getBlock() == BNBlocks.GLOWSTONE_SPELEOTHEM.get() && size > 4) {
			world.setBlockState(mutPos.down(), Blocks.GLOWSTONE.getDefaultState(), 2);
		}
		
		if(material.getBlock() == BNBlocks.QUARTZ_SPELEOTHEM.get() && size > 4) {
			world.setBlockState(mutPos.down(), Blocks.QUARTZ_BLOCK.getDefaultState(), 2);
		}
		
		for(int i = 1; i <= size; ++i) {
			world.setBlockState(mutPos, material.with(StalactiteBlock.SIZE, j), 2);
			j--;
			mutPos.move(Direction.UP);
		}
	}*/

	private static Block pickMaterial(IWorld world, BlockPos.Mutable pos, Random random) {
		Block netherrack = BNBlocks.NETHERRACK_SPELEOTHEM.get();
		Block glowstone = BNBlocks.GLOWSTONE_SPELEOTHEM.get();
		Block blackstone = BNBlocks.BLACKSTONE_SPELEOTHEM.get();
		Block basalt = BNBlocks.BASALT_SPELEOTHEM.get();
		Block quartz = BNBlocks.QUARTZ_SPELEOTHEM.get();
		under = null;
		
				
		if (random.nextFloat()>0.8) {
			return glowstone;
		}
		
		BlockState floorState = world.getBlockState(pos.down());
		
		if (floorState.getBlock() == Blocks.NETHER_QUARTZ_ORE) {
			under = Blocks.QUARTZ_BLOCK;
			return quartz;
		} else if (floorState.isIn(BNTags.Blocks.NETHERRACK)){
			if (random.nextFloat()>0.9) {
				under = Blocks.GLOWSTONE;
				return glowstone;
			}
			return netherrack;
		} else if (floorState == Blocks.BLACKSTONE.getDefaultState()) {
			under = Blocks.BLACKSTONE;
			return blackstone;
		} else if (floorState == Blocks.BASALT.getDefaultState()) {
			under = Blocks.BASALT;
			return basalt;
		} else
			return netherrack;
	}
	
	private static boolean invalidPosition(IWorld world, BlockPos pos) {
		if (!world.isAirBlock(pos)) {
			return true;
		} else {
			BlockState blockstate = world.getBlockState(pos.down());
			return !(blockstate.isIn(BNTags.Blocks.NETHERRACK) || (blockstate == Blocks.BLACKSTONE.getDefaultState()) || (blockstate == Blocks.BASALT.getDefaultState()));
		}
	}
}