package me.dodo.staticitems.cache;

import org.bukkit.Material;

import java.util.List;

public class Item {
    Material material;
    int customModelData;
    int slot;
    String name;
    List<String> lore;
    String command;

    public Item(Material material, int customModelData, int slot, String name, List<String> lore, String command) {
        this.material = material;
        this.customModelData = customModelData;
        this.slot = slot;
        this.name = name;
        this.lore = lore;
        this.command = command;
    }

    public Material getMaterial() {
        return material;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public int getSlot() {
        return slot;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public String getCommand() {
        return command;
    }

    public void setMaterial(Material _material) {
        this.material = _material;
    }

    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    public void setSlot(int _slot) {
        this.slot = _slot;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public void setLore(List<String> _lore) {
        this.lore = _lore;
    }

    public void setCommand(String _command) {
        this.command = _command;
    }
}
