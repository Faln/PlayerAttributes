package me.faln.playerattributes.objects.rewards;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CommandReward extends Reward<String> {

    public CommandReward(String reward) {
        super(reward);
    }

    @Override
    public void process(final Player player) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), this.getReward().replace("%player%", player.getName()));
    }
}
