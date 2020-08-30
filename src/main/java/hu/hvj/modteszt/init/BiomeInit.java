package hu.hvj.modteszt.init;

import hu.hvj.modteszt.ModTeszteles;
import hu.hvj.modteszt.world.biomes.ExampleBiome;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BiomeInit {
	public static final DeferredRegister<Biome> BIOMES = new DeferredRegister<>(ForgeRegistries.BIOMES,
			ModTeszteles.MODID);

	public static final RegistryObject<Biome> EXAMPLE_BIOME = BIOMES.register("example_biome",
			() -> new ExampleBiome(new Biome.Builder().precipitation(RainType.SNOW).scale(2.0f).temperature(0.5f)
					.waterColor(12345678).waterFogColor(16253425)
					.surfaceBuilder(SurfaceBuilder.DEFAULT,
							new SurfaceBuilderConfig(BlockInitNew.EXAMPLE_FENCE.get().getDefaultState(),
									Blocks.ANVIL.getDefaultState(), Blocks.END_STONE.getDefaultState()))
					.category(Category.PLAINS).downfall(1.0f).depth(0.2f).parent(null)));
	
	public static void registerBiomes() {
		registerBiome(EXAMPLE_BIOME.get(), Type.PLAINS, Type.OVERWORLD);
	}
	
	private static void registerBiome(Biome biome, Type... types) {
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addSpawnBiome(biome);
		BiomeManager.addBiome(BiomeManager.BiomeType.COOL, new BiomeManager.BiomeEntry(biome, 100));
	}
}
