package org.cloudburstmc.server.block.behavior;

import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.block.BlockState;
import org.cloudburstmc.server.block.BlockTypes;
import org.cloudburstmc.server.event.block.BlockFadeEvent;
import org.cloudburstmc.server.item.Item;
import org.cloudburstmc.server.level.Level;

public class BlockBehaviorOreRedstoneGlowing extends BlockBehaviorOreRedstone {

    @Override
    public int getLightLevel() {
        return 9;
    }

    @Override
    public Item toItem(Block block) {
        return Item.get(BlockTypes.REDSTONE_ORE);
    }

    @Override
    public int onUpdate(Block block, int type) {
        if (type == Level.BLOCK_UPDATE_SCHEDULED || type == Level.BLOCK_UPDATE_RANDOM) {
            BlockFadeEvent event = new BlockFadeEvent(this, BlockState.get(BlockTypes.REDSTONE_ORE));
            level.getServer().getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                level.setBlock(this.getPosition(), event.getNewState(), false, false);
            }

            return Level.BLOCK_UPDATE_WEAK;
        }

        return 0;
    }

    @Override
    public boolean canHarvestWithHand() {
        return false;
    }

    @Override
    public boolean canSilkTouch() {
        return true;
    }
}
