package me.faln.playerattributes.objects.rewards;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemReward extends Reward<ItemStack> {

    public ItemReward(final ItemStack item) {
        super(item);
    }

    @Override
    public void process(final Player player) {

        if (player.getInventory().firstEmpty() == -1) {
            player.getWorld().dropItemNaturally(player.getLocation(), this.getReward());
            return;
        }

        player.getInventory().addItem(this.getReward());
    }
}
