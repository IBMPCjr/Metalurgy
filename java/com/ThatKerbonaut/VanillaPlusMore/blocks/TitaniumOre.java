package com.ThatKerbonaut.VanillaPlusMore.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TitaniumOre extends BlockBase {
	public TitaniumOre(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 3);
	}
}
