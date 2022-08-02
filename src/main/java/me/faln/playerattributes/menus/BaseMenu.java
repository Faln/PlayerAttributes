package me.faln.playerattributes.menus;

import de.themoep.inventorygui.InventoryGui;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.utils.Item;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public abstract class BaseMenu implements Menu {

    private InventoryGui menu;
    private String[] layout;

    public BaseMenu(final ConfigurationSection section, final PlayerAttributes plugin) {
        if (section == null) return;

        this.setLayout(section.getStringList("layout"));
        this.menu = new InventoryGui(plugin, null, "&7Menu", layout);

        this.setTitle(section.getString("title"));
        this.setFiller(new Item(section.getConfigurationSection("filler")).build());

    }

    public BaseMenu setLayout(final List<String> layout) {
        this.layout = layout.toArray(new String[0]);
        return this;
    }

    @Override
    public InventoryGui getMenu() {
        return this.menu;
    }

}
