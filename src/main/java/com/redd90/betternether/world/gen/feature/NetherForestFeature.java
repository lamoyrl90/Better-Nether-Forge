package com.redd90.betternether.world.gen.feature;

import java.util.Random;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.WeepingVineFeature;
import net.minecraft.world.gen.feature.structure.StructureManager;

@SuppressWarnings("deprecation")
public class NetherForestFeature extends Feature<NetherForestConfig> {
	   public NetherForestFeature(Codec<NetherForestConfig> codec) {
	      super(codec);
	   }

	   //Generation test
	   
	   public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen, Random rand, BlockPos pos, NetherForestConfig config) {
	      Block baseBlock = config.baseBlock.getBlock();
	      BlockPos growPos = null;
	      
	      // Test if this grew from a fungus
	      
	      if (config.plantedTest) {
	         Block groundBlock = world.getBlockState(pos.down()).getBlock();
	         if (groundBlock == baseBlock) {
	        	 growPos = pos;
	         }
	      } else {
	    	  growPos = searchDownForFungusPlacement(world, pos, baseBlock);
	      }

	      // Test to see if we found a valid spot to grow
	      
	      if (growPos == null) {
	         return false;
	      } else {
	    	 // Determine the fungus size
	         int height = MathHelper.nextInt(rand, 4, 13);
	         if (rand.nextInt(12) == 0) {
	        	 height *= 2;
	         }

	         //Test to see if the fungus would grow beyond build limit
	         if (!config.plantedTest) {
	            int i = chunkGen.func_230355_e_();
	            if (growPos.getY() + height + 1 >= i) {
	               return false;
	            }
	         }

	         //Use this flag if natural gen and passed density test
	         
	         boolean flag = !config.plantedTest && rand.nextFloat() < 0.06F;
	         world.setBlockState(pos, Blocks.AIR.getDefaultState(), 4);
	         this.growStem(world, rand, config, growPos, height, flag);
	         this.growHat(world, rand, config, growPos, height, flag);
	         return true;
	      }
	   }

	   private static boolean func_236315_a_(IWorld world, BlockPos pos, boolean test) {
	      return world.hasBlockState(pos, (p_236320_1_) -> {
	         Material material = p_236320_1_.getMaterial();
	         return p_236320_1_.isAir() || p_236320_1_.isIn(Blocks.WATER) || p_236320_1_.isIn(Blocks.LAVA) || material == Material.TALL_PLANTS || test && material == Material.PLANTS;
	      });
	   }

	   private void growStem(IWorld world, Random rand, NetherForestConfig config, BlockPos growPos, int height, boolean growTest) {
	      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
	      BlockState stem = config.stem;
	      int i = growTest ? 1 : 0;

	      for(int j = -i; j <= i; ++j) {
	         for(int k = -i; k <= i; ++k) {
	        	 //if natural, this flag is on for |j, k| = 1
	        	 //if grown from fungus, this flag is on for j, k = 0
	            boolean flag = growTest && MathHelper.abs(j) == i && MathHelper.abs(k) == i;

	            //Grow the fungus
	            for(int l = 0; l < height; ++l) {
	            //Add vector to blockpos
	               blockpos$mutable.func_239621_a_(growPos, j, l, k);
	               if (func_236315_a_(world, blockpos$mutable, true)) {
	                  if (config.plantedTest) {
	                     if (!world.getBlockState(blockpos$mutable.down()).isAir()) {
	                        world.destroyBlock(blockpos$mutable, true);
	                     }

	                     world.setBlockState(blockpos$mutable, stem, 3);
	                  } else if (flag) {
	                     if (rand.nextFloat() < 0.1F) {
	                        this.func_230367_a_(world, blockpos$mutable, stem);
	                     }
	                  } else {
	                     this.func_230367_a_(world, blockpos$mutable, stem);
	                  }
	               }
	            }
	         }
	      }

	   }

	   private void growHat(IWorld world, Random rand, NetherForestConfig config, BlockPos growPos, int height, boolean worldGen) {
	      BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
	      boolean isNetherWart = config.hat.isIn(Blocks.NETHER_WART_BLOCK);
	      int i = Math.min(rand.nextInt(1 + height / 3) + 5, height);
	      int j = height - i;

	      for(int k = j; k <= height; ++k) {
	         int l = k < height - rand.nextInt(3) ? 2 : 1;
	         if (i > 8 && k < j + 4) {
	            l = 3;
	         }

	         if (worldGen) {
	            ++l;
	         }

	         for(int i1 = -l; i1 <= l; ++i1) {
	            for(int j1 = -l; j1 <= l; ++j1) {
	               boolean flag1 = i1 == -l || i1 == l;
	               boolean flag2 = j1 == -l || j1 == l;
	               boolean flag3 = !flag1 && !flag2 && k != height;
	               boolean flag4 = flag1 && flag2;
	               boolean flag5 = k < j + 3;
	               //Add vector to blockpos
	               blockpos$mutable.func_239621_a_(growPos, i1, k, j1);
	               if (func_236315_a_(world, blockpos$mutable, false)) {
	                  if (config.plantedTest && !world.getBlockState(blockpos$mutable.down()).isAir()) {
	                     world.destroyBlock(blockpos$mutable, true);
	                  }

	                  if (flag5) {
	                     if (!flag3) {
	                        this.func_236318_a_(world, rand, blockpos$mutable, config.hat, isNetherWart);
	                     }
	                  } else if (flag3) {
	                     this.func_236316_a_(world, rand, config, blockpos$mutable, 0.1F, 0.2F, isNetherWart ? 0.1F : 0.0F);
	                  } else if (flag4) {
	                     this.func_236316_a_(world, rand, config, blockpos$mutable, 0.01F, 0.7F, isNetherWart ? 0.083F : 0.0F);
	                  } else {
	                     this.func_236316_a_(world, rand, config, blockpos$mutable, 5.0E-4F, 0.98F, isNetherWart ? 0.07F : 0.0F);
	                  }
	               }
	            }
	         }
	      }

	   }

	   private void func_236316_a_(IWorld world, Random random, NetherForestConfig config, BlockPos.Mutable mut, float float1, float float2, float float3) {
	      if (random.nextFloat() < float1) {
	         this.func_230367_a_(world, mut, config.decor);
	      } else if (random.nextFloat() < float2) {
	         this.func_230367_a_(world, mut, config.hat);
	         if (random.nextFloat() < float3) {
	            genWeepingVine(mut, world, random);
	         }
	      }

	   }

	   private void func_236318_a_(IWorld world, Random rand, BlockPos pos, BlockState hat, boolean isNetherWart) {
	      if (world.getBlockState(pos.down()).isIn(hat.getBlock())) {
	         this.func_230367_a_(world, pos, hat);
	      } else if ((double)rand.nextFloat() < 0.15D) {
	         this.func_230367_a_(world, pos, hat);
	         if (isNetherWart && rand.nextInt(11) == 0) {
	            genWeepingVine(pos, world, rand);
	         }
	      }

	   }

	   @Nullable
	   private static BlockPos.Mutable searchDownForFungusPlacement(IWorld world, BlockPos pos, Block block1) {
	      BlockPos.Mutable blockpos$mutable = pos.toMutable();

	      for(int i = pos.getY(); i >= 1; --i) {
	         blockpos$mutable.setY(i);
	         Block block = world.getBlockState(blockpos$mutable.down()).getBlock();
	         if (block == block1) {
	            return blockpos$mutable;
	         }
	      }

	      return null;
	   }

	   private static void genWeepingVine(BlockPos pos, IWorld world, Random rand) {
	      BlockPos.Mutable blockpos$mutable = pos.toMutable().move(Direction.DOWN);
	      if (world.isAirBlock(blockpos$mutable)) {
	         int i = MathHelper.nextInt(rand, 1, 5);
	         if (rand.nextInt(7) == 0) {
	            i *= 2;
	         }

	         int j = 23;
	         int k = 25;
	         WeepingVineFeature.func_236427_a_(world, rand, blockpos$mutable, i, j, k);
	      }
	   }
	}