package com.redd90.betternether.world.gen.surfacebuilders;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.block.SoulSandstoneBlock;
import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.NetherSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class WartForestSurfaceBuilder extends NetherSurfaceBuilder {

	private static final BlockState SOUL_SAND = Blocks.SOUL_SAND.getDefaultState();
	private static final BlockState SOUL_SOIL = Blocks.SOUL_SOIL.getDefaultState();
	private static final BlockState NETHERRACK_MOSS = BNBlocks.NETHERRACK_MOSS.get().getDefaultState();
	private static final BlockState SOUL_SANDSTONE = BNBlocks.SOUL_SANDSTONE.get().getDefaultState();
	
	public WartForestSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
		super(codec);
	}
	
	@SuppressWarnings("deprecation")
	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, 
			double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, 
			long seed, SurfaceBuilderConfig config) {
		int i = seaLevel;
	      int j = x & 15;
	      int k = z & 15;
	      boolean flag = this.field_205553_b.func_205563_a((double)x * 0.03125D, (double)z * 0.03125D, 0.0D) * 75.0D + random.nextDouble() > 0.0D;
	      boolean flag1 = this.field_205553_b.func_205563_a((double)x * 0.03125D, 109.0D, (double)z * 0.03125D) * 75.0D + random.nextDouble() > 0.0D;
	      int l = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
	      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
	      int i1 = -1;
	      BlockState blockstate = genSurfColumn(chunkIn, blockpos$mutable, random, config.getTop());
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
	                  blockstate = genSurfColumn(chunkIn, blockpos$mutable, random, blockstate);
	                  blockstate1 = config.getUnder();
	                  if (flag1) {
	                     blockstate = SOUL_SOIL;
	                     blockstate1 = config.getUnder();
	                  }

	                  if (flag) {
	                     blockstate = SOUL_SAND;
	                     blockstate1 = SOUL_SAND;
	                  }
	               }

	               if (j1 < i && flag2) {
	                  blockstate = defaultFluid;
	               }
	               
	               i1 = l;
	               if (j1 >= i - 1) {
	                  chunkIn.setBlockState(blockpos$mutable, blockstate, false);
	               } else {
	            	   if(blockstate1.getBlock() == SOUL_SANDSTONE.getBlock()) {
	            		   if (chunkIn.getBlockState(blockpos$mutable.up()).getBlock() == SOUL_SANDSTONE.getBlock()) {
	            			   chunkIn.setBlockState(blockpos$mutable, blockstate1.with(SoulSandstoneBlock.UP, true), false);
	            		   } else { 
	            			   chunkIn.setBlockState(blockpos$mutable, blockstate1.with(SoulSandstoneBlock.UP, false), false);
	            		   }
	            	   } else {chunkIn.setBlockState(blockpos$mutable, blockstate1, false);}
	               }
	            } else if (i1 > 0) {
	               --i1;
	               chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
	            }
	         }
	      }
	}

	public BlockState genSurfColumn(IChunk world, BlockPos pos, Random random, BlockState stateIn)
	{
		switch (random.nextInt(4))
		{
		case 0:
			return SOUL_SAND;
		case 1:
			return SOUL_SOIL;
		case 2:
			return NETHERRACK_MOSS;
		default:
			return stateIn;
		}
	}
	
}
