package com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill;

import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.ContainerBallMill;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.BallMill;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.GuiBallMill;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.BallMillRecipes;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.slots.SlotBallMillFuel;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBoat;
import net.minecraft.item.ItemDoor;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TileEntityBallMill extends TileEntityLockable implements ITickable, ISidedInventory{
	private static final int[] SLOTS_TOP = new int[] {0};
	private static final int[] SLOTS_BOTTOM = new int[] {2, 1};
	private static final int[] SLOTS_SIDES = new int[] {1};
	private NonNullList<ItemStack> ballMillItemStacks = NonNullList.<ItemStack>withSize(3, ItemStack.EMPTY);
	private int millBurnTime;
	private int currentItemMillingTime;
	private int millingTime;
	private int totalMillingTime;
	private String blockCustomName;
	@Override
	public int getSizeInventory() {
		return this.ballMillItemStacks.size();
	}
	@Override
	public boolean isEmpty() {
		for(ItemStack stack : this.ballMillItemStacks){
			if(!stack.isEmpty()){
				return false;
			}
		}
		return true;
	}
	@Override
	public ItemStack getStackInSlot(int index) {
		return this.ballMillItemStacks.get(index);
	}
	@Override
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(ballMillItemStacks, index, count);
	}
	@Override
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(ballMillItemStacks, index);
	}
	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemStack = this.ballMillItemStacks.get(index);
		boolean flag = !stack.isEmpty() && stack.isItemEqual(itemStack) && ItemStack.areItemStackShareTagsEqual(stack, itemStack);
		this.ballMillItemStacks.set(index, stack);
		if(stack.getCount() > this.getInventoryStackLimit()){
			stack.setCount(this.getInventoryStackLimit());
		}
		if(index == 0 && !flag){
			this.totalMillingTime = this.getMillingTime(stack);
			this.millingTime = 0;
			this.markDirty();
		}
	}
	@Override
	public String getName() {
		return this.hasCustomName() ? this.blockCustomName : "container.ball_mill";
	}
	@Override
	public boolean hasCustomName() {
		return this.blockCustomName != null && !this.blockCustomName.isEmpty();
	}
	public void setCustomInventoryName(String blockCustomName){
		this.blockCustomName = blockCustomName;
	}
	public static void registerFixesFurnace(DataFixer fixer){
		fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(TileEntityBallMill.class, new String[] {"Items"}));
	}
	public void readFromNBT(NBTTagCompound compound){
        super.readFromNBT(compound);
        this.ballMillItemStacks = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(compound, this.ballMillItemStacks);
        this.millBurnTime = compound.getInteger("BurnTime");
        this.millingTime = compound.getInteger("CookTime");
        this.totalMillingTime = compound.getInteger("CookTimeTotal");
        this.currentItemMillingTime = getItemBurnTime(this.ballMillItemStacks.get(1));
        if (compound.hasKey("CustomName", 8)){
            this.blockCustomName = compound.getString("CustomName");
        }
    }
    public NBTTagCompound writeToNBT(NBTTagCompound compound){
        super.writeToNBT(compound);
        compound.setInteger("millBurnTime", (short)this.millBurnTime);
        compound.setInteger("MillingTime", (short)this.millingTime);
        compound.setInteger("MillingTimeTotal", (short)this.totalMillingTime);
        ItemStackHelper.saveAllItems(compound, this.ballMillItemStacks);
        if (this.hasCustomName()){
            compound.setString("CustomName", this.blockCustomName);
        }
        return compound;
    }
    @Override
    public int getInventoryStackLimit() {
    	return 64;
    }
    public boolean isMilling(){
    	return this.millBurnTime > 0;
    }
    @SideOnly(Side.CLIENT)
    public static boolean isMilling(IInventory inventory){
    	return inventory.getField(0) > 0;
    }
    public void update(){
        boolean flag = this.isMilling();
        boolean flag1 = false;
        if (this.isMilling()){
            --this.millBurnTime;
        }
        if (!this.world.isRemote){
            ItemStack itemstack = this.ballMillItemStacks.get(1);
            if (this.isMilling() || !itemstack.isEmpty() && !((ItemStack)this.ballMillItemStacks.get(0)).isEmpty()){
                if (!this.isMilling() && this.canSmelt()){
                    this.millBurnTime = getItemBurnTime(itemstack);
                    this.currentItemMillingTime = this.millBurnTime;
                    if (this.isMilling()){
                        flag1 = true;
                        if (!itemstack.isEmpty()){
                            Item item = itemstack.getItem();
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()){
                                ItemStack item1 = item.getContainerItem(itemstack);
                                this.ballMillItemStacks.set(1, item1);
                            }
                        }
                    }
                }
                if (this.isMilling() && this.canSmelt()){
                    ++this.millingTime;
                    if (this.millingTime == this.totalMillingTime){
                        this.millingTime = 0;
                        this.totalMillingTime = this.getMillingTime(this.ballMillItemStacks.get(0));
                        this.smeltItem();
                        flag1 = true;
                    }
                }
                else{
                    this.millingTime = 0;
                }
            }
            else if (!this.isMilling() && this.millingTime > 0){
                this.millingTime = MathHelper.clamp(this.millingTime - 2, 0, this.totalMillingTime);
            }
            if (flag != this.isMilling()){
                flag1 = true;
                BallMill.setState(this.isMilling(), this.world, this.pos);
            }
        }
        if (flag1){
            this.markDirty();
        }
    }
    public int getMillingTime(ItemStack stack){
    	return 10;
    }
    private boolean canSmelt(){
        if (((ItemStack)this.ballMillItemStacks.get(0)).isEmpty()){
            return false;
        }
        else{
            ItemStack itemstack = BallMillRecipes.instance().getCookingResult(this.ballMillItemStacks.get(0));
            if (itemstack.isEmpty()){
                return false;
            }
            else{
                ItemStack itemstack1 = this.ballMillItemStacks.get(2);
                if (itemstack1.isEmpty()){
                    return true;
                }
                else if (!itemstack1.isItemEqual(itemstack)){
                    return false;
                }
                else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit() && itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize())  {
                    return true;
                }
                else{

                    return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); 
                }
            }
        }
    }
    public void smeltItem() {
        if (this.canSmelt()){
            ItemStack itemstack = this.ballMillItemStacks.get(0);
            ItemStack itemstack1 = BallMillRecipes.instance().getCookingResult(itemstack);
            ItemStack itemstack2 = this.ballMillItemStacks.get(2);
            if (itemstack2.isEmpty()){
                this.ballMillItemStacks.set(2, itemstack1.copy());
            }
            else if (itemstack2.getItem() == itemstack1.getItem()){
                itemstack2.grow(itemstack1.getCount());
            }
            if (itemstack.getItem() == Item.getItemFromBlock(Blocks.SPONGE) && itemstack.getMetadata() == 1 && !((ItemStack)this.ballMillItemStacks.get(1)).isEmpty() && ((ItemStack)this.ballMillItemStacks.get(1)).getItem() == Items.BUCKET){
                this.ballMillItemStacks.set(1, new ItemStack(Items.WATER_BUCKET));
            }
            itemstack.shrink(1);
        }
    }
    public static int getItemBurnTime(ItemStack stack){
        if (stack.isEmpty()){
            return 0;
        }
        else{
            int burnTime = net.minecraftforge.event.ForgeEventFactory.getItemBurnTime(stack);
            if (burnTime >= 0) return burnTime;
            Item item = stack.getItem();
            if (item == Item.getItemFromBlock(Blocks.WOODEN_SLAB)){
                return 150;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOOL)){
                return 100;
            }
            else if (item == Item.getItemFromBlock(Blocks.CARPET)){
                return 67;
            }
            else if (item == Item.getItemFromBlock(Blocks.LADDER)){
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.WOODEN_BUTTON)){
                return 100;
            }
            else if (Block.getBlockFromItem(item).getDefaultState().getMaterial() == Material.WOOD){
                return 300;
            }
            else if (item == Item.getItemFromBlock(Blocks.COAL_BLOCK)) {
                return 16000;
            }
            else if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())){
                return 200;
            }
            else if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())){
                return 200;
            }
            else if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())){
                return 200;
            }
            else if (item == Items.STICK){
                return 100;
            }
            else if (item != Items.BOW && item != Items.FISHING_ROD) {
                if (item == Items.SIGN){
                    return 200;
                }
                else if (item == Items.COAL) {
                    return 1600;
                }
                else if (item != Item.getItemFromBlock(Blocks.SAPLING) && item != Items.BOWL){
                    if (item == Items.BLAZE_ROD) {
                        return 2400;
                    }
                    else if (item instanceof ItemDoor && item != Items.IRON_DOOR) {
                        return 200;
                    }
                    else{
                        return item instanceof ItemBoat ? 400 : 0;
                    }
                }
                else{
                    return 100;
                }
            }
            else {
                return 300;
            }
        }
    }
    public static boolean isItemFuel(ItemStack stack){
    	return getItemBurnTime(stack) > 0;
    }
    @Override
    public boolean isUsableByPlayer(EntityPlayer player) {
    	if(this.world.getTileEntity(this.pos) != this){
    		return false;
    	}
    	else{
    		return player.getDistanceSq((double)this.pos.getX() + 0.5D, (double)this.pos.getY() + 0.5D, (double)this.pos.getZ() + 0.5D) <= 64.0D;
    	}
    }
    public void openInventory(EntityPlayer player){
    }
    public void closeInventory(EntityPlayer player){
    }
    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack) {
    	if(index == 2){
    		return false;
    	}
    	else if(index != 1){
    		return true;
    	}
    	else{
    		ItemStack itemStack = this.ballMillItemStacks.get(1);
    		return isItemFuel(stack) || SlotBallMillFuel.isBucket(stack) && itemStack.getItem() != Items.BUCKET;
    	}
    }
    public int[] getSlotsForFace(EnumFacing side){
    	if(side == EnumFacing.DOWN){
    		return SLOTS_BOTTOM;
    	}
    	else{
    		return side == EnumFacing.UP ? SLOTS_TOP : SLOTS_SIDES;
    	}
    }
    @Override
    public boolean canInsertItem(int index, ItemStack itemStackIn, EnumFacing direction) {
    	return this.isItemValidForSlot(index, itemStackIn);
    }
    @Override
    public boolean canExtractItem(int index, ItemStack stack, EnumFacing direction) {
    	if(direction == EnumFacing.DOWN && index == 1){
    		Item item = stack.getItem();
    		if(item != Items.WATER_BUCKET && item != Items.BUCKET){
    			return false;
    		}
    	}
    	return true;
    }
    @Override
    public String getGuiID() 
    {
    	return "vpmm:ball_mill";
    }
    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
    	return new ContainerBallMill(playerInventory, this);
    }
    public int getField(int id){
        switch (id){
            case 0:
                return this.millBurnTime;
            case 1:
                return this.currentItemMillingTime;
            case 2:
                return this.millingTime;
            case 3:
                return this.totalMillingTime;
            default:
                return 0;
        }
    }
    public void setField(int id, int value){
        switch (id){
            case 0:
                this.millBurnTime = value;
                break;
            case 1:
                this.currentItemMillingTime = value;
                break;
            case 2:
                this.millingTime = value;
                break;
            case 3:
                this.totalMillingTime = value;
        }
    }
    @Override
    public int getFieldCount() {
    	return 4;
    }
    @Override
    public void clear() {
    	this.ballMillItemStacks.clear();
    }
    net.minecraftforge.items.IItemHandler handlerTop = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.UP);
    net.minecraftforge.items.IItemHandler handlerBottom = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.DOWN);
    net.minecraftforge.items.IItemHandler handlerSide = new net.minecraftforge.items.wrapper.SidedInvWrapper(this, net.minecraft.util.EnumFacing.WEST);
    @Override
    @javax.annotation.Nullable
    public <T> T getCapability(net.minecraftforge.common.capabilities.Capability<T> capability, @javax.annotation.Nullable net.minecraft.util.EnumFacing facing){
        if (facing != null && capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
            if (facing == EnumFacing.DOWN)
                return (T) handlerBottom;
            else if (facing == EnumFacing.UP)
                return (T) handlerTop;
            else
                return (T) handlerSide;
        return super.getCapability(capability, facing);
    }
}
