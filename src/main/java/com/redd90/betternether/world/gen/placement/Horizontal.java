package com.redd90.betternether.world.gen.placement;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.redd90.betternether.BetterNether;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.placement.FrequencyConfig;
import net.minecraft.world.gen.placement.Placement;

//////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////////////        CURRENTLY BROKEN          ////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////

public class Horizontal extends Placement<FrequencyConfig> {
	
	public Horizontal(Codec<FrequencyConfig> codec) {
		super(codec);
	}

	private boolean checkWall(IWorld world, BlockPos pos) {
		return world.isAirBlock(pos) && world.isAirBlock(pos.up()) && world.isAirBlock(pos.down());
	}
	
	private boolean isInChunk(int x, int z, BlockPos pos) {
		return (pos.getX() > x && pos.getX() < x+15) && (pos.getZ() > z && pos.getZ() < z+15);
	}
	
	@Override
	public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator generatorIn, Random random,
			FrequencyConfig configIn, BlockPos pos) {
		List<BlockPos> list = Lists.newArrayList();
		Mutable npos = pos.toMutable();
		BetterNether.LOGGER.debug("Chunk starting at: " + pos.toString());
		for(int i = 0; i < configIn.count + 1; ++i) {
			
			Direction NORTH = Direction.NORTH;
			Direction EAST = Direction.EAST;
			Direction SOUTH = Direction.SOUTH;
			Direction WEST = Direction.WEST;
			Direction[] directions = {NORTH, EAST, SOUTH, WEST};
			int x = pos.getX();
			int z = pos.getX();
			
			
			
			testTwice:
			for(int t=0;t<2;t++) {
				Collections.shuffle(Arrays.asList(directions));			
				int j = random.nextInt(14) + x + 1;
				int k = random.nextInt(14) + z + 1;
				int l = random.nextInt(120) + 4;
				for(Direction dir:directions) {
					BetterNether.LOGGER.debug("Direction: " + dir.toString());
					npos.setPos(j,l,k);
					BetterNether.LOGGER.debug("Position: " + npos.toString());
					if (!worldIn.isAirBlock(npos))// && !checkWall(worldIn, npos.offset(dir.getOpposite())))
						BetterNether.LOGGER.debug("Line 62 pass");
						while(!worldIn.isAirBlock(npos) && isInChunk(x,z,npos)) {
							BetterNether.LOGGER.debug("Moving: " + dir.toString());
							npos.move(dir);
							BetterNether.LOGGER.debug("Position: " + npos.toString());
							}
					while(worldIn.isAirBlock(npos) && isInChunk(x,z,npos)) {
						npos.move(dir);
						}	
					if (checkWall(worldIn, npos.offset(dir.getOpposite()))) {
						list.add((npos.offset(dir.getOpposite())).toImmutable());
						break testTwice;
					}
					
				}
			}
		}
		return list.stream();
	}

}