package hu.hvj.modteszt.events;

import hu.hvj.modteszt.ModTeszteles;
import hu.hvj.modteszt.init.BlockInit;
import hu.hvj.modteszt.init.BlockInitNew;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

//@Mod.EventBusSubscriber(modid = ModTeszteles.MODID, bus = Bus.FORGE)
public class TestJumpEvent
{
	@SubscribeEvent
	public static void testJumpEvent(LivingJumpEvent event)
	{
		//ModTeszteles.LOGGER.info("testJumpEvent fired");
		LivingEntity livingEntity = event.getEntityLiving();
		World world = livingEntity.getEntityWorld();
		//BlockPos blockpos = new BlockPos(livingEntity.getPosX(), livingEntity.getPosY(), livingEntity.getPosZ());
		//BlockState blockstate = world.getBlockState(blockpos);
		if (livingEntity.getType() != EntityType.PLAYER)
		{
			//livingEntity.setFire(10000);
			world.setBlockState(livingEntity.getPosition().add(0, -1, 0), BlockInitNew.EXAMPLE_BLOCK.get().getDefaultState());
		}
		//livingEntity.setGlowing(false);
	}
}
