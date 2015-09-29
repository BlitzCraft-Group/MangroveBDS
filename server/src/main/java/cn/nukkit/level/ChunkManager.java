package cn.nukkit.level;

import cn.nukkit.level.format.generic.BaseFullChunk;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public interface ChunkManager {

    int getBlockIdAt(int x, int y, int z);

    void setBlockIdAt(int x, int y, int z, int id);

    int getBlockDataAt(int x, int y, int z);

    void setBlockDataAt(int x, int y, int z, int data);

    BaseFullChunk getChunk(int chunkX, int chunkZ);

    BaseFullChunk getChunk(int chunkX, int chunkZ, boolean create);

    void setChunk(int chunkX, int chunkZ);

    void setChunk(int chunkX, int chunkZ, BaseFullChunk chunk);

    void setChunk(int chunkX, int chunkZ, BaseFullChunk chunk, boolean unload);

    int getSeed();
}
