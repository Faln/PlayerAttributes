package me.faln.playerattributes.menus.base;

import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import lombok.Getter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.config.files.YMLConfig;
import me.faln.playerattributes.utils.Utils;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

@Getter
public abstract class MenuImpl implements Menu {

    private final PlayerAttributes plugin;
    private final YMLConfig file;
    private final ChestGui menu;

    protected MenuImpl(final PlayerAttributes plugin, final YMLConfig file) {
        this.plugin = plugin;
        this.file = file;

        final ConfigurationSection section = this.getFile().section("");
        this.menu = new ChestGui(section.getInt("rows"), Utils.colorize(section.getString("title", "&7Levels")));

        if (section.contains("filler")) {
            this.setBackground(Material.getMaterial(section.getString("filler", "AIR")));
        }
    }

    public abstract MenuImpl buildFor(final Player player);

}
