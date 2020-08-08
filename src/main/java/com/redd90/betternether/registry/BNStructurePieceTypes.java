package com.redd90.betternether.registry;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.world.gen.feature.city.CavePiece;
import com.redd90.betternether.world.gen.feature.city.CityPiece;

import net.minecraft.world.gen.feature.structure.IStructurePieceType;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNStructurePieceTypes {
	public static final IStructurePieceType NETHER_CITY = IStructurePieceType.register(CityPiece::new, "bncity");
	public static final IStructurePieceType CAVE = IStructurePieceType.register(CavePiece::new, "bncave");
	
	public static void init() {
		
	}
}
