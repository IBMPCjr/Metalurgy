package com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.slots;

import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.TileEntityBallMill;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotBallMillFuel extends Slot{

	public SlotBallMillFuel(IInventory inventoryIn, int index, int xPosition, int yPosition) {
		super(inventoryIn, index, xPosition, yPosition);
	}
	@Override
	public boolean isItemValid(ItemStack stack) {
		return TileEntityBallMill.isItemFuel(stack) || isBucket(stack);
	}
	@Override
	public int getItemStackLimit(ItemStack stack) {
		return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}
	public static boolean isBucket(ItemStack stack){
		return stack.getItem() == Items.BUCKET;
	}
}
