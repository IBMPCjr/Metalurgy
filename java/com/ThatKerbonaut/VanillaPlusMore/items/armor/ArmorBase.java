package com.ThatKerbonaut.VanillaPlusMore.items.armor;

import com.ThatKerbonaut.VanillaPlusMore.Main;
import com.ThatKerbonaut.VanillaPlusMore.init.ModItems;
import com.ThatKerbonaut.VanillaPlusMore.util.IHasModel;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;

public class ArmorBase extends ItemArmor implements IHasModel{

	public ArmorBase(String name, ArmorMaterial materialIn, int renderIndexIn, EntityEquipmentSlot equipmentSlotIn) {
		super(materialIn, renderIndexIn, equipmentSlotIn);
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
