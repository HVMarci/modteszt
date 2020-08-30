package hu.hvj.modteszt.objects.items;

import java.util.List;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ExampleFuel extends Item
{
	public ExampleFuel(Properties properties)
	{
		super(properties);
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new StringTextComponent("Kiéget 3 item-et!"));
		super.addInformation(stack, worldIn, tooltip, flagIn);
	}
	
	@Override
	public int getBurnTime(ItemStack itemStack) {
		return 600;
	}
}
