package com.ThatKerbonaut.VanillaPlusMore.init;

import java.util.ArrayList;
import java.util.List;

import com.ThatKerbonaut.VanillaPlusMore.blocks.BlockBase;
import com.ThatKerbonaut.VanillaPlusMore.blocks.CornerQuartzPiller;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ReinforcedBlock;
import com.ThatKerbonaut.VanillaPlusMore.blocks.TitaniumBlock;
import com.ThatKerbonaut.VanillaPlusMore.blocks.TitaniumOre;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.BallMill;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class ModBlocks {
	public static final List<Block> BLOCKS = new ArrayList<Block>();
	public static final Block REINFORCED_BLOCK = new ReinforcedBlock("reinforced_block", Material.IRON);
	public static final Block TITANIUM_ORE = new TitaniumOre("titanium_ore", Material.ROCK);
	public static final Block TITANIUM_BLOCK = new TitaniumBlock("titanium_block", Material.IRON);
	public static final Block BALL_MILL_OFF = new BallMill("ball_mill_off", false);
	public static final Block BALL_MILL_ON = new BallMill("ball_mill_on", true);
	//Removes these blocks for the public
	//public static final Block CORNER_QUARTZ_PILLER = new CornerQuartzPiller("corner_quartz_piller", Material.ROCK);
}
