package com.ThatKerbonaut.VanillaPlusMore.util.handlers;

import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.TileEntityBallMill;
import com.ThatKerbonaut.VanillaPlusMore.util.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TileEntityHandler {
	public static void registerTileEntities(){
		GameRegistry.registerTileEntity(TileEntityBallMill.class, new ResourceLocation(Reference.MOD_ID + ":ball_mill"));
	}
}
