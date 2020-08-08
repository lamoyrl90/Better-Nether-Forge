package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.registry.BNTags;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;

public class NetherrackMossBlock extends BNBlock {

	public NetherrackMossBlock() {
		super(BNBlockProperties.netherrack());
		this.properties.tickRandomly().sound(SoundType.NYLIUM);
	}
	
	private boolean canSpread(IWorld world, BlockPos pos) {
		BlockState up = world.getBlockState(pos.up());
		boolean flag = false;
		for (Direction dir : Direction.values()) {
			if (world.getBlockState(pos.offset(dir)).isIn(BNTags.Blocks.SOUL_GROUND)){
				flag = true;
				break;
			}
		}
		return flag && world.getBlockState(pos).isIn(Blocks.NETHERRACK) && !hasSolidSide(up, world, pos.up(), Direction.DOWN);
	}

	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;
		
		if (hasSolidSide(worldIn.getBlockState(pos.up()), worldIn, pos.up(), Direction.DOWN)) {
			worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			return;
		}
		
		for (int i=0; i<4; ++i) {
			BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
			if (this.canSpread(worldIn, blockpos)) {
				worldIn.setBlockState(blockpos, this.getDefaultState());
			}
		}
	}
	
}
