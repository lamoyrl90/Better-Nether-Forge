package com.redd90.betternether.block;

import net.minecraft.block.FenceGateBlock;

public class BNGateBlock extends FenceGateBlock implements IRenderTypeable {

	public BNGateBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BNRenderLayer getRenderLayer() {
		return BNRenderLayer.SOLID;
	}

}
