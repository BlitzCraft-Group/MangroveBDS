package cn.nukkit.block;

import cn.nukkit.item.Item;
import cn.nukkit.item.Tool;

/**
 * author: MagicDroidX
 * Nukkit Project
 */
public class CoalOre extends Solid {

    protected int id = COAL_ORE;

    public CoalOre() {
        super(COAL_ORE);
    }

    @Override
    public double getHardness() {
        return 3;
    }

    @Override
    public int getToolType() {
        return Tool.TYPE_PICKAXE;
    }

    @Override
    public String getName() {
        return "Coal Ore";
    }

    @Override
    public int[][] getDrops(Item item) {
        if (item.isPickaxe() && item.getTier() >= 1) {
            return new int[][]{new int[]{Item.COAL, 0, 1}};
        } else {
            return new int[0][];
        }
    }
}
