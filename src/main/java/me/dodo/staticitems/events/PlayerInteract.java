package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteract implements Listener {
    StaticItems instance;

    public PlayerInteract() {
        instance = StaticItems.getInstance();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        ItemStack itemStack = event.getItem();

        if(itemStack != null && instance.filterItem(itemStack, 8))
        {
            event.setCancelled(true);
            event.getPlayer().chat("/" + instance.getItems().get(0).getCommand());
        }
    }
}
