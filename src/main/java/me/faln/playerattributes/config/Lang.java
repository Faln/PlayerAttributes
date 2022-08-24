package me.faln.playerattributes.config;

import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.utils.Utils;
import org.bukkit.command.CommandSender;

import java.util.List;

public enum Lang {

    SAVE("save"),
    INCREASE_ATTRIBUTE("attribute-increase"),
    INCREASE_EXP("experience-increase");

    private final PlayerAttributes plugin = PlayerAttributes.getPlugin(PlayerAttributes.class);
    private final String path;

    Lang(final String path) {
        this.path = path;
    }

    public List<String> getList() {
        return Utils.colorize(plugin.getFiles().getFile("lang").list(path));
    }

    public String getMessage() {
        return Utils.colorize(plugin.getFiles().getFile("lang").string(path, null));
    }

    public void send(final CommandSender sender) {
        sender.sendMessage(this.getMessage());
    }
}
