package com.redd90.betternether.world.gen.feature.structure;

import java.util.List;
import java.util.Random;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.registry.BNStructurePieceTypes;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class BoneReefPieces {
	private static final ResourceLocation[] field_236993_a_ = new ResourceLocation[]{
			new ResourceLocation(BetterNether.MODID, "bone_01"),
			new ResourceLocation(BetterNether.MODID, "bone_02"),
			new ResourceLocation(BetterNether.MODID, "bone_03")};

	public static void func_236994_a_(TemplateManager p_236994_0_, List<StructurePiece> p_236994_1_, Random random, BlockPos p_236994_3_) {
		Rotation rotation = Rotation.randomRotation(random);
		Mirror mirror = Mirror.values()[random.nextInt(3)];
		ResourceLocation location = Util.getRandomObject(field_236993_a_, random);
		p_236994_1_.add(new BoneReefPieces.Piece(p_236994_0_, location, p_236994_3_, rotation, mirror));
	}

		public static class Piece extends TemplateStructurePiece {
			private final ResourceLocation location;
			private final Rotation rotation;
			private final Mirror mirror;
			
			public Piece(TemplateManager p_i232108_1_, ResourceLocation p_i232108_2_, BlockPos p_i232108_3_, Rotation p_i232108_4_, Mirror mirror) {
	         super(BNStructurePieceTypes.BONE_REEF, 0);
	         this.location = p_i232108_2_;
	         this.templatePosition = p_i232108_3_;
	         this.rotation = p_i232108_4_;
	         this.mirror = mirror;
	         this.init(p_i232108_1_);
	      }

	      public Piece(TemplateManager p_i232107_1_, CompoundNBT p_i232107_2_) {
	         super(BNStructurePieceTypes.OLD_BROWN_MUSHROOM, p_i232107_2_);
	         this.location = new ResourceLocation(p_i232107_2_.getString("Template"));
	         this.rotation = Rotation.valueOf(p_i232107_2_.getString("Rot"));
	         this.mirror = Mirror.valueOf(p_i232107_2_.getString("Mirror"));
	         this.init(p_i232107_1_);
	      }

	      private void init(TemplateManager templateManager) {
	         Template template = templateManager.getTemplateDefaulted(this.location);
	         
	         Mutable blockpos2 = new Mutable().setPos(template.getSize());
				if (this.mirror == Mirror.FRONT_BACK)
					blockpos2.setX(-blockpos2.getX());
				if (this.mirror == Mirror.LEFT_RIGHT)
					blockpos2.setZ(-blockpos2.getZ());
				blockpos2.setPos(blockpos2.rotate(this.rotation));
	         
			this.templatePosition = this.templatePosition.add(-blockpos2.getX() >> 1, 0, -blockpos2.getZ() >> 1);
	         
	         PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(this.mirror).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
	         this.setup(template, this.templatePosition, placementsettings);
	      }

	      /**
	       * (abstract) Helper method to read subclass data from NBT
	       */
	      protected void readAdditional(CompoundNBT tagCompound) {
	         super.readAdditional(tagCompound);
	         tagCompound.putString("Template", this.location.toString());
	         tagCompound.putString("Rot", this.rotation.name());
	      }

	      protected void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
	      }

	      public boolean func_230383_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator generator, Random random, MutableBoundingBox bounds, ChunkPos chunkPos, BlockPos pos) {
	    	  if (BlocksHelper.isNetherGround(world.getBlockState(pos.down())) && world.isAirBlock(pos.up(2)) && world.isAirBlock(pos.up(4))) {
	    		  
	    			  bounds.expandTo(this.template.getMutableBoundingBox(this.placeSettings, this.templatePosition));
	    			  return super.func_230383_a_(world, structureManager, generator, random, bounds, chunkPos, pos);
	    		  
	    	  } return false;
	      }
	      
	}
}
