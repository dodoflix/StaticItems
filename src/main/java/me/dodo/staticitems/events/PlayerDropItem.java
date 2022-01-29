package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDropItem implements Listener {
    StaticItems instance;

    public PlayerDropItem() {
        instance = StaticItems.getInstance();
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        ItemStack itemStack = event.getItemDrop().getItemStack();
        if(instance.filterItem(itemStack, instance.getItems().get(0).getSlot()))
            event.setCancelled(true);
    }
}
