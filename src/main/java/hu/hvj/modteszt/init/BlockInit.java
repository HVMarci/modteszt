package hu.hvj.modteszt.init;

import net.minecraftforge.registries.ObjectHolder;
import hu.hvj.modteszt.ModTeszteles;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import hu.hvj.modteszt.init.ModItemGroups;
import hu.hvj.modteszt.objects.blocks.BlockQuarry;
import hu.hvj.modteszt.objects.blocks.SpecialBlock;

@ObjectHolder(ModTeszteles.MODID)
@Mod.EventBusSubscriber(modid = ModTeszteles.MODID, bus = Bus.MOD)
public class BlockInit
{
	
	//public static final Block example_block = null;
	public static final Block special_block = null;
	public static final Block quarry = null;
	
	@SubscribeEvent
	public static void registerBlocks(final RegistryEvent.Register<Block> event)
	{
		//event.getRegistry().register(new Block(Block.Properties.create(Material.TNT).hardnessAndResistance(0.5f, 15.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL)).setRegistryName("example_block"));
		event.getRegistry().register(new SpecialBlock(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 10.0f).harvestLevel(2).harvestTool(ToolType.AXE).sound(SoundType.WOOD).lightValue(10).slipperiness(1.2f).speedFactor(0.7f)).setRegistryName("special_block"));
		event.getRegistry().register(new BlockQuarry(Block.Properties.create(Material.IRON).hardnessAndResistance(2.0f, 10.0f).harvestLevel(2).harvestTool(ToolType.PICKAXE).sound(SoundType.STONE)).setRegistryName("quarry"));
	}
	
	@SubscribeEvent
	public static void registerBlockItems(final RegistryEvent.Register<Item> event)
	{
		//event.getRegistry().register(new BlockItem(example_block, new Item.Properties().maxStackSize(64).group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_block"));
		event.getRegistry().register(new BlockItem(special_block, new Item.Properties().maxStackSize(64).group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("special_block"));
		event.getRegistry().register(new BlockItem(quarry, new Item.Properties().maxStackSize(64).group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("quarry"));
	}
	
}
