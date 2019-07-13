package com.ThatKerbonaut.VanillaPlusMore.items;


import com.ThatKerbonaut.VanillaPlusMore.Main;
import com.ThatKerbonaut.VanillaPlusMore.init.ModItems;
import com.ThatKerbonaut.VanillaPlusMore.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemBase extends Item implements IHasModel {
  public ItemBase(String name) {
    setUnlocalizedName(name);
    setRegistryName(name);
    setCreativeTab(Main.METALURGY);
    ModItems.ITEMS.add(this);
  }
  @Override
  public void registerModels() {
	  Main.proxy.registerItemRenderer(this, 0, "inventory");
  }
}
