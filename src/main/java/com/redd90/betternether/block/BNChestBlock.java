package com.redd90.betternether.block;

import com.redd90.betternether.registry.BNTileEntities;
import com.redd90.betternether.tile.BNChestTileEntity;

import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BNChestBlock extends ChestBlock implements IRenderTypeable {

	private BNRenderLayer renderLayer;
	
	public BNChestBlock(Properties properties) {
		super(properties, () -> BNTileEntities.CHEST.get());
		this.renderLayer = BNRenderLayer.SOLID;
	}

	@Override
	public boolean isFlammable(BlockState state, IBlockReader world, BlockPos pos, Direction face) {
		return false;
	}
	
	@Override
	public TileEntity createNewTileEntity(IBlockReader world) {
		return new BNChestTileEntity();
	}
	
	@Override
	public BNRenderLayer getRenderLayer() {
		return this.renderLayer;
	}

}
