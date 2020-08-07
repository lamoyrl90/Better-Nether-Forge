package com.redd90.betternether.block;

import net.minecraft.block.AbstractButtonBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNButtonBlock extends AbstractButtonBlock implements IRenderTypeable {

	private BNRenderLayer layer = BNRenderLayer.SOLID;
	
	public BNButtonBlock(boolean isWooden, Properties properties) {
		super(isWooden, properties);
	}

	public void setRenderLayer(BNRenderLayer layer)
	{
		this.layer = layer;
	}
	
	@Override
	public BNRenderLayer getRenderLayer() {
		return layer;
	}

	@Override
	protected SoundEvent getSoundEvent(boolean isOn) {
		SoundEvent onSound = null;
		SoundEvent offSound = null;
		if (this.material == Material.WOOD || this.material == Material.NETHER_WOOD) {
			onSound = SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_ON;
			offSound = SoundEvents.BLOCK_WOODEN_BUTTON_CLICK_OFF;
		} else {
			onSound = SoundEvents.BLOCK_STONE_BUTTON_CLICK_ON;
			offSound = SoundEvents.BLOCK_STONE_BUTTON_CLICK_OFF;
		}
		return isOn ? onSound : offSound;
	}
	
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return 0;
    }
}
