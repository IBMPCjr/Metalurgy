package com.ThatKerbonaut.VanillaPlusMore.init;

import java.util.ArrayList;
import java.util.List;
import com.ThatKerbonaut.VanillaPlusMore.items.ItemBase;
import com.ThatKerbonaut.VanillaPlusMore.items.armor.ArmorBase;
import com.ThatKerbonaut.VanillaPlusMore.items.tools.ToolAxe;
import com.ThatKerbonaut.VanillaPlusMore.items.tools.ToolHoe;
import com.ThatKerbonaut.VanillaPlusMore.items.tools.ToolPickaxe;
import com.ThatKerbonaut.VanillaPlusMore.items.tools.ToolSpade;
import com.ThatKerbonaut.VanillaPlusMore.items.tools.ToolSword;
import com.ThatKerbonaut.VanillaPlusMore.util.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ModItems {
  public static final List<Item> ITEMS = new ArrayList<Item>();
  //Materials-Tools
  public static final ToolMaterial MATERIAL_REINFORCED_INGOT = EnumHelper.addToolMaterial("material_reinforced_ingot", 3, 1000, 7.0F, 2.5F, 10);
  public static final ToolMaterial MATERIAL_DIAMOND_DUST_WOOD = EnumHelper.addToolMaterial("material_diamond_dust_wood", 1, 59, 3.0F, 0.5F, 15);
  public static final ToolMaterial MATERIAL_DIAMOND_DUST_STONE = EnumHelper.addToolMaterial("material_diamond_dust_stone", 2, 131, 5.0F, 1.5F, 5);
  public static final ToolMaterial MATERIAL_DIAMOND_DUST_IRON = EnumHelper.addToolMaterial("material_diamond_dust_iron", 3, 250, 7.0F, 2.5F, 14);
  public static final ToolMaterial MATERIAL_DIAMOND_DUST_GOLD = EnumHelper.addToolMaterial("material_diamond_dust_gold", 1, 32, 12.0F, 1.0F, 22);
  public static final ToolMaterial MATERIAL_TITANIUM_INGOT = EnumHelper.addToolMaterial("material_titanium_ingot", 3, 1250, 8.0F, 3.0F, 14);
  //Materials-Armour
  public static final ArmorMaterial ARMOR_MATERIAL_REINFORCED_INGOT = EnumHelper.addArmorMaterial("armor_material_reinforced_ingot", Reference.MOD_ID + ":reinforced_ingot", 13, new int[] {2, 6, 5, 2}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0.0F);
  public static final ArmorMaterial ARMOR_MATERIAL_TITANIUM_INGOT = EnumHelper.addArmorMaterial("armor_material_titanium_ingot", Reference.MOD_ID + ":titanium_ingot", 15, new int[] {3, 6, 6, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1.0F);
  //Items
  //public static final Item ARMORED_ELYTRA = new ItemBase("armored_elytra");
  public static final Item REINFORCED_INGOT = new ItemBase("reinforced_ingot");
  public static final Item TITANIUM_INGOT =new ItemBase("titanium_ingot");
  public static final Item RAW_REINFORCED_INGOT = new ItemBase("raw_reinforced_ingot");
  public static final Item DIAMOND_DUST = new ItemBase("diamond_dust");
  //Tools
  public static final ItemSword REINFORCED_SWORD = new ToolSword("reinforced_sword", MATERIAL_REINFORCED_INGOT);
  public static final ItemSpade REINFORCED_SHOVEL = new ToolSpade("reinforced_shovel", MATERIAL_REINFORCED_INGOT);
  public static final ItemPickaxe REINFORCED_PICKAXE = new ToolPickaxe("reinforced_pickaxe", MATERIAL_REINFORCED_INGOT);
  public static final ItemAxe REINFORCED_AXE = new ToolAxe("reinforced_axe", MATERIAL_REINFORCED_INGOT);
  public static final ItemHoe REINFORCED_HOE = new ToolHoe("reinforced_hoe", MATERIAL_REINFORCED_INGOT);
  public static final ItemSword DIAMOND_EDGED_WOOD_SWORD = new ToolSword("diamond_edged_wood_sword", MATERIAL_DIAMOND_DUST_WOOD);
  public static final ItemSword DIAMOND_EDGED_STONE_SWORD = new ToolSword("diamond_edged_stone_sword", MATERIAL_DIAMOND_DUST_STONE);
  public static final ItemSword DIAMOND_EDGED_IRON_SWORD = new ToolSword("diamond_edged_iron_sword", MATERIAL_DIAMOND_DUST_IRON);
  public static final ItemSword DIAMOND_EDGED_GOLD_SWORD = new ToolSword("diamond_edged_gold_sword", MATERIAL_DIAMOND_DUST_GOLD);
  public static final ItemSpade DIAMOND_TIPPED_WOOD_SHOVEL = new ToolSpade("diamond_tipped_wood_shovel", MATERIAL_DIAMOND_DUST_WOOD);
  public static final ItemSpade DIAMOND_TIPPED_STONE_SHOVEL = new ToolSpade("diamond_tipped_stone_shovel", MATERIAL_DIAMOND_DUST_STONE);
  public static final ItemSpade DIAMOND_TIPPED_IRON_SHOVEL = new ToolSpade("diamond_tipped_iron_shovel", MATERIAL_DIAMOND_DUST_IRON);
  public static final ItemSpade DIAMOND_TIPPED_GOLD_SHOVEL = new ToolSpade("diamond_tipped_gold_shovel", MATERIAL_DIAMOND_DUST_GOLD);
  public static final ItemPickaxe DIAMOND_TIPPED_WOOD_PICKAXE = new ToolPickaxe("diamond_tipped_wood_pickaxe", MATERIAL_DIAMOND_DUST_WOOD);
  public static final ItemPickaxe DIAMOND_TIPPED_STONE_PICKAXE = new ToolPickaxe("diamond_tipped_stone_pickaxe", MATERIAL_DIAMOND_DUST_STONE);
  public static final ItemPickaxe DIAMOND_TIPPED_IRON_PICKAXE = new ToolPickaxe("diamond_tipped_iron_pickaxe", MATERIAL_DIAMOND_DUST_IRON);
  public static final ItemPickaxe DIAMOND_TIPPED_GOLD_PICKAXE = new ToolPickaxe("diamond_tipped_gold_pickaxe", MATERIAL_DIAMOND_DUST_GOLD);
  public static final ItemAxe DIAMOND_TIPPED_WOOD_AXE = new ToolAxe("diamond_tipped_wood_axe", MATERIAL_DIAMOND_DUST_WOOD);
  public static final ItemAxe DIAMOND_TIPPED_STONE_AXE = new ToolAxe("diamond_tipped_stone_axe", MATERIAL_DIAMOND_DUST_STONE);
  public static final ItemAxe DIAMOND_TIPPED_IRON_AXE = new ToolAxe("diamond_tipped_iron_axe", MATERIAL_DIAMOND_DUST_IRON);
  public static final ItemAxe DIAMOND_TIPPED_GOLD_AXE = new ToolAxe("diamond_tipped_gold_axe", MATERIAL_DIAMOND_DUST_GOLD);
  public static final ItemHoe DIAMOND_TIPPED_WOOD_HOE = new ToolHoe("diamond_tipped_wood_hoe", MATERIAL_DIAMOND_DUST_WOOD);
  public static final ItemHoe DIAMOND_TIPPED_STONE_HOE = new ToolHoe("diamond_tipped_stone_hoe", MATERIAL_DIAMOND_DUST_STONE);
  public static final ItemHoe DIAMOND_TIPPED_IRON_HOE = new ToolHoe("diamond_tipped_iron_hoe", MATERIAL_DIAMOND_DUST_IRON);
  public static final ItemHoe DIAMOND_TIPPED_GOLD_HOE = new ToolHoe("diamond_tipped_gold_hoe", MATERIAL_DIAMOND_DUST_GOLD);
  public static final ItemSword TITANIUM_SWORD = new ToolSword("titanium_sword", MATERIAL_TITANIUM_INGOT);
  public static final ItemSpade TITANIUM_SHOVEL = new ToolSpade("titanium_shovel", MATERIAL_TITANIUM_INGOT);
  public static final ItemPickaxe TITANIUM_PICKAXE = new ToolPickaxe("titanium_pickaxe", MATERIAL_TITANIUM_INGOT);
  public static final ItemAxe TITANIUM_AXE = new ToolAxe("titanium_axe", MATERIAL_TITANIUM_INGOT);
  public static final ItemHoe TITANIUM_HOE = new ToolHoe("titanium_hoe", MATERIAL_TITANIUM_INGOT);
  //Armour
  public static final Item REINFORCED_HELMET = new ArmorBase("reinforced_helmet", ARMOR_MATERIAL_REINFORCED_INGOT, 1, EntityEquipmentSlot.HEAD);
  public static final Item REINFORCED_CHESTPLATE = new ArmorBase("reinforced_chestplate", ARMOR_MATERIAL_REINFORCED_INGOT, 1, EntityEquipmentSlot.CHEST);
  public static final Item REINFORCED_LEGGINGS = new ArmorBase("reinforced_leggings", ARMOR_MATERIAL_REINFORCED_INGOT, 2, EntityEquipmentSlot.LEGS);
  public static final Item REINFORCED_BOOTS = new ArmorBase("reinforced_boots", ARMOR_MATERIAL_REINFORCED_INGOT, 1, EntityEquipmentSlot.FEET);
  public static final Item TITANIUM_HELMET = new ArmorBase("titanium_helmet", ARMOR_MATERIAL_TITANIUM_INGOT, 1, EntityEquipmentSlot.HEAD);
  public static final Item TITANIUM_CHESTPLATE = new ArmorBase("titanium_chestplate", ARMOR_MATERIAL_TITANIUM_INGOT, 1, EntityEquipmentSlot.CHEST);
  public static final Item TITANIUM_LEGGINGS = new ArmorBase("titanium_leggings", ARMOR_MATERIAL_TITANIUM_INGOT, 2, EntityEquipmentSlot.LEGS);
  public static final Item TITANIUM_BOOTS = new ArmorBase("titanium_boots", ARMOR_MATERIAL_TITANIUM_INGOT, 1, EntityEquipmentSlot.FEET);
}