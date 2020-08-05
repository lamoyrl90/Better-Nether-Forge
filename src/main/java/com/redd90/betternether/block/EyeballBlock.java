package com.redd90.betternether.block;

import java.util.Random;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EyeballBlock extends EyeBaseBlock {
	
	public EyeballBlock()
	{
		super(AbstractBlock.Properties.create(Material.WOOD, MaterialColor.BROWN)
				.sound(SoundType.SLIME)
				.hardnessAndResistance(0.5f));
	}

	public void animateTick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (random.nextInt(5) == 0)
		{
			double x = pos.getX() + random.nextDouble();
			double y = pos.getY() + random.nextDouble() * 0.3;
			double z = pos.getZ() + random.nextDouble();
			world.addParticle(ParticleTypes.DRIPPING_WATER, x, y, z, 0, 0, 0);
		}
	}
}
