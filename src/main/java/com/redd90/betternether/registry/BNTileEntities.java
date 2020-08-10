package com.redd90.betternether.registry;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.client.renderer.tileentity.BNChestRenderer;
import com.redd90.betternether.tile.BNChestTileEntity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = BetterNether.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BNTileEntities {
	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, BetterNether.MODID);
	
	public static final RegistryObject<TileEntityType<BNChestTileEntity>> CHEST = TILES.register("bn_chest", () -> TileEntityType.Builder.create(BNChestTileEntity::new, BNBlocks.getChests()).build(null));

	
	public static void registerRenderers() {
		ClientRegistry.bindTileEntityRenderer(BNTileEntities.CHEST.get(), BNChestRenderer::new);
	}
}
