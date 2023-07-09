package org.cloudburstmc.server.inventory.transaction.action;

import com.google.inject.Inject;
import lombok.extern.log4j.Log4j2;
import org.cloudburstmc.api.crafting.CraftingGrid;
import org.cloudburstmc.api.crafting.CraftingRecipe;
import org.cloudburstmc.api.item.ItemStack;
import org.cloudburstmc.api.registry.ItemRegistry;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.response.ItemStackResponseContainer;
import org.cloudburstmc.server.inventory.CloudCraftingGrid;
import org.cloudburstmc.server.inventory.transaction.CraftItemStackTransaction;
import org.cloudburstmc.server.inventory.transaction.InventoryTransaction;
import org.cloudburstmc.server.player.CloudPlayer;
import org.cloudburstmc.server.registry.CloudRecipeRegistry;

import java.util.ArrayList;
import java.util.List;

import static org.cloudburstmc.api.item.ItemBehaviors.GET_MAX_STACK_SIZE;

@Log4j2
public class CraftRecipeAction extends ItemStackAction {

    @Inject
    ItemRegistry itemRegistry;

    private final int recipeId;

    public CraftRecipeAction(int reqId, int recipeId) {
        super(reqId, null, null);
        this.recipeId = recipeId;
    }

    @Override
    public boolean isValid(CloudPlayer player) {
        if (this.getTransaction() == null || !(this.getTransaction() instanceof CraftItemStackTransaction))
            return false;
        CraftItemStackTransaction transaction = ((CraftItemStackTransaction) getTransaction());
        CraftingRecipe recipe = transaction.getRecipe();
        CraftingGrid inv = transaction.getCraftingGrid();

        if (CloudRecipeRegistry.get().getRecipeNetId(recipe.getId()) != this.recipeId) {
            log.warn("Crafting recipe miss-match: {} =/= {}", this.recipeId, CloudRecipeRegistry.get().getRecipeNetId(recipe.getId()));
            return false;
        }

        int size = inv.getCraftingGridSize();

        ItemStack[][] inputs = new ItemStack[size][size];
        ItemStack[][] extraOutputs = new ItemStack[size][size];

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                int slot = (size * r) + c;
                if (transaction.getExtraOutputs().size() <= slot || transaction.getExtraOutputs().get(slot) == null)
                    transaction.getExtraOutputs().add(slot, ItemStack.EMPTY);
                extraOutputs[r][c] = transaction.getExtraOutputs().get(slot);
                inputs[r][c] = inv.getItem(slot);
            }
        }

        ItemStack item = recipe.getResult();
        int maxStackSize = this.itemRegistry.getBehavior(item.getType(), GET_MAX_STACK_SIZE).execute();
        return recipe.matchItems(inputs, extraOutputs) && inv.setItem(CloudCraftingGrid.CRAFTING_RESULT_SLOT,
                recipe.getResult().withCount(maxStackSize));
    }

    @Override
    public boolean execute(CloudPlayer player) {
        return true;
    }

    @Override
    public void onAddToTransaction(InventoryTransaction transaction) {
        super.onAddToTransaction(transaction);
        transaction.addInventory(transaction.getSource().getInventoryManager().getCraftingGrid());
    }

    @Override
    protected List<ItemStackResponseContainer> getContainers(CloudPlayer source) {
        return new ArrayList<>();
    }
}
