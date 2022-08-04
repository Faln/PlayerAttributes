package me.faln.playerattributes;

import co.aikar.commands.BukkitCommandManager;
import lombok.Getter;
import me.faln.playerattributes.cache.LevelCache;
import me.faln.playerattributes.cache.MenuCache;
import me.faln.playerattributes.cache.UserCache;
import me.faln.playerattributes.commands.AddCmds;
import me.faln.playerattributes.commands.AttributeCmds;
import me.faln.playerattributes.config.files.registry.FilesRegistry;
import me.faln.playerattributes.hooks.PlaceholderAPIHook;
import me.faln.playerattributes.listeners.JoinListener;
import me.faln.playerattributes.listeners.PlayerAttackListener;
import me.faln.playerattributes.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class PlayerAttributes extends JavaPlugin {

    private FilesRegistry files;

    private UserCache userCache;
    private LevelCache levelCache;
    private MenuCache menuCache;
    private BukkitCommandManager commandManager;

    @Override
    public void onEnable() {
        this.reload();
    }

    public void reload() {
        this.files = new FilesRegistry(this);
        this.userCache = new UserCache(this);
        this.levelCache = new LevelCache(this);
        this.menuCache = new MenuCache(this);
        this.commandManager = new BukkitCommandManager(this);
        this.commandManager.registerCommand(new AddCmds(this));
        this.commandManager.registerCommand(new AttributeCmds(this));

        if (this.getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            new PlaceholderAPIHook(this);
        }

        new JoinListener(this);
        new PlayerAttackListener(this);
    }

    @Override
    public void onDisable() {
        this.userCache.save();
    }

    public void log(String message) {
        this.getLogger().info(Utils.colorize(message));
    }
}
