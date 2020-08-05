package com.redd90.betternether.client.renderer.entity;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.client.renderer.entity.model.OvergrownSkeletonModel;
import com.redd90.betternether.entity.OvergrownSkeletonEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.client.renderer.entity.layers.ElytraLayer;
import net.minecraft.client.renderer.entity.layers.HeadLayer;
import net.minecraft.client.renderer.entity.layers.HeldItemLayer;
import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.util.ResourceLocation;

public class OvergrownSkeletonRenderer extends MobRenderer<OvergrownSkeletonEntity, SkeletonModel<OvergrownSkeletonEntity>> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(BetterNether.MODID, "textures/entity/jungle_skeleton.png");
	
	public OvergrownSkeletonRenderer(EntityRendererManager renderManager)
	{
		super(renderManager, new OvergrownSkeletonModel(), 0.4F);
		this.addLayer(new BipedArmorLayer<OvergrownSkeletonEntity, SkeletonModel<OvergrownSkeletonEntity>, SkeletonModel<OvergrownSkeletonEntity>>(this, new SkeletonModel<OvergrownSkeletonEntity>(0.5F, true), new SkeletonModel<OvergrownSkeletonEntity>(1.0F, true)));
		this.addLayer(new HeldItemLayer<OvergrownSkeletonEntity, SkeletonModel<OvergrownSkeletonEntity>>(this));
		this.addLayer(new ElytraLayer<OvergrownSkeletonEntity, SkeletonModel<OvergrownSkeletonEntity>>(this));
		this.addLayer(new HeadLayer<OvergrownSkeletonEntity, SkeletonModel<OvergrownSkeletonEntity>>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(OvergrownSkeletonEntity entity)
	{
		return TEXTURE;
	}
}
