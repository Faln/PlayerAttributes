package me.faln.playerattributes.cache;

import lombok.Getter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.menus.base.Menu;
import me.faln.playerattributes.menus.types.LevelsMenu;
import me.faln.playerattributes.menus.types.SkillsMenu;

import java.util.HashMap;
import java.util.Map;

@Getter
public class MenuCache {

    private final Map<String, Menu> menus = new HashMap<>();

    private final PlayerAttributes plugin;

    public MenuCache(final PlayerAttributes plugin) {
        this.plugin = plugin;
        this.cache();
    }

    private void cache() {
        this.menus.clear();
        this.add("levels", new LevelsMenu(plugin));
        this.add("skills", new SkillsMenu(plugin));
    }

    public void add(final String name, final Menu menu) {
        this.menus.put(name, menu);
    }

    public void remove(final String name) {
        this.menus.remove(name);
    }

    public Menu get(final String name) {
        return this.menus.get(name);
    }

}
