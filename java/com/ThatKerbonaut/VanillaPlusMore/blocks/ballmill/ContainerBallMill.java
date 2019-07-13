package com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill;

import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.slots.SlotBallMillFuel;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.slots.SlotBallMillOutput;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerBallMill extends Container{
	private final IInventory tileMill;
	private int millingTime, totalmillingTime, millBurnTime, currentItemMillingTime;
	public ContainerBallMill(InventoryPlayer playerInventory, IInventory millInventory){
		this.tileMill = millInventory;
		this.addSlotToContainer(new Slot(millInventory, 0, 56, 17));
		this.addSlotToContainer(new SlotBallMillFuel(millInventory, 1, 56, 53));
		this.addSlotToContainer(new SlotBallMillOutput(playerInventory.player, millInventory, 2, 116, 35));
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 9; ++j){
				this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
			}
		}
		for(int k = 0; k < 9; k++){
			this.addSlotToContainer(new Slot(playerInventory, k, 8 + k * 18, 142));
		}
	}
	@Override
	public void addListener(IContainerListener listener) {
		super.addListener(listener);
		listener.sendAllWindowProperties(this, this.tileMill);
	}
	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
        for (int i = 0; i < this.listeners.size(); ++i){
            IContainerListener icontainerlistener = this.listeners.get(i);
            if (this.millingTime != this.tileMill.getField(2)){
                icontainerlistener.sendWindowProperty(this, 2, this.tileMill.getField(2));
            }
            if (this.millBurnTime != this.tileMill.getField(0)){
                icontainerlistener.sendWindowProperty(this, 0, this.tileMill.getField(0));
            }
            if (this.currentItemMillingTime != this.tileMill.getField(1)){
                icontainerlistener.sendWindowProperty(this, 1, this.tileMill.getField(1));
            }
            if (this.totalmillingTime != this.tileMill.getField(3)){
                icontainerlistener.sendWindowProperty(this, 3, this.tileMill.getField(3));
            }
        }
        this.millingTime = this.tileMill.getField(2);
        this.millBurnTime = this.tileMill.getField(0);
        this.currentItemMillingTime = this.tileMill.getField(1);
        this.totalmillingTime = this.tileMill.getField(3);
	}
	@Override
	public void updateProgressBar(int id, int data) {
		this.tileMill.setField(id, data);
	}
	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return  this.tileMill.isUsableByPlayer(playerIn);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()){
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();
            if (index == 2){
                if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (index != 1 && index != 0){
                if (!BallMillRecipes.instance().getCookingResult(itemstack1).isEmpty()){
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)){
                        return ItemStack.EMPTY;
                    }
                }
                else if (TileEntityBallMill.isItemFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false)){
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 3 && index < 30) {
                    if (!this.mergeItemStack(itemstack1, 30, 39, false)){
                        return ItemStack.EMPTY;
                    }
                }
                else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)){
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 3, 39, false)){
                return ItemStack.EMPTY;
            }
            if (itemstack1.isEmpty()){
                slot.putStack(ItemStack.EMPTY);
            }
            else{
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()){
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }
}
