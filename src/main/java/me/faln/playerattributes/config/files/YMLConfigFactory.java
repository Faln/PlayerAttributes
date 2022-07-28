package me.faln.playerattributes.config.files;

import me.faln.playerattributes.PlayerAttributes;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.logging.Logger;

public class YMLConfigFactory {

    private final Logger logger;
    private final PlayerAttributes main;

    public YMLConfigFactory(final PlayerAttributes main) {
        this.main = main;
        this.logger = main.getLogger();
    }

    public YMLConfig createConfig(final File folder, final String name) {
        if (!folder.exists()) {
            if (folder.mkdirs()) {
                this.logger.info("Created folder for " + name + ".yml");
            }
        }

        final File ymlFile = new File(folder, name + ".yml");

        if (!ymlFile.exists()) {
            try {
                this.main.saveResource(ymlFile.getName(), false);
                if (ymlFile.createNewFile()) {
                    this.logger.info("Created file: " + ymlFile.getName());
                }
            } catch (IOException ex) {
                throw new UncheckedIOException(ex);
            }
        }

        final FileConfiguration config = YamlConfiguration.loadConfiguration(ymlFile);

        return new YMLConfig(ymlFile, config);
    }
}
