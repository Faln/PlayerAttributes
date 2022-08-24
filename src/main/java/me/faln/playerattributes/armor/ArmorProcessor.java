package me.faln.playerattributes.armor;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.math.BigDecimal;

public final class ArmorProcessor {

    public static BigDecimal process(final Player player, final BigDecimal value) {

        if (value.equals(BigDecimal.ZERO)) {
            return value;
        }

        double negation = 0.0;

        for (final ItemStack item : player.getInventory().getArmorContents()) {
            if (item == null || item.getType() == Material.AIR) {
                continue;
            }

            for (final ArmorType type : ArmorType.values()) {
                if (!item.getType().toString().toLowerCase().contains(type.getMaterial())) {
                    continue;
                }
                negation += type.getNegation();
            }
        }

        return BigDecimal.valueOf(value.doubleValue() * (negation / 100));
    }
}
