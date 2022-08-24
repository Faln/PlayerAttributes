package me.faln.playerattributes.utils;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

public class Utils {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");
    private static final String[] LETTERS = {"", "K", "M", "B", "T"};

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

    public static String decimalFormat(final double decimal) {
        return DECIMAL_FORMAT.format(decimal);
    }

    public static String format(final long value) {

        int number = Integer.parseInt(String.valueOf(value).split("\\.")[1]);
        int index = 0;

        while ((number / 1000) >= 1) {
            number /= 1000;
            index++;
        }

        return decimalFormat(number).concat(LETTERS[index]);
    }

    public static String addAndFormat(final BigDecimal var1, final BigDecimal var2) {
        return format(var1.add(var2).longValueExact());
    }

    public static String subAndFormat(final BigDecimal var1, final BigDecimal var2) {
        return format(var1.subtract(var2).longValueExact());
    }

}
