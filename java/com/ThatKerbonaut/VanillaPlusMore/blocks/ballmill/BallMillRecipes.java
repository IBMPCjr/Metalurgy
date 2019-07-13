package com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill;

import net.minecraft.item.ItemStack;

import com.ThatKerbonaut.VanillaPlusMore.init.ModBlocks;
import com.ThatKerbonaut.VanillaPlusMore.init.ModItems;
import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class BallMillRecipes {
	private static final BallMillRecipes COOKING_BASE = new BallMillRecipes();
	private final Map<ItemStack, ItemStack> cookingList = Maps.<ItemStack, ItemStack>newHashMap();
	private final Map<ItemStack, Float> experienceList = Maps.<ItemStack, Float>newHashMap();
	public static BallMillRecipes instance(){
        return COOKING_BASE;
    }
	private BallMillRecipes(){
		this.addCookingRecipeForBlock(ModBlocks.TITANIUM_ORE, new ItemStack(ModItems.TITANIUM_INGOT, 2), 0.6f);
	}
	public void addCookingRecipeForBlock(Block input, ItemStack stack, float experience){
        this.addCooking(Item.getItemFromBlock(input), stack, experience);
    }
	public void addCooking(Item input, ItemStack stack, float experience){
        this.addCookingRecipe(new ItemStack(input, 1, 32767), stack, experience);
    }
	public void addCookingRecipe(ItemStack input, ItemStack stack, float experience){
        if (getCookingResult(input) != ItemStack.EMPTY) { 
        	net.minecraftforge.fml.common.FMLLog.log.info("Ignored cooking recipe with conflicting input: {} = {}", input, stack); return; 
        }
        this.cookingList.put(input, stack);
        this.experienceList.put(stack, Float.valueOf(experience));
    }
	public ItemStack getCookingResult(ItemStack stack){
        for (Entry<ItemStack, ItemStack> entry : this.cookingList.entrySet()){
            if (this.compareItemStacks(stack, entry.getKey())){
                return entry.getValue();
            }
        }
        return ItemStack.EMPTY;
    }
	private boolean compareItemStacks(ItemStack stack1, ItemStack stack2){
        return stack2.getItem() == stack1.getItem() && (stack2.getMetadata() == 32767 || stack2.getMetadata() == stack1.getMetadata());
    }
    public Map<ItemStack, ItemStack> getCookingList(){
        return this.cookingList;
    }
    public float getCookingExperience(ItemStack stack){
        float ret = stack.getItem().getSmeltingExperience(stack);
        if (ret != -1) return ret;
        for (Entry<ItemStack, Float> entry : this.experienceList.entrySet()){
            if (this.compareItemStacks(stack, entry.getKey())){
                return ((Float)entry.getValue()).floatValue();
            }
        }
        return 0.0F;
    }
}
