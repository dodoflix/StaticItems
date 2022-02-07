package me.dodo.staticitems.events;

import me.dodo.staticitems.StaticItems;
import me.dodo.staticitems.cache.Item;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class PlayerRespawn implements Listener {
    private final List<Item> items;

    public PlayerRespawn() {
        StaticItems instance = StaticItems.getInstance();
        items = instance.getItems();
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        ItemStack item = new ItemStack(items.get(0).getMaterial());
        if (item.getType() == Material.PLAYER_HEAD) {
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            assert skullMeta != null;
            skullMeta.setOwningPlayer(player);
            skullMeta.setDisplayName(items.get(0).getName());
            skullMeta.setLore(items.get(0).getLore());
            item.setItemMeta(skullMeta);
            player.getInventory().setItem(items.get(0).getSlot(), item);
        } else {
            ItemMeta itemMeta = item.getItemMeta();
            assert itemMeta != null;
            itemMeta.setDisplayName(items.get(0).getName());
            itemMeta.setLore(items.get(0).getLore());
            itemMeta.setCustomModelData(items.get(0).getCustomModelData());
            item.setItemMeta(itemMeta);
            player.getInventory().setItem(items.get(0).getSlot(), item);
        }
    }
}
