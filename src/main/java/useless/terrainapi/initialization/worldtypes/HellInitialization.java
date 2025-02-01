package useless.terrainapi.initialization.worldtypes;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.generate.feature.WorldFeatureClay;
import net.minecraft.core.world.generate.feature.WorldFeatureDeadBush;
import useless.terrainapi.TerrainMain;
import useless.terrainapi.generation.StructureFeatures;
import useless.terrainapi.generation.hell.HellConfig;
import useless.terrainapi.generation.hell.HellFunctions;
import useless.terrainapi.generation.hell.api.ChunkDecoratorOverworldHellAPI;
import useless.terrainapi.generation.overworld.OverworldBiomeFeatures;
import useless.terrainapi.generation.overworld.OverworldFunctions;
import useless.terrainapi.generation.overworld.OverworldOreFeatures;
import useless.terrainapi.generation.overworld.OverworldRandomFeatures;
import useless.terrainapi.initialization.BaseInitialization;

public class HellInitialization extends BaseInitialization {
	private static final HellConfig hellConfig = ChunkDecoratorOverworldHellAPI.hellConfig;
	public static final StructureFeatures structureFeatures = ChunkDecoratorOverworldHellAPI.structureFeatures;
	public static final OverworldOreFeatures oreFeatures = ChunkDecoratorOverworldHellAPI.oreFeatures;
	public static final OverworldRandomFeatures randomFeatures = ChunkDecoratorOverworldHellAPI.randomFeatures;
	public static final OverworldBiomeFeatures biomeFeatures = ChunkDecoratorOverworldHellAPI.biomeFeatures;
	@Override
	protected void initValues() {
		hellConfig.setOreValues(TerrainMain.MOD_ID, Blocks.BLOCK_CLAY, 32, 20, 1);
	}

	@Override
	protected void initStructure() {
		structureFeatures.addFeature(HellFunctions::generateLavaLakeFeature, null);
		structureFeatures.addFeature(HellFunctions::generateObsidianLakeFeature, null);
		structureFeatures.addFeature(HellFunctions::generateRandomFluid, new Object[]{50, Blocks.FLUID_WATER_FLOWING.id()});
		structureFeatures.addFeature(OverworldFunctions::generateDungeons, null);
		structureFeatures.addFeature(HellFunctions::generateLabyrinths, null);
		structureFeatures.addFeature(OverworldFunctions::generateRandomFluid, new Object[]{5, Blocks.FLUID_WATER_FLOWING.id()});
		structureFeatures.addFeature(HellFunctions::generateRandomFluid, new Object[]{20, Blocks.FLUID_LAVA_FLOWING.id()});
	}

	@Override
	protected void initOre() {
		String blockKey = Blocks.BLOCK_CLAY.getKey();
		oreFeatures.addFeature(
			(x) -> new WorldFeatureClay(hellConfig.clusterSize.get(blockKey)), null,
			OverworldFunctions::getStandardOreBiomesDensity, new Object[]{hellConfig.chancesPerChunk.get(blockKey), null},
			hellConfig.verticalStartingRange.get(blockKey), hellConfig.verticalEndingRange.get(blockKey));
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.DIRT, 32, 20, 1, false);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.GRAVEL, 32, 10, 1, false);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.ORE_COAL_STONE, 16, 20, 1, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.ORE_IRON_STONE, 8, 20, 1f/2, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.ORE_GOLD_STONE, 8, 2, 1f/4, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.ORE_REDSTONE_STONE, 7, 8, 1f/8, true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.ORE_DIAMOND_STONE, 7, 1, 1f/8,true);
		oreFeatures.addManagedOreFeature(TerrainMain.MOD_ID, Blocks.ORE_LAPIS_STONE, 6, 1, 1f/4, true);
	}

	@Override
	protected void initRandom() {

	}

	@Override
	protected void initBiome() {
		biomeFeatures.addFeature(HellFunctions::getTreeFeature, null, HellFunctions::getTreeDensity, null, -1f);
		biomeFeatures.addFeature(new WorldFeatureDeadBush(Blocks.DEADBUSH.id()), 1, 10, null);
	}
}
