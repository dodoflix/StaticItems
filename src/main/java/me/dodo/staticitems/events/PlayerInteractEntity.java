package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractEntity implements Listener {
    StaticItems instance;

    public PlayerInteractEntity() {
        instance = StaticItems.getInstance();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        ItemStack itemStack = event.getPlayer().getInventory().getItem(event.getHand());
        if(instance.filterItem(itemStack, 8))
        {
            event.setCancelled(true);
            event.getPlayer().performCommand(instance.getItems().get(0).getCommand());
        }
    }
}
