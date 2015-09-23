package cn.nukkit.level;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.block.Block;
import cn.nukkit.block.Ice;
import cn.nukkit.entity.Entity;
import cn.nukkit.item.Item;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.level.format.LevelProvider;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.level.generator.GeneratorRegisterTask;
import cn.nukkit.level.generator.GeneratorUnregisterTask;
import cn.nukkit.math.Vector2;
import cn.nukkit.math.Vector3;
import cn.nukkit.metadata.BlockMetadataStore;
import cn.nukkit.metadata.MetadataValue;
import cn.nukkit.metadata.Metadatable;
import cn.nukkit.network.protocol.DataPacket;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.tile.Tile;
import cn.nukkit.utils.LevelException;
import cn.nukkit.utils.PriorityObject;

import java.util.*;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class Level implements ChunkManager, Metadatable {

    private static int levelIdCounter = 1;
    private static int chunkLoaderCounter = 1;
    public static int COMPRESSION_LEVEL = 8;


    public static final int BLOCK_UPDATE_NORMAL = 1;
    public static final int BLOCK_UPDATE_RANDOM = 2;
    public static final int BLOCK_UPDATE_SCHEDULED = 3;
    public static final int BLOCK_UPDATE_WEAK = 4;
    public static final int BLOCK_UPDATE_TOUCH = 5;

    public static final int TIME_DAY = 0;
    public static final int TIME_SUNSET = 12000;
    public static final int TIME_NIGHT = 14000;
    public static final int TIME_SUNRISE = 23000;

    public static final int TIME_FULL = 24000;

    private Map<Integer, Tile> tiles = new HashMap<>();

    private Map<Integer, Map<Integer, int[]>> motionToSend = new HashMap<>();
    private Map<Integer, Map<Integer, int[]>> moveToSend = new HashMap<>();

    private Map<Integer, Player> players = new HashMap<>();

    private Map<Integer, Entity> entities = new HashMap<>();

    public Map<Integer, Entity> updateEntities = new HashMap<>();

    public Map<Integer, Tile> updateTiles = new HashMap<>();

    private Map<String, Block> blockCache = new HashMap<>();

    private Map<String, DataPacket> chunkCache = new HashMap<>();

    private boolean cacheChunks = false;

    private int sendTimeTicker = 0;

    private Server server;

    private int levelId;

    private LevelProvider provider;

    private Map<String, ChunkLoader> loaders = new HashMap<>();

    private Map<String, Integer> loaderCounter = new HashMap<>();

    private Map<String, Map<Integer, ChunkLoader>> chunkLoaders = new HashMap<>();

    private Map<String, Map<Integer, Player>> playerPloaders = new HashMap<>();

    private Map<String, List<DataPacket>> chunkPackets = new HashMap<>();

    private Map<String, Long> unloadQueue;

    private float time;
    public boolean stopTime;

    private String folderName;

    private Map<String, FullChunk> chunks = new HashMap<>();

    private Map<String, Map<String, Vector3>> changedBlocks = new HashMap<>();

    private PriorityQueue<PriorityObject> updateQueue;
    private Map<String, Integer> updateQueueIndex = new HashMap<>();

    private Map<String, Map<String, Player>> chunkSendQueue = new HashMap<>();
    private Map<String, Boolean> chunkSendTasks = new HashMap<>();

    private Map<String, Boolean> chunkPopulationQueue = new HashMap<>();
    private Map<String, Boolean> chunkPopulationLock = new HashMap<>();
    private Map<String, Boolean> chunkGenerationQueue = new HashMap<>();
    private int chunkGenerationQueueSize = 8;
    private int chunkPopulationQueueSize = 2;

    private boolean autoSave = true;

    private BlockMetadataStore blockMetadata;

    private boolean useSections;
    private byte blockOrder;

    private Position temporalPosition;
    private Vector3 temporalVector;

    private Block[] blockStates;

    public int sleepTicks = 0;

    private int chunkTickRadius;
    private Map<String, Integer> chunkTickList = new HashMap<>();
    private int chunksPerTicks;
    private boolean clearChunksOnTick;
    private HashMap<Integer, Class<? extends Block>> randomTickBlocks = new HashMap<Integer, Class<? extends Block>>() {{
        //todo alot blocks
        put(Block.ICE, Ice.class);
    }};

    private int tickRate;
    public int tickRateTime = 0;
    public int tickRateCounter = 0;

    private Class<? extends Generator> generator;
    private Generator generatorInstance;

    public Level(Server server, String name, String path, Class<? extends LevelProvider> provider) {
        this.blockStates = Block.fullList;
        this.levelId = levelIdCounter++;
        this.blockMetadata = new BlockMetadataStore(this);
        this.server = server;
        this.autoSave = server.getAutoSave();

        try {
            this.provider = provider.getConstructor(Level.class, String.class).newInstance(this, path);
        } catch (Exception e) {
            throw new LevelException("Wrong constructor in class " + provider.getName());
        }

        this.server.getLogger().info(this.server.getLanguage().translateString("nukkit.level.preparing", this.provider.getName()));
        this.generator = Generator.getGenerator(this.provider.getGenerator());

        try {
            this.blockOrder = (byte) provider.getMethod("getProviderOrder").invoke(null);
            this.useSections = (boolean) provider.getMethod("usesChunkSection").invoke(null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.folderName = name;
        this.updateQueue = new PriorityQueue<>(11, new Comparator<PriorityObject>() {
            @Override
            public int compare(PriorityObject o1, PriorityObject o2) {
                return o1.priority < o2.priority ? 1 : (o1.priority == o2.priority ? 0 : -1);
            }
        });
        this.time = this.provider.getTime();

        this.chunkTickRadius = Math.min(this.server.getViewDistance(), Math.max(1, (Integer) this.server.getConfig("chunk-ticking.tick-radius", 4)));
        this.chunksPerTicks = (int) this.server.getConfig("chunk-ticking.per-tick", 40);
        this.chunkGenerationQueueSize = (int) this.server.getConfig("chunk-generation.queue-size", 8);
        this.chunkPopulationQueueSize = (int) this.server.getConfig("chunk-generation.population-queue-size", 2);
        this.chunkTickList.clear();
        this.clearChunksOnTick = (boolean) this.server.getConfig("chunk-ticking.clear-tick-list", true);
        this.cacheChunks = (boolean) this.server.getConfig("chunk-sending.cache-chunks", false);

        this.temporalPosition = new Position(0, 0, 0, this);
        this.temporalVector = new Vector3(0, 0, 0);
        this.tickRate = 1;
    }

    public static String chunkHash(int x, int z) {
        return x + ":" + z;
    }

    public static String blockHash(int x, int y, int z) {
        return x + ":" + y + ":" + z;
    }

    public static Vector3 getBlockVector3(String hash) {
        String[] h = hash.split(":");
        return new Vector3(Integer.valueOf(h[0]), Integer.valueOf(h[1]), Integer.valueOf(h[2]);
    }

    public static Vector2 getBlockVector2(String hash) {
        String[] h = hash.split(":");
        return new Vector2(Integer.valueOf(h[0]), Integer.valueOf(h[1]));
    }

    public static int generateChunkLoaderId(ChunkLoader loader) {
        if (loader.getLoaderId() == 0) {
            return chunkLoaderCounter++;
        } else {
            throw new IllegalStateException("ChunkLoader has a loader id already assigned: " + loader.getLoaderId());
        }
    }

    public int getTickRate() {
        return tickRate;
    }

    public int getTickRateTime() {
        return tickRateTime;
    }

    public void setTickRate(int tickRate) {
        this.tickRate = tickRate;
    }

    public void initLevel() {
        try {
            this.generatorInstance = generator.getConstructor(Map.class).newInstance(this.provider.getGeneratorOptions());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.generatorInstance.init(this, new Random(this.getSeed()));

        this.registerGenerator();
    }

    public void registerGenerator() {
        int size = this.server.getScheduler().getAsyncTaskPoolSize();
        for (int i = 0; i < size; ++i) {
            this.server.getScheduler().scheduleAsyncTaskToWorker(new GeneratorRegisterTask(this, this.generatorInstance), i);
        }
    }

    public void unregisterGenerator() {
        int size = this.server.getScheduler().getAsyncTaskPoolSize();
        for (int i = 0; i < size; ++i) {
            this.server.getScheduler().scheduleAsyncTaskToWorker(new GeneratorUnregisterTask(this), i);
        }
    }

    public BlockMetadataStore getBlockMetadata() {
        return this.blockMetadata;
    }

    public Server getServer() {
        return server;
    }

    final public LevelProvider getProvider() {
        return this.provider;
    }

    final public int getId() {
        return this.levelId;
    }

    public String getName() {

        //todo !!!
        return "TODO！！！！！！！！";
    }

    public String getFolderName() {
        return "todo";
    }
    
    public void clearChunkCache(int chunkX, int chunkZ) {
        //todo !!!!
    }

    public Block getBlock(Vector3 pos) {
        return this.getBlock(pos, true);
    }

    public Block getBlock(Vector3 pos, boolean cached) {
        //todo !!!!
        return null;
    }

    public boolean setBlock(Vector3 pos, Block block) {
        return this.setBlock(pos, block, false, true);
    }

    public boolean setBlock(Vector3 pos, Block block, boolean direct) {
        return this.setBlock(pos, block, direct, true);
    }

    public boolean setBlock(Vector3 pos, Block block, boolean direct, boolean update) {
        //todo!!
        return false;
    }

    public Item useBreakOn(Vector3 vector) {
        return this.useBreakOn(vector, null, false);
    }

    public Item useBreakOn(Vector3 vector, Player player) {
        return this.useBreakOn(vector, player, false);
    }

    public Item useBreakOn(Vector3 vector, Player player, boolean createParticles) {
        //todo
        return null;
    }

    public void chunkRequestCallback(int x, int z, byte[] payload) {
        //todo
    }

    public void addTile(Tile tile) throws LevelException {
        if (tile.getLevel() != this) {
            throw new LevelException("Invalid Tile level");
        }
        tiles.put(tile.getId(), tile);
        this.clearChunkCache((int) tile.getX() >> 4, (int) tile.getZ() >> 4);
    }

    public void removeTile(Tile tile) throws LevelException {
        if (tile.getLevel() != this) {
            throw new LevelException("Invalid Tile level");
        }
        tiles.remove(tile.getId());
        updateTiles.remove(tile.getId());
        this.clearChunkCache((int) tile.getX() >> 4, (int) tile.getZ() >> 4);
    }

    @Override
    public void setMetadata(String metadataKey, MetadataValue newMetadataValue) throws Exception {
        this.server.getLevelMetadata().setMetadata(this, metadataKey, newMetadataValue);
    }

    @Override
    public List<MetadataValue> getMetadata(String metadataKey) throws Exception {
        return this.server.getLevelMetadata().getMetadata(this, metadataKey);
    }

    @Override
    public boolean hasMetadata(String metadataKey) throws Exception {
        return this.server.getLevelMetadata().hasMetadata(this, metadataKey);
    }

    @Override
    public void removeMetadata(String metadataKey, Plugin owningPlugin) throws Exception {
        this.server.getLevelMetadata().removeMetadata(this, metadataKey, owningPlugin);
    }


}
