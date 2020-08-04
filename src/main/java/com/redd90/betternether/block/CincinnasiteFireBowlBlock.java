package com.redd90.betternether.block;

import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;


public class CincinnasiteFireBowlBlock extends BNBlock {

	private static final VoxelShape SHAPE = Block.makeCuboidShape(0, 0, 0, 16, 12, 16);
	public static final BooleanProperty LIT = BlockStateProperties.LIT;
	   
	
	public CincinnasiteFireBowlBlock(Properties properties) {
		super(properties.notSolid());
		this.setDefaultState(getStateContainer().getBaseState().with(LIT, false));
		this.setRenderLayer(BNRenderLayer.CUTOUT);
	}

	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> stateContainer) {
		stateContainer.add(LIT);
	}
	
	@Override
	public VoxelShape getShape(BlockState state, IBlockReader view, BlockPos pos, ISelectionContext ctx)
	{
		return SHAPE;
	}
	
	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
	{
		if (hit.getFace() == Direction.UP)
		{
			if (player.getHeldItemMainhand().getItem() == Items.FLINT_AND_STEEL && !state.get(LIT))
			{
				world.setBlockState(pos, state.with(LIT, true));
				if (!player.isCreative() && !world.isRemote)
					player.getHeldItemMainhand().attemptDamageItem(1, world.rand, (ServerPlayerEntity) player);
				world.playSound(player, pos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
				return ActionResultType.SUCCESS;
			}
			else if (player.getHeldItemMainhand().isEmpty() && state.get(LIT))
			{
				world.setBlockState(pos, state.with(LIT, false));
				world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0F, world.rand.nextFloat() * 0.4F + 0.8F);
				return ActionResultType.SUCCESS;
			}
		}
		return ActionResultType.FAIL;
	}
	
	@Override
	public void onEntityWalk(World world, BlockPos pos, Entity entity)
	{
		if (!entity.isImmuneToFire() && entity instanceof LivingEntity && world.getBlockState(pos).get(LIT))
		{
			entity.attackEntityFrom(DamageSource.HOT_FLOOR, 1.0F);
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random random)
	{
		if (state.get(LIT))
		{
			if (random.nextInt(24) == 0)
				world.playSound(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, SoundEvents.BLOCK_FIRE_AMBIENT, SoundCategory.BLOCKS, 1.0F + random.nextFloat(), random.nextFloat() * 0.7F + 0.3F, false);
			if (random.nextInt(4) == 0)
				world.addParticle(ParticleTypes.LARGE_SMOKE, pos.getX() + random.nextDouble(), pos.getY() + 0.75, pos.getZ() + random.nextDouble(), 0.0D, 0.0D, 0.0D);
		}
	}
	
    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (world.getBlockState(pos).getBlock() instanceof CincinnasiteFireBowlBlock) {
    		return world.getBlockState(pos).get(LIT) ? 15 : 0;
        }
        return 0;
    }
}
