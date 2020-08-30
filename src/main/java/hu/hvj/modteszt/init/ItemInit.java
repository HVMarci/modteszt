package hu.hvj.modteszt.init;

import java.util.function.Supplier;

import hu.hvj.modteszt.ModTeszteles;
import hu.hvj.modteszt.init.ModItemGroups.ModItemGroup;
import hu.hvj.modteszt.objects.items.ExampleFuel;
import hu.hvj.modteszt.objects.items.SpecialItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.HoeItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@Mod.EventBusSubscriber(modid = ModTeszteles.MODID, bus = Bus.MOD)
@ObjectHolder(ModTeszteles.MODID)
public class ItemInit {
	public static final Item example_item = null;
	public static final Item hamburger = null;
	public static final Item special_item = null;
	public static final Item example_fuel = null;
	
	//Tools
	public static final Item example_sword = null;
	public static final Item example_shovel = null;
	public static final Item example_hoe = null;
	public static final Item example_pickaxe = null;
	public static final Item example_axe = null;
	
	//Armor
	public static final Item example_helmet = null;
	public static final Item example_chestplate = null;
	public static final Item example_leggings = null;
	public static final Item example_boots = null;
	
	@SubscribeEvent
	public static void registerItems(final RegistryEvent.Register<Item> event)
	{
		event.getRegistry().register(new Item(new Item.Properties().group(ItemGroup.REDSTONE).maxStackSize(32)).setRegistryName("example_item"));
		event.getRegistry().register(new Item(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).food(new Food.Builder().hunger(6).saturation(1.2f).build()).maxStackSize(64)).setRegistryName("hamburger"));
		event.getRegistry().register(new SpecialItem(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(1)).setRegistryName("special_item"));
		event.getRegistry().register(new ExampleFuel(new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP).maxStackSize(64)).setRegistryName("example_fuel"));
		
		//Tools
		event.getRegistry().register(new SwordItem(ModItemTier.EXAMPLE, 7, 5.0f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_sword"));
		event.getRegistry().register(new ShovelItem(ModItemTier.EXAMPLE, 2, 5.0f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_shovel"));
		event.getRegistry().register(new PickaxeItem(ModItemTier.EXAMPLE, 5, 5.0f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_pickaxe"));
		event.getRegistry().register(new AxeItem(ModItemTier.EXAMPLE, 9, 5.0f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_axe"));
		event.getRegistry().register(new HoeItem(ModItemTier.EXAMPLE, 5.0f, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_hoe"));
		
		//Armor
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.HEAD, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_helmet"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.CHEST, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_chestplate"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.LEGS, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_leggings"));
		event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.FEET, new Item.Properties().group(ModItemGroups.MOD_ITEM_GROUP)).setRegistryName("example_boots"));
	}
	
	public enum ModItemTier implements IItemTier
	{
		EXAMPLE(4, 1500, 15.0F, 7.0F, 250, () -> {
			return Ingredient.fromItems(ItemInit.example_item);
		});
		
		private final int harvestLevel;
		private final int maxUses;
		private final float efficiency;
		private final float attackDamage;
		private final int enchantibility;
		private final LazyValue<Ingredient> repairMaterial;
		
		private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantibility, Supplier<Ingredient> repairMaterial)
		{
			this.harvestLevel = harvestLevel;
			this.maxUses = maxUses;
			this.efficiency = efficiency;
			this.attackDamage = attackDamage;
			this.enchantibility = enchantibility;
			this.repairMaterial = new LazyValue<>(repairMaterial);
		}

		@Override
		public int getMaxUses() {
			return this.maxUses;
		}

		@Override
		public float getEfficiency() {
			return this.efficiency;
		}

		@Override
		public float getAttackDamage() {
			return this.attackDamage;
		}

		@Override
		public int getHarvestLevel() {
			return this.harvestLevel;
		}

		@Override
		public int getEnchantability() {
			return this.enchantibility;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}
	}
	
	public enum ModArmorMaterial implements IArmorMaterial
	{
		TEST(ModTeszteles.MODID + ":test", 5, new int[] {7, 9, 11, 7}, 420, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 6.9f, () -> {
			return Ingredient.fromItems(ItemInit.example_item);
		});
		
		private static final int[] MAX_DAMAGE_ARRAY = new int[] { 300, 300, 300, 300 };
		private final String name;
		private final int maxDamageFactor;
		private final int[] damageReductionAmountArray;
		private final int enchantability;
		private final SoundEvent soundEvent;
		private final float toughness;
		private final LazyValue<Ingredient> repairMaterial;
		
		private ModArmorMaterial(String nameIn, int maxDamageFactorIn, int[] damageReductionAmountIn, int enchantabilityIn, SoundEvent soundEventIn, float toughnessIn, Supplier<Ingredient> repairMaterialIn) {
			this.name = nameIn;
			this.maxDamageFactor = maxDamageFactorIn;
			this.damageReductionAmountArray = damageReductionAmountIn;
			this.enchantability = enchantabilityIn;
			this.soundEvent = soundEventIn;
			this.toughness = toughnessIn;
			this.repairMaterial = new LazyValue<>(repairMaterialIn);
			
		}

		@Override
		public int getDurability(EquipmentSlotType slotIn) {
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
		}
		
		@Override
		public int getDamageReductionAmount(EquipmentSlotType slotIn) {
			return this.damageReductionAmountArray[slotIn.getIndex()];
		}

		@Override
		public int getEnchantability() {
			return this.enchantability;
		}

		@Override
		public SoundEvent getSoundEvent() {
			return this.soundEvent;
		}

		@Override
		public Ingredient getRepairMaterial() {
			return this.repairMaterial.getValue();
		}

		@OnlyIn(Dist.CLIENT)
		@Override
		public String getName() {
			return this.name;
		}

		@Override
		public float getToughness() {
			return this.toughness;
		}
	}
}
