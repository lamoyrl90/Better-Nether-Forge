package com.redd90.betternether.world.gen.placement;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.placement.Placement;

public class VerticalUp extends Placement<DensityConfig> {

	public VerticalUp(Codec<DensityConfig> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator generatorIn, Random random,
			DensityConfig configIn, BlockPos pos) {
		List<BlockPos> list = Lists.newArrayList();
		Mutable npos = new Mutable();
		float density = configIn.density;
		int min = (int) Math.floor(density);
		float chanceExtra = density - min;
		int count = random.nextFloat() <= chanceExtra ? min + 1 : min;
		for(int i = 0; i < count; ++i) {
			int j = random.nextInt(16) + pos.getX();
			int k = random.nextInt(16) + pos.getZ();
			int l = random.nextInt(120) + 4;
			npos.setPos(j,l,k);
			
			if (!worldIn.isAirBlock(npos) && !worldIn.isAirBlock(npos.down()) && npos.getY()<=128)
				while(!worldIn.isAirBlock(npos)) {
					npos.setY(npos.getY()+1);};
			while(worldIn.isAirBlock(npos) && npos.getY()<=128) {
				npos.setY(npos.getY()+1);}
			
			//while(!worldIn.hasBlockState(npos, (state)->state.isIn(BNTags.Blocks.NETHER_GROUND))) {
			//	npos.setY(npos.getY()+1);}
			if (npos.getY()<128)
				list.add((npos.down()).toImmutable());
		}

	      return list.stream();
	}

}
