package com.redd90.betternether.world.gen.feature.plants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.WartSeedBlock;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class WartTreeFeature extends Feature<NoFeatureConfig> {
	
	private static final BlockState WART_BLOCK = Blocks.NETHER_WART_BLOCK.getDefaultState();
	private static final Direction[] HORIZONTAL = HorizontalBlock.HORIZONTAL_FACING.getAllowedValues().toArray(new Direction[] {});
	protected static final Mutable POS = new Mutable();
	private static final Integer distance = 7;
	private static final Integer manDist = 7;
	
	public WartTreeFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator,
			Random random, BlockPos pos, NoFeatureConfig config) {
		if (isGround(world.getBlockState(pos.down())) && noObjNear(world, pos))
		{
			grow(world, pos, random);
			return true;
		} else return false;
	}
	
	public void grow(IWorld world, BlockPos pos, Random random)
	{
		if (world.isAirBlock(pos.up(1)) && world.isAirBlock(pos.up(2)))
		{
			if (world.isAirBlock(pos.up(2).north()) && world.isAirBlock(pos.up(2).south()) && world.isAirBlock(pos.up(2).east()) && world.isAirBlock(pos.up(2).west()))
				if (world.isAirBlock(pos.up(3).north(2)) && world.isAirBlock(pos.up(3).south(2)) && world.isAirBlock(pos.up(3).east(2)) && world.isAirBlock(pos.up(3).west(2)))
				{
					int height = 5 + random.nextInt(5);
					int h2 = height - 1;
					int width = (height >>> 2) + 1;
					int offset = width >>> 1;
					List<BlockPos> seedBlocks = new ArrayList<BlockPos>();
					for (int x = 0; x < width; x++)
					{
						int px = x + pos.getX() - offset;
						for (int z = 0; z < width; z++)
						{
							int pz = z + pos.getZ() - offset;
							for (int y = 0; y < height; y++)
							{
								int py = y + pos.getY();
								POS.setPos(px, py, pz);
								if (isReplaceable(world.getBlockState(POS)))
								{
									if (y == 0 && !isReplaceable(world.getBlockState(POS.down())))
										BlocksHelper.setWithoutUpdate(world, POS, BNBlocks.WART_ROOTS.get().getDefaultState());
									else if (y < h2)
										BlocksHelper.setWithoutUpdate(world, POS, BNBlocks.WART_LOG.get().getDefaultState());
									else
										BlocksHelper.setWithoutUpdate(world, POS, WART_BLOCK);
									if (random.nextInt(8) == 0)
									{
										Direction dir = HORIZONTAL[random.nextInt(HORIZONTAL.length)];
										seedBlocks.add(new BlockPos(POS).offset(dir));
									}
								}
							}
						}
					}

					for (int x = 0; x < width; x++)
					{
						int px = x + pos.getX() - offset;
						for (int z = 0; z < width; z++)
						{
							int pz = z + pos.getZ() - offset;
							for (int y = 1; y < height >> 1; y++)
							{
								int py = pos.getY() - y;
								POS.setPos(px, py, pz);
								if (isReplaceable(world.getBlockState(POS)))
								{
									if (isReplaceable(world.getBlockState(POS.down())))
										BlocksHelper.setWithoutUpdate(world, POS, BNBlocks.WART_LOG.get().getDefaultState());
									else
									{
										BlocksHelper.setWithoutUpdate(world, POS, BNBlocks.WART_ROOTS.get().getDefaultState());
										break;
									}
								}
							}
						}
					}

					int headWidth = width + 2;
					offset ++;
					height = height - width - 1 + pos.getY();
					for (int x = 0; x < headWidth; x++)
					{
						int px = x + pos.getX() - offset;
						for (int z = 0; z < headWidth; z++)
						{
							if (x != z && x != (headWidth - z - 1))
							{
								int pz = z + pos.getZ() - offset;
								for (int y = 0; y < width; y++)
								{
									int py = y + height;
									POS.setPos(px, py, pz);
									if (world.isAirBlock(POS))
									{
										BlocksHelper.setWithoutUpdate(world, POS, WART_BLOCK);
										for (int i = 0; i < 4; i++)
											seedBlocks.add(new BlockPos(POS).offset(Direction.values()[random.nextInt(6)]));
									}
								}
							}
						}
					}
					for (BlockPos pos2 : seedBlocks)
						PlaceRandomSeed(world, pos2);
				}
		}
	}

	private void PlaceRandomSeed(IWorld world, BlockPos pos)
	{
		BlockState seed = BNBlocks.WART_SEED.get().getDefaultState();
		if (isReplaceable(world.getBlockState(pos)))
		{
			if (isWart(world.getBlockState(pos.up())))
				seed = seed.with(WartSeedBlock.FACING, Direction.DOWN);
			else if (isWart(world.getBlockState(pos.down())))
				seed = seed.with(WartSeedBlock.FACING, Direction.UP);
			else if (isWart(world.getBlockState(pos.north())))
				seed = seed.with(WartSeedBlock.FACING, Direction.SOUTH);
			else if (isWart(world.getBlockState(pos.south())))
				seed = seed.with(WartSeedBlock.FACING, Direction.NORTH);
			else if (isWart(world.getBlockState(pos.east())))
				seed = seed.with(WartSeedBlock.FACING, Direction.WEST);
			else if (isWart(world.getBlockState(pos.west())))
				seed = seed.with(WartSeedBlock.FACING, Direction.EAST);
			BlocksHelper.setWithoutUpdate(world, pos, seed);
		}
	}

	private boolean isReplaceable(BlockState state)
	{
		Block block = state.getBlock();
		return state.getMaterial().isReplaceable() ||
				block == Blocks.AIR ||
				block == BNBlocks.WART_SEED.get() ||
				block == BNBlocks.BLACK_BUSH.get() ||
				block == BNBlocks.SOUL_VEIN.get() ||
				block == BNBlocks.SOUL_LILY.get() ||
				block == BNBlocks.SOUL_LILY_SAPLING.get() ||
				block == Blocks.NETHER_WART;
	}

	private boolean isWart(BlockState state)
	{
		return state == WART_BLOCK || state.getBlock() == BNBlocks.WART_LOG.get();
	}


	protected boolean isStructure(BlockState state)
	{
		return isWart(state);
	}


	protected boolean isGround(BlockState state)
	{
		return BlocksHelper.isSoulSand(state);
	}
	
	private boolean noObjNear(IWorld world, BlockPos pos)
	{
		int x1 = pos.getX() - distance;
		int z1 = pos.getZ() - distance;
		int x2 = pos.getX() + distance;
		int z2 = pos.getZ() + distance;
		POS.setY(pos.getY());
		for (int x = x1; x <= x2; x++)
		{
			POS.setX(x);
			for (int z = z1; z <= z2; z++)
			{
				POS.setZ(z);
				if (isInside(x - pos.getX(), z - pos.getZ()) && isStructure(world.getBlockState(POS)))
					return false;
			}
		}
		return true;
	}
	
	private boolean isInside(int x, int z)
	{
		return (Math.abs(x) + Math.abs(z)) <= manDist;
	}
}
