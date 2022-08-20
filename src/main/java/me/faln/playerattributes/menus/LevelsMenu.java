package me.faln.playerattributes.menus;

import de.themoep.inventorygui.DynamicGuiElement;
import de.themoep.inventorygui.InventoryGui;
import de.themoep.inventorygui.StaticGuiElement;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.menus.base.SimpleMenu;
import me.faln.playerattributes.objects.user.User;
import me.faln.playerattributes.objects.user.UserLevel;
import me.faln.playerattributes.utils.Item;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.stream.Collectors;

public class LevelsMenu extends SimpleMenu {

    private final PlayerAttributes plugin;

    private Item unlocked;
    private Item locked;
    private Item info;

    public LevelsMenu(final ConfigurationSection section, final PlayerAttributes plugin) {
        super(section, plugin);

        this.plugin = plugin;
        this.cacheItems(section);
    }

    private void cacheItems(final ConfigurationSection section) {
        this.unlocked = new Item(section.getConfigurationSection("items.unlocked-levels"));
        this.locked = new Item(section.getConfigurationSection("items.locked-levels"));
        this.info = new Item(section.getConfigurationSection("items.info"));

        for (final UserLevel level : this.plugin.getLevelCache().getLevels()) {
            this.getMenu().addElement(new DynamicGuiElement('x', (viewer) -> new StaticGuiElement('x', this.locked.build(), click -> true,
                    this.locked.getName().replace("%level%", level.getId() + ""),
                    this.locked.getLore().stream().map(s -> s
                            .replace("%exp-required%", level.getRequiredExp().toString()))
                            .collect(Collectors.joining("\n"))))
            );
        }
    }

    @Override
    public void buildFor(final Player player) {
        final User user = this.plugin.getUserCache().get(player);
        this.getMenu().show(player);
    }

    @Override
    public InventoryGui getMenu() {
        return this.getMenu();
    }

}
