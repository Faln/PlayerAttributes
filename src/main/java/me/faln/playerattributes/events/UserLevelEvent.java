package me.faln.playerattributes.events;

import lombok.Getter;
import me.faln.playerattributes.objects.user.User;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

@Getter
public class UserLevelEvent extends Event implements Cancellable {

    private final HandlerList handlerList = new HandlerList();

    private final User player;
    private final int fromLevel;
    private final int toLevel;
    private boolean cancelled = false;

    public UserLevelEvent(final User player, final int fromLevel, final int toLevel) {
        this.player = player;
        this.fromLevel = fromLevel;
        this.toLevel = toLevel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
