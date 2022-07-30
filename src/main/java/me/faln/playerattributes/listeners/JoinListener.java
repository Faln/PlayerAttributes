package me.faln.playerattributes.listeners;

import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.objects.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.UUID;

public class JoinListener implements Listener {

    private final PlayerAttributes plugin;

    public JoinListener(final PlayerAttributes plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent event) {
        final Player player = event.getPlayer();
        final UUID id = player.getUniqueId();

        if (plugin.getUserCache().contains(id)) return;

        this.plugin.getUserCache().add(id, new User(id, 1).applyDefault());
        this.plugin.getUserCache().saveUser(this.plugin.getUserCache().get(player));

    }

}
