package me.faln.playerattributes.objects.rewards;

import org.bukkit.entity.Player;

public abstract class Reward<T> {

    private final T reward;

    public Reward(final T reward) {
        this.reward = reward;
    }

    public abstract void process(final Player player);

    public T getReward() {
        return this.reward;
    }
}
