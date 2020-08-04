package com.redd90.betternether.block;

import net.minecraft.block.RotatedPillarBlock;

public class BNPillarBlock extends RotatedPillarBlock implements IRenderTypeable {

	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	public BNPillarBlock(Properties properties) {
		super(properties);
	}
	
	public void setRenderLayer(BNRenderLayer layer)
	{
		this.layer = layer;
	}
	
	@Override
	public BNRenderLayer getRenderLayer() {
		return layer;
	}

}
