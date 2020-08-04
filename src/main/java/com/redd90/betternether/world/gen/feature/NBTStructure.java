package com.redd90.betternether.world.gen.feature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import com.redd90.betternether.BetterNether;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;

public class NBTStructure {
	protected ResourceLocation location;
	protected Template structure;
	protected Mirror mirror = Mirror.NONE;
	protected Rotation rotation = Rotation.NONE;

	public NBTStructure(String structure)
	{
		location = new ResourceLocation(BetterNether.MODID, structure);
		this.structure = readStructureFromJar(location);
	}

	protected NBTStructure(ResourceLocation location, Template structure)
	{
		this.location = location;
		this.structure = structure;
	}
	
	public NBTStructure setRotation(Rotation rotation)
	{
		this.rotation = rotation;
		return this;
	}
	
	public Mirror getMirror()
	{
		return mirror;
	}
	
	public NBTStructure setMirror(Mirror mirror)
	{
		this.mirror = mirror;
		return this;
	}
	
	public void randomRM(Random random)
	{
		rotation = Rotation.values()[random.nextInt(4)];
		mirror = Mirror.values()[random.nextInt(3)];
	}
	
	public boolean generateCentered(IWorld world, BlockPos pos)
	{
		if(structure == null)
		{
			System.out.println("No structure: " + location.toString());
			return false;
		}
		
		Mutable blockpos2 = new Mutable().setPos(structure.getSize());
		if (this.mirror == Mirror.FRONT_BACK)
			blockpos2.setX(-blockpos2.getX());
		if (this.mirror == Mirror.LEFT_RIGHT)
			blockpos2.setZ(-blockpos2.getZ());
		blockpos2.setPos(blockpos2.rotate(rotation));
		PlacementSettings data = new PlacementSettings().setRotation(this.rotation).setMirror(this.mirror);
		structure.func_237144_a_(world, pos.add(-blockpos2.getX() >> 1, 0, -blockpos2.getZ() >> 1), data, world.getRandom());
		return true;
	}
	
	private Template readStructureFromJar(ResourceLocation resource)
    {
        String ns = resource.getNamespace();
        String nm = resource.getPath();

        try
        {
        	InputStream inputstream = MinecraftServer.class.getResourceAsStream("/data/" + ns + "/structures/" + nm + ".nbt");
            return readStructureFromStream(inputstream);
        }
        catch (IOException e)
        {
			e.printStackTrace();
		}
        
        return null;
    }
	
	private Template readStructureFromStream(InputStream stream) throws IOException
    {
		CompoundNBT nbttagcompound = CompressedStreamTools.readCompressed(stream);

        Template template = new Template();
        template.read(nbttagcompound);
        
        return template;
    }
	
	public BlockPos getSize()
	{
		if (rotation == Rotation.NONE || rotation == Rotation.CLOCKWISE_180)
			return structure.getSize();
		else
		{
			BlockPos size = structure.getSize();
			int x = size.getX();
			int z = size.getZ();
			return new BlockPos(z, size.getY(), x);
		}
	}
	
	public String getName()
	{
		return location.getPath();
	}

	public MutableBoundingBox getBoundingBox(BlockPos pos)
	{
		return structure.getMutableBoundingBox(new PlacementSettings().setRotation(this.rotation).setMirror(mirror), pos);
	}
}
