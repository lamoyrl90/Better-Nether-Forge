package com.redd90.betternether.block;

import net.minecraft.block.Block;

public class BNBlock extends Block implements IRenderTypeable {


	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	public BNBlock(Properties properties) {
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
