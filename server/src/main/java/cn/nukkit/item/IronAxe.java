package cn.nukkit.item;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class IronAxe extends Tool {

    public IronAxe() {
        this(0, 1);
    }

    public IronAxe(int meta) {
        this(meta, 1);
    }

    public IronAxe(int meta, int count) {
        super(IRON_AXE, meta, count, "Iron Axe");
    }

    @Override
    public int getMaxDurability() {
        return Tool.DURABILITY_IRON;
    }

    @Override
    public boolean isAxe() {
        return true;
    }
}
