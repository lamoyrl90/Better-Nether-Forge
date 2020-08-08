package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.registry.BNBiomes;
import com.redd90.betternether.world.gen.feature.plants.IMushroomFeature;
import com.redd90.betternether.world.gen.feature.plants.MediumBrownMushroomFeature;
import com.redd90.betternether.world.gen.feature.plants.MediumRedMushroomFeature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.server.ServerWorld;

public class NetherMyceliumBlock extends BNBlock {

	public static final BooleanProperty IS_BLUE = BooleanProperty.create("blue");
	
	public NetherMyceliumBlock() {
		super(BNBlockProperties.netherrack());
		this.properties.tickRandomly().sound(SoundType.NYLIUM);
		this.setDefaultState(getStateContainer().getBaseState().with(IS_BLUE, false));
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(IS_BLUE);
	}
	
	@SuppressWarnings("deprecation")
	public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
		if (!worldIn.isAreaLoaded(pos, 3)) return;
		
		if (hasSolidSide(worldIn.getBlockState(pos.up()), worldIn, pos.up(), Direction.DOWN)) {
			worldIn.setBlockState(pos, Blocks.NETHERRACK.getDefaultState());
			return;
		}
		
		if (random.nextInt(24) == 0) {
			if (worldIn.getBlockState(pos.up()).getBlock() == Blocks.BROWN_MUSHROOM || worldIn.getBlockState(pos.up()).getBlock() == Blocks.RED_MUSHROOM) {
				
				Block mushroom = worldIn.getBlockState(pos.up()).getBlock();
				
				if (random.nextInt(6) == 0) {
					IMushroomFeature mushfeature = mushroom == Blocks.BROWN_MUSHROOM ? new MediumBrownMushroomFeature(NoFeatureConfig.field_236558_a_) : new MediumRedMushroomFeature(NoFeatureConfig.field_236558_a_);
					mushfeature.grow(worldIn, pos.up(), random);
				} else {
					for(int t=0; t<24; ++t) {
						int i = random.nextInt(5)-2;
						int j = random.nextInt(5)-2;
						int k = random.nextInt(3)-1;
						BlockPos npos = new BlockPos(pos.getX() + i, pos.getY() + j + 1, pos.getZ() + k);
						
						if (worldIn.isAirBlock(npos) && Blocks.BROWN_MUSHROOM.isValidPosition(mushroom.getDefaultState(), worldIn, npos)) {
							worldIn.setBlockState(npos, mushroom.getDefaultState());
							break;
						}
					}
				}
			}
		}
		
		if (worldIn.getBiome(pos) == BNBiomes.FUNGAL_WOODLANDS.get() || worldIn.getBiome(pos) == BNBiomes.OLD_FUNGIWOODS.get()) {
			for (int i=0; i<4; ++i) {
				BlockPos blockpos = pos.add(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
				if (this.canSpread(worldIn, blockpos)) {
					worldIn.setBlockState(blockpos, this.getDefaultState());
				}
			}
		}
	}
	
	private boolean canSpread(IWorld world, BlockPos pos) {
		BlockState up = world.getBlockState(pos.up());
		return world.getBlockState(pos).isIn(Blocks.NETHERRACK) && !hasSolidSide(up, world, pos.up(), Direction.DOWN);
	}
	
	public void animateTick(BlockState state, World world, BlockPos pos, Random random)
	{
		super.animateTick(state, world, pos, random);
		world.addParticle(ParticleTypes.MYCELIUM,
				pos.getX() + random.nextDouble(),
				pos.getY() + 1.1D,
				pos.getZ() + random.nextDouble(),
				0.0D, 0.0D, 0.0D);
	}
	
}
