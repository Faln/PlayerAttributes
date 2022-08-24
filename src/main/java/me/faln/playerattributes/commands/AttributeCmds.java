package me.faln.playerattributes.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.cache.LevelCache;
import me.faln.playerattributes.cache.UserCache;
import me.faln.playerattributes.config.Lang;
import me.faln.playerattributes.menus.types.LevelsMenu;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.math.BigDecimal;

@CommandAlias("attribute|attributes|att|stats")
public class AttributeCmds extends BaseCommand {

    private final PlayerAttributes plugin;
    private final UserCache userCache;
    private final LevelCache levelCache;

    public AttributeCmds(final PlayerAttributes plugin) {
        this.plugin = plugin;
        this.userCache = plugin.getUserCache();
        this.levelCache = plugin.getLevelCache();
    }

    @Default
    public void openMenu(final Player player) {
        LevelsMenu menu = (LevelsMenu) this.plugin.getMenuCache().get("levels").getImpl().buildFor(player);
        menu.show(player);
    }

    @Subcommand("add")
    @CommandPermission("attribute.add")
    @Syntax("<type> <amount> <player>")
    @CommandCompletion("damage|defense|resistance|exp 1|2|3|4|5 @players")
    public void add(final String type, final Player player, double amount) {

        switch (type.toLowerCase()) {
            case "damage":
                this.userCache.get(player).increment(AttributeType.DAMAGE, BigDecimal.valueOf(amount));
                Lang.INCREASE_ATTRIBUTE.getList().stream().map(s -> s.replace("%type%", type)).forEach(player::sendMessage);
                break;
            case "defense":
                this.userCache.get(player).increment(AttributeType.DEFENSE, BigDecimal.valueOf(amount));
                Lang.INCREASE_ATTRIBUTE.getList().stream().map(s -> s.replace("%type%", type)).forEach(player::sendMessage);
                break;
            case "resistance":
                this.userCache.get(player).increment(AttributeType.RESISTANCE, BigDecimal.valueOf(amount));
                Lang.INCREASE_ATTRIBUTE.getList().stream().map(s -> s.replace("%type%", type)).forEach(player::sendMessage);
                break;
            case "exp":
                this.userCache.get(player).addExp(BigDecimal.valueOf(amount));
                Lang.INCREASE_EXP.getList().stream().map(s -> s.replace("%amount%", type)).forEach(player::sendMessage);
                break;
        }
    }

    @Subcommand("save")
    @CommandPermission("attribute.save")
    public void save(final CommandSender sender) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> this.plugin.getUserCache().save());
        Lang.SAVE.send(sender);
    }


}


