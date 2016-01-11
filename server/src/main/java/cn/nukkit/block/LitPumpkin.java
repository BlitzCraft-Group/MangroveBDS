package cn.nukkit.block;

/**
 * Created on 2015/12/8 by xtypr.
 * Package cn.nukkit.block in project Nukkit .
 */
public class LitPumpkin extends Pumpkin {
    public LitPumpkin() {
        this(0);
    }

    public LitPumpkin(int meta) {
        super(0);
    }

    @Override
    public String getName() {
        return "Jack o'Lantern";
    }

    @Override
    public int getId() {
        return LIT_PUMPKIN;
    }

    @Override
    public int getLightLevel() {
        return 15;
    }

}
