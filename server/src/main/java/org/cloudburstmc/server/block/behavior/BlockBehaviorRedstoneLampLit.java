package org.cloudburstmc.server.block.behavior;

import org.cloudburstmc.server.block.BlockState;
import org.cloudburstmc.server.event.redstone.RedstoneUpdateEvent;
import org.cloudburstmc.server.item.Item;
import org.cloudburstmc.server.level.Level;
import org.cloudburstmc.server.utils.Identifier;

import static org.cloudburstmc.server.block.BlockTypes.REDSTONE_LAMP;

/**
 * @author Pub4Game
 */
public class BlockBehaviorRedstoneLampLit extends BlockBehaviorRedstoneLamp {

    public BlockBehaviorRedstoneLampLit(Identifier id) {
        super(id);
    }

    @Override
    public int getLightLevel() {
        return 15;
    }

    @Override
    public Item toItem(BlockState state) {
        return Item.get(REDSTONE_LAMP);
    }

    @Override
    public int onUpdate(Block block, int type) {
        if ((type == Level.BLOCK_UPDATE_NORMAL || type == Level.BLOCK_UPDATE_REDSTONE) && !this.level.isBlockPowered(this.getPosition())) {
            // Redstone event
            RedstoneUpdateEvent ev = new RedstoneUpdateEvent(this);
            getLevel().getServer().getPluginManager().callEvent(ev);
            if (ev.isCancelled()) {
                return 0;
            }
            this.level.scheduleUpdate(this, 4);
            return 1;
        }

        if (type == Level.BLOCK_UPDATE_SCHEDULED && !this.level.isBlockPowered(this.getPosition())) {
            this.level.setBlock(this.getPosition(), BlockState.get(REDSTONE_LAMP), false, false);
        }
        return 0;
    }
}
