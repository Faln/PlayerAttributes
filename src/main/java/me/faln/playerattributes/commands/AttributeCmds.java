package me.faln.playerattributes.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.Default;
import co.aikar.commands.annotation.Subcommand;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandAlias("attribute")
public class AttributeCmds extends BaseCommand {

    private final PlayerAttributes plugin;

    public AttributeCmds(final PlayerAttributes plugin) {
        this.plugin = plugin;
    }

    @Default
    public void openMenu(final Player player) {
        this.plugin.getMenuCache().get("levels").getImpl().buildFor(player);
    }

    @Subcommand("save")
    public void save(final CommandSender sender) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> plugin.getUserCache().save());
        sender.sendMessage(Utils.colorize("&aSaving Data"));
    }


}


