package hu.hvj.modteszt.tileentity;

import javax.annotation.Nullable;

import hu.hvj.modteszt.ModTeszteles;
import hu.hvj.modteszt.init.ModTileEntityTypes;
import hu.hvj.modteszt.util.helpers.NBTHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.fluid.IFluidState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;

public class QuarryTileEntity extends TileEntity implements ITickableTileEntity {

	public int x, y, z, tick;
	boolean initialized = false;
	
	public QuarryTileEntity(final TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}
	
	public QuarryTileEntity() {
		this(ModTileEntityTypes.QUARRY.get());
	}
	
	@Override
	public void tick() {
		if (!initialized) init();
		tick++;
		if (tick == 40) {
			tick = 0;
			if (y > 2) execute();
		}
		//ModTeszteles.LOGGER.info("tick()");
	}

	private void init() {
		initialized = true;
		x = this.pos.getX() - 1;
		y = this.pos.getY() - 1;
		z = this.pos.getZ() - 1;
		tick = 0;
		
		BlockPos chestPos = new BlockPos(this.x + 1, this.y + 2, this.z + 1);
		BlockPos hopperPos = new BlockPos(this.x + 1, this.y + 3, this.z + 1);
		BlockPos posToPlace1 = new BlockPos(this.x, this.y + 4, this.z);
		BlockPos posToPlace2 = new BlockPos(this.x, this.y + 4, this.z + 1);
		BlockPos posToPlace3 = new BlockPos(this.x, this.y + 4, this.z + 2);
		BlockPos posToPlace4 = new BlockPos(this.x + 1, this.y + 4, this.z);
		BlockPos posToPlace5 = new BlockPos(this.x + 1, this.y + 4, this.z + 2);
		BlockPos posToPlace6 = new BlockPos(this.x + 2, this.y + 4, this.z);
		BlockPos posToPlace7 = new BlockPos(this.x + 2, this.y + 4, this.z + 1);
		BlockPos posToPlace8 = new BlockPos(this.x + 2, this.y + 4, this.z + 2);
		BlockPos posToPlace11 = new BlockPos(this.x, this.y + 5, this.z);
		BlockPos posToPlace12 = new BlockPos(this.x, this.y + 5, this.z + 1);
		BlockPos posToPlace13 = new BlockPos(this.x, this.y + 5, this.z + 2);
		BlockPos posToPlace14 = new BlockPos(this.x + 1, this.y + 5, this.z);
		BlockPos posToPlace15 = new BlockPos(this.x + 1, this.y + 5, this.z + 2);
		BlockPos posToPlace16 = new BlockPos(this.x + 2, this.y + 5, this.z);
		BlockPos posToPlace17 = new BlockPos(this.x + 2, this.y + 5, this.z + 1);
		BlockPos posToPlace18 = new BlockPos(this.x + 2, this.y + 5, this.z + 2);
		world.setBlockState(chestPos, Blocks.CHEST.getDefaultState());
		world.setBlockState(chestPos.add(-1, 0, 0), Blocks.CHEST.getDefaultState());
		world.setBlockState(hopperPos, Blocks.HOPPER.getDefaultState());
		world.setBlockState(posToPlace1, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace2, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace3, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace4, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace5, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace6, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace7, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace8, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace11, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace12, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace13, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace14, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace15, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace16, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace17, Blocks.SANDSTONE.getDefaultState());
		world.setBlockState(posToPlace18, Blocks.SANDSTONE.getDefaultState());
		//ModTeszteles.LOGGER.info("init()");
	}

	private void execute() {
		int index = 0;
		Block[] blocksRemoved = new Block[9];
		for(int x = 0; x < 3; x++) {
			for(int z = 0; z < 3; z++) {
				BlockPos posToBreak = new BlockPos(this.x + x, this.y, this.z + z);
				blocksRemoved[index] = this.world.getBlockState(posToBreak).getBlock();
				destroyBlock(posToBreak, true, null);
				index++;
			}
		}
		this.y--;
		//ModTeszteles.LOGGER.info("execute()");
	}

	private boolean destroyBlock(BlockPos pos, boolean dropBlock, @Nullable Entity entity) {
		BlockState blockstate = world.getBlockState(pos);
		//ModTeszteles.LOGGER.info("destroyBlock()");
		//if (place == false) {
			if (blockstate.isAir(world, pos)) return false;
			else {
				IFluidState ifluidstate = world.getFluidState(pos);
				world.playEvent(2001, pos, Block.getStateId(blockstate));
				if (dropBlock) {
					TileEntity tileentity = blockstate.hasTileEntity() ? world.getTileEntity(pos) : null;
					Block.spawnDrops(blockstate, world, this.pos.add(0, 3.5, 0), tileentity, entity, ItemStack.EMPTY);
				}
				return world.setBlockState(pos, ifluidstate.getBlockState(), 3);
			}
		/*} else {
			if (blockstate.isAir(world, pos)) return false;
			else {
				IFluidState ifluidstate = world.getFluidState(pos);
				world.playEvent(2001, pos, Block.getStateId(blockstate));
				if (dropBlock) {
					TileEntity tileentity = blockstate.hasTileEntity() ? world.getTileEntity(pos) : null;
					Block.spawnDrops(blockstate, world, this.pos.add(0, 2.5, 0), tileentity, entity, ItemStack.EMPTY);
				}
				return world.setBlockState(pos, ifluidstate.getBlockState(), 3);
		}*/
		
	}
	
	@Override
	public CompoundNBT write(CompoundNBT compound) {
		compound.put("initvalues", NBTHelper.toNBT(this));
		return super.write(compound);
	}
	
	@Override
	public void read(CompoundNBT compound) {
		super.read(compound);
		CompoundNBT initValues = compound.getCompound("initvalues");
		if (initValues != null) {
			this.x = initValues.getInt("x");
			this.y = initValues.getInt("y");
			this.z = initValues.getInt("z");
			this.tick = 0;
			initialized = true;
			return;
		}
		init();
	}
}
