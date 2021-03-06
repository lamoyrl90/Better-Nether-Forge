package com.redd90.betternether.block;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.TorchBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GlowstoneTorchBlock extends TorchBlock implements IRenderTypeable {

	private BNRenderLayer layer = BNRenderLayer.CUTOUT;
	
	public GlowstoneTorchBlock(Properties properties) {
		super(properties, null);
	}
	
	public void setRenderLayer(BNRenderLayer layer)
	{
		this.layer = layer;
	}
	
	@Override
	public BNRenderLayer getRenderLayer() {
		return layer;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {

	}
	
}
