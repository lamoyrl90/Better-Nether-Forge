package com.redd90.betternether.entity;

import java.util.Random;

import com.redd90.betternether.registry.BNSounds;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.HoglinEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.monster.piglin.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class DustDevilEntity extends MonsterEntity {
	   
	Random random = new Random();
	
	   public DustDevilEntity(EntityType<? extends DustDevilEntity> type, World p_i50215_2_) {
	      super(type, p_i50215_2_);
	      
	      this.setPathPriority(PathNodeType.DANGER_CACTUS, 0.0F);
	      this.setPathPriority(PathNodeType.DANGER_CACTUS, 0.0F);
	      this.experienceValue = 10;
	   }

	   protected void registerGoals() {
	      this.goalSelector.addGoal(8, new MeleeAttackGoal(this, 1.0D, false));
	      this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
	      this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	      this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
	      this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
	      this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PiglinEntity.class, true));
	      this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, HoglinEntity.class, true));
	   }

	   public static AttributeModifierMap.MutableAttribute registerAttributes() {
	      return MonsterEntity.func_234295_eP_()
	    		  .createMutableAttribute(Attributes.MAX_HEALTH, 15)
	    		  .createMutableAttribute(Attributes.ATTACK_DAMAGE, 3.0D)
	    		  .createMutableAttribute(Attributes.MOVEMENT_SPEED, (double)0.3F)
	    		  .createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D);
	   }

	   protected SoundEvent getAmbientSound() {
	      return BNSounds.DUST_DEVIL_AMBIENT;
	   }

	   protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
	      return SoundEvents.ENTITY_BLAZE_HURT;
	   }

	   protected SoundEvent getDeathSound() {
	      return SoundEvents.ENTITY_BLAZE_DEATH;
	   }
	   
	   public boolean isInvulnerableTo(DamageSource source) {
		   if (source == DamageSource.CACTUS || source == DamageSource.FALLING_BLOCK || source == DamageSource.IN_WALL) {
			   return true;
		   };
		   return super.isInvulnerableTo(source);
	   }


	   /**
	    * Called frequently so the entity can update its state every tick as required. For example
	    */
	   public void livingTick() {
	      if (!this.onGround && this.getMotion().y < 0.0D) {
	         this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
	      }

	      if (this.world.isRemote) {
	         if (this.rand.nextInt(600) == 0 && !this.isSilent()) {
	            this.world.playSound(this.getPosX() + 0.5D, this.getPosY() + 0.5D, this.getPosZ() + 0.5D, BNSounds.DUST_DEVIL_ADDITIONAL, this.getSoundCategory(), 1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
	         }

	         for(int i = 0; i < 4; ++i) {
	            this.world.addParticle(ParticleTypes.ASH, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
	         }
	         
	         //for(int i = 0; i < 100; ++i) {
	        //	 if (random.nextFloat()>0.1f) {
		     //      this.world.addParticle(ParticleTypes.SMOKE, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
	        //	 }
		     //}
	      }

	      super.livingTick();
	   }

		@Override
		public int getMaxSpawnedInChunk()
		{
			return 4;
		}

	   public boolean onLivingFall(float distance, float damageMultiplier) {
	      return false;
	   }

	   
	    public static boolean canSpawn(EntityType<DustDevilEntity> entityType, IWorld world, SpawnReason spawnReason, BlockPos pos, Random random)
	    {
	        return world.getDifficulty() != Difficulty.PEACEFUL;
	    }

	}