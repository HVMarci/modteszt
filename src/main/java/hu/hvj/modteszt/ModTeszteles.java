package hu.hvj.modteszt;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import hu.hvj.modteszt.init.BiomeInit;
import hu.hvj.modteszt.init.BlockInit;
import hu.hvj.modteszt.init.BlockInitNew;
import hu.hvj.modteszt.init.DimensionInit;
import hu.hvj.modteszt.init.ItemInitNew;
import hu.hvj.modteszt.init.ModItemGroups;
import hu.hvj.modteszt.init.ModTileEntityTypes;
import hu.hvj.modteszt.world.gen.TutorialOreGen;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod(ModTeszteles.MODID)
@Mod.EventBusSubscriber(modid = ModTeszteles.MODID, bus = Bus.MOD)
public class ModTeszteles
{
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "modteszt";
	public static ModTeszteles instance;
	public static final ResourceLocation EXAMPLE_DIM_TYPE = new ResourceLocation(MODID, "example");
	
	public ModTeszteles()
	{
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.addListener(this::setup);
		modEventBus.addListener(this::doClientStuff);
		
		ItemInitNew.ITEMS.register(modEventBus);
		BlockInitNew.BLOCKS.register(modEventBus);
		ModTileEntityTypes.TILE_ENTITY_TYPES.register(modEventBus);
		BiomeInit.BIOMES.register(modEventBus);
		DimensionInit.MOD_DIMENSIONS.register(modEventBus);
		
		instance = this;
		MinecraftForge.EVENT_BUS.register(this);
	}
	
	@SubscribeEvent
	public static void onRegisterItems(final RegistryEvent.Register<Item> event) {
		final IForgeRegistry<Item> registry = event.getRegistry();
		
		BlockInitNew.BLOCKS.getEntries().stream().map(RegistryObject::get).forEach(block -> {
			final Item.Properties properties = new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP);
			final BlockItem blockItem = new BlockItem(block, properties);
			blockItem.setRegistryName(block.getRegistryName());
			registry.register(blockItem);
		});
		
		LOGGER.debug("Registered BlockItems!");
	}
	
	@SubscribeEvent
	public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
		BiomeInit.registerBiomes();
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		
	}
	
	private void doClientStuff(final FMLClientSetupEvent event)
	{
		
	}
	
	@SubscribeEvent
	public void onServerStarting(FMLServerStartingEvent event)
	{
		
	}
	
	@SubscribeEvent
	public static void loadCompleteEvent(FMLLoadCompleteEvent event)
	{
		TutorialOreGen.generateOre();
	}
	
/*	public static class TutorialItemGroup extends ItemGroup
	{
		public static final TutorialItemGroup instance = new TutorialItemGroup(ItemGroup.GROUPS.length, "tutorialtab");
		
		private TutorialItemGroup(int index, String label)
		{
			super(index, label);
		}
		
		@Override
		public ItemStack createIcon()
		{
			return new ItemStack(BlockInit.example_block);
		}
	}*/
}