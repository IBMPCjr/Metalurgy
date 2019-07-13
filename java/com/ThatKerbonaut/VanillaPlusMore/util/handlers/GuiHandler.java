package com.ThatKerbonaut.VanillaPlusMore.util.handlers;

import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.ContainerBallMill;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.GuiBallMill;
import com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill.TileEntityBallMill;
import com.ThatKerbonaut.VanillaPlusMore.util.Reference;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler{
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if(ID == Reference.GUI_BALL_MILL){
			return new ContainerBallMill(player.inventory, (TileEntityBallMill)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) { 
		if(ID == Reference.GUI_BALL_MILL){
			return new GuiBallMill(player.inventory, (TileEntityBallMill)world.getTileEntity(new BlockPos(x, y, z)));
		}
		return null;
	}
	public static void registerGuis(){
	}
}
