package com.redd90.betternether.world.gen.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.NetherSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

@SuppressWarnings("deprecation")
public class ObsidianChasmSurfaceBuilder extends NetherSurfaceBuilder {
	
	private static final BlockState GRAVEL = Blocks.GRAVEL.getDefaultState();
	private static final BlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();
	
	private static final boolean[] MASK;
	
	public ObsidianChasmSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
		super(codec);
	}

	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		int i = seaLevel;
		int j = x & 15;
		int k = z & 15;
		boolean flag = this.field_205553_b.func_205563_a((double)x * 0.03125D, (double)z * 0.03125D, 0.0D) * 75.0D + random.nextDouble() > 0.0D;
		boolean flag1 = this.field_205553_b.func_205563_a((double)x * 0.03125D, 109.0D, (double)z * 0.03125D) * 75.0D + random.nextDouble() > 0.0D;
		int l = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		int i1 = -1;
		BlockState blockstate = config.getTop();
		BlockState blockstate1 = config.getUnder();

		for(int j1 = 127; j1 >= 0; --j1) {
			blockpos$mutable.setPos(j, j1, k);
			BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutable);
			if (blockstate2.isAir()) {
				i1 = -1;
			} else if (blockstate2.isIn(defaultBlock.getBlock())) {
				if (i1 == -1) {
					boolean flag2 = false;
					if (l <= 0) {
						flag2 = true;
						blockstate1 = config.getUnder();
					} else if (j1 >= i - 4 && j1 <= i + 1) {
						blockstate = config.getTop();
						blockstate1 = config.getUnder();
						if (flag1) {
							blockstate = GRAVEL;
							blockstate1 = config.getUnder();
						}
	
						if (flag) {
							blockstate = SOUL_SAND;
							blockstate1 = SOUL_SAND;
						}
					}
						
					if (isMask(x, z) && !blockstate.isAir()){
						boolean magma = true;
						
						if (random.nextInt(4) == 0)
							{
								if (validWall(chunkIn, blockpos$mutable.down()) && validWall(chunkIn, blockpos$mutable.north()) && validWall(chunkIn, blockpos$mutable.south()) && validWall(chunkIn, blockpos$mutable.east()) && validWall(chunkIn, blockpos$mutable.west()))
								{
									blockstate = Blocks.LAVA.getDefaultState();
									magma = false;
								}
							}
							
						if (magma)
							for (int y = 0; y < random.nextInt(3) + 1; y++)
							{
								if (BlocksHelper.isNetherGround(chunkIn.getBlockState(blockpos$mutable)))
										blockstate = Blocks.MAGMA_BLOCK.getDefaultState();
							}
							
					}
	
					if (j1 < i && flag2) {
						blockstate = defaultFluid;
					}
	
					i1 = l;
					if (j1 >= i - 1) {
						chunkIn.setBlockState(blockpos$mutable, blockstate, false);
					} else {
						chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
					}
				} else if (i1 > 0) {
					--i1;
					chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
				}
			}
		}
	}


	
	protected boolean validWall(IChunk world, BlockPos pos)
	{
		BlockState state = world.getBlockState(pos);
		return BlocksHelper.isLava(state) || BlocksHelper.isNetherGroundMagma(state);
	}
		
	protected boolean isMask(int x, int z)
	{
		x &= 15;
		z &= 15;
		return MASK[(x << 4) | z];
	}
		
	static
	{
		MASK = new boolean[] {
				false,  true, false, false, false, false, false,  true, false, false, false, false,  true,  true, false, false,
				false, false, false, false, false, false, false,  true, false, false, false, false,  true,  true, false, false,
				 true,  true,  true, false, false, false,  true,  true, false, false, false,  true, false, false,  true,  true,
				 true, false, false,  true,  true,  true,  true, false,  true,  true,  true,  true, false, false, false,  true,
				 true, false, false,  true,  true,  true, false, false, false, false, false,  true, false, false, false, false,
				 true, false, false, false,  true, false, false, false, false, false, false, false,  true, false, false, false,
				false, false, false, false,  true, false, false, false, false, false, false, false,  true,  true,  true,  true,
				 true, false, false, false,  true,  true,  true,  true, false, false, false,  true,  true, false,  true,  true,
				 true,  true,  true,  true,  true, false, false,  true,  true, false,  true,  true, false, false, false,  true,
				false, false,  true, false, false, false, false, false,  true,  true,  true, false, false, false, false,  true,
				false, false,  true, false, false, false, false, false, false,  true, false, false, false, false,  true, false,
				false, false,  true,  true, false, false, false, false, false,  true, false, false, false, false,  true, false,
				 true, false, false, false,  true, false, false, false, false,  true, false, false, false, false,  true, false,
				 true,  true, false, false,  true, false, false, false,  true,  true,  true,  true,  true,  true, false,  true,
				false,  true,  true,  true,  true,  true,  true,  true, false, false, false, false,  true, false, false, false,
				false,  true,  true, false, false, false,  true, false, false, false, false, false,  true,  true, false, false
		};
	}
	
}
