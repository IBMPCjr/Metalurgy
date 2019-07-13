package com.ThatKerbonaut.VanillaPlusMore.Tabs;

import com.ThatKerbonaut.VanillaPlusMore.init.ModItems;
import com.ThatKerbonaut.VanillaPlusMore.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MetalurgyTab extends CreativeTabs{
	  public MetalurgyTab() {
		  super(Reference.MOD_ID);
		// TODO Auto-generated constructor stub
	}
	  @SideOnly(Side.CLIENT)
	  @Override
	  public ItemStack getTabIconItem() {
		  return new ItemStack(ModItems.REINFORCED_INGOT);
	  }
}
