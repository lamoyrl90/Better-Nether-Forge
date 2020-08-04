package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MoldBlock extends BNBlock {
	public MoldBlock(MaterialColor color)
	{
		super(Block.Properties.create(Material.PLANTS, color)
				.sound(SoundType.CROP)
				.notSolid()
				.doesNotBlockMovement()
				.zeroHardnessAndResistance()
				.tickRandomly());
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}
	
	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightLevel(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}
	
	@Override
	public Block.OffsetType getOffsetType()
	{
		return Block.OffsetType.XZ;
	}
	
	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return BlocksHelper.isNetherMycelium(world.getBlockState(pos.down()));
	}
	
	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (!isValidPosition(state, world, pos))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random)
	{
		super.tick(state, world, pos, random);
		if (random.nextInt(16) == 0)
		{
			int c = 0;
			c = world.getBlockState(pos.north()).getBlock() == this ? c++ : c;
			c = world.getBlockState(pos.south()).getBlock() == this ? c++ : c;
			c = world.getBlockState(pos.east()).getBlock() == this ? c++ : c;
			c = world.getBlockState(pos.west()).getBlock() == this ? c++ : c;
			if (c < 2)
			{
				BlockPos npos = new BlockPos(pos);
				switch (random.nextInt(4))
				{
				case 0:
					npos = npos.add(-1, 0, 0);
					break;
				case 1:
					npos = npos.add(1, 0, 0);
					break;
				case 2:
					npos = npos.add(0, 0, -1);
					break;
				default:
					npos = npos.add(0, 0, 1);
					break;
				}
				if (world.isAirBlock(npos) && isValidPosition(state, world, npos))
				{
					BlocksHelper.setWithoutUpdate(world, npos, getDefaultState());
				}
			}
		}
	}
}
