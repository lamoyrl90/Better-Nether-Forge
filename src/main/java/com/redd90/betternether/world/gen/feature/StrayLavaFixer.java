package com.redd90.betternether.world.gen.feature;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import com.mojang.serialization.Codec;
import com.redd90.betternether.util.BlocksHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class StrayLavaFixer extends Feature<NoFeatureConfig> {

	private static final BlockState SOUL_SOIL = Blocks.SOUL_SOIL.getDefaultState();
	private static final BlockState LAVA = Blocks.LAVA.getDefaultState();
	private static final BlockState AIR = Blocks.AIR.getDefaultState();
	public static final Set<Biome> ValidBiomes = new HashSet<>();
	
	public StrayLavaFixer(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGen, Random random, BlockPos pos, NoFeatureConfig config) {

		
			
		
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
		for(int i = 0; i < 16; ++i) {
			for(int j = 0; j < 16; ++j) {
				int x = pos.getX() + i;
				int z = pos.getZ() + j;
				for(int y=127; y >= 0; y--) {
					blockpos$mutable.setPos(x, y, z);
					
					if(world.getBlockState(blockpos$mutable) == LAVA) {
						if(world.isAirBlock(blockpos$mutable.east()) ||
								world.isAirBlock(blockpos$mutable.west()) ||
								world.isAirBlock(blockpos$mutable.north()) ||
								world.isAirBlock(blockpos$mutable.south())){
							if(world.isAirBlock(blockpos$mutable.down())) {
								world.setBlockState(blockpos$mutable, AIR, BlocksHelper.SET_SILENT);
							} else {
								world.setBlockState(blockpos$mutable, SOUL_SOIL, BlocksHelper.SET_SILENT);
							}
						} else if(world.isAirBlock(blockpos$mutable.down())){
							world.setBlockState(blockpos$mutable, SOUL_SOIL, BlocksHelper.SET_SILENT);
						} else if (world.getBlockState(blockpos$mutable.down()) == LAVA)
							break;
					}
				
				}				
			}			
		}
		
		return true;
	}
	
}
