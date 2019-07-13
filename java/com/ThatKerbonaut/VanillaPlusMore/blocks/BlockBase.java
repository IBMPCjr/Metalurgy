package com.ThatKerbonaut.VanillaPlusMore.blocks;

import com.ThatKerbonaut.VanillaPlusMore.Main;
import com.ThatKerbonaut.VanillaPlusMore.init.ModBlocks;
import com.ThatKerbonaut.VanillaPlusMore.init.ModItems;
import com.ThatKerbonaut.VanillaPlusMore.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;

public class BlockBase extends Block implements IHasModel {
	public BlockBase(String name, Material material) {
		super(material);
	    setUnlocalizedName(name);
	    setRegistryName(name);
	    setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
	    ModBlocks.BLOCKS.add(this);
	    ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	}

	@Override
	public void registerModels() {
		Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
	}
}
