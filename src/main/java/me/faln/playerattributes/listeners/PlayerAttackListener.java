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
import java.math.RoundingMode;
import java.util.Collections;

public class PlayerAttackListener implements Listener {

    private final PlayerAttributes plugin;

    public PlayerAttackListener(final PlayerAttributes plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
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
            damage = user.get(AttributeType.DAMAGE).multiply(BigDecimal.valueOf(100).divide(victim.get(AttributeType.DEFENSE).add(BigDecimal.valueOf(100)), RoundingMode.UNNECESSARY));
        } else {
            damage = user.get(AttributeType.DAMAGE);
        }

        final String entityHealth = entity.getHealth() - damage.longValueExact() <= 0 ? "0" : Utils.decimalFormat(entity.getHealth() - damage.longValueExact());

        event.setDamage(damage.longValueExact());
        Utils.send(player, Collections.singletonList(
                "&c&l[!] &cYou have dealt " + Utils.decimalFormat(damage.longValueExact()) + " damage to " + entity.getName() + ". &7[Target Health: " + entityHealth + "]"));
    }

}
