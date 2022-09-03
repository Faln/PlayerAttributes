package me.faln.playerattributes.events;

import lombok.Getter;
import me.faln.playerattributes.objects.user.User;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Getter
public class UserExpGainEvent extends Event implements Cancellable {

    private final HandlerList handlerList = new HandlerList();

    private final User player;
    private final BigDecimal amount;
    private boolean cancelled = false;

    public UserExpGainEvent(final User player, final BigDecimal amount) {
        this.player = player;
        this.amount = amount;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }
}
