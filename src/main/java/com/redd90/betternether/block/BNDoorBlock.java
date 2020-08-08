package com.redd90.betternether.block;

import net.minecraft.block.DoorBlock;

public class BNDoorBlock extends DoorBlock implements IRenderTypeable {

	public BNDoorBlock(Properties builder) {
		super(builder);
	}

	@Override
	public BNRenderLayer getRenderLayer() {
		return BNRenderLayer.CUTOUT;
	}

}
