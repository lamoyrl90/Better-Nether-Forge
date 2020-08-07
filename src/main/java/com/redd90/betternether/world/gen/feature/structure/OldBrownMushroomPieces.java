package com.redd90.betternether.world.gen.feature.structure;

import java.util.List;
import java.util.Random;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.registry.BNBlocks;
import com.redd90.betternether.registry.BNStructurePieceTypes;
import com.redd90.betternether.util.BlocksHelper;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
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

public class OldBrownMushroomPieces {
	private static final StructureWithOffset[] field_236993_a_ = new StructureWithOffset[]{
			new StructureWithOffset(new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_01"), -4),
			new StructureWithOffset(new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_02"), -3),
			new StructureWithOffset(new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_03"), -3),
			new StructureWithOffset(new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_04"), -2)};

	public static void func_236994_a_(TemplateManager p_236994_0_, List<StructurePiece> p_236994_1_, Random random, BlockPos p_236994_3_) {
		Rotation rotation = Rotation.randomRotation(random);
		Mirror mirror = Mirror.values()[random.nextInt(3)];
		StructureWithOffset structure = Util.getRandomObject(field_236993_a_, random);
		ResourceLocation location = structure.location;
		int offsetY = structure.offsetY;
		p_236994_1_.add(new OldBrownMushroomPieces.Piece(p_236994_0_, location, p_236994_3_, offsetY, rotation, mirror));
	}

		public static class Piece extends TemplateStructurePiece {
			private final ResourceLocation location;
			private final int offsetY;
			private final Rotation rotation;
			private final Mirror mirror;
			private final int distance = 9;
			private final int manDist = (int) Math.ceil(distance * 1.5);

	      public Piece(TemplateManager p_i232108_1_, ResourceLocation p_i232108_2_, BlockPos p_i232108_3_, int offsetY, Rotation p_i232108_4_, Mirror mirror) {
	         super(BNStructurePieceTypes.OLD_BROWN_MUSHROOM, 0);
	         this.location = p_i232108_2_;
	         this.templatePosition = p_i232108_3_;
	         this.offsetY = offsetY;
	         this.rotation = p_i232108_4_;
	         this.mirror = mirror;
	         this.init(p_i232108_1_);
	      }

	      public Piece(TemplateManager p_i232107_1_, CompoundNBT p_i232107_2_) {
	         super(BNStructurePieceTypes.OLD_BROWN_MUSHROOM, p_i232107_2_);
	         this.location = new ResourceLocation(p_i232107_2_.getString("Template"));
	         this.rotation = Rotation.valueOf(p_i232107_2_.getString("Rot"));
	         this.mirror = Mirror.valueOf(p_i232107_2_.getString("Mirror"));
	         this.offsetY = p_i232107_2_.getInt("offsetY");
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
	    	  if (isGround(world.getBlockState(pos.down())) && isGround(world.getBlockState(pos.down(2))) && noObjNear(world, pos)) {
	    		  if (canGenerate(world, pos)) {
	    			  this.templatePosition = this.templatePosition.up(this.offsetY);
	    			  bounds.expandTo(this.template.getMutableBoundingBox(this.placeSettings, this.templatePosition));
	    			  return super.func_230383_a_(world, structureManager, generator, random, bounds, chunkPos, pos.up(this.offsetY));
	    		  }
	    	  } return false;
	      }
	      
	      private boolean isGround(BlockState state) {
	    	  return state.getBlock() == BNBlocks.NETHER_MYCELIUM.get() || BlocksHelper.isNetherGround(state);
	      }
	      
	  	private boolean noObjNear(IWorld world, BlockPos pos)
		{
	  		Mutable POS = new Mutable();
	  			  		
			int x1 = pos.getX() - distance;
			int z1 = pos.getZ() - distance;
			int x2 = pos.getX() + distance;
			int z2 = pos.getZ() + distance;
			POS.setY(pos.getY());
			for (int x = x1; x <= x2; x++)
			{
				POS.setX(x);
				for (int z = z1; z <= z2; z++)
				{
					POS.setZ(z);
					if (isInside(x - pos.getX(), z - pos.getZ()) && isStructure(world.getBlockState(POS)))
						return false;
				}
			}
			return true;
		}
	  	
		private boolean isInside(int x, int z)
		{
			return (Math.abs(x) + Math.abs(z)) <= manDist;
		}
		
		protected boolean isStructure(BlockState state)
		{
			return  state.getBlock() == Blocks.MUSHROOM_STEM ||
					state.getBlock() == Blocks.BROWN_MUSHROOM_BLOCK ||
					state.getBlock() == Blocks.RED_MUSHROOM_BLOCK;
		}
		
		private boolean canGenerate(IWorld world, BlockPos pos)
		{
			return getAirFraction(world, pos) > 0.7 && getAirFractionFoundation(world, pos) < 0.3;
		}
		
		private float getAirFraction(IWorld world, BlockPos pos)
		{
			Mutable POS = new Mutable();
			
			int airCount = 0;
			
			Mutable size = new Mutable().setPos(this.template.getSize().rotate(rotation));
			size.setX(Math.abs(size.getX()));
			size.setZ(Math.abs(size.getZ()));
			
			BlockPos start = pos.add(-size.getX() >> 1, 0, -size.getZ() >> 1);
			BlockPos end = pos.add(size.getX() >> 1, size.getY() + offsetY, size.getZ() >> 1);
			int count = 0;
			
			for (int x = start.getX(); x <= end.getX(); x++)
				for (int y = start.getY(); y <= end.getY(); y++)
					for (int z = start.getZ(); z <= end.getZ(); z++)
					{
						POS.setPos(x, y, z);
						if (world.isAirBlock(POS))
							airCount ++;
						count ++;
					}
			
			return (float) airCount / count;
		}
		
		private float getAirFractionFoundation(IWorld world, BlockPos pos)
		{
			Mutable POS = new Mutable();
			
			int airCount = 0;
			
			Mutable size = new Mutable().setPos(this.template.getSize().rotate(rotation));
			size.setX(Math.abs(size.getX()));
			size.setZ(Math.abs(size.getZ()));
			
			BlockPos start = pos.add(-size.getX() >> 1, -1, -size.getZ() >> 1);
			BlockPos end = pos.add(size.getX() >> 1, 0, size.getZ() >> 1);
			int count = 0;
			
			for (int x = start.getX(); x <= end.getX(); x++)
				for (int y = start.getY(); y <= end.getY(); y++)
					for (int z = start.getZ(); z <= end.getZ(); z++)
					{
						POS.setPos(x, y, z);
						if (world.isAirBlock(POS))
							airCount ++;
						count ++;
					}
			
			return (float) airCount / count;
		}
	}
}
