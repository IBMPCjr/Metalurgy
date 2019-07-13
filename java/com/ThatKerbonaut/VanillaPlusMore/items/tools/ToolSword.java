package com.ThatKerbonaut.VanillaPlusMore.items.tools;

import com.ThatKerbonaut.VanillaPlusMore.Main;
import com.ThatKerbonaut.VanillaPlusMore.init.ModItems;
import com.ThatKerbonaut.VanillaPlusMore.util.IHasModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSword;

public class ToolSword extends ItemSword implements IHasModel{
	  public ToolSword(String name, ToolMaterial material) {
		  super(material);
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
