package com.redd90.betternether.block;

import java.util.Random;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.OreBlock;
import net.minecraft.util.math.MathHelper;

public class BNOreBlock extends OreBlock implements IRenderTypeable {
	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	public BNOreBlock(Properties properties) {
		super(properties);
	}

	public void setRenderLayer(BNRenderLayer layer)
	{
		this.layer = layer;
	}
	
	protected int getExperience(Random rand) {
		if (this == BNBlocks.NETHER_RUBY_ORE.get()) {
			return MathHelper.nextInt(rand, 3, 7);
		} else if (this == BNBlocks.CINCINNASITE_ORE.get()) {
			return MathHelper.nextInt(rand, 0, 2);
		}
		return 0;
	}
	
	@Override
	public BNRenderLayer getRenderLayer() {
		return layer;
	}
	
}
