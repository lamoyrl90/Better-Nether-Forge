package com.redd90.betternether.world.gen.feature.structure;

import java.util.List;
import java.util.Random;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.registry.BNBlocks;
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
import net.minecraft.world.IBlockReader;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class OldBrownMushroomPieces extends AbstractScatteredStructurePieces {

	private static final ResourceLocation[] PIECES = new ResourceLocation[]{
			new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_01"),
			new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_02"),
			new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_03"),
			new ResourceLocation(BetterNether.MODID, "trees/brown_mushroom_04")};
	
	public static void addPiece(TemplateManager manager, List<StructurePiece> pieces, Random random, BlockPos pos, IBlockReader blockreader) {
		Mutable blockpos = new Mutable();
		blockpos.setPos(pos);
		for(int i=0; i<16; ++i) {
			Rotation rotation = Rotation.randomRotation(random);
			Mirror mirror = Mirror.values()[random.nextInt(3)];
			StructureType type = StructureType.FLOOR;
			int offsetY = -2-random.nextInt(4);
			Piece piece = new OldBrownMushroomPieces.Piece(manager, Util.getRandomObject(PIECES, random), pos, rotation, mirror, offsetY, type);
			if (piece.canGenerate(blockreader, pos)) {
				pieces.add(piece);
				break;
			}
			
			int j = random.nextInt(5)-2;
			int k = random.nextInt(3)-1;
			int l = random.nextInt(5)-2;
			
			blockpos.setPos(pos.getX()+j, pos.getY()+k, pos.getZ()+l);
		}
	}
	
	public static class Piece extends AbstractScatteredStructurePieces.Piece {

		public Piece(TemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation, Mirror mirror,
				int offsetY, StructureType type) {
			super(manager, location, pos, rotation, mirror, offsetY, type, 9, 9);
		}
		
		public Piece(TemplateManager manager, CompoundNBT tagCompound) {
			super(manager, tagCompound);
		}

		//Generate
		public boolean func_230383_a_(ISeedReader world, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox bounds, ChunkPos chunkpos, BlockPos pos) {
			if (isGround(world.getBlockState(pos.down())) && isGround(world.getBlockState(pos.down(2))) && noObjNear(world, pos))
			{
				bounds.expandTo(this.template.getMutableBoundingBox(this.placeSettings, this.templatePosition));
				return super.func_230383_a_(world, manager, generator, random, bounds, chunkpos, pos);
			} else return false;
		}
		
		@Override
		public boolean isStructure(BlockState state) {
			return  state.getBlock() == Blocks.MUSHROOM_STEM ||
					state.getBlock() == Blocks.BROWN_MUSHROOM_BLOCK ||
					state.getBlock() == Blocks.RED_MUSHROOM_BLOCK;
		}

		@Override
		public boolean isGround(BlockState state) {
			return state.getBlock() == BNBlocks.NETHER_MYCELIUM.get() || BlocksHelper.isNetherGround(state);
		}
	}
}

