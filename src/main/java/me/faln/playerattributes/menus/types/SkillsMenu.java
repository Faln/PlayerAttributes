package me.faln.playerattributes.menus.types;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.menus.base.MenuImpl;
import me.faln.playerattributes.objects.user.User;
import me.faln.playerattributes.utils.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.math.BigDecimal;

public class SkillsMenu extends MenuImpl {

    private final PlayerAttributes plugin;

    public SkillsMenu(final PlayerAttributes plugin) {
        super(plugin, plugin.getFiles().getFile("skills-menu"));
        this.plugin = plugin;
        this.setClickable(false);
    }

    @Override
    public Inventory getInventory() {
        return this.getMenu().getInventory();
    }

    @Override
    public MenuImpl getImpl() {
        return this;
    }

    @Override
    public MenuImpl buildFor(final Player player) {

        final User user = plugin.getUserCache().get(player);
        final StaticPane pane = new StaticPane(0, 5, 9, 3);

        pane.addItem(new GuiItem(new Item(this.getFile().section("items.damage")).build(), event -> {
            if (user.getPoints() <= 0) {
                player.closeInventory();
                return;
            }

            user.increment(AttributeType.DAMAGE, BigDecimal.ONE);
            this.getMenu().update();

        }), 3, 1);

        pane.addItem(new GuiItem(new Item(this.getFile().section("items.defense")).build(), event -> {
            if (user.getPoints() <= 0) {
                player.closeInventory();
                return;
            }

            user.increment(AttributeType.DEFENSE, BigDecimal.ONE);
            this.getMenu().update();

        }), 5, 1);

        pane.addItem(new GuiItem(new Item(this.getFile().section("items.resistance")).build(), event -> {
            if (user.getPoints() <= 0) {
                player.closeInventory();
                return;
            }

            user.increment(AttributeType.RESISTANCE, BigDecimal.ONE);
            this.getMenu().update();

        }), 5, 1);

        pane.addItem(new GuiItem(new Item(this.getFile().section("items.player-info"))
                .ofUUID(player.getUniqueId())
                .replace("%player%", player.getName())
                .replace("%player-damage%", user.get(AttributeType.DAMAGE).longValueExact() + "")
                .replace("%player-defense%", user.get(AttributeType.DEFENSE).longValueExact() + "")
                .replace("%player-resistance%", user.get(AttributeType.RESISTANCE).longValueExact() + "")
                .build()), 4, 0);
        this.getMenu().addPane(pane);

        return this;
    }
}
