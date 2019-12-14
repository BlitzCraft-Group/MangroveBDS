package cn.nukkit.level.generator.populator.impl;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.chunk.Chunk;
import cn.nukkit.level.generator.populator.helper.EnsureBelow;
import cn.nukkit.level.generator.populator.helper.EnsureCover;
import cn.nukkit.level.generator.populator.helper.EnsureGrassBelow;
import cn.nukkit.level.generator.populator.type.PopulatorSurfaceBlock;
import cn.nukkit.math.NukkitRandom;

/**
 * @author Niall Lindsay (Niall7459)
 * <p>
 * Nukkit Project
 * </p>
 */
public class PopulatorSugarcane extends PopulatorSurfaceBlock {
    private static final Block SUGARCANE_BLOCK = Block.get(BlockID.SUGARCANE_BLOCK, 1);

    private boolean findWater(int x, int y, int z, ChunkManager level) {
        int count = 0;
        for (int i = x - 4; i < (x + 4); i++) {
            for (int j = z - 4; j < (z + 4); j++) {
                int b = level.getBlockIdAt(i, y, j);
                if (b == Block.WATER || b == Block.STILL_WATER) {
                    count++;
                }
                if (count > 10) {
                    return true;
                }
            }
        }
        return (count > 10);
    }

    @Override
    protected boolean canStay(int x, int y, int z, Chunk chunk, ChunkManager level) {
        return EnsureCover.ensureCover(x, y, z, chunk) && (EnsureGrassBelow.ensureGrassBelow(x, y, z, chunk) || EnsureBelow.ensureBelow(x, y, z, SAND, chunk)) && findWater(x, y - 1, z, level);
    }

    @Override
    protected Block getBlock(int x, int z, NukkitRandom random, Chunk chunk) {
        return SUGARCANE_BLOCK;
    }
}
