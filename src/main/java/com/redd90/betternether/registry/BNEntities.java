package com.redd90.betternether.registry;

import net.minecraft.entity.Entity;
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

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.client.renderer.entity.DustDevilRenderer;
import com.redd90.betternether.client.renderer.entity.DustmiteRenderer;
import com.redd90.betternether.client.renderer.entity.FireflyRenderer;
import com.redd90.betternether.client.renderer.entity.OvergrownSkeletonRenderer;
import com.redd90.betternether.entity.DustDevilEntity;
import com.redd90.betternether.entity.DustmiteEntity;
import com.redd90.betternether.entity.FireflyEntity;
import com.redd90.betternether.entity.OvergrownSkeletonEntity;


@EventBusSubscriber(modid = BetterNether.MODID, bus = EventBusSubscriber.Bus.MOD)
public class BNEntities {
	
	public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, BetterNether.MODID);
	public static final List<EntityType<?>> netherEntities = new ArrayList<EntityType<?>>();
		
	
	
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
	
	public static final RegistryObject<EntityType<OvergrownSkeletonEntity>> OVERGROWN_SKELETON = registerEntity("overgrown_skeleton", () -> EntityType.Builder.create(OvergrownSkeletonEntity::new, EntityClassification.MONSTER)
			.size(0.6F, 1.99F)
			.func_233606_a_(8)
			.setShouldReceiveVelocityUpdates(true)
			.build("overgrown_skeleton"));
	
	public static final RegistryObject<EntityType<FireflyEntity>> FIREFLY = registerEntity("firefly", () -> EntityType.Builder.create(FireflyEntity::new, EntityClassification.CREATURE)
			.size(0.5F, 0.5F)
			.func_233606_a_(8)
			.setShouldReceiveVelocityUpdates(true)
			.build("firefly"));
	
	private static <E extends EntityType<?>> RegistryObject<E> registerEntity(String name, Supplier<? extends E> supplier) {
		RegistryObject<E> entity = ENTITIES.register(name, supplier);
		//BNItems.ITEMS.register(name, () -> new BNSpawnEggItem((Supplier<EntityType<?>>) supplier, 0, 0, new Item.Properties().group(BNItemGroup.ITEM_GROUP)));
		return entity;
	}


	public static void registerSpawnPlacements() {
		EntitySpawnPlacementRegistry.register(DUST_DEVIL.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DustDevilEntity::canMonsterSpawn);
		EntitySpawnPlacementRegistry.register(DUSTMITE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, DustmiteEntity::canMonsterSpawn);
		EntitySpawnPlacementRegistry.register(OVERGROWN_SKELETON.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, OvergrownSkeletonEntity::canMonsterSpawn);
		EntitySpawnPlacementRegistry.register(FIREFLY.get(), EntitySpawnPlacementRegistry.PlacementType.NO_RESTRICTIONS, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FireflyEntity::canMonsterSpawn);
	}
	
	public static void registerRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(DUST_DEVIL.get(), DustDevilRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(DUSTMITE.get(), DustmiteRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(OVERGROWN_SKELETON.get(), OvergrownSkeletonRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(FIREFLY.get(), FireflyRenderer::new);
	}
	
	public static void registerEntityAttributes() {
		GlobalEntityTypeAttributes.put(DUST_DEVIL.get(), DustDevilEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(DUSTMITE.get(), DustmiteEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(OVERGROWN_SKELETON.get(), OvergrownSkeletonEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(FIREFLY.get(), FireflyEntity.registerAttributes().create());
	}
	
	public static void finalizeEntities() {
		registerSpawnPlacements();
		registerEntityAttributes();
		registerNetherEntities();
	}
	
	public static void modifyVanillaBehavior() {
		
	}

	public static void registerNetherEntities() {
		netherEntities.add(EntityType.BLAZE); 
		netherEntities.add(EntityType.PIGLIN); 
		netherEntities.add(EntityType.HOGLIN);
		netherEntities.add(EntityType.GHAST);
		netherEntities.add(EntityType.STRIDER);
		netherEntities.add(EntityType.SKELETON);
		netherEntities.add(EntityType.WITHER_SKELETON);
		netherEntities.add(EntityType.ZOMBIFIED_PIGLIN);
		netherEntities.add(DUSTMITE.get());
		netherEntities.add(DUST_DEVIL.get());
		netherEntities.add(OVERGROWN_SKELETON.get());
		netherEntities.add(FIREFLY.get());
	}

	public static boolean isNetherEntity(Entity entity) {
		return netherEntities.contains(entity.getType());
	}
}
