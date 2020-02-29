package cn.nukkit.level.generator.standard.gen.density;

import cn.nukkit.level.generator.standard.biome.map.BiomeMap;
import cn.nukkit.level.generator.standard.misc.GenerationPass;
import cn.nukkit.utils.Identifier;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.NonNull;

/**
 * Provides density values used for the initial world surface generation.
 *
 * @author DaPorkchop_
 * @see net.daporkchop.lib.noise.NoiseSource
 */
@JsonDeserialize(using = DensitySourceDeserializer.class)
public interface DensitySource extends GenerationPass {
    /**
     * Gets the density at the given coordinates.
     *
     * @param x      the X coordinate
     * @param y      the Y coordinate
     * @param z      the Z coordinate
     * @param biomes a {@link BiomeMap} containing the biomes to be used in the vicinity of the requested region
     * @return the density at the given coordinates
     */
    double get(int x, int y, int z, @NonNull BiomeMap biomes);

    @Override
    Identifier getId();
}
