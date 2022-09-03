package me.faln.playerattributes.objects.user.level;

import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.events.UserExpGainEvent;
import me.faln.playerattributes.events.UserLevelEvent;
import me.faln.playerattributes.objects.user.User;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.math.BigDecimal;

public class LevelHandlerImpl implements LevelHandler, Listener {

    private final PlayerAttributes plugin;

    public LevelHandlerImpl(final PlayerAttributes plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onExpGain(final UserExpGainEvent event) {

    }

    @Override
    public void increaseExp(final User user, final BigDecimal amount) {
        final long total = user.getCurrentExp().add(amount).longValueExact();

        if (user.getLevel().getRequiredExp().longValueExact() <= total) {

            if (user.isMaxLevel()) {
                user.setCurrentExp(BigDecimal.ZERO);
                user.setLevel(this.plugin.getLevelCache().getMax());
                return;
            }

            final long extra = total - user.getLevel().getRequiredExp().longValueExact();

            user.setCurrentExp(extra == 0.0 ? BigDecimal.ZERO : BigDecimal.valueOf(extra));
            user.setLevel(user.getLevel().getId() + 1);
        }

        user.setCurrentExp(user.getCurrentExp().add(amount));
    }
}
