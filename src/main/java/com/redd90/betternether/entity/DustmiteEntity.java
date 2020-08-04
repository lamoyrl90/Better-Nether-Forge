package com.redd90.betternether.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class DustmiteEntity extends MonsterEntity {

	public DustmiteEntity(EntityType<? extends MonsterEntity> type, World world) {
		super(type, world);
		this.experienceValue = 1;
	}

	public SoundCategory getSoundCategory() {
		return SoundCategory.NEUTRAL;
	}
	
	protected void registerGoals() {
		this.goalSelector.addGoal(12, new PanicGoal(this, 3.0D));
		this.goalSelector.addGoal(11, new AvoidEntityGoal<>(this, PlayerEntity.class, 16.0F, 3.0D, 3.0D));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.6D));
		this.goalSelector.addGoal(1, new LookAtGoal(this, PlayerEntity.class, 10.0F));
	}
	
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.13F;
	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MonsterEntity.func_234295_eP_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 8.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.20D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 48)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D);
	}
	   
	protected boolean canTriggerWalking() {
		return false;
	}

	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_ENDERMITE_AMBIENT;
	}

	 protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		 return SoundEvents.ENTITY_ENDERMITE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_ENDERMITE_DEATH;
	}

	protected void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(SoundEvents.ENTITY_ENDERMITE_STEP, 0.15F, 1.0F);
	}
	
	public void tick() {
		this.renderYawOffset = this.rotationYaw;
		super.tick();
	}

		   /**
		    * Set the render yaw offset
		    */
	public void setRenderYawOffset(float offset) {
		this.rotationYaw = offset;
		super.setRenderYawOffset(offset);
	}

		   /**
		    * Returns the Y Offset of this entity.
		    */
	public double getYOffset() {
		return 0.1D;
	}
	
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.ARTHROPOD;
	}
	
	public boolean isInvulnerableTo(DamageSource source) {
		if (source == DamageSource.CACTUS) {
			return true;
		};
		return super.isInvulnerableTo(source);
	}
}
