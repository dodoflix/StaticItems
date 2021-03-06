package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class PlayerCommandPreprocess implements Listener {
    StaticItems instance;

    public PlayerCommandPreprocess() {
        instance = StaticItems.getInstance();
    }


    @EventHandler
    public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0].replaceAll("/", "");
        if(instance.getItems().get(0).getCommandBlackList().contains(command) && instance.filterItem(event.getPlayer().getInventory().getItemInMainHand(), event.getPlayer().getInventory().getHeldItemSlot())) {
            event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aKomut girmeden önce lütfen elinize başka bir eşya alın."));
            event.setCancelled(true);
        }
    }
}
