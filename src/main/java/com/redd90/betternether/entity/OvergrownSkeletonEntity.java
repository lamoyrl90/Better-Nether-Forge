package com.redd90.betternether.entity;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.SkeletonEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class OvergrownSkeletonEntity extends SkeletonEntity {
	
	public OvergrownSkeletonEntity(EntityType<? extends SkeletonEntity> entityType, World world)
	{
		super(entityType, world);
	}

	@Override
	public void livingTick()
	{
		this.updateArmSwingProgress();
		this.func_213623_ec();
		super.livingTick();
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason spawnReason, @Nullable ILivingEntityData entityData, @Nullable CompoundNBT entityTag)
	{
		entityData = super.onInitialSpawn(world, difficulty, spawnReason, entityData, entityTag);
		super.setEquipmentBasedOnDifficulty(difficulty);

		this.setItemStackToSlot(EquipmentSlotType.MAINHAND, getHandItem());
		this.setItemStackToSlot(EquipmentSlotType.OFFHAND, getOffhandItem());

		this.setEnchantmentBasedOnDifficulty(difficulty);
		this.setCombatTask();
		this.setCanPickUpLoot(this.rand.nextFloat() < 0.55F * difficulty.getClampedAdditionalDifficulty());
		if (this.getItemStackFromSlot(EquipmentSlotType.HEAD).isEmpty())
		{
			LocalDate localDate = LocalDate.now();
			int i = localDate.get(ChronoField.DAY_OF_MONTH);
			int j = localDate.get(ChronoField.MONTH_OF_YEAR);
			if (j == 10 && i == 31 && this.rand.nextFloat() < 0.25F) {
				this.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack(this.rand.nextFloat() < 0.1F ? Blocks.JACK_O_LANTERN : Blocks.CARVED_PUMPKIN));
				this.inventoryArmorDropChances[EquipmentSlotType.HEAD.getIndex()] = 0.0F;
			}
		}

		return entityData;
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes()
	{
		return AbstractSkeletonEntity
				.func_234275_m_();
	}
	
	private ItemStack getHandItem()
	{
		int n = this.rand.nextInt(3);
		switch(n)
		{
		case 0:
		default:
			return new ItemStack(this.rand.nextBoolean() ? Items.WOODEN_SWORD : Items.STONE_SWORD);
		case 1:
			return new ItemStack(Items.BOW);
		case 2:
			return new ItemStack(Items.AIR);
		}
	}
	
	private ItemStack getOffhandItem()
	{
		return this.rand.nextInt(8) == 0 ? new ItemStack(Items.SHIELD) : new ItemStack(Items.AIR);
	}
	
	public static boolean canSpawn(EntityType<? extends OvergrownSkeletonEntity> type, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random)
	{
		
		return world.getDifficulty() != Difficulty.PEACEFUL;
		
	}
}
