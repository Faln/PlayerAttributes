package me.faln.playerattributes.menus.base;

import de.themoep.inventorygui.GuiElement;
import de.themoep.inventorygui.InventoryGui;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;

public interface Menu {

    MenuImpl getImpl();

    InventoryGui getMenu();

    @Nullable default Player getHolder() {
        return (Player) this.getMenu().getOwner();
    }

    @Nonnull default String getTitle() {
        return this.getMenu().getTitle();
    }

    @Nonnull default Collection<GuiElement> getElements() {
        return this.getMenu().getElements();
    }

    default void setTitle(final String title) {
        this.getMenu().setTitle(title);
    }

    default void setHolder(final Player player) {
        this.getMenu().setOwner(player);
    }

    default void setFiller(final ItemStack itemStack) {
        this.getMenu().setFiller(itemStack);
    }

    default void show(final Player player) {
        this.getMenu().show(player);
    }

}
