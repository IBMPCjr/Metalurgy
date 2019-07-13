package com.ThatKerbonaut.VanillaPlusMore.blocks;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class CornerQuartzPiller extends BlockBase{
	public CornerQuartzPiller(String name, Material material) {
		super(name, material);
		setSoundType(SoundType.STONE);
		setHardness(1.5F);
		setResistance(30.0F);
		setHarvestLevel("pickaxe", 0);
		setLightLevel(0.0F);
		setLightOpacity(0);
		//setBlockUnbreakable();
	}
	
}