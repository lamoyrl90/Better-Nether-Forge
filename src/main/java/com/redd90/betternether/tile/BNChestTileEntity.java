package com.redd90.betternether.tile;

import com.redd90.betternether.registry.BNTileEntities;

import net.minecraft.tileentity.ChestTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.registries.ForgeRegistries;

public class BNChestTileEntity extends ChestTileEntity {

	private String material = "normal";
	private boolean update = true;
	
	public BNChestTileEntity(TileEntityType<?> type) {
		super(type);
	}
	
	public BNChestTileEntity() {
		super(BNTileEntities.CHEST.get());
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox() {
		return new AxisAlignedBB(pos.getX() - 1, pos.getY(), pos.getZ() - 1, pos.getX() + 2, pos.getY() + 2, pos.getZ() + 2);
	}
	
	public String getMaterial()
	{
		if (update)
		{
			material = world == null ? "normal" : ForgeRegistries.BLOCKS.getKey(world.getBlockState(pos).getBlock()).getPath();
			update = false;
		}
		return material;
	}
}
