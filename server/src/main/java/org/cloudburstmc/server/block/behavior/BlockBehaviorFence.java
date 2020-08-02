package org.cloudburstmc.server.block.behavior;

import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.block.BlockState;
import org.cloudburstmc.server.block.BlockTraits;
import org.cloudburstmc.server.item.Item;
import org.cloudburstmc.server.utils.BlockColor;

public abstract class BlockBehaviorFence extends BlockBehaviorTransparent {

//    @Override //TODO: bounding box
//    public AxisAlignedBB getBoundingBox(Block block) {
//        boolean north = this.canConnect(block.north().getState());
//        boolean south = this.canConnect(block.south().getState());
//        boolean west = this.canConnect(block.west().getState());
//        boolean east = this.canConnect(block.east().getState());
//        float n = north ? 0 : 0.375f;
//        float s = south ? 1 : 0.625f;
//        float w = west ? 0 : 0.375f;
//        float e = east ? 1 : 0.625f;
//        return new SimpleAxisAlignedBB(
//                block.getX() + w,
//                block.getY(),
//                block.getZ() + n,
//                block.getX() + e,
//                block.getY() + 1.5f,
//                block.getZ() + s
//        );
//    }

    public abstract boolean canConnect(BlockState state);

    @Override
    public BlockColor getColor(Block block) {
        return block.getState().ensureTrait(BlockTraits.WOOD_TYPE).getColor();
    }

    @Override
    public Item toItem(Block block) {
        return Item.get(block.getState().defaultState());
    }

    @Override
    public boolean canWaterlogSource() {
        return true;
    }
}
