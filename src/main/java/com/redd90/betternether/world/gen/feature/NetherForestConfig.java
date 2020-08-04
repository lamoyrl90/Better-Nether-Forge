package com.redd90.betternether.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.IFeatureConfig;

public class NetherForestConfig implements IFeatureConfig {
	   public static final Codec<NetherForestConfig> codec = RecordCodecBuilder.create((config) -> {
	      return config.group(BlockState.BLOCKSTATE_CODEC.fieldOf("valid_base_block").forGetter((validBaseBlock) -> {
	         return validBaseBlock.baseBlock;
	      }), BlockState.BLOCKSTATE_CODEC.fieldOf("stem_state").forGetter((stemState) -> {
	         return stemState.stem;
	      }), BlockState.BLOCKSTATE_CODEC.fieldOf("hat_state").forGetter((hatState) -> {
	         return hatState.hat;
	      }), BlockState.BLOCKSTATE_CODEC.fieldOf("decor_state").forGetter((decorState) -> {
	         return decorState.decor;
	      }), Codec.BOOL.fieldOf("planted").withDefault(false).forGetter((planted) -> {
	         return planted.plantedTest;
	      })).apply(config, NetherForestConfig::new);
	   });
	   public static final NetherForestConfig CRIMSON = new NetherForestConfig(Blocks.CRIMSON_NYLIUM.getDefaultState(), Blocks.CRIMSON_STEM.getDefaultState(), Blocks.NETHER_WART_BLOCK.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), true);
	   public static final NetherForestConfig CRIMSON_CONFIG;
	   public static final NetherForestConfig WARPED = new NetherForestConfig(Blocks.WARPED_NYLIUM.getDefaultState(), Blocks.WARPED_STEM.getDefaultState(), Blocks.WARPED_WART_BLOCK.getDefaultState(), Blocks.SHROOMLIGHT.getDefaultState(), true);
	   public static final NetherForestConfig WARPED_CONFIG;
	   public final BlockState baseBlock;
	   public final BlockState stem;
	   public final BlockState hat;
	   public final BlockState decor;
	   public final boolean plantedTest;

	   public NetherForestConfig(BlockState baseBlock, BlockState stem, BlockState hat, BlockState decor, boolean plantedTest) {
	      this.baseBlock = baseBlock;
	      this.stem = stem;
	      this.hat = hat;
	      this.decor = decor;
	      this.plantedTest = plantedTest;
	   }

	   static {
	      CRIMSON_CONFIG = new NetherForestConfig(CRIMSON.baseBlock, CRIMSON.stem, CRIMSON.hat, CRIMSON.decor, false);
	      WARPED_CONFIG = new NetherForestConfig(WARPED.baseBlock, WARPED.stem, WARPED.hat, WARPED.decor, false);
	   }
	}