package hu.hvj.modteszt.init;

import java.util.function.Supplier;
import hu.hvj.modteszt.ModTeszteles;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ModItemGroups {
	public static class ModItemGroup extends ItemGroup {

		private final Supplier<ItemStack> iconSupplier;

		public ModItemGroup(final String name, final Supplier<ItemStack> iconSupplier) {
			super(name);
			this.iconSupplier = iconSupplier;
		}

		@Override
		public ItemStack createIcon() {
			return iconSupplier.get();
		}

	}
	public static final ItemGroup MOD_ITEM_GROUP = new ModItemGroup(ModTeszteles.MODID, () -> new ItemStack(Items.LIGHT_BLUE_BANNER));
}
