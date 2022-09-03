package me.faln.playerattributes.menus.base;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.OutlinePane;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public interface Menu {

    Inventory getInventory();

    MenuImpl getImpl();

    ChestGui getChestMenu();

    default void show(final Player player) {
        this.getChestMenu().show(player);
    }

    default void setBackground(final Material material) {
        final OutlinePane outlinePane = new OutlinePane(0, 0, 9, 6);
        outlinePane.addItem(new GuiItem(new ItemStack(material)));
        outlinePane.setRepeat(true);
        this.getChestMenu().addPane(outlinePane);
    }

    default void setClickable(final boolean value) {
        this.getChestMenu().setOnGlobalClick(event -> event.setCancelled(value));
    }

}
