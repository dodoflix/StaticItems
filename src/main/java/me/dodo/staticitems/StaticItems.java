package me.dodo.staticitems;

import me.dodo.staticitems.cache.Item;
import me.dodo.staticitems.events.*;
import me.dodo.staticitems.settings.ConfigManager;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class StaticItems extends JavaPlugin {
    private static StaticItems Main;
    private final List<Item> Items = new ArrayList<>();

    @Override
    public void onEnable() {
        Main = this;

        ConfigManager configManager = new ConfigManager(this);
        configManager.loadConfig();
        Item item = new Item(configManager.getItemsConf().material(), configManager.getItemsConf().slot(), configManager.getItemsConf().name(), configManager.getItemsConf().lore(), configManager.getItemsConf().command());
        Items.add(item);

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new InventoryClick(), this);
        getServer().getPluginManager().registerEvents(new PlayerSwapHandItems(), this);
        getServer().getPluginManager().registerEvents(new PlayerDropItem(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteract(), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractEntity(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(), this);
    }

    public static StaticItems getInstance() {
        return Main;
    }

    public List<Item> getItems() {
        return Items;
    }

    public boolean filterItem(ItemStack itemStack, int _slot) {
        Material material = Items.get(0).getMaterial();
        int slot = Items.get(0).getSlot();
        String name = Items.get(0).getName();
        List<String> lore = Items.get(0).getLore();

        return itemStack.getType().equals(material) && _slot == slot && itemStack.getItemMeta().getDisplayName().equals(name) && itemStack.getItemMeta().getLore().equals(lore);
    }
}
