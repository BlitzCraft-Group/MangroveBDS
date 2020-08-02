package org.cloudburstmc.server.block.behavior;

import com.nukkitx.protocol.bedrock.data.inventory.ContainerType;
import com.nukkitx.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.server.block.Block;
import org.cloudburstmc.server.item.Item;
import org.cloudburstmc.server.item.ItemTool;
import org.cloudburstmc.server.player.Player;
import org.cloudburstmc.server.player.Player.CraftingType;
import org.cloudburstmc.server.utils.BlockColor;

public class BlockBehaviorCraftingTable extends BlockBehaviorSolid {

    @Override
    public boolean canBeActivated(Block block) {
        return true;
    }

    @Override
    public float getHardness() {
        return 2.5f;
    }

    @Override
    public float getResistance() {
        return 15;
    }

    @Override
    public int getToolType() {
        return ItemTool.TYPE_AXE;
    }

    @Override
    public boolean onActivate(Block block, Item item, Player player) {
        if (player != null) {
            player.craftingType = CraftingType.BIG;
            player.setCraftingGrid(player.getUIInventory().getBigCraftingGrid());
            ContainerOpenPacket pk = new ContainerOpenPacket();
            pk.setId((byte) -1);
            pk.setBlockPosition(block.getPosition());
            pk.setType(ContainerType.WORKBENCH);
            pk.setUniqueEntityId(player.getUniqueId());
            player.sendPacket(pk);
        }
        return true;
    }

    @Override
    public BlockColor getColor(Block state) {
        return BlockColor.WOOD_BLOCK_COLOR;
    }
}
