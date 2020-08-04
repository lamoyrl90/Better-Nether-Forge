package com.redd90.betternether.mixin;

import java.util.Optional;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.block.RespawnStatueBlock;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.server.ServerWorld;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

	@Inject(method = "func_234567_a_", at = @At(value = "HEAD"), cancellable = true)
	private static void statueRespawn(ServerWorld p_234567_0_, BlockPos p_234567_1_, boolean p_234567_2_, boolean p_234567_3_, CallbackInfoReturnable<Optional<Vector3d>> info)
	{
		BetterNether.LOGGER.debug("Hello from my mixin!");
		BlockState blockState = p_234567_0_.getBlockState(p_234567_1_);
		Block block = blockState.getBlock();
		if (block instanceof RespawnStatueBlock)
		{
			info.setReturnValue(findRespawnPosition(p_234567_0_, p_234567_1_, blockState));
			info.cancel();
		}
	}
	
	private static Optional<Vector3d> findRespawnPosition(ServerWorld world, BlockPos pos, BlockState state)
	{
		if (state.get(RespawnStatueBlock.TOP))
			pos = pos.down();
		pos = pos.offset(state.get(RespawnStatueBlock.FACING));
		BlockState state2 = world.getBlockState(pos);
		if (!state2.getMaterial().blocksMovement() && state2.getCollisionShape(world, pos).isEmpty())
			return Optional.of(Vector3d.copy(pos).add(0.5, 0, 0.5));
		else
			return Optional.empty();
	}
}
