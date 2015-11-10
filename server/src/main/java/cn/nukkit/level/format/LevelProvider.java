package cn.nukkit.level.format;

import cn.nukkit.level.Level;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.math.Vector3;
import cn.nukkit.scheduler.AsyncTask;

import java.util.Map;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public interface LevelProvider {
    byte ORDER_YZX = 0;
    byte ORDER_ZXY = 1;

    AsyncTask requestChunkTask(int x, int z);

    String getPath();

    String getGenerator();

    Map<String, Object> getGeneratorOptions();

    BaseFullChunk getChunk(int X, int Z);

    BaseFullChunk getChunk(int X, int Z, boolean create);

    void saveChunks();

    void saveChunk(int X, int Z);

    void unloadChunks();

    boolean loadChunk(int X, int Z);

    boolean loadChunk(int X, int Z, boolean create);

    boolean unloadChunk(int X, int Z);

    boolean unloadChunk(int X, int Z, boolean safe);

    boolean isChunkGenerated(int X, int Z);

    boolean isChunkPopulated(int X, int Z);

    boolean isChunkLoaded(int X, int Z);

    void setChunk(int chunkX, int chunkZ, FullChunk chunk);

    String getName();

    int getTime();

    void setTime(int value);

    long getSeed();

    void setSeed(long value);

    Vector3 getSpawn();

    void setSpawn(Vector3 pos);

    Map<String, ? extends FullChunk> getLoadedChunks();

    void doGarbageCollection();

    Level getLevel();

    void close();
}
