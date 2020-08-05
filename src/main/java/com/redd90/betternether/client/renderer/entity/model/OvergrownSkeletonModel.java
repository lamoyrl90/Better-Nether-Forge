package com.redd90.betternether.client.renderer.entity.model;

import java.util.Random;

import com.redd90.betternether.entity.OvergrownSkeletonEntity;
import com.redd90.betternether.util.MHelper;

import net.minecraft.client.renderer.entity.model.SkeletonModel;
import net.minecraft.client.renderer.model.ModelRenderer;


public class OvergrownSkeletonModel extends SkeletonModel<OvergrownSkeletonEntity> {
	private static final float ANGLE45 = (float) Math.PI * 0.25F;
	private static final float ANGLE90 = (float) Math.PI * 0.5F;
	private static final Random RANDOM = new Random();
	private static final float BOUND_MIN = ANGLE90 * 2F / 3F;
	private static final float BOUND_MAX = ANGLE90 * 4F / 5F;
	
	public OvergrownSkeletonModel()
	{
		super(0.0F, false);
		
		for (int i = 0; i < 4; i++)
		{
			float angle = ANGLE45 + (i / 4F) * MHelper.PI2;
			ModelRenderer leaf = new ModelRenderer(this, 24, 0);
			leaf.setRotationPoint((float) -Math.sin(angle), -8, (float) -Math.cos(angle));
			leaf.addBox(-3.0F, -8.0F, 0.0F, 6.0F, 8.0F, 0.0F);
			leaf.rotateAngleX = MHelper.randRange(BOUND_MIN, BOUND_MAX, RANDOM);
			leaf.rotateAngleY = angle;
			this.bipedHead.addChild(leaf);
		}
	}
}
