package me.faln.playerattributes.config.files.registry;

import lombok.Getter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.config.files.YMLConfig;
import me.faln.playerattributes.config.files.YMLConfigFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FilesRegistry {

    private final Map<String, YMLConfig> filesMap = new HashMap<>();

    private final PlayerAttributes main;
    private final YMLConfigFactory configFactory;

    public FilesRegistry(final PlayerAttributes main) {
        this.main = main;
        this.configFactory = new YMLConfigFactory(main);
        this.createFiles();
    }

    private void createFiles() {
        for (final String fileName : new String[] {"config", "levels", "levels-menu", "lang"}) {
            final YMLConfig ymlConfig = configFactory.createConfig(main.getDataFolder(), fileName);
            this.filesMap.put(fileName, ymlConfig);
        }
    }

    public YMLConfig getFile(final String fileName) {
        return this.filesMap.getOrDefault(fileName, null);
    }

    public void reloadAll(final List<String> files) {
        for (String file : files) {
            this.getFile(file).reload();
        }
    }

}
