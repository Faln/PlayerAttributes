package me.faln.playerattributes.cache;

import lombok.Getter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.objects.rewards.CommandReward;
import me.faln.playerattributes.objects.rewards.ItemReward;
import me.faln.playerattributes.objects.rewards.Reward;
import me.faln.playerattributes.objects.UserLevel;
import org.bukkit.configuration.ConfigurationSection;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

@Getter
public class LevelCache {

    private final List<UserLevel> levels = new ArrayList<>();

    private final PlayerAttributes plugin;

    public LevelCache(final PlayerAttributes plugin) {
        this.plugin = plugin;
        try {
            this.cache();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cache() throws IOException {

        this.levels.clear();

        final ConfigurationSection section = plugin.getFiles().getFile("levels").getConfig().getConfigurationSection("");
        if (section == null) return;

        for (final String id : section.getKeys(false)) {

            final BigDecimal requiredExp = BigDecimal.valueOf(section.getLong(id + ".required-exp"));
            final String permission = section.getString(id + ".permission");
            final BigDecimal damageCap = BigDecimal.valueOf(section.getLong(".attribute-cap.damage"));
            final BigDecimal defenseCap = BigDecimal.valueOf(section.getLong(".attribute-cap.defense"));
            final BigDecimal resistanceCap = BigDecimal.valueOf(section.getLong(".attribute-cap.resistance"));
            final List<Reward> rewardList = this.parseRewards(section.getConfigurationSection(id + ".rewards"));

            this.levels.add(Integer.parseInt(id) - 1, new UserLevel(Integer.parseInt(id), requiredExp, permission)
                    .setDefenseCap(damageCap)
                    .setDefenseCap(defenseCap)
                    .setResistanceCap(resistanceCap)
                    .setRewards(rewardList));
        }

    }

    private List<Reward> parseRewards(final ConfigurationSection section) {
        List<Reward> rewardList = new LinkedList<>(Collections.emptyList());

        if (section == null) return rewardList;

        for (final String key : section.getKeys(false)) {
            final String type = section.getString(key + ".type", "");
            switch (type) {
                case "item":
                    rewardList.add(new ItemReward());
                    break;
                case "command":
                    rewardList.add(new CommandReward());
                    break;
                default:
                    break;
            }
        }

        return rewardList;
    }


}
