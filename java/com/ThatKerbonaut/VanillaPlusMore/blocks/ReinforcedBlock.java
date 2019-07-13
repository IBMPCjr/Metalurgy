package com.ThatKerbonaut.VanillaPlusMore.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class ReinforcedBlock extends BlockBase {
	public ReinforcedBlock(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.METAL);
		setHardness(7.5F);
		setResistance(40.0F);
		setHarvestLevel("pickaxe", 2);
		setLightLevel(0.0F);
		setLightOpacity(0);
		//setBlockUnbreakable();
	}
	
}
