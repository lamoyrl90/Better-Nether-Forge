package com.redd90.betternether.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

public class CincinnasiteFrameBlock extends BNBlock {

	public CincinnasiteFrameBlock(Properties properties) {
		super(properties);
		this.properties.notSolid();
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}

	@OnlyIn(Dist.CLIENT)
	public float getAmbientOcclusionLightLevel(BlockState state, IBlockReader view, BlockPos pos)
	{
		return 1.0F;
	}
	
	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader view, BlockPos pos)
	{
		return true;
	}
	
	@SuppressWarnings("deprecation")
	@OnlyIn(Dist.CLIENT)
	public boolean isSideInvisible(BlockState state, BlockState neighbor, Direction facing)
	{
		return neighbor.getBlock() == this ? true : super.isSideInvisible(state, neighbor, facing);
	}
}
