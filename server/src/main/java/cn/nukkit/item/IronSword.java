package cn.nukkit.item;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class IronSword extends Tool {

    public IronSword() {
        this(0, 1);
    }

    public IronSword(int meta) {
        this(meta, 1);
    }

    public IronSword(int meta, int count) {
        super(IRON_SWORD, meta, count, "Iron Sword");
    }

    @Override
    public int getMaxDurability() {
        return Tool.DURABILITY_IRON;
    }

    @Override
    public boolean isSword() {
        return true;
    }
}
