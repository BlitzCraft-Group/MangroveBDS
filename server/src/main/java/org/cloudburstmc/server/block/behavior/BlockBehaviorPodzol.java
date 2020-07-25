package org.cloudburstmc.server.block.behavior;

import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.block.BlockState;
import org.cloudburstmc.server.item.Item;
import org.cloudburstmc.server.player.Player;
import org.cloudburstmc.server.utils.BlockColor;

public class BlockBehaviorPodzol extends BlockBehaviorDirt {

    @Override
    public boolean canSilkTouch() {
        return true;
    }

    @Override
    public boolean canBeActivated() {
        return false;
    }

    @Override
    public boolean onActivate(Block block, Item item, Player player) {
        return false;
    }

    @Override
    public BlockColor getColor(BlockState state) {
        return BlockColor.SPRUCE_BLOCK_COLOR;
    }
}
