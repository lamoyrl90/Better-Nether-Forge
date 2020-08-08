package com.redd90.betternether.block;

import net.minecraft.block.SlabBlock;

public class BNSlabBlock extends SlabBlock implements IRenderTypeable {

	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	public BNSlabBlock(Properties properties) {
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
