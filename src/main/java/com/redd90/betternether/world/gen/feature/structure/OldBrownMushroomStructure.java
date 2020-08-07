package com.redd90.betternether.world.gen.feature.structure;

import com.mojang.serialization.Codec;
import com.redd90.betternether.world.gen.feature.StructureFrequencyConfig;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.MarginedStructureStart;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.gen.settings.StructureSeparationSettings;

public class OldBrownMushroomStructure extends Structure<StructureFrequencyConfig> {
	public OldBrownMushroomStructure(Codec<StructureFrequencyConfig> p_i232105_1_) {
		super(p_i232105_1_);
	}

	public IStartFactory<StructureFrequencyConfig> getStartFactory() {
		return OldBrownMushroomStructure.Start::new;
	}

	public StructureStart<?> func_236389_a_(ChunkGenerator generator, BiomeProvider biomeProvider, TemplateManager templateManager, long seed, ChunkPos chunkPos, Biome biome, int p_236389_8_, SharedSeedRandom seedRandom, StructureSeparationSettings separation, StructureFrequencyConfig config) {
		ChunkPos chunkpos = this.func_236392_a_(separation, seed, seedRandom, chunkPos.x, chunkPos.z);
		if (chunkPos.x == chunkpos.x && chunkPos.z == chunkpos.z && this.func_230363_a_(generator, biomeProvider, seed, seedRandom, chunkPos.x, chunkPos.z, biome, chunkpos, config)) {
			int count = config.frequency;
			for(int i=0; i<count;i++) {
				StructureStart<StructureFrequencyConfig> structurestart = this.func_236387_a_(chunkPos.x, chunkPos.z, MutableBoundingBox.getNewBoundingBox(), p_236389_8_, seed);
				structurestart.func_230364_a_(generator, templateManager, chunkPos.x, chunkPos.z, biome, config);
				if (structurestart.isValid()) {
					return structurestart;
				}
			}
		}

		return StructureStart.DUMMY;
	}
		   
		   public static class Start extends MarginedStructureStart<StructureFrequencyConfig> {
		      public Start(Structure<StructureFrequencyConfig> p_i232106_1_, int p_i232106_2_, int p_i232106_3_, MutableBoundingBox p_i232106_4_, int p_i232106_5_, long p_i232106_6_) {
		         super(p_i232106_1_, p_i232106_2_, p_i232106_3_, p_i232106_4_, p_i232106_5_, p_i232106_6_);
		      }

		      public void func_230364_a_(ChunkGenerator p_230364_1_, TemplateManager p_230364_2_, int p_230364_3_, int p_230364_4_, Biome p_230364_5_, StructureFrequencyConfig p_230364_6_) {
		         ChunkPos chunkpos = new ChunkPos(p_230364_3_, p_230364_4_);
		         int i = chunkpos.getXStart() + this.rand.nextInt(16);
		         int j = chunkpos.getZStart() + this.rand.nextInt(16);
		         int k = p_230364_1_.func_230356_f_();
		         int l = k + this.rand.nextInt(p_230364_1_.func_230355_e_() - 2 - k);
		         IBlockReader iblockreader = p_230364_1_.func_230348_a_(i, j);

		         for(BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(i, l, j); l > k; --l) {
		            BlockState blockstate = iblockreader.getBlockState(blockpos$mutable);
		            blockpos$mutable.move(Direction.DOWN);
		            BlockState blockstate1 = iblockreader.getBlockState(blockpos$mutable);
		            if (blockstate.isAir() && (blockstate1.isIn(Blocks.SOUL_SAND) || blockstate1.isSolidSide(iblockreader, blockpos$mutable, Direction.UP))) {
		               break;
		            }
		         }

		         if (l > k) {
		            OldBrownMushroomPieces.func_236994_a_(p_230364_2_, this.components, this.rand, new BlockPos(i, l, j));
		            this.recalculateStructureSize();
		         }
		      }
		   }
	
}
