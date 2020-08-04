package com.redd90.betternether.world.gen.placement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.placement.Placement;

public class WallSearch extends Placement<DensityConfig> {

	Direction DOWN = Direction.DOWN;
	Direction UP = Direction.UP;
	Direction NORTH = Direction.NORTH;
	Direction EAST = Direction.EAST;
	Direction SOUTH = Direction.SOUTH;
	Direction WEST = Direction.WEST;
	Direction[] directions = {NORTH, EAST, SOUTH, WEST};
	
	
	public WallSearch(Codec<DensityConfig> codec) {
		super(codec);
	}

	public List<Direction> getAdjacentAirBlocks(IWorld world, BlockPos pos) {
		List<Direction> adjacentAirBlocks = new ArrayList<Direction>();
		for(Direction dir : directions) {
			if (world.isAirBlock(pos.offset(dir)))
				adjacentAirBlocks.add(dir);
		}
		return adjacentAirBlocks;
	}
	
	public boolean isValidWall(IWorld world, BlockPos pos, List<Direction> airBlocks) {
		return !airBlocks.isEmpty() && !world.isAirBlock(pos);
	}
	
	@Override
	public Stream<BlockPos> getPositions(IWorld worldIn, ChunkGenerator generatorIn, Random random,
			DensityConfig configIn, BlockPos pos) {
		List<BlockPos> list = Lists.newArrayList();
		List<Direction> adjacentAirBlocks = new ArrayList<Direction>();
		Mutable npos = new Mutable();
		Direction dir = DOWN;
		float density = configIn.density;
		int min = (int) Math.floor(density);
		float chanceExtra = density - min;
		int count = random.nextFloat() <= chanceExtra ? min + 1 : min;
		for(int i = 0; i < count; ++i) {
			
			//Randomize search direction
			if (random.nextBoolean()) {
				dir = DOWN;}
			else {dir = UP;}
			
			//Randomize search start
			int j = random.nextInt(14) + pos.getX()+1;
			int k = random.nextInt(14) + pos.getZ()+1;
			int l = random.nextInt(120) + 4;
			npos.setPos(j,l,k);
			
			//Check to see if we're inside a nonvalid, non-air block
			if (!worldIn.isAirBlock(npos))
				//get adjacent air blocks
				adjacentAirBlocks = getAdjacentAirBlocks(worldIn, npos);
				//while we're still in a nonvalid wall inside the world, move and update adjacent air blocks
				while(!isValidWall(worldIn, npos, adjacentAirBlocks) && 128 > npos.getY() && npos.getY() > 0) {
					npos.move(dir);
					adjacentAirBlocks=getAdjacentAirBlocks(worldIn, npos);
				}
			//while we're still air and between the bedrock layers, move
			while(worldIn.isAirBlock(npos) && 128 > npos.getY() && npos.getY() > 0) {
				npos.move(dir);
				}
			
			//If we're still inside the world and we have a valid wall block, return a random valid direction
			if (128 > npos.getY() && npos.getY() > 0) {
				adjacentAirBlocks = getAdjacentAirBlocks(worldIn, npos);
				if(isValidWall(worldIn, npos, adjacentAirBlocks)) {
					npos.move(adjacentAirBlocks.get(random.nextInt(adjacentAirBlocks.size())));
					list.add((npos.up()).toImmutable());
				}
			}
		}
	      return list.stream();
	}
}
