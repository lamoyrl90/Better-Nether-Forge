package com.redd90.betternether.block;

import net.minecraft.block.PressurePlateBlock;

public class BNPlateBlock extends PressurePlateBlock implements IRenderTypeable {

	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	public BNPlateBlock(PressurePlateBlock.Sensitivity sensitivity, Properties properties) {
		super(sensitivity, properties);
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
