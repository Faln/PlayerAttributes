package me.faln.playerattributes.menus.types;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.PaginatedPane;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.cache.LevelCache;
import me.faln.playerattributes.menus.base.MenuImpl;
import me.faln.playerattributes.objects.user.User;
import me.faln.playerattributes.utils.Item;
import me.faln.playerattributes.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.stream.Collectors;

public class LevelsMenu extends MenuImpl {

    private final LevelCache levelCache;

    public LevelsMenu(final PlayerAttributes plugin) {
        super(plugin, plugin.getFiles().getFile("levels-menu"));
        this.levelCache = plugin.getLevelCache();

        this.setClickable(false);
    }

    @Override
    public LevelsMenu buildFor(final Player player) {

        final PaginatedPane pages = new PaginatedPane(0, 0, 9, 5);
        final User user = this.getPlugin().getUserCache().get(player);

        pages.populateWithGuiItems(this.getPlugin().getLevelCache().getLevels().stream()
                .filter(userLevel -> userLevel.getId() <= user.getLevel().getId())
                .map(userLevel -> new GuiItem(new Item(this.getFile().section("items.unlocked-levels"))
                        .replace("%level%", userLevel.getId() + "")
                        .build()))
                .collect(Collectors.toList()));
        pages.populateWithGuiItems(this.getPlugin().getLevelCache().getLevels().stream()
                .filter(userLevel -> userLevel.getId() > user.getLevel().getId())
                .map(userLevel -> new GuiItem(new Item(this.getFile().section("items.locked-levels"))
                        .replace("%level%", userLevel.getId() + "")
                        .replace("%exp-required%", Utils.format(userLevel.getRequiredExp().longValueExact()))
                        .build()))
                .collect(Collectors.toList()));

        final StaticPane extras = new StaticPane(0, 5, 9, 1);

        extras.addItem(new GuiItem(new Item(this.getFile().section("items.next-page")).build()), 5, 0);
        extras.addItem(new GuiItem(new Item(this.getFile().section("items.previous-page")).build()), 3, 0);
        extras.addItem(new GuiItem(new Item(this.getFile().section("items.player-info"))
                .ofUUID(player.getUniqueId())
                .replace("%player%", player.getName())
                .replace("%current-exp%", Utils.format(user.getCurrentExp().longValueExact()))
                .replace("%required-exp%", Utils.subAndFormat(this.levelCache.get(user.getLevel().getId() + 1).getRequiredExp(), user.getCurrentExp()))
                .replace("%player-damage%", user.get(AttributeType.DAMAGE).longValueExact() + "")
                .replace("%player-defense%", user.get(AttributeType.DEFENSE).longValueExact() + "")
                .replace("%player-resistance%", user.get(AttributeType.RESISTANCE).longValueExact() + "")
                .build()), 4, 0);
        this.getMenu().addPane(extras);

        return this;
    }


    @Override
    public void show(final Player player) {
        this.getMenu().show(player);
    }

    @Override
    public ChestGui getMenu() {
        return this.getMenu();
    }

    @Override
    public Inventory getInventory() {
        return this.getMenu().getInventory();
    }

    @Override
    public MenuImpl getImpl() {
        return this;
    }

}
