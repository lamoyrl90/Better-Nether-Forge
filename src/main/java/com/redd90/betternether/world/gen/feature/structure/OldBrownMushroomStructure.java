package com.redd90.betternether.world.gen.feature.structure;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNTags;
import com.redd90.betternether.world.gen.feature.StructureFrequencyConfig;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.template.TemplateManager;

@SuppressWarnings("deprecation")
public class OldBrownMushroomStructure extends AbstractScatteredStructure {

	public OldBrownMushroomStructure(Codec<StructureFrequencyConfig> codec) {
		super(codec);
	}

	public Structure.IStartFactory<StructureFrequencyConfig> getStartFactory() {
	      return OldBrownMushroomStructure.Start::new;
	   }

	   public static class Start extends AbstractBNStructure.Start {
	      public Start(Structure<StructureFrequencyConfig> structure, int chunkx, int chunkz, MutableBoundingBox bounds, int reference, long seed) {
	         super(structure, chunkx, chunkz, bounds, reference, seed);
	      }

	      public void func_230364_a_(ChunkGenerator generator, TemplateManager manager, int chunkx, int chunkz, Biome biome, StructureFrequencyConfig config) {
				ChunkPos chunkpos = new ChunkPos(chunkx, chunkz);
				IBlockReader blockreader = generator.func_230348_a_(chunkx, chunkz);
				int frequency = config.frequency;
				
				//Loop until frequency is met
				for(int t = 0; t < frequency; t++) {
					int i = chunkpos.getXStart() + this.rand.nextInt(16);
					int j = chunkpos.getZStart() + this.rand.nextInt(16);
					int k = generator.func_230356_f_(); //getCeilingHeight
					int l = k + this.rand.nextInt(generator.func_230355_e_() - 2 - k);
					IBlockReader iblockreader = generator.func_230348_a_(i, j);
		
					//Block placement rules go in here
					for(BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable(i, l, j); l > k; --l) {
						BlockState blockstate = iblockreader.getBlockState(blockpos$mutable);
						blockpos$mutable.move(Direction.DOWN);
						BlockState blockstate1 = iblockreader.getBlockState(blockpos$mutable);
						if (blockstate.isAir() && (blockstate1.isIn(BNTags.Blocks.NETHER_GROUND))) {
							break;
						}
					}
					
					
					//This is where the bits are generated
					OldBrownMushroomPieces.addPiece(manager, this.components, this.rand, new BlockPos(i, l, j), blockreader);
					this.recalculateStructureSize();
					
				}
	      }
	   }
}
