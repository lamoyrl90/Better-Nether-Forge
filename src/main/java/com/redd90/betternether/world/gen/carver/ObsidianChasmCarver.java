package com.redd90.betternether.world.gen.carver;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

import org.apache.commons.lang3.mutable.MutableBoolean;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.CaveWorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class ObsidianChasmCarver extends CaveWorldCarver {
	   public ObsidianChasmCarver(Codec<ProbabilityConfig> codec) {
	      super(codec, 128);
	      this.carvableBlocks = ImmutableSet.of(Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.NETHERRACK, Blocks.SOUL_SAND, Blocks.SOUL_SOIL, Blocks.CRIMSON_NYLIUM, Blocks.WARPED_NYLIUM, Blocks.NETHER_WART_BLOCK, Blocks.WARPED_WART_BLOCK, Blocks.BASALT, Blocks.BLACKSTONE, Blocks.MAGMA_BLOCK);
	      this.carvableFluids = ImmutableSet.of(Fluids.LAVA, Fluids.WATER);
	   }

	   protected int func_230357_a_() {
	      return 10;
	   }

	   protected float func_230359_a_(Random p_230359_1_) {
	      return (p_230359_1_.nextFloat() * 2.0F + p_230359_1_.nextFloat()) * 2.0F;
	   }

	   protected double func_230360_b_() {
	      return 5.0D;
	   }

	   protected int func_230361_b_(Random p_230361_1_) {
	      return p_230361_1_.nextInt(this.maxHeight);
	   }

	   protected boolean func_230358_a_(IChunk chunkIn, Function<BlockPos, Biome> biomeFunction, BitSet bitSet, Random rand, BlockPos.Mutable mutable1, BlockPos.Mutable mutable2, BlockPos.Mutable mutable3, int p_230358_8_, int p_230358_9_, int p_230358_10_, int p_230358_11_, int p_230358_12_, int p_230358_13_, int p_230358_14_, int p_230358_15_, MutableBoolean p_230358_16_) {
	      int i = p_230358_13_ | p_230358_15_ << 4 | p_230358_14_ << 8;
	      if (bitSet.get(i)) {
	         return false;
	      } else {
	         bitSet.set(i);
	         mutable1.setPos(p_230358_11_, p_230358_14_, p_230358_12_);
	         if (this.isCarvable(chunkIn.getBlockState(mutable1))) {
	            BlockState blockstate;
	            if (p_230358_14_ <= 31) {
	               blockstate = LAVA.getBlockState();
	            } else {
	               blockstate = CAVE_AIR;
	            }

	            chunkIn.setBlockState(mutable1, blockstate, false);
	            return true;
	         } else {
	            return false;
	         }
	      }
	   }
	}