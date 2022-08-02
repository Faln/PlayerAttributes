package me.faln.playerattributes.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Subcommand;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

@CommandAlias("attribute")
public class AttributeCmds extends BaseCommand {

    private final PlayerAttributes plugin;

    public AttributeCmds(final PlayerAttributes plugin) {
        this.plugin = plugin;
    }

    @Subcommand("save")
    public void save(final CommandSender sender) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> plugin.getUserCache().save());
        sender.sendMessage(Utils.colorize("&aSaving Data"));
    }


}


