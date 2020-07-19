package org.cloudburstmc.server.dispenser;

import org.cloudburstmc.server.block.behavior.BlockBehaviorDispenser;
import org.cloudburstmc.server.item.Item;

/**
 * @author CreeperFace
 */
public interface DispenseBehavior {

    void dispense(BlockBehaviorDispenser block, Item item);

}
