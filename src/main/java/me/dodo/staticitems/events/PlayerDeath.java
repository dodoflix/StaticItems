package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerDeath implements Listener {
    StaticItems instance;

    public PlayerDeath() {
        instance = StaticItems.getInstance();
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        int counter = 0;
        for (ItemStack item : event.getDrops()) {
            if (item != null && instance.filterItem(item, 8))
                event.getDrops().remove(counter);
            counter++;
        }
    }
}
