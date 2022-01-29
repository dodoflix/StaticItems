package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerSwapHandItems implements Listener {
    StaticItems instance;

    public PlayerSwapHandItems() {
        instance = StaticItems.getInstance();
    }

    @EventHandler
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        ItemStack mainHand = event.getMainHandItem();
        ItemStack offHand = event.getOffHandItem();
        assert mainHand != null;
        assert offHand != null;
        if(instance.filterItem(mainHand, 8) || instance.filterItem(offHand, 8))
            event.setCancelled(true);
    }

}
