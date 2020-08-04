package com.redd90.betternether.block;

import net.minecraft.block.PaneBlock;

public class BNPaneBlock extends PaneBlock implements IRenderTypeable {
	
	private BNRenderLayer layer = BNRenderLayer.CUTOUT;
	
	public BNPaneBlock(Properties properties) {
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
