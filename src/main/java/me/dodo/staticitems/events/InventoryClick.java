package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class InventoryClick implements Listener {
    StaticItems instance;

    public InventoryClick() {
        instance = StaticItems.getInstance();
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        ItemStack clickItem = event.getCurrentItem();
        if(clickItem == null)
            return;
        if(instance.filterItem(clickItem, event.getSlot()) || event.getHotbarButton() == instance.getItems().get(0).getSlot())
            event.setCancelled(true);
    }
}
