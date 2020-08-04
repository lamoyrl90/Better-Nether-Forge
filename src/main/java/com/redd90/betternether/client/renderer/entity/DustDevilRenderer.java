package com.redd90.betternether.client.renderer.entity;


import com.redd90.betternether.BetterNether;
import com.redd90.betternether.client.renderer.entity.model.DustDevilModel;
import com.redd90.betternether.entity.DustDevilEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DustDevilRenderer extends MobRenderer<DustDevilEntity, DustDevilModel<DustDevilEntity>> {
   private static final ResourceLocation DUST_DEVIL_TEXTURES = new ResourceLocation(BetterNether.MODID, "textures/entity/dust_devil.png");

   public DustDevilRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new DustDevilModel<>(), 0.5F);
   }

   public ResourceLocation getEntityTexture(DustDevilEntity entity) {
      return DUST_DEVIL_TEXTURES;
   }
}