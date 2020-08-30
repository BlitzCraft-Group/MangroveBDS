package org.cloudburstmc.server.block.behavior;

import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.item.behavior.ItemTool;
import org.cloudburstmc.server.utils.BlockColor;

public class BlockBehaviorStairsEndStoneBrick extends BlockBehaviorStairs {

    @Override
    public float getHardness() {
        return 2.0f;
    }

    @Override
    public float getResistance() {
        return 45;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_PICKAXE;
    }

    @Override
    public BlockColor getColor(Block block) {
        return BlockColor.SAND_BLOCK_COLOR;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }
}
