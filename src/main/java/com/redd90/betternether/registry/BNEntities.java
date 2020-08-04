package com.redd90.betternether.registry;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.client.renderer.entity.DustDevilRenderer;
import com.redd90.betternether.client.renderer.entity.DustmiteRenderer;
import com.redd90.betternether.entity.DustDevilEntity;
import com.redd90.betternether.entity.DustmiteEntity;


@EventBusSubscriber(modid = BetterNether.MODID, bus = EventBusSubscriber.Bus.MOD)
public class BNEntities {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, BetterNether.MODID);
	
	public static final RegistryObject<EntityType<DustDevilEntity>> DUST_DEVIL = registerEntity("dust_devil", () -> EntityType.Builder.create(DustDevilEntity::new, EntityClassification.MONSTER)
			.immuneToFire()
			.size(0.6F, 1.8F)
			.func_233606_a_(8)
			.setShouldReceiveVelocityUpdates(true)
			.build("dust_devil"));
	public static final RegistryObject<EntityType<DustmiteEntity>> DUSTMITE = registerEntity("dustmite", () -> EntityType.Builder.create(DustmiteEntity::new, EntityClassification.CREATURE)
			.immuneToFire()
			.size(0.4F, 0.3F)
			.func_233606_a_(8)
			.setShouldReceiveVelocityUpdates(true)
			.build("dustmite"));
	
	private static <E extends EntityType<?>> RegistryObject<E> registerEntity(String name, Supplier<? extends E> supplier) {
		RegistryObject<E> entity = ENTITIES.register(name, supplier);
		//BNItems.ITEMS.register(name, () -> new BNSpawnEggItem((Supplier<EntityType<?>>) supplier, 0, 0, new Item.Properties().group(BNItemGroup.ITEM_GROUP)));
		return entity;
	}


	public static void registerSpawnPlacements() {
		EntitySpawnPlacementRegistry.register(DUST_DEVIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DustDevilEntity::canMonsterSpawn);
		EntitySpawnPlacementRegistry.register(DUSTMITE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DustmiteEntity::canMonsterSpawn);
	}
	
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(DUST_DEVIL.get(), DustDevilRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(DUSTMITE.get(), DustmiteRenderer::new);
	}
	
	public static void registerEntityAttributes() {
		GlobalEntityTypeAttributes.put(DUST_DEVIL.get(), DustDevilEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(DUSTMITE.get(), DustmiteEntity.registerAttributes().create());
	}
	
	public static void finalizeEntities() {
		registerSpawnPlacements();
		registerEntityAttributes();
	}
	
	public static void modifyVanillaBehavior() {
		
	}
}
