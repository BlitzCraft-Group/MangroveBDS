package cn.nukkit.block;

import cn.nukkit.item.Tool;
import cn.nukkit.utils.Color;

/**
 * Created on 2015/11/25 by xtypr.
 * Package cn.nukkit.block in project Nukkit .
 */
public class BrickStairs extends Stair {
    public BrickStairs() {
        this(0);
    }

    public BrickStairs(int meta) {
        super(meta);
    }

    @Override
    public int getId() {
        return BRICK_STAIRS;
    }

    @Override
    public double getHardness() {
        return 2;
    }

    @Override
    public double getResistance() {
        return 30;
    }

    @Override
    public int getToolType() {
        return Tool.TYPE_PICKAXE;
    }

    @Override
    public String getName() {
        return "Brick Stairs";
    }

    @Override
    public Color getMapColor() {
        return Color.stoneColor;
    }

}
