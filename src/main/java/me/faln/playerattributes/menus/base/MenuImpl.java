package me.faln.playerattributes.menus.base;

import de.themoep.inventorygui.InventoryGui;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.utils.Item;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.List;

public abstract class MenuImpl implements Menu {

    private final PlayerAttributes plugin;

    private InventoryGui menu;
    private String[] layout;

    public MenuImpl(final ConfigurationSection section, final PlayerAttributes plugin) {
        this.plugin = plugin;
        if (section == null) return;

        this.setLayout(section.getStringList("layout"));
        this.menu = new InventoryGui(plugin, null, "&7Menu", layout);

        this.setTitle(section.getString("title"));
        this.setFiller(new Item(section.getConfigurationSection("filler")).build());

    }

    public MenuImpl setLayout(final List<String> layout) {
        this.layout = layout.toArray(new String[0]);
        return this;
    }

    protected void register(final String name, final Menu menu) {
        this.plugin.getMenuCache().add(name, menu);
    }

    public abstract void buildFor(final @Nullable Player player);

    @Override
    public MenuImpl getImpl() {
        return this;
    }

}