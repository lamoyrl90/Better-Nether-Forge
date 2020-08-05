package com.redd90.betternether.client.renderer;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.ResourceLocation;

public class BNRenderType extends RenderType {

	public static RenderState.TransparencyState translucentTransparency;
	public static RenderState.WriteMaskState colorMask;
	public static RenderState.FogState fog;
	public static RenderState.DepthTestState lEqualDepthTest;
	public static LayerState polygonZLayering;
	public static TargetState translucentTarget;
	
	public BNRenderType(String nameIn, VertexFormat formatIn, int drawModeIn, int bufferSizeIn, boolean useDelegateIn,
			boolean needsSortingIn, Runnable setupTaskIn, Runnable clearTaskIn) {
		super(nameIn, formatIn, drawModeIn, bufferSizeIn, useDelegateIn, needsSortingIn, setupTaskIn, clearTaskIn);
	}
	
	public static RenderType getFirefly(ResourceLocation texture)
	{
		RenderType.State multiPhaseParameters = RenderType.State.getBuilder()
				.texture(new RenderState.TextureState(texture, false, false))
				.transparency(translucentTransparency)
				.writeMask(colorMask)
				.fog(fog)
				.depthTest(lEqualDepthTest)
				.layer(polygonZLayering)
				.target(translucentTarget)
				.build(true);
		return RenderType.makeType("firefly", DefaultVertexFormats.POSITION_COLOR_TEX, 7, 256, false, true, multiPhaseParameters);
	}
}
