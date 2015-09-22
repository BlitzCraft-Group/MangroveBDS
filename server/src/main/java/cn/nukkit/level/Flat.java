package cn.nukkit.level;

import cn.nukkit.block.*;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.level.generator.object.OreType;
import cn.nukkit.level.generator.populator.Ore;
import cn.nukkit.level.generator.populator.Populator;
import cn.nukkit.math.Vector3;

import java.util.*;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class Flat extends Generator {

    private ChunkManager level;

    private FullChunk chunk;

    private Random random;

    private List<Populator> populators = new ArrayList<>();

    private Map<String, String> options;

    private String preset;


    @Override
    public Map<String, String> getSettings() {
        return this.options;
    }

    @Override
    public String getName() {
        return "flat";
    }

    public Flat() {
        this(new HashMap<>());
    }

    public Flat(Map<String, String> options) {
        this.preset = "2;7,2x3,2;1;";
        this.options = options;
        this.chunk = null;

        if (this.options.containsKey("decoration")) {
            Ore ores = new Ore();
            ores.setOreTypes(new OreType[]{
                    new OreType(new CoalOre(), 20, 16, 0, 128),
                    new OreType(new IronOre(), 20, 8, 0, 64),
                    new OreType(new RedstoneOre(), 8, 7, 0, 16),
                    new OreType(new LapisOre(), 1, 6, 0, 32),
                    new OreType(new GoldOre(), 2, 8, 0, 32),
                    new OreType(new DiamondOre(), 1, 7, 0, 16),
                    new OreType(new Dirt(), 20, 32, 0, 128),
                    new OreType(new Gravel(), 20, 16, 0, 128),
            });
            this.populators.add(ores);
        }
    }


    @Override
    public void init(ChunkManager level, Random random) {

    }

    @Override
    public void generateChunk(int chunkX, int chunkZ) {

    }

    @Override
    public void populateChunk(int chunkX, int chunkZ) {

    }

    @Override
    public Vector3 getSpawn() {
        return null;
    }
}
