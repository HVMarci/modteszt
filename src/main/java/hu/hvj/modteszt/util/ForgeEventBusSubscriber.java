package hu.hvj.modteszt.util;

import hu.hvj.modteszt.ModTeszteles;
import hu.hvj.modteszt.init.DimensionInit;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.RegisterDimensionsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = ModTeszteles.MODID, bus = Bus.FORGE)
public class ForgeEventBusSubscriber {
	
	@SubscribeEvent
	public static void registerDimensions(final RegisterDimensionsEvent event) {
		if(DimensionType.byName(ModTeszteles.EXAMPLE_DIM_TYPE) == null) {
			DimensionManager.registerDimension(ModTeszteles.EXAMPLE_DIM_TYPE, DimensionInit.EXAMPLE_DIM.get(), null, true);
		}
		ModTeszteles.LOGGER.info("Dimenziók Regisztrálva!");
	}
}
