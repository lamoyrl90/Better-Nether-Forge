package com.redd90.betternether.world.gen.feature.structure;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.TemplateManager;

public abstract class AbstractScatteredStructurePieces extends AbstractBNStructurePieces {

	public static abstract class Piece extends AbstractBNStructurePieces.Piece {

		final int distance;
		final int manDist;
		
		public Piece(TemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation, Mirror mirror,
				int offsetY, StructureType type, int distance, int manDist) {
			super(manager, location, pos, rotation, mirror, offsetY, type);
			this.distance = distance;
			this.manDist = manDist;
		}
		
		public Piece(TemplateManager manager, CompoundNBT tagCompound) {
			super(manager, tagCompound);
			this.distance = Integer.parseInt(tagCompound.getString("distance"));
			this.manDist = Integer.parseInt(tagCompound.getString("manDist"));
		}
		
		public boolean noObjNear(IBlockReader world, BlockPos pos)
		{
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
		
		public boolean isInside(int x, int z)
		{
			return (Math.abs(x) + Math.abs(z)) <= this.manDist;
		}
		
		public abstract boolean isStructure(BlockState state);
		
		public abstract boolean isGround(BlockState state);
		
		//Generate
		public boolean func_230383_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox bounds, ChunkPos chunkpos, BlockPos pos) {
			if (isGround(world.getBlockState(pos.down())) && isGround(world.getBlockState(pos.down(2))) && noObjNear(world, pos))
			{
				bounds.expandTo(this.template.getMutableBoundingBox(this.placeSettings, this.templatePosition));
				return super.func_230383_a_(world, manager, generator, random, bounds, chunkpos, pos);
			} else return false;
		}
	}
	
}
