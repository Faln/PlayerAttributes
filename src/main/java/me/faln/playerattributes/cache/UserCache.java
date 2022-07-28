package me.faln.playerattributes.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.config.files.YMLConfig;
import me.faln.playerattributes.objects.User;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class UserCache {

    private final Map<UUID, User> users = new HashMap<>();

    private final PlayerAttributes plugin;

    public UserCache(final PlayerAttributes plugin) {
        this.plugin = plugin;
        try {
            this.cache();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    private void cache() throws IOException {

        this.users.clear();

        final File directory = new File(plugin.getDataFolder() + File.separator + "users");
        if (!directory.exists()) {
            if (directory.mkdirs()) plugin.log("&cUsers folder missing, creating a new one.");
            return;
        }
        final Path dir = Paths.get(directory.toURI());
        Files.walk(dir).forEach(path -> loadFile(path.toFile()));
    }

    private void loadFile(final File file) {
        if (file.isDirectory()) return;
        try {
            final String fileName = file.getName().split("\\.")[0];
            final ConfigurationSection section = YamlConfiguration.loadConfiguration(file).getConfigurationSection("");
            if (section == null) return;

            final UUID id = UUID.fromString(fileName);
            final BigInteger level = BigInteger.valueOf(section.getLong("level"));
            final BigDecimal damage = BigDecimal.valueOf(section.getLong("damage"));
            final BigDecimal defense = BigDecimal.valueOf(section.getLong("defense"));
            final BigDecimal resistance = BigDecimal.valueOf(section.getLong("resistance"));

            this.add(id, new User(id, level)
                    .setDamage(damage)
                    .setDefense(defense)
                    .setResistance(resistance));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveUser(final User user) {

        if (!this.users.containsKey(user.getId())) return;

        final String id = user.getId().toString();
        final File file = new File(plugin.getDataFolder() + File.separator + "users" + File.separator + id + ".yml");
        final YMLConfig config = new YMLConfig(file, YamlConfiguration.loadConfiguration(file));

        plugin.getServer().getScheduler().runTaskAsynchronously(plugin, () -> {
            config.getConfig().set("uuid", id);
            config.getConfig().set("level", user.getLevel().intValue());
            config.getConfig().set("damage", user.getDamage().get().longValue());
            config.getConfig().set("defense", user.getDefense().get().longValue());
            config.getConfig().set("resistance", user.getResistance().get().longValue());
            config.save();
        });

    }

    public void save() {
        for (final User user : this.users.values()) {
            this.saveUser(user);
        }
    }

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
