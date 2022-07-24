package me.faln.playerattributes;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    public static String colorize(final String message) {
        return message == null ? null : ChatColor.translateAlternateColorCodes('&', message);
    }

    public static List<String> colorize(final List<String> list) {
        return list.stream().map(Utils::colorize).collect(Collectors.toList());
    }

    public static void send(final Player player, final List<String> messageList) {
        messageList.forEach(s -> player.sendMessage(Utils.colorize(s)));
    }

    public static void send(final CommandSender sender, final List<String> messageList) {
        messageList.forEach(s -> sender.sendMessage(Utils.colorize(s)));
    }

}
