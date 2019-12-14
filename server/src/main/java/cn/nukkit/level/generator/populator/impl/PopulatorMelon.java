package cn.nukkit.level.generator.populator.impl;

import cn.nukkit.block.Block;
import cn.nukkit.block.BlockID;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.chunk.Chunk;
import cn.nukkit.level.generator.populator.helper.EnsureCover;
import cn.nukkit.level.generator.populator.helper.EnsureGrassBelow;
import cn.nukkit.level.generator.populator.type.PopulatorSurfaceBlock;
import cn.nukkit.math.NukkitRandom;

/**
 * @author DaPorkchop_
 */
public class PopulatorMelon extends PopulatorSurfaceBlock {
    private static final Block MELON_BLOCK = Block.get(BlockID.MELON_BLOCK);

    @Override
    protected boolean canStay(int x, int y, int z, Chunk chunk, ChunkManager level) {
        return EnsureCover.ensureCover(x, y, z, chunk) && EnsureGrassBelow.ensureGrassBelow(x, y, z, chunk);
    }

    @Override
    protected Block getBlock(int x, int z, NukkitRandom random, Chunk chunk) {
        return MELON_BLOCK;
    }
}
