package com.redd90.betternether.world.gen.feature.city;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraft.world.gen.feature.structure.StructurePiece;

public abstract class CustomPiece extends StructurePiece {
	protected CustomPiece(IStructurePieceType type, int i)
	{
		super(type, i);
	}

	protected CustomPiece(IStructurePieceType type, CompoundNBT tag)
	{
		super(type, tag);
	}
}
