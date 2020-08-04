package com.redd90.betternether.world.gen.feature;

import java.util.Random;

import javax.annotation.Nullable;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNTags;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class NetherrackFeature extends Feature<NoFeatureConfig> {

	private BlockState blockstate;
	
	public NetherrackFeature(BlockState blockstate, Codec<NoFeatureConfig> codec) {
		super(codec);
		this.blockstate = blockstate;
	}


	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator,
			Random rand, BlockPos pos, NoFeatureConfig config) {
		if (world.getBlockState(pos).isIn(BNTags.Blocks.NETHERRACK) && world.getBlockState(pos).isAir()) {
			BlocksHelper.setWithoutUpdate(world, pos, blockstate);
			return true;
		} else return false;
	}
	
	   @Nullable
	   private static BlockPos.Mutable searchDownForPlacement(IWorld world, BlockPos pos) {
	      BlockPos.Mutable blockpos$mutable = pos.toMutable();

	      for(int i = pos.getY(); i >= 1; --i) {
	         blockpos$mutable.setY(i);
	         Block block = world.getBlockState(blockpos$mutable.down()).getBlock();
	         if (block.isIn(BNTags.Blocks.NETHER_GROUND)) {
	            return blockpos$mutable;
	         }
	      }

	      return null;
	   }

}
