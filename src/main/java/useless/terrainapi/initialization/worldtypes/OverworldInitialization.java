package useless.terrainapi.initialization.worldtypes;

import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.biome.Biomes;
import net.minecraft.core.world.generate.feature.*;
import useless.terrainapi.TerrainMain;
import useless.terrainapi.generation.Parameters;
import useless.terrainapi.generation.StructureFeatures;
import useless.terrainapi.generation.overworld.*;
import useless.terrainapi.generation.overworld.api.ChunkDecoratorOverworldAPI;
import useless.terrainapi.initialization.BaseInitialization;

public class OverworldInitialization extends BaseInitialization {

	private static final OverworldConfig overworldConfig = ChunkDecoratorOverworldAPI.overworldConfig;
	public static final StructureFeatures structureFeatures = ChunkDecoratorOverworldAPI.structureFeatures;
	public static final OverworldOreFeatures oreFeatures = ChunkDecoratorOverworldAPI.oreFeatures;
	public static final OverworldRandomFeatures randomFeatures = ChunkDecoratorOverworldAPI.randomFeatures;
	public static final OverworldBiomeFeatures biomeFeatures = ChunkDecoratorOverworldAPI.biomeFeatures;
	@Override
	protected void initValues() {
		overworldConfig.setOreValues(TerrainMain.MOD_ID, Blocks.BLOCK_CLAY, 32, 20, 1f);

		overworldConfig.addGrassDensity(Biomes.OVERWORLD_FOREST, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_MEADOW, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_RAINFOREST, 10);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_DESERT, 5);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_SEASONAL_FOREST, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_TAIGA, 1);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_BOREAL_FOREST, 5);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_PLAINS, 10);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_SWAMPLAND, 4);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_SHRUBLAND, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_OUTBACK_GRASSY, 25);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_BIRCH_FOREST, 10);

		overworldConfig.addFlowerDensity(Biomes.OVERWORLD_SEASONAL_FOREST, 1);
		overworldConfig.addFlowerDensity(Biomes.OVERWORLD_MEADOW, 2);
		overworldConfig.addFlowerDensity(Biomes.OVERWORLD_BOREAL_FOREST, 2);
		overworldConfig.addFlowerDensity(Biomes.OVERWORLD_SHRUBLAND, 1);

		overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_FOREST, 2);
		overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_SWAMPLAND, 2);
		overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_TAIGA, 2);
		overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_PLAINS, 3);
		overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_OUTBACK_GRASSY, 2);
		overworldConfig.addYellowFlowerDensity(Biomes.OVERWORLD_OUTBACK, 2);

		overworldConfig.addTreeDensity(Biomes.OVERWORLD_FOREST, 5);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_BIRCH_FOREST, 4);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_RAINFOREST, 10);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_SEASONAL_FOREST, 2);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_TAIGA, 5);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_BOREAL_FOREST, 3);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_DESERT, -1000);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_TUNDRA, -1000);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_PLAINS, -1000);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_SWAMPLAND, 4);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_OUTBACK_GRASSY, 0);

		overworldConfig.addRandomGrassBlocks(Biomes.OVERWORLD_RAINFOREST, Blocks.TALLGRASS_FERN);
		overworldConfig.addRandomGrassBlocks(Biomes.OVERWORLD_SWAMPLAND, Blocks.TALLGRASS_FERN);
		overworldConfig.addRandomGrassBlocks(Biomes.OVERWORLD_BOREAL_FOREST, Blocks.TALLGRASS_FERN);
		overworldConfig.addRandomGrassBlocks(Biomes.OVERWORLD_TAIGA, Blocks.TALLGRASS_FERN);

		overworldConfig.addLakeDensity(Biomes.OVERWORLD_SWAMPLAND, 2);
		overworldConfig.addLakeDensity(Biomes.OVERWORLD_DESERT, 0);
	}

	@Override
	protected void initStructure() {
		overworldConfig.addFeatureChance(TerrainMain.MOD_ID, "labyrinth", 700);

		structureFeatures.addFeature(OverworldFunctions::generateSwamp, null);
		structureFeatures.addFeature(OverworldFunctions::generateLakeFeature, null);
		structureFeatures.addFeature(OverworldFunctions::generateLavaLakeFeature, null);
		structureFeatures.addFeature(OverworldFunctions::generateDungeons, null);
		structureFeatures.addFeature(OverworldFunctions::generateLabyrinths, new Object[]{overworldConfig.getFeatureChanceOrDefault(TerrainMain.MOD_ID, "labyrinth", 700)});
		structureFeatures.addFeature(OverworldFunctions::generateRandomFluid, new Object[]{50, Blocks.FLUID_WATER_FLOWING.id()});
		structureFeatures.addFeature(OverworldFunctions::generateRandomFluid, new Object[]{20, Blocks.FLUID_LAVA_FLOWING.id()});
	}

	@Override
	protected void initOre() {
		String currentBlocks = Blocks.BLOCK_CLAY.getKey();
		oreFeatures.addFeature(
			(x) -> new WorldFeatureClay(overworldConfig.clusterSize.get(currentBlocks)), null,
			OverworldFunctions::getStandardOreBiomesDensity, new Object[]{overworldConfig.chancesPerChunk.get(currentBlocks), null},
			overworldConfig.verticalStartingRange.get(currentBlocks), overworldConfig.verticalEndingRange.get(currentBlocks));
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.DIRT, 32, 20, 1f, false);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.GRAVEL, 32, 10, 1f, false);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.ORE_COAL_STONE, 16, 20, 1f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.ORE_IRON_STONE, 8, 20, 1/2f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.ORE_GOLD_STONE, 8, 2, 1/4f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.ORE_REDSTONE_STONE, 7, 8, 1/8f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.ORE_DIAMOND_STONE, 7, 1, 1/8f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.MOSS_STONE, 32, 1, 1/2f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Blocks.ORE_LAPIS_STONE, 6, 1, 1/8f, true);
	}

	@Override
	protected void initRandom() {
		randomFeatures.addFeature(new WorldFeatureFlowers(Blocks.FLOWER_RED.id(), 1, true), 2, 1); Dont know what to put for count
		randomFeatures.addFeature(new WorldFeatureFlowers(Blocks.MUSHROOM_BROWN.id(), 1, true), 4, 1); Dont know what to put for count
		randomFeatures.addFeature(new WorldFeatureFlowers(Blocks.MUSHROOM_RED.id(), 1, true), 8, 1); Dont know what to put for count
		randomFeatures.addFeatureSurface(new WorldFeatureSugarCane(), 5);
		randomFeatures.addFeatureSurface(new WorldFeaturePumpkin(), 128);
		randomFeatures.addFeatureSurface(new WorldFeatureSponge(), 64);
	}

	@Override
	protected void initBiome() {
		biomeFeatures.addFeatureSurface(new WorldFeatureRichScorchedDirt(10), 1, new Biome[]{Biomes.OVERWORLD_OUTBACK, Biomes.OVERWORLD_OUTBACK_GRASSY});
		biomeFeatures.addFeature(OverworldFunctions::getTreeFeature, null, OverworldFunctions::getTreeDensity, null, -1f);
		biomeFeatures.addFeatureSurface(new WorldFeatureSugarCaneTall(), 1, new Biome[]{Biomes.OVERWORLD_RAINFOREST});
		biomeFeatures.addFeature(OverworldFunctions::flowerTypeCondition, null, (Parameters x) -> overworldConfig.getFlowerDensity(x.biome, 0), null, 1f);
		biomeFeatures.addFeature((Parameters x) -> new WorldFeatureFlowers(Blocks.FLOWER_YELLOW.id(), 1, true), null, (Parameters x) -> overworldConfig.getYellowFlowerDensity(x.biome, 0), null, 1); Dont know what to put for count
		biomeFeatures.addFeature(OverworldFunctions::grassTypeCondition, null, (Parameters x) -> overworldConfig.getGrassDensity(x.biome, 0), null, 1);
		biomeFeatures.addFeature(new WorldFeatureSpinifexPatch(), 1, 4, new Biome[]{Biomes.OVERWORLD_OUTBACK});
		biomeFeatures.addFeature(new WorldFeatureDeadBush(Blocks.DEADBUSH.id()), 1, 2, new Biome[]{Biomes.OVERWORLD_DESERT});
		biomeFeatures.addFeature(new WorldFeatureCactus(), 1, 10, new Biome[]{Biomes.OVERWORLD_DESERT});
	}
}
