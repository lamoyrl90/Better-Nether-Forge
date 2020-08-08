package com.redd90.betternether.world.gen.feature.city;

import java.util.List;
import java.util.Random;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.world.gen.feature.structure.StructureNBT;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.Template.BlockInfo;

public class CityBuildingStructure extends StructureNBT {
	protected static final BlockState AIR = Blocks.AIR.getDefaultState();
	
	private BoundingBox bb;
	public BlockPos[] ends;
	private Direction[] dirs;
	private BlockPos rotationOffset;
	private int offsetY;
	
	public CityBuildingStructure(String template)
	{
		super(template);
		this.offsetY = 0;
		init();
	}
	
	public CityBuildingStructure(String template, int offsetY)
	{
		super(template);
		this.offsetY = offsetY;
		init();
	}
	
	protected CityBuildingStructure(ResourceLocation location, Template template)
	{
		super(location, template);
		init();
	}
	
	private void init()
	{
		BlockPos size = template.getSize();
		bb = new BoundingBox(size);
		List<BlockInfo> map = template.func_215386_a(BlockPos.ZERO, new PlacementSettings(), Blocks.STRUCTURE_BLOCK, false);
		ends = new BlockPos[map.size()];
		dirs = new Direction[map.size()];
		int i = 0;
		BlockPos center = new BlockPos(size.getX() >> 1, size.getY(), size.getZ() >> 1);
		for(BlockInfo info : map)
		{
			ends[i] = info.pos;
			dirs[i++] = getDir(info.pos.add(-center.getX(), 0, -center.getZ()));
		}
		rotationOffset = new BlockPos(0, 0, 0);
		rotation = Rotation.NONE;
	}
	
	private Direction getDir(BlockPos pos)
	{
		int ax = Math.abs(pos.getX());
		int az = Math.abs(pos.getZ());
		int mx = Math.max(ax, az);
		if (mx == ax)
		{
			if (pos.getX() > 0)
				return Direction.EAST;
			else
				return Direction.WEST;
		}
		else
		{
			if (pos.getZ() > 0)
				return Direction.SOUTH;
			else
				return Direction.NORTH;
		}
	}

	public BoundingBox getBoundingBox()
	{
		return bb;
	}
	
	protected Rotation mirrorRotation(Rotation r)
	{
		switch (r)
		{
		case CLOCKWISE_90:
			return Rotation.COUNTERCLOCKWISE_90;
		default:
			return r;
		}
	}
	
	public boolean placeInChunk(IWorld world, BlockPos pos, MutableBoundingBox boundingBox)//, StructureProcessor paletteProcessor)
	{
		if(template == null)
		{
			System.out.println("No template: " + location.toString());
			return false;
		}
		
		BetterNether.LOGGER.debug("CityBuildingStructure: Placing in chunk: " + location);
		BlockPos p = pos.add(rotationOffset);
		PlacementSettings placementsettings = 
				new PlacementSettings()
				.setRotation(rotation)
				.setMirror(mirror)
				.setBoundingBox(boundingBox)
				.addProcessor(BlockIgnoreStructureProcessor.AIR_AND_STRUCTURE_BLOCK);
		template.func_237144_a_(world, p, 
				placementsettings,
				world.getRandom());
		return true;
	}

	public BlockPos[] getEnds()
	{
		return ends;
	}
	
	public int getEndsCount()
	{
		return ends.length;
	}
	
	public BlockPos getOffsettedPos(int index)
	{
		return ends[index].offset(dirs[index]);
	}

	public BlockPos getPos(int index)
	{
		return ends[index];
	}
	
	public CityBuildingStructure getRotated(Rotation rotation)
	{
		CityBuildingStructure building = this.clone();
		building.rotation = rotation;
		building.rotationOffset = building.template.getSize().rotate(rotation);
		int x = building.rotationOffset.getX();
		int z = building.rotationOffset.getZ();
		if (x < 0)
			x = -x - 1;
		else
			x = 0;
		if (z < 0)
			z = -z - 1;
		else
			z = 0;
		building.rotationOffset = new BlockPos(x, 0, z);
		for (int i = 0; i < building.dirs.length; i++)
		{
			building.dirs[i] = rotated(building.dirs[i], rotation);
			building.ends[i] = building.ends[i].rotate(rotation).add(building.rotationOffset);
		}
		building.bb.rotate(rotation);
		building.offsetY = this.offsetY;
		return building;
	}
	
	public CityBuildingStructure getRandomRotated(Random random)
	{
		return getRotated(Rotation.values()[random.nextInt(4)]);
	}
	
	public CityBuildingStructure clone()
	{
		return new CityBuildingStructure(location, template);
	}
	
	private Direction rotated(Direction dir, Rotation rotation)
	{
		Direction f;
		switch (rotation)
		{
		case CLOCKWISE_90:
			f = dir.rotateY();
			break;
		case CLOCKWISE_180:
			f = dir.getOpposite();
			break;
		case COUNTERCLOCKWISE_90:
			f = dir.rotateYCCW();
			break;
		default:
			f = dir;
			break;
		}
		return f;
	}
	
	public int getYOffset()
	{
		return offsetY;
	}

	public Rotation getRotation()
	{
		return rotation;
	}

	@Override
	public MutableBoundingBox getBoundingBox(BlockPos pos)
	{
		return template.getMutableBoundingBox(new PlacementSettings().setRotation(this.rotation).setMirror(mirror), pos.add(rotationOffset));
	}
	
	@Override
	public CityBuildingStructure setRotation(Rotation rotation)
	{
		this.rotation = rotation;
		rotationOffset = template.getSize().rotate(rotation);
		return this;
	}
}
