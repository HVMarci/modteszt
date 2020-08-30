package hu.hvj.modteszt.init;

import hu.hvj.modteszt.ModTeszteles;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.pattern.BlockMaterialMatcher;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockInitNew {

	public static final DeferredRegister<Block> BLOCKS = new DeferredRegister<>(ForgeRegistries.BLOCKS, ModTeszteles.MODID);
	
	//public static final RegistryObject<Block> DEF_BLOCK = BLOCKS.register("def_block", () -> new Block(Block.Properties.create(Material.IRON)));
	public static final RegistryObject<Block> EXAMPLE_BLOCK = BLOCKS.register("example_block", () -> new Block(Block.Properties.create(Material.TNT).hardnessAndResistance(0.5f, 15.0f).harvestTool(ToolType.PICKAXE).sound(SoundType.METAL).jumpFactor(200).speedFactor(200)));
	public static final RegistryObject<Block> EXAMPLE_STAIRS = BLOCKS.register("example_stairs", () -> new StairsBlock(() -> BlockInitNew.EXAMPLE_BLOCK.get().getDefaultState(), Block.Properties.create(Material.TNT)));
	public static final RegistryObject<Block> EXAMPLE_FENCE = BLOCKS.register("example_fence", () -> new FenceBlock(Block.Properties.create(Material.TNT, MaterialColor.GOLD)));
	public static final RegistryObject<Block> EXAMPLE_WOOD_BUTTON = BLOCKS.register("example_wood_button", () -> new ExampleWoodButton(Block.Properties.create(Material.TNT)));
}
