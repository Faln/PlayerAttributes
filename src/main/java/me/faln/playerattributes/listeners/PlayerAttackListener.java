package me.faln.playerattributes.listeners;

import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.Utils;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.objects.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PlayerAttackListener implements Listener {

    private final PlayerAttributes plugin;

    public PlayerAttackListener(final PlayerAttributes plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAttack(final EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }

        final Player player = (Player) event.getDamager();
        final User user = this.plugin.getUserCache().get(player.getUniqueId());
        final double damage = user.get(AttributeType.DAMAGE).doubleValue();

        event.setDamage(damage);
        player.sendMessage(Utils.colorize("&c&l[!] &cYou have dealt " + damage + " to " + event.getEntity().getName() + "."));
    }

}
