package com.redd90.betternether.world.gen.placement;

import java.util.Random;
import java.util.stream.Stream;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.placement.Placement;

public class FloorSearch extends Placement<DensityConfig> {

	public FloorSearch(Codec<DensityConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator generatorIn, Random random,
			DensityConfig config, BlockPos pos) {
		int top = 128;
		int x = pos.getX();
		int z = pos.getZ();
		
		Mutable mut = new Mutable(x,top,z);
		float density = config.density;
		Stream.Builder<BlockPos> stream = Stream.builder();
		for(int i=0;i<16;i++) {
			mut.setX(x+i);
			
			mut.setZ(z);
			for (int k=0;k<16;k++) {
				mut.setZ(z+k);
				
				mut.setY(top);
				for (int m=top-1;m>=0;m--) {
					mut.setY(m);
					//BetterNether.LOGGER.debug("Testing " + mut.toString());
					if(random.nextFloat()<density) {
						if(worldIn.isAirBlock(mut) && !worldIn.isAirBlock(mut.down()))
						stream.add(mut.toImmutable());
						//BetterNether.LOGGER.debug("Added potential floor feature location: " + mut.toString());
					}
				}
			}
		}
		return stream.build();
	}

}
