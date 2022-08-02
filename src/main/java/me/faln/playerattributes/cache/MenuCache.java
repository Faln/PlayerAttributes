package me.faln.playerattributes.cache;

import lombok.Getter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.menus.Menu;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MenuCache {

    private final Map<String, Menu> menus = new HashMap<>();

    private final PlayerAttributes plugin;

    public MenuCache(final PlayerAttributes plugin) {
        this.plugin = plugin;
    }
}
