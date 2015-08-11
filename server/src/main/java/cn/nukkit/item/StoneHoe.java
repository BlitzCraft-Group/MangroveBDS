package cn.nukkit.item;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class StoneHoe extends Tool {

    public StoneHoe() {
        this(0, 1);
    }

    public StoneHoe(int meta) {
        this(meta, 1);
    }

    public StoneHoe(int meta, int count) {
        super(STONE_HOE, meta, count, "Stone Hoe");
    }

    @Override
    public int getMaxDurability() {
        return Tool.DURABILITY_STONE;
    }

    @Override
    public boolean isHoe() {
        return true;
    }
}
