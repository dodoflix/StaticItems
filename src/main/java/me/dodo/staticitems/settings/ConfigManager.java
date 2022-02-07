package me.dodo.staticitems.settings;

import me.dodo.staticitems.settings.configurations.ItemsConf;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.util.List;
import java.util.Objects;

public class ConfigManager {
    private ItemsConf itemsConf;

    private final File configFile;
    private final File configDirectory;
    private final JavaPlugin javaPlugin;

    public ConfigManager(JavaPlugin javaPlugin) {
        this.javaPlugin = javaPlugin;
        this.configFile = new File(this.javaPlugin.getDataFolder(), "config.yml");
        this.configDirectory = new File(this.javaPlugin.getDataFolder().getPath());
    }

    public void loadConfig() {
        if (!this.configFile.exists()) {
            this.writeDefaultConfig();
        }
        try {
            YamlConfiguration yamlConfiguration = new YamlConfiguration();
            yamlConfiguration.loadFromString(FileUtils.readFileToString(this.configFile, "UTF-8"));
            this.itemsConf = new ItemsConf() {
                @Override
                public Material material() {
                    String materialSt = Objects.requireNonNull(yamlConfiguration.getConfigurationSection("item")).getString("material");
                    return Material.getMaterial(materialSt);
                }

                @Override
                public int customModelData() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("item")).getInt("customModelData");
                }

                @Override
                public int slot() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("item")).getInt("slot");
                }

                @Override
                public String name() {
                    String name = Objects.requireNonNull(yamlConfiguration.getConfigurationSection("item")).getString("name");
                    if (name != null)
                        return ChatColor.translateAlternateColorCodes('&', name);
                    else
                        return null;
                }

                @Override
                public List<String> lore() {
                    List<String> lore = Objects.requireNonNull(yamlConfiguration.getConfigurationSection("item")).getStringList("lore");
                    int counter = 0;
                    for (String text : lore) {
                        lore.set(counter, ChatColor.translateAlternateColorCodes('&', text));
                        counter++;
                    }
                    return lore;
                }

                @Override
                public String command() {
                    return Objects.requireNonNull(yamlConfiguration.getConfigurationSection("item")).getString("command");
                }

            };
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    private void writeDefaultConfig() {
        this.javaPlugin.getLogger().info("Creating the default config.");
        InputStream inputStream = this.javaPlugin.getResource("config.yml");
        if (this.configDirectory.mkdirs()) {
            this.javaPlugin.getLogger().info("Created the plugin directory.");
        }
        try {
            if (this.configFile.createNewFile()) {
                this.javaPlugin.getLogger().info("Created the default config.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.configFile))) {
            assert inputStream != null;
            IOUtils.copy(inputStream, bufferedWriter, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ItemsConf getItemsConf() {
        return itemsConf;
    }

}
