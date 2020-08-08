package com.redd90.betternether.world.gen.feature.city;

import java.util.List;

import com.mojang.serialization.Codec;
import com.redd90.betternether.BNConfig;

import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.provider.BiomeProvider;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.FlatChunkGenerator;
import net.minecraft.world.gen.Heightmap.Type;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class CityStructure extends Structure<NoFeatureConfig> {

	private final int distance;
	private final int separation;
	
	public CityStructure(Codec<NoFeatureConfig> codec) {
		super(codec);
		distance = 64;//BNConfig.CitySeparation;
		separation = distance >> 1;
	}

	private static final CityPieces PIECES = new CityPieces();
	
	@Override
	public IStartFactory<NoFeatureConfig> getStartFactory() {
		return CityStructure.Start::new;
	}
	
	public String getStructureName() {
		return "nether_city";
	}
	
	protected boolean func_230363_a_(ChunkGenerator generator, BiomeProvider biomeProvider, long seed, SharedSeedRandom seedRandom, int chunkx, int chunkz, Biome biome, ChunkPos chunkpos, NoFeatureConfig config) {
		int q = chunkpos.x < 0 ? chunkpos.x - separation + 1 : chunkpos.x;
		int r = chunkpos.z < 0 ? chunkpos.z - separation + 1 : chunkpos.z;
		int s = q / distance;
		int t = r / distance;
		seedRandom.setLargeFeatureSeedWithSalt(seed, s, t, 897527);
		s *= distance;
		t *= distance;
		s += seedRandom.nextInt(separation);
		t += seedRandom.nextInt(separation);
		return s == chunkpos.x && t == chunkpos.z;
	}
	
	public class Start extends StructureStart<NoFeatureConfig> {

		public Start(Structure<NoFeatureConfig> structure, int chunkx, int chunkz,
				MutableBoundingBox bounds, int reference, long seed) {
			super(structure, chunkx, chunkz, bounds, reference, seed);
		}

		@Override
		public void func_230364_a_(ChunkGenerator generator, TemplateManager manager, int chunkx,
				int chunkz, Biome biome, NoFeatureConfig config) {
			int px = (chunkx << 4) | 8;
			int pz = (chunkz << 4) | 8;
			int y = 40;
			if (generator instanceof FlatChunkGenerator)
			{
				y = generator.getHeight(px, pz, Type.WORLD_SURFACE);
			}
			
			BlockPos center = new BlockPos(px, y, pz);
			
			//CityPalette palette = Palettes.getRandom(random);
			List<CityPiece> buildings = PIECES.generate(center, this.rand, Palettes.EMPTY);
			MutableBoundingBox cityBox = MutableBoundingBox.getNewBoundingBox();
			for (CityPiece p: buildings)
				cityBox.expandTo(p.getBoundingBox());
			
			int d1 = Math.max((center.getX() - cityBox.minX), (cityBox.maxX - center.getX()));
			int d2 = Math.max((center.getZ() - cityBox.minZ), (cityBox.maxZ - center.getZ()));
			int radius = Math.max(d1, d2);
			if (radius / 2 + center.getY() < cityBox.maxY)
			{
				radius = (cityBox.maxY - center.getY()) / 2;
			}
			
			if (!(generator instanceof FlatChunkGenerator))
			{
				CavePiece cave = new CavePiece(center, radius, rand);
				this.components.add(cave);
				this.components.addAll(buildings);
				this.bounds = cave.getBoundingBox();
			}
			else
			{
				this.components.addAll(buildings);
				this.recalculateStructureSize();
			}
			
			this.bounds.minX -= 12;
			this.bounds.maxX += 12;
			this.bounds.minZ -= 12;
			this.bounds.maxZ += 12;
		}

	}
}
