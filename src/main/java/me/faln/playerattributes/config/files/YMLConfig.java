package me.faln.playerattributes.config.files;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

public class YMLConfig {
    private final File file;
    private FileConfiguration config;

    public YMLConfig(final File file, final FileConfiguration config) {
        this.file = file;
        this.config = config;
    }

    public void save() {
        try {
            this.config.save(this.file);
        } catch (IOException ex) {
            throw new UncheckedIOException("Could not save YMLConfig", ex);
        }
    }

    public void reload() {
        this.config = YamlConfiguration.loadConfiguration(this.file);
        this.save();
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public String string(final String path, final String def) {
        return this.config.getString(path, def);
    }

    public int parseInt(final String path) {
        return this.parseInt(path, 0);
    }

    public int parseInt(final String path, int def) {
        return this.config.getInt(path, def);
    }

    public long parseLong(final String path, long def) {
        return this.config.getLong(path, def);
    }

    public double parseDouble(final String path, double def) {
        return this.config.getDouble(path, def);
    }

    public List<String> list(final String path) {
        return this.config.getStringList(path);
    }

    public char parseChar(final String path) {
        return this.string(path, null).charAt(0);
    }

    public ConfigurationSection section(final String path) {
        return this.config.getConfigurationSection(path);
    }
}
