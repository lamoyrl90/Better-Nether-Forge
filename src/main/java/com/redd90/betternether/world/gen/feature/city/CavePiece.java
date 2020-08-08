package com.redd90.betternether.world.gen.feature.city;

import java.util.Random;

import com.redd90.betternether.registry.BNStructurePieceTypes;
import com.redd90.betternether.util.OpenSimplexNoise;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.template.TemplateManager;

public class CavePiece extends CustomPiece {
	private static final BlockState LAVA = Blocks.LAVA.getDefaultState();
	private static final OpenSimplexNoise NOISE = new OpenSimplexNoise(927649);
	private static final Mutable POS = new Mutable();
	
	private BlockPos center;
	private int radius;
	private int radSqr;
	private int minY;
	private int maxY;
	
	public CavePiece(BlockPos center, int radius, Random random)
	{
		super(BNStructurePieceTypes.CAVE, random.nextInt());
		this.center = center;
		this.radius = radius;
		this.radSqr = radius * radius;
		makeBoundingBox();
	}
	
	public CavePiece(TemplateManager manager, CompoundNBT tag)
	{
		super(BNStructurePieceTypes.CAVE, tag);
		this.center = NBTUtil.readBlockPos(tag.getCompound("center"));
		this.radius = tag.getInt("radius");
		this.radSqr = radius * radius;
		makeBoundingBox();
	}

	@Override
	protected void readAdditional(CompoundNBT tag)
	{
		tag.put("center", NBTUtil.writeBlockPos(center));
		tag.putInt("radius", radius);
	}

	@Override
	public boolean func_230383_a_(ISeedReader world, StructureManager arg, ChunkGenerator chunkGenerator, Random random, MutableBoundingBox MutableBoundingBox, ChunkPos chunkPos, BlockPos blockPos)
	{
		BlockState bottom = LAVA;
		if (!(world.func_230315_m_().func_236037_d_()))
		{
			bottom = Blocks.NETHERRACK.getDefaultState();
		}
		for (int x = MutableBoundingBox.minX; x <= MutableBoundingBox.maxX; x++)
		{
			int px = x - center.getX();
			px *= px;
			for (int z = MutableBoundingBox.minZ; z <= MutableBoundingBox.maxZ; z++)
			{
				int pz = z - center.getZ();
				pz *= pz;
				for (int y = minY; y <= maxY; y++)
				{
					int py = (y - center.getY()) << 1;
					py *= py;
					if (px + py + pz <= radSqr + NOISE.eval(x * 0.1, y * 0.1, z * 0.1) * 800)
					{
						POS.setPos(x, y, z);
						if (y > 31)
						{
							world.setBlockState(POS, CAVE_AIR, 0);
						}
						else
							world.setBlockState(POS, bottom, 0);
					}
				}
			}
		}
		return true;
	}
	
	private void makeBoundingBox()
	{
		int x1 = center.getX() - radius;
		int x2 = center.getX() + radius;
		minY = Math.max(22, center.getY() - radius);
		maxY = Math.min(96, center.getY() + radius);
		int z1 = center.getZ() - radius;
		int z2 = center.getZ() + radius;
		this.boundingBox = new MutableBoundingBox(x1, minY, z1, x2, maxY, z2);
	}
}
