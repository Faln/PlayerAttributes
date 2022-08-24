package me.faln.playerattributes.hooks;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.cache.LevelCache;
import me.faln.playerattributes.objects.user.User;
import me.faln.playerattributes.utils.Utils;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class PlaceholderAPIHook extends PlaceholderExpansion {

    private final PlayerAttributes plugin;
    private final LevelCache levelCache;

    public PlaceholderAPIHook(final PlayerAttributes plugin) {
        this.plugin = plugin;
        this.levelCache = plugin.getLevelCache();
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
            case "current-exp":
                return Utils.format(user.getCurrentExp().longValueExact());
            case "required-exp":
                return Utils.subAndFormat(levelCache.get(user.getLevel().getId() + 1).getRequiredExp(), user.getCurrentExp());
            case "damage":
                return user.get(AttributeType.DAMAGE).longValueExact() + "";
            case "damage_formatted":
                return Utils.format(user.get(AttributeType.DAMAGE).longValueExact());
            case "defense":
                return user.get(AttributeType.DEFENSE).longValueExact() + "";
            case "defense_formatted":
                return Utils.format(user.get(AttributeType.DEFENSE).longValueExact());
            case "resistance":
                return user.get(AttributeType.RESISTANCE).longValueExact() + "";
            case "resistance_formatted":
                return Utils.format(user.get(AttributeType.RESISTANCE).longValueExact());
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
