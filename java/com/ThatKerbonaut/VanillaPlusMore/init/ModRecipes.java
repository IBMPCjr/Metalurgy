package com.ThatKerbonaut.VanillaPlusMore.init;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModRecipes {
	public static void init() {
		GameRegistry.addSmelting(ModItems.RAW_REINFORCED_INGOT, new ItemStack(ModItems.REINFORCED_INGOT, 2), 1.4F);
		GameRegistry.addSmelting(ModBlocks.TITANIUM_ORE, new ItemStack(ModItems.TITANIUM_INGOT, 1), 0.5F);
	}
}
