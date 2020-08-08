package com.redd90.betternether.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntitySpawnPlacementRegistry.PlacementType;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BNGlassBlock extends BNBlock {
	
	public BNGlassBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.properties.notSolid();
		this.setRenderLayer(BNRenderLayer.TRANSLUCENT);
	}

    @Override
    public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return 1.0F;
    }
	
    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return true;
    }
    
    @Override
    public boolean canCreatureSpawn(BlockState state, IBlockReader world, BlockPos pos, PlacementType type, EntityType<?> entityType) {
        return false;
    }

    @Override
    public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext ctx) {
        return VoxelShapes.empty();
    }

}
