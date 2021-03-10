package org.cloudburstmc.server.block.behavior;

import com.nukkitx.math.vector.Vector3f;
import lombok.val;
import org.cloudburstmc.api.block.Block;
import org.cloudburstmc.api.block.BlockTypes;
import org.cloudburstmc.api.event.block.BlockGrowEvent;
import org.cloudburstmc.api.item.ItemStack;
import org.cloudburstmc.server.CloudServer;
import org.cloudburstmc.server.block.BlockTraits;
import org.cloudburstmc.server.item.ItemTypes;
import org.cloudburstmc.server.level.CloudLevel;
import org.cloudburstmc.server.math.Direction;
import org.cloudburstmc.server.player.CloudPlayer;
import org.cloudburstmc.server.utils.BlockColor;

import java.util.Random;

public class BlockBehaviorNetherWart extends FloodableBlockBehavior {

    @Override
    public boolean place(ItemStack item, Block block, Block target, Direction face, Vector3f clickPos, CloudPlayer player) {
        val down = block.down().getState();
        if (down.getType() == BlockTypes.SOUL_SAND) {
            placeBlock(block, item);
            return true;
        }
        return false;
    }

    @Override
    public int onUpdate(Block block, int type) {
        if (type == CloudLevel.BLOCK_UPDATE_NORMAL) {
            if (block.down().getState().getType() != BlockTypes.SOUL_SAND) {
                block.getLevel().useBreakOn(block.getPosition());
                return CloudLevel.BLOCK_UPDATE_NORMAL;
            }
        } else if (type == CloudLevel.BLOCK_UPDATE_RANDOM) {
            if (new Random().nextInt(10) == 1) {
                val state = block.getState();
                if (state.ensureTrait(BlockTraits.AGE) < 3) {
                    BlockGrowEvent ev = new BlockGrowEvent(block, state.incrementTrait(BlockTraits.AGE));
                    CloudServer.getInstance().getEventManager().fire(ev);

                    if (!ev.isCancelled()) {
                        block.set(ev.getNewState(), true);
                    } else {
                        return CloudLevel.BLOCK_UPDATE_RANDOM;
                    }
                }
            } else {
                return CloudLevel.BLOCK_UPDATE_RANDOM;
            }
        }

        return 0;
    }

    @Override
    public BlockColor getColor(Block block) {
        return BlockColor.RED_BLOCK_COLOR;
    }

    @Override
    public ItemStack[] getDrops(Block block, ItemStack hand) {
        if (block.getState().ensureTrait(BlockTraits.AGE) == 3) {
            return new ItemStack[]{
                    ItemStack.get(ItemTypes.NETHER_WART, 2 + (int) (Math.random() * ((4 - 2) + 1)))
            };
        } else {
            return new ItemStack[]{
                    ItemStack.get(ItemTypes.NETHER_WART)
            };
        }
    }

    @Override
    public ItemStack toItem(Block block) {
        return ItemStack.get(ItemTypes.NETHER_WART);
    }
}


