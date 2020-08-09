package com.redd90.betternether.block;

import net.minecraft.block.FenceBlock;

public class BNFenceBlock extends FenceBlock implements IRenderTypeable {

	public BNFenceBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BNRenderLayer getRenderLayer() {
		return BNRenderLayer.SOLID;
	}
}
