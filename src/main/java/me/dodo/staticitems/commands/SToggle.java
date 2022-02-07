package me.dodo.staticitems.commands;

import me.dodo.staticitems.StaticItems;
import me.dodo.staticitems.cache.Item;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;


public class SToggle implements CommandExecutor {
    StaticItems instance;

    public SToggle() {
        instance = StaticItems.getInstance();
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(!(sender instanceof Player))
            return false;
        Player player = (Player) sender;
        if(player.getInventory().getItem(instance.getItems().get(0).getSlot()) != null && instance.filterItem(player.getInventory().getItem(instance.getItems().get(0).getSlot()), instance.getItems().get(0).getSlot()))
            player.getInventory().removeItem(player.getInventory().getItem(instance.getItems().get(0).getSlot()));
        else {
            List<Item> items = instance.getItems();
            ItemStack item = new ItemStack(items.get(0).getMaterial());
            ItemStack dropItem = player.getInventory().getItem(items.get(0).getSlot());
            if(dropItem != null)
                player.getWorld().dropItemNaturally(player.getLocation(), dropItem);
            if(item.getType() == Material.PLAYER_HEAD){
                SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
                assert skullMeta != null;
                skullMeta.setOwningPlayer(player);
                skullMeta.setDisplayName(items.get(0).getName());
                skullMeta.setLore(items.get(0).getLore());
                item.setItemMeta(skullMeta);
                player.getInventory().setItem(items.get(0).getSlot(), item);
            }else {
                ItemMeta itemMeta = item.getItemMeta();
                assert itemMeta != null;
                itemMeta.setDisplayName(items.get(0).getName());
                itemMeta.setLore(items.get(0).getLore());
                item.setItemMeta(itemMeta);
                player.getInventory().setItem(items.get(0).getSlot(), item);
            }
        }
        return true;
    }
}
