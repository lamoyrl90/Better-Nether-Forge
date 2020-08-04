package com.redd90.betternether.world.gen.feature.decorations;

import java.util.Random;

import com.mojang.serialization.Codec;
import com.redd90.betternether.registry.BNTags;
import com.redd90.betternether.util.BlocksHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;


public class BlackstoneSpikeFeature extends Feature<NoFeatureConfig> {

	private static final Block BLACKSTONE = Blocks.BLACKSTONE;
	private static final Block GILDED_BLACKSTONE = Blocks.GILDED_BLACKSTONE;
	private static final double SQRT05 = Math.sqrt(0.5);
	private static final Mutable POS = new Mutable();
	private static final float MAX_ANGLE_X = (float) Math.toRadians(45);
	private static final float MAX_ANGLE_Y = (float) (Math.PI * 2);
	
	public BlackstoneSpikeFeature(Codec<NoFeatureConfig> codec) {
		super(codec);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean func_230362_a_(ISeedReader world, StructureManager structureManager, ChunkGenerator chunkGenerator,
			Random rand, BlockPos pos, NoFeatureConfig config) {
		//BetterNether.LOGGER.debug("Attempting to place Obsidian Crystal");
		if (world.getBlockState(pos.down()).isIn(BNTags.Blocks.NETHER_GROUND) && world.getBlockState(pos).isAir()){
			generate(world, pos, rand);
			//BetterNether.LOGGER.debug("Successfully placed Obsidian Crystal");
			return true;
		} else {
			//BetterNether.LOGGER.debug("Failed to place Obsidian Crystal");
			return false;
		}
	}
	
	public void generate(IWorld world, BlockPos pos, Random random)
	{
		double a = random.nextDouble();
		double radius = 2 + a * a * 3;
		int sideXZ = (int) Math.ceil(radius * 2);
		int sideY = (int) Math.ceil(radius * 3);
		float angleX = random.nextFloat() * MAX_ANGLE_X;
		float angleY = random.nextFloat() * MAX_ANGLE_Y;
		for (int y = -sideY; y <= sideY; y++)
			for (int x = -sideXZ; x <= sideXZ; x++)
				for (int z = -sideXZ; z <= sideXZ; z++)
				{
					boolean flag = random.nextInt(512) == 0 ? true : false;
					BlockState state = flag ? GILDED_BLACKSTONE.getDefaultState() : BLACKSTONE.getDefaultState();
					Vector3d v = new Vector3d(x, y, z).rotatePitch(angleX).rotateYaw(angleY);
					if (isInside(v.x, v.y, v.z, radius))
					{
						POS.setX(pos.getX() + x);
						POS.setY(pos.getY() + y);
						POS.setZ(pos.getZ() + z);
						BlocksHelper.setWithoutUpdate(world, POS, state);
					}
				}
	}
	
	private boolean isInside(double x, double y, double z, double size)
	{
		return dodecahedronSDF(x / size, y / size * 0.3, z / size) <= 0;
	}
	
	private double dodecahedronSDF(double x, double y, double z)
	{
		x = Math.abs(x);
		y = Math.abs(y);
		z = Math.abs(z);
	    return (Math.max(Math.max(x + y, y + z), z + x) - 1) * SQRT05;
	}
}

