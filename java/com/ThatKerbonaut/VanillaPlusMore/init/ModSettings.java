package com.ThatKerbonaut.VanillaPlusMore.init;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import net.minecraft.block.state.BlockPistonStructureHelper;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class ModSettings {
	public static void changeSettings() {
		ReflectionHelper.setPrivateValue(ToolMaterial.class, ToolMaterial.DIAMOND, 4, 5);
	}
	public static void changeBlockSettings() {
		Blocks.DIAMOND_ORE.setHarvestLevel("pickaxe", 3);
		Blocks.DIAMOND_ORE.setHardness(5);
	}
}