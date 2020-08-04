package com.redd90.betternether.client.renderer.entity;

import com.redd90.betternether.BetterNether;
import com.redd90.betternether.client.renderer.entity.model.DustmiteModel;
import com.redd90.betternether.entity.DustmiteEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.EndermiteEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DustmiteRenderer extends MobRenderer<DustmiteEntity, DustmiteModel<DustmiteEntity>> {
   private static final ResourceLocation ENDERMITE_TEXTURES = new ResourceLocation(BetterNether.MODID, "textures/entity/dustmite.png");

   public DustmiteRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new DustmiteModel<>(), 0.3F);
   }

   protected float getDeathMaxRotation(EndermiteEntity entityLivingBaseIn) {
      return 180.0F;
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(DustmiteEntity entity) {
      return ENDERMITE_TEXTURES;
   }
}