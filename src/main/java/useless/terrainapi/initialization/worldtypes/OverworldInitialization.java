package useless.terrainapi.initialization.worldtypes;

import net.minecraft.core.block.Block;
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
		overworldConfig.setOreValues(TerrainMain.MOD_ID, Block.blockClay, 32, 20, 1f);

		overworldConfig.addGrassDensity(Biomes.OVERWORLD_FOREST, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_MEADOW, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_RAINFOREST, 10);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_SEASONAL_FOREST, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_TAIGA, 1);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_BOREAL_FOREST, 5);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_GRASSLANDS, 8);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_CAATINGA, 2);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_SWAMPLAND, 4);
		overworldConfig.addGrassDensity(Biomes.OVERWORLD_BIRCH_FOREST, 2);

		overworldConfig.addTreeDensity(Biomes.OVERWORLD_FOREST, 5);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_SHRUBLAND, -2);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_BIRCH_FOREST, 4);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_RAINFOREST, 10);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_SEASONAL_FOREST, 2);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_TAIGA, 5);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_BOREAL_FOREST, 3);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_DESERT, -1000);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_TUNDRA, -1000);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_PLAINS, -1000);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_CAATINGA, 4);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_SWAMPLAND, 4);
		overworldConfig.addTreeDensity(Biomes.OVERWORLD_OUTBACK_GRASSY, 0);

		overworldConfig.addRandomGrassBlock(Biomes.OVERWORLD_RAINFOREST, Block.tallgrassFern);
		overworldConfig.addRandomGrassBlock(Biomes.OVERWORLD_SWAMPLAND, Block.tallgrassFern);
		overworldConfig.addRandomGrassBlock(Biomes.OVERWORLD_BOREAL_FOREST, Block.tallgrassFern);
		overworldConfig.addRandomGrassBlock(Biomes.OVERWORLD_TAIGA, Block.tallgrassFern);

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
		structureFeatures.addFeature(OverworldFunctions::generateRandomFluid, new Object[]{50, Block.fluidWaterFlowing.id});
		structureFeatures.addFeature(OverworldFunctions::generateRandomFluid, new Object[]{20, Block.fluidLavaFlowing.id});
	}

	@Override
	protected void initOre() {
		String currentBlock = Block.blockClay.getKey();
		oreFeatures.addFeature(
			(x) -> new WorldFeatureClay(overworldConfig.clusterSize.get(currentBlock)), null,
			OverworldFunctions::getStandardOreBiomesDensity, new Object[]{overworldConfig.chancesPerChunk.get(currentBlock), null},
			overworldConfig.verticalStartingRange.get(currentBlock), overworldConfig.verticalEndingRange.get(currentBlock));
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.dirt, 32, 20, 1f, false);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.gravel, 32, 10, 1f, false);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.oreCoalStone, 16, 20, 1f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.oreIronStone, 8, 20, 1/2f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.oreGoldStone, 8, 2, 1/4f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.oreRedstoneStone, 7, 8, 1/8f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.oreDiamondStone, 7, 1, 1/8f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.mossStone, 32, 1, 1/2f, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID,Block.oreLapisStone, 6, 1, 1/8f, true);
	}

	@Override
	protected void initRandom() {
		randomFeatures.addFeature(new WorldFeatureFlowers(Block.flowerRed.id), 2, 1);
		randomFeatures.addFeature(new WorldFeatureFlowers(Block.mushroomBrown.id), 4, 1);
		randomFeatures.addFeature(new WorldFeatureFlowers(Block.mushroomRed.id), 8, 1);
		randomFeatures.addFeatureSurface(new WorldFeatureSugarCane(), 5);
		randomFeatures.addFeatureSurface(new WorldFeaturePumpkin(), 128);
		randomFeatures.addFeatureSurface(new WorldFeatureSponge(), 64);
	}

	@Override
	protected void initBiome() {
		biomeFeatures.addFeatureSurface(new WorldFeatureRichScorchedDirt(10), 1, new Biome[]{Biomes.OVERWORLD_OUTBACK, Biomes.OVERWORLD_OUTBACK_GRASSY});
		biomeFeatures.addFeature(OverworldFunctions::getTreeFeature, null, OverworldFunctions::getTreeDensity, null, -1f);
		biomeFeatures.addFeatureSurface(new WorldFeatureSugarCaneTall(), 1, new Biome[]{Biomes.OVERWORLD_RAINFOREST});

		biomeFeatures.addFeature(
			parameters -> {
				int blockId = Block.flowerPurple.id;
				if (parameters.random.nextInt(12) == 0) {
					blockId = Block.flowerRed.id;
				}

				if (parameters.random.nextInt(6) == 0) {
					blockId = Block.flowerYellow.id;
				}
				return new WorldFeatureTallGrass(blockId);
			}, null,
			parameters -> {
				if (parameters.biome == Biomes.OVERWORLD_MEADOW){
					return 3;
				}
				if (parameters.biome == Biomes.OVERWORLD_BOREAL_FOREST){
					return 2;
				}
				if (parameters.biome == Biomes.OVERWORLD_SHRUBLAND){
					return 1;
				}
				if (parameters.biome == Biomes.OVERWORLD_TAIGA){
					return 1;
				}
				return 0;
			}, null, 1f);

		biomeFeatures.addFeature(
			parameters -> new WorldFeatureFlowers(Block.flowerLightBlue.id), null,
			parameters -> {
				if (parameters.biome == Biomes.OVERWORLD_FOREST){
					return 2;
				}
				if (parameters.biome == Biomes.OVERWORLD_SWAMPLAND){
					return 3;
				}
				if (parameters.biome == Biomes.OVERWORLD_RAINFOREST){
					return 4;
				}
				if (parameters.biome == Biomes.OVERWORLD_CAATINGA || parameters.biome == Biomes.OVERWORLD_CAATINGA_PLAINS){
					return 3;
				}
				return 0;
			}, null, 1f);

		biomeFeatures.addFeature(
			parameters -> new WorldFeatureFlowers(Block.flowerOrange.id), null,
			parameters -> {
				if (parameters.biome == Biomes.OVERWORLD_GRASSLANDS){
					return 1;
				}
				if (parameters.biome == Biomes.OVERWORLD_PLAINS){
					return 5;
				}
				if (parameters.biome == Biomes.OVERWORLD_CAATINGA || parameters.biome == Biomes.OVERWORLD_CAATINGA_PLAINS){
					return 5;
				}
				if (parameters.biome == Biomes.OVERWORLD_OUTBACK_GRASSY || parameters.biome == Biomes.OVERWORLD_OUTBACK){
					return 3;
				}
				return 0;
			}, null, 1f);

		biomeFeatures.addFeature(
			parameters -> new WorldFeatureFlowers(Block.flowerYellow.id), null,
			parameters -> {
				if (parameters.biome == Biomes.OVERWORLD_FOREST){
					return 2;
				}
				if (parameters.biome == Biomes.OVERWORLD_SWAMPLAND){
					return 2;
				}
				if (parameters.biome == Biomes.OVERWORLD_TAIGA){
					return 2;
				}
				if (parameters.biome == Biomes.OVERWORLD_PLAINS){
					return 3;
				}
				if (parameters.biome == Biomes.OVERWORLD_CAATINGA || parameters.biome == Biomes.OVERWORLD_CAATINGA_PLAINS){
					return 5;
				}
				if (parameters.biome == Biomes.OVERWORLD_OUTBACK_GRASSY || parameters.biome == Biomes.OVERWORLD_OUTBACK){
					return 2;
				}
				return 0;
			}, null, 1f);

		biomeFeatures.addFeature(OverworldFunctions::grassTypeCondition, null, (Parameters x) -> overworldConfig.getGrassDensity(x.biome, 0), null, 1);
		biomeFeatures.addFeature(new WorldFeatureSpinifexPatch(), 1, 4, new Biome[]{Biomes.OVERWORLD_OUTBACK});
		biomeFeatures.addFeature(new WorldFeatureDeadBush(Block.deadbush.id), 1, 2, new Biome[]{Biomes.OVERWORLD_DESERT, Biomes.OVERWORLD_CAATINGA, Biomes.OVERWORLD_CAATINGA_PLAINS});
		biomeFeatures.addFeature(parameters -> new WorldFeatureDeadBush(Block.deadbush.id), null, parameters -> {
			if (parameters.biome == Biomes.OVERWORLD_DESERT) {
				return 2;
			}

			if (parameters.biome  == Biomes.OVERWORLD_CAATINGA_PLAINS || parameters.biome == Biomes.OVERWORLD_CAATINGA) {
				return 1;
			}
			return 0;
		}, null, 1f);
		biomeFeatures.addFeature(new WorldFeatureCactus(), 1, 10, new Biome[]{Biomes.OVERWORLD_DESERT, Biomes.OVERWORLD_CAATINGA, Biomes.OVERWORLD_OUTBACK_GRASSY});
	}
}
