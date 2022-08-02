package me.faln.playerattributes.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.objects.user.User;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final PlayerAttributes plugin;

    public PlaceholderAPIHook(final PlayerAttributes plugin) {
        this.plugin = plugin;
    }

    @Override
    public String onRequest(final OfflinePlayer player, final @NotNull String placeholder) {
        if (player == null || !plugin.getUserCache().contains(player.getUniqueId())) return "???";

        final User user = plugin.getUserCache().get(player.getUniqueId());

        switch (placeholder) {
            case "level":
                return user.getLevel() + "";
            case "points":
                return user.getPoints() + "";
            case "damage":
                return user.getDamage().toString();
            case "defense":
                return user.getDefense().toString();
            case "resistance":
                return user.getResistance().toString();
            default:
                return "Invalid Placeholder";
        }
    }


    @Override
    public @NotNull String getIdentifier() {
        return "attribute";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Faln";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }
}
