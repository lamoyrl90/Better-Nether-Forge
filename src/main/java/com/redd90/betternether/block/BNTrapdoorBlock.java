package com.redd90.betternether.block;

import net.minecraft.block.TrapDoorBlock;

public class BNTrapdoorBlock extends TrapDoorBlock implements IRenderTypeable {

	public BNTrapdoorBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BNRenderLayer getRenderLayer() {
		return BNRenderLayer.CUTOUT;
	}

}
