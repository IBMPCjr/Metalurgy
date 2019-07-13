package com.ThatKerbonaut.VanillaPlusMore.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class TitaniumBlock extends BlockBase{
	public TitaniumBlock(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(13.0F);
		setResistance(55.0F);
		setHarvestLevel("pickaxe", 3);
		setLightLevel(0.0F);
		setLightOpacity(0);
		//setBlockUnbreakable();
	}
	
}
