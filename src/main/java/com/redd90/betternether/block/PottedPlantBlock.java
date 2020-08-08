package com.redd90.betternether.block;

import java.util.Collections;
import java.util.List;

import com.redd90.betternether.registry.BNBlocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;


public class PottedPlantBlock extends BNBlock {
	public static final EnumProperty<PottedPlantShape> PLANT = EnumProperty.create("plant", PottedPlantShape.class);
	
	public PottedPlantBlock()
	{
		super(AbstractBlock.Properties.create(Material.PLANTS, MaterialColor.BLACK)
				.sound(SoundType.CROP)
				.notSolid()
				.doesNotBlockMovement()
				.zeroHardnessAndResistance());
		this.setRenderLayer(BNRenderLayer.CUTOUT);
		this.setDefaultState(getStateContainer().getBaseState().with(PLANT, PottedPlantShape.AGAVE));
	}
		
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
    	if (state.get(PLANT) == PottedPlantShape.WILLOW)
			return 12;
		else if (state.get(PLANT) == PottedPlantShape.JELLYFISH_MUSHROOM)
			return 13;
		else
			return 0;
    }
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ePos)
	{
		BlockState block = state.get(PLANT).getBlock().getDefaultState();
		Vector3d vec3d = block.getOffset(view, pos);
		return block.getShape(view, pos, ePos).withOffset(-vec3d.x, -0.5 - vec3d.y, -vec3d.z);
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateManager)
	{
		stateManager.add(PLANT);
	}

	@Override
	public boolean isValidPosition(BlockState state, IWorldReader world, BlockPos pos)
	{
		return world.getBlockState(pos.down()).getBlock() instanceof BNPotBlock;
	}

	@Override
	public BlockState updatePostPlacement(BlockState state, Direction facing, BlockState neighborState, IWorld world, BlockPos pos, BlockPos neighborPos)
	{
		if (!isValidPosition(state, world, pos))
			return Blocks.AIR.getDefaultState();
		else
			return state;
	}

	public static BlockState getPlant(Item item)
	{
		for (PottedPlantShape shape: PottedPlantShape.values())
		{
			if (shape.getItem().equals(item))
				return BNBlocks.POTTED_PLANT.get().getDefaultState().with(PLANT, shape);
		}
		return null;
	}
	
	@Override
	public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder)
	{
		Block block = state.get(PLANT).getBlock();
		return Collections.singletonList(new ItemStack(block.asItem()));
	}
	
	public static enum PottedPlantShape implements IStringSerializable
	{
		AGAVE("agave", BNBlocks.AGAVE.get()),
		BARREL_CACTUS("barrel_cactus", BNBlocks.BARREL_CACTUS.get()),
		BLACK_APPLE("black_apple", BNBlocks.BLACK_APPLE_SEED.get()),
		BLACK_BUSH("black_bush", BNBlocks.BLACK_BUSH.get()),
		EGG_PLANT("egg_plant", BNBlocks.EGG_PLANT.get()),
		INK_BUSH("ink_bush", BNBlocks.INK_BUSH_SEED.get()),
		REEDS("reeds", BNBlocks.NETHER_REED.get()),
		NETHER_CACTUS("nether_cactus", BNBlocks.NETHER_CACTUS.get()),
		NETHER_GRASS("nether_grass", BNBlocks.NETHER_GRASS.get()),
		ORANGE_MUSHROOM("orange_mushroom", BNBlocks.ORANGE_MUSHROOM.get()),
		RED_MOLD("red_mold", BNBlocks.RED_MOLD.get()),
		GRAY_MOLD("gray_mold", BNBlocks.GRAY_MOLD.get()),
		MAGMA_FLOWER("magma_flower", BNBlocks.MAGMA_FLOWER.get()),
		NETHER_WART("nether_wart", BNBlocks.WART_SEED.get()),
		WILLOW("willow", BNBlocks.WILLOW_SAPLING.get()),
		SMOKER("smoker", BNBlocks.SMOKER.get()),
		WART("wart", Blocks.NETHER_WART),
		JUNGLE_PLANT("jungle_plant", BNBlocks.JUNGLE_PLANT.get()),
		JELLYFISH_MUSHROOM("jellyfish_mushroom", BNBlocks.JELLYFISH_MUSHROOM_SAPLING.get()),
		SWAMP_GRASS("swamp_grass", BNBlocks.SWAMP_GRASS.get()),
		SOUL_GRASS("soul_grass", BNBlocks.SOUL_GRASS.get()),
		BONE_GRASS("bone_grass", BNBlocks.BONE_GRASS.get()),
		BONE_MUSHROOM("bone_mushroom", BNBlocks.BONE_MUSHROOM.get());
		
		private final Block block;
		private final String name;

		private PottedPlantShape(String name, Block block)
		{
			this.name = name;
			this.block = block;
		}
		
		@Override
		public String getString()
		{
			return name;
		}
		
		@Override
		public String toString()
		{
			return name;
		}
		
		public Item getItem()
		{
			return block.asItem();
		}
		
		public Block getBlock()
		{
			return block;
		}
	}
}
