package useless.terrainapi.generation.nether.api;

import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.CavesLargeFeature;
import net.minecraft.core.world.generate.chunk.perlin.ChunkGeneratorPerlin;
import net.minecraft.core.world.generate.chunk.perlin.nether.SurfaceGeneratorNether;
import net.minecraft.core.world.generate.chunk.perlin.nether.TerrainGeneratorNether;

public class ChunkGeneratorNetherAPI extends ChunkGeneratorPerlin {
	public ChunkGeneratorNetherAPI(World world) {
		super(world, new ChunkDecoratorNetherAPI(world), new TerrainGeneratorNether(world), new SurfaceGeneratorNether(world), new CavesLargeFeature[]{new CavesLargeFeature()});
	}
}
