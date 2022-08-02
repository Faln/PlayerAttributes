package me.faln.playerattributes.config;

import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.utils.Utils;

import java.util.List;

public enum Lang {

    TEST("test");

    private final PlayerAttributes plugin = PlayerAttributes.getPlugin(PlayerAttributes.class);
    private final String path;

    Lang(final String path) {
        this.path = path;
    }

    public List<String> getList() {
        return Utils.colorize(plugin.getFiles().getFile("lang").list(path));
    }

    public String getMessage() {
        return Utils.colorize(plugin.getFiles().getFile("lang").string(path, null));
    }
}
