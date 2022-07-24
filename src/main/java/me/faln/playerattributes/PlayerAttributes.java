package me.faln.playerattributes;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.faln.playerattributes.cache.UserCache;
import me.faln.playerattributes.commands.AddCmds;
import me.faln.playerattributes.listeners.JoinListener;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class PlayerAttributes extends JavaPlugin {

    private UserCache userCache;

    private BukkitCommandManager commandManager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.userCache = new UserCache(this);
        this.commandManager = new BukkitCommandManager(this);
        this.commandManager.registerCommand(new AddCmds(this));

        new JoinListener(this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
