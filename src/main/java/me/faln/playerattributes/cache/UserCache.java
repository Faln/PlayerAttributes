package me.faln.playerattributes.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.objects.User;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor @Getter
public class UserCache {

    private final Map<UUID, User> users = new HashMap<>();

    private final PlayerAttributes plugin;

    public User get(final Player player) {
        return this.users.get(player.getUniqueId());
    }

    public User get(final UUID id) {
        return this.users.get(id);
    }

    public boolean contains(final Player player) {
        return this.users.containsKey(player.getUniqueId());
    }

    public boolean contains(final UUID id) {
        return this.users.containsKey(id);
    }

    public void add(final UUID id, final User user) {
        if (contains(id)) {
            return;
        }
        this.users.put(id, user);
    }

}
