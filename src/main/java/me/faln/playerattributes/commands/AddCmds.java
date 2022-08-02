package me.faln.playerattributes.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandCompletion;
import co.aikar.commands.annotation.Subcommand;
import co.aikar.commands.annotation.Syntax;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.utils.Utils;
import me.faln.playerattributes.attributes.AttributeType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

@CommandAlias("add")
public class AddCmds extends BaseCommand {

    private final PlayerAttributes plugin;

    public AddCmds(final PlayerAttributes plugin) {
        this.plugin = plugin;
    }

    @Subcommand("damage")
    @Syntax("<amount> <player>")
    @CommandCompletion("1|2|3|4|5 @players")
    public void addDamage(final CommandSender sender, final Player player, double amount) {
        plugin.getUserCache().get(player).increment(AttributeType.DAMAGE, BigDecimal.valueOf(amount));
        sender.sendMessage(Utils.colorize("&a&l[!] &aYou have added " + amount + " damage attributes to " + player.getName() + "."));
    }

}
