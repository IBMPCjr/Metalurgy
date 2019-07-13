package com.ThatKerbonaut.VanillaPlusMore.blocks.ballmill;

import java.util.Random;
import com.ThatKerbonaut.VanillaPlusMore.Main;
import com.ThatKerbonaut.VanillaPlusMore.init.ModBlocks;
import com.ThatKerbonaut.VanillaPlusMore.init.ModItems;
import com.ThatKerbonaut.VanillaPlusMore.util.IHasModel;
import com.ThatKerbonaut.VanillaPlusMore.util.Reference;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BallMill extends BlockContainer implements IHasModel{
	public static final PropertyDirection FACING =  PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);
	private final boolean isOn;
	private static boolean keepInventory;
	private static boolean hasTileEntity;
	Main m = new Main();
	Reference r = new Reference();
	public BallMill(String name, boolean isOn) {
		super(Material.IRON);
		setUnlocalizedName(name);
		setRegistryName(name);
		setCreativeTab(Main.METALURGY);
		setSoundType(SoundType.METAL);
		setHardness(5.0F);
		setResistance(15.0F);
		setHarvestLevel("pickaxe", 2);
		this.setDefaultState(this.blockState.getBaseState().withProperty(
	              FACING, EnumFacing.NORTH));
		this.isOn = isOn;
		ModItems.ITEMS.add(new ItemBlock(this).setRegistryName(this.getRegistryName()));
	    ModBlocks.BLOCKS.add(this);
	}
	@Override
	public boolean onBlockActivated(World parWorld, BlockPos parBlockPos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		playerIn.openGui(Main.class, Reference.GUI_BALL_MILL, parWorld, parBlockPos.getX(), parBlockPos.getY(), parBlockPos.getZ());
		return true;
		}
	@Override
	public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
		this.setDefaultFacing(worldIn, pos, state);
		super.onBlockAdded(worldIn, pos, state);
	}
	private void setDefaultFacing(World worldIn, BlockPos pos, IBlockState state){
        if (!worldIn.isRemote){
            IBlockState iblockstate = worldIn.getBlockState(pos.north());
            IBlockState iblockstate1 = worldIn.getBlockState(pos.south());
            IBlockState iblockstate2 = worldIn.getBlockState(pos.west());
            IBlockState iblockstate3 = worldIn.getBlockState(pos.east());
            EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
            if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()){
                enumfacing = EnumFacing.SOUTH;
            }
            else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()){
                enumfacing = EnumFacing.NORTH;
            }
            else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()){
                enumfacing = EnumFacing.EAST;
            }
            else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()){
                enumfacing = EnumFacing.WEST;
            }
            worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
        }
    }
	public static void setState(boolean active, World worldIn, BlockPos pos){
        IBlockState iblockstate = worldIn.getBlockState(pos);
        TileEntity tileentity = worldIn.getTileEntity(pos);
        keepInventory = true;
        if (active){
            worldIn.setBlockState(pos, ModBlocks.BALL_MILL_ON.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.BALL_MILL_ON.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        else{
            worldIn.setBlockState(pos, ModBlocks.BALL_MILL_OFF.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
            worldIn.setBlockState(pos, ModBlocks.BALL_MILL_OFF.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
        }
        keepInventory = false;
        if (tileentity != null){
            tileentity.validate();
            worldIn.setTileEntity(pos, tileentity);
        }
    }
		@Override
		public TileEntity createNewTileEntity(World worldIn, int meta) {
			return new TileEntityBallMill();
		}
		@Override
		public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
			return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
		}
		@Override
		public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
			worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()));
			if(stack.hasDisplayName()) {
				TileEntity tileentity = worldIn.getTileEntity(pos);
				if(tileentity instanceof TileEntityBallMill) {
					((TileEntityBallMill)tileentity).setCustomInventoryName(stack.getDisplayName());
				}
			}
		}
		@Override
		public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
			if(!keepInventory) {
				TileEntityBallMill tileentity = (TileEntityBallMill)worldIn.getTileEntity(pos);
				InventoryHelper.dropInventoryItems(worldIn, pos, tileentity);
				worldIn.updateComparatorOutputLevel(pos, this);
				super.breakBlock(worldIn, pos, state);
			}
		}
		@Override
		public boolean hasComparatorInputOverride(IBlockState state) {
			return true;
		}
		@Override
		public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
			return Container.calcRedstone(worldIn.getTileEntity(pos));
		}
		@Override
		public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
			return new ItemStack(ModBlocks.BALL_MILL_OFF);
		}
		@Override
		public EnumBlockRenderType getRenderType(IBlockState state) {
			return EnumBlockRenderType.MODEL;
		}
		@Override
		public IBlockState getStateFromMeta(int meta) {
			EnumFacing enumfacing = EnumFacing.getFront(meta);
			if(enumfacing.getAxis() == EnumFacing.Axis.Y) {
				enumfacing = EnumFacing.NORTH;
			}
			return this.getDefaultState().withProperty(FACING, enumfacing);
		}
		@Override
		public int getMetaFromState(IBlockState state) {
			return ((EnumFacing)state.getValue(FACING)).getIndex();
		}
		@Override
		public IBlockState withRotation(IBlockState state, Rotation rot) {
			return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
		}
		@Override
		public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
			return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
		}
		@Override
		protected BlockStateContainer createBlockState() {
			return new BlockStateContainer(this, new IProperty[] {FACING});
		}
		@Override
		public BlockRenderLayer getBlockLayer() {
			return BlockRenderLayer.CUTOUT;
		}
		@Override
		public void registerModels() {
			Main.proxy.registerItemRenderer(Item.getItemFromBlock(this), 0, "inventory");
			
		}	    
}