package com.redd90.betternether.registry;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.world.gen.feature.structure.BoneReefPieces;
import com.redd90.betternether.world.gen.feature.structure.OldBrownMushroomPieces;
import com.redd90.betternether.world.gen.feature.structure.OldRedMushroomPieces;

import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNStructurePieceTypes {
	public static final IStructurePieceType OLD_BROWN_MUSHROOM = IStructurePieceType.register(OldBrownMushroomPieces.Piece::new, "obm");
	public static final IStructurePieceType OLD_RED_MUSHROOM = IStructurePieceType.register(OldRedMushroomPieces.Piece::new, "orm");
	public static final IStructurePieceType BONE_REEF = IStructurePieceType.register(BoneReefPieces.Piece::new, "bnrf");

	public static void init() {
		
	}
}
