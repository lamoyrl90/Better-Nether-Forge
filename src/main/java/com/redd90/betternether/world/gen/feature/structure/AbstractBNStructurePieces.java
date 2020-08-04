package com.redd90.betternether.world.gen.feature.structure;

import java.util.List;
import java.util.Random;

import com.redd90.betternether.registry.BNStructurePieceTypes;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;

@SuppressWarnings("deprecation")
public abstract class AbstractBNStructurePieces {

	//The individual pieces' paths go in here
	private static final ResourceLocation[] PIECES = new ResourceLocation[]{};
	
	//This places the piece
	public static void addPiece(TemplateManager manager, List<StructurePiece> components, Random rand,
			BlockPos blockPos) {
		Rotation rotation = Rotation.randomRotation(rand);
		Mirror mirror = Mirror.values()[rand.nextInt(3)];
		StructureType type = StructureType.FLOOR;
		int offsetY = 0;
		components.add(new AbstractBNStructurePieces.Piece(manager, Util.getRandomObject(PIECES, rand), blockPos, rotation, mirror, offsetY, type));
	}

	public static class Piece extends TemplateStructurePiece {
		protected static final Mutable POS = new Mutable();
		private final ResourceLocation location;
		private final Rotation rotation;
		private final Mirror mirror;
		private final StructureType type;
		private final int offsetY;

		public Piece(TemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation, Mirror mirror, int offsetY, StructureType type) {
			super(BNStructurePieceTypes.OLD_BROWN_MUSHROOM, 0);
			this.location = location;
			this.templatePosition = pos;
			this.rotation = rotation;
			this.mirror = mirror;
			this.offsetY = offsetY;
			this.type = type;
			this.init(manager);
		}

		public Piece(TemplateManager manager, CompoundNBT tagCompound) {
			super(BNStructurePieceTypes.OLD_BROWN_MUSHROOM, tagCompound);
			this.location = new ResourceLocation(tagCompound.getString("Template"));
			this.rotation = Rotation.valueOf(tagCompound.getString("Rot"));
			this.mirror = Mirror.valueOf(tagCompound.getString("Mirror"));
			this.offsetY = Integer.parseInt(tagCompound.getString("offsetY"));
			this.type = StructureType.fromString(tagCompound.getString("type"));
			this.init(manager);
		}

		public void init(TemplateManager manager) {
			Template template = manager.getTemplateDefaulted(this.location);
			PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(this.mirror).addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
			
			Mutable centerpos = new Mutable().setPos(template.getSize());
			if(this.mirror == Mirror.FRONT_BACK) 
				centerpos.setX(-centerpos.getX());
			if(this.mirror == Mirror.LEFT_RIGHT)
				centerpos.setZ(-centerpos.getZ());
			centerpos.setPos(centerpos.rotate(rotation));
			
			BlockPos newpos = this.templatePosition.add(-centerpos.getX() >> 1, this.offsetY, -centerpos.getZ() >> 1);
			
			this.setup(template, newpos, placementsettings);
		}

		      /**
		       * (abstract) Helper method to read subclass data from NBT
		       */
		public void readAdditional(CompoundNBT tagCompound) {
			super.readAdditional(tagCompound);
			tagCompound.putString("Template", this.location.toString());
			tagCompound.putString("Rot", this.rotation.name());
		}

		public void handleDataMarker(String function, BlockPos pos, IWorld worldIn, Random rand, MutableBoundingBox sbb) {
		}

		//Generate
		public boolean func_230383_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox bounds, ChunkPos chunkpos, BlockPos pos) {
			if(canGenerate(world, pos)) {
				bounds.expandTo(this.template.getMutableBoundingBox(this.placeSettings, this.templatePosition));
				return super.func_230383_a_(world, manager, generator, random, bounds, chunkpos, pos);
			} else return false;
		}
		
		/*
		private BlockPos getSize() {
			if (this.rotation == Rotation.CLOCKWISE_180 || this.rotation == Rotation.NONE) {
				return this.template.getSize();
			} else {
				BlockPos size = this.template.getSize();
				int x = size.getX();
				int z = size.getZ();
				return new BlockPos(z, size.getY(), x);
			}
		}
		*/
		
		public float getAirFraction(IBlockReader world, BlockPos pos)
		{
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
						if (world.getBlockState(POS).isAir())
							airCount ++;
						count ++;
					}
			
			return (float) airCount / count;
		}
		
		public float getAirFractionFoundation(IBlockReader world, BlockPos pos)
		{
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
						if (world.getBlockState(POS).isAir())
							airCount ++;
						count ++;
					}
			
			return (float) airCount / count;
		}
		
		public float getAirFractionBottom(IBlockReader world, BlockPos pos)
		{
			int airCount = 0;
			
			Mutable size = new Mutable().setPos(this.template.getSize().rotate(rotation));
			size.setX(Math.abs(size.getX()));
			size.setZ(Math.abs(size.getZ()));
			
			float y1 = Math.min(offsetY, 0);
			float y2 = Math.max(offsetY, 0);
			BlockPos start = pos.add(-size.getX() >> 1, y1, -size.getZ() >> 1);
			BlockPos end = pos.add(size.getX() >> 1, y2, size.getZ() >> 1);
			int count = 0;
			
			for (int x = start.getX(); x <= end.getX(); x++)
				for (int y = start.getY(); y <= end.getY(); y++)
					for (int z = start.getZ(); z <= end.getZ(); z++)
					{
						POS.setPos(x, y, z);
						if (world.getBlockState(POS).isAir())
							airCount ++;
						count ++;
					}
			
			return (float) airCount / count;
		}
		
		public boolean canGenerate(IBlockReader world, BlockPos pos)
		{
			if (type == StructureType.FLOOR)
				return getAirFraction(world, pos) > 0.7 && getAirFractionFoundation(world, pos) < 0.3;
			else if (type == StructureType.LAVA)
				return getAirFraction(world, pos) > 0.8;
			else if (type == StructureType.UNDER)
				return getAirFraction(world, pos) < 0.2;
			else if (type == StructureType.CEIL)
				return getAirFractionBottom(world, pos) > 0.7 && getAirFraction(world, pos) < 0.6;
			else
				return false;
		}
	}
	
}
