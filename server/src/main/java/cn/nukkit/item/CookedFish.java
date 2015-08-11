package cn.nukkit.item;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class CookedFish extends Item {

    public CookedFish() {
        this(0, 1);
    }

    public CookedFish(int meta) {
        this(meta, 1);
    }

    public CookedFish(int meta, int count) {
        super(RAW_FISH, meta, count, "Cooked Fish");
        if (this.meta == 1) {
            this.name = "Cooked Salmon";
        }
    }
}
