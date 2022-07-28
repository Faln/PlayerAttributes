package me.faln.playerattributes.listeners;

import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.Utils;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.objects.User;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import java.math.BigDecimal;
import java.util.Collections;

public class PlayerAttackListener implements Listener {

    private final PlayerAttributes plugin;

    public PlayerAttackListener(final PlayerAttributes plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onAttack(final EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player) || !(event.getEntity() instanceof LivingEntity)) {
            return;
        }

        final LivingEntity entity = (LivingEntity) event.getEntity();
        final Player player = (Player) event.getDamager();
        final User user = this.plugin.getUserCache().get(player.getUniqueId());
        final User victim;

        final BigDecimal damage;

        if (event.getEntity() instanceof Player) {
            victim = this.plugin.getUserCache().get((Player) event.getEntity());
            damage = BigDecimal.valueOf(user.get(AttributeType.DAMAGE).doubleValue() * (100/(100 + victim.get(AttributeType.DEFENSE).doubleValue())));
        } else {
            damage = user.get(AttributeType.DAMAGE);
        }

        event.setDamage(damage.longValueExact());
        Utils.send(player, Collections.singletonList(
                "&c&l[!] &cYou have dealt " + Utils.decimalFormat(damage.longValueExact()) + " to " + entity.getName() + ". &7[Target Health: " + Utils.decimalFormat(entity.getHealth() - damage.longValueExact()) + "]"));
    }

}
