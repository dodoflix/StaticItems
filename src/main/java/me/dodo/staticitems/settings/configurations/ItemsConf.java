package me.dodo.staticitems.settings.configurations;

import org.bukkit.Material;

import java.util.List;

public interface ItemsConf {
    Material material();
    int customModelData();
    int slot();
    String name();
    List<String> lore();
    String command();
}
