package me.faln.playerattributes.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Item {

    private final ItemStack stack;
    private final ItemMeta meta;
    private ConfigurationSection section;

    public Item(@Nullable ItemStack stack) {
        this.stack = stack != null ? stack : new ItemStack(Material.AIR);
        this.meta = stack.hasItemMeta() ? stack.getItemMeta() : Bukkit.getServer().getItemFactory().getItemMeta(stack.getType());
    }

    public Item(Material material) {
        this(new ItemStack(material));
    }

    public Item(ConfigurationSection section) {
        this(section.contains("material") ? Material.getMaterial(section.getString("material")) : Material.STONE);
        this.section = section;
        if (stack.getType().equals(Material.PLAYER_HEAD) || stack.getType().equals(Material.PLAYER_WALL_HEAD)) {
            if (section.contains("texture")) {
                ofName(section.getString("name"));
            } else if (section.contains("owner")) {
                ofPlayer(Bukkit.getOfflinePlayer(UUID.fromString(section.getString("owner"))));
            }
        }
        setData(section.getInt("data", 0));
        if (section.contains("name"))
            setName(section.getString("name"));
        if (section.contains("lore")) {
            setLore(section.getStringList("lore"));
        }
        if (section.contains("flags")) {
            for (String line : section.getStringList("flags")) {
                addFlag(ItemFlag.valueOf(line.toUpperCase()));
            }
        }
    }

    public Item setType(Material material) {
        stack.setType(material);
        return this;
    }

    public Item setAmount(int amount) {
        stack.setAmount(amount);
        return this;
    }

    public Item setGlow(boolean value) {
        if (value) {
            stack.addEnchantment(Enchantment.ARROW_INFINITE, 1);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        return this;
    }

    public Item setName(String name) {
        meta.setDisplayName(Utils.colorize(name));
        return this;
    }

    public Item setData(int data) {
        if (data > 0)
            meta.setCustomModelData(data);
        return this;
    }

    public Item setLore(List<String> lore) {
        meta.setLore(Utils.colorize(lore));
        return this;
    }

    public List<String> getLore() {
        return meta.hasLore() ? meta.getLore() : null;
    }

    public String getName() {
        return this.meta.getDisplayName();
    }

    public Item addFlag(ItemFlag flag) {
        meta.addItemFlags(flag);
        return this;
    }

    public Item remFlag(ItemFlag flag) {
        meta.removeItemFlags(flag);
        return this;
    }

    public Item ofName(String owner) {
        SkullMeta headMeta = (SkullMeta) meta;
        headMeta.setOwner(owner);
        return this;
    }

    public Item ofUUID(UUID uuid) {
        return ofPlayer(Bukkit.getOfflinePlayer(uuid));
    }

    public Item ofPlayer(OfflinePlayer offlinePlayer) {
        SkullMeta headMeta = (SkullMeta) meta;
        headMeta.setOwningPlayer(offlinePlayer);
        return this;
    }

    public ItemStack build() {
        stack.setItemMeta(meta);
        return stack;
    }

    public Item replace(final @Nonnull String lookFor, final @NotNull String replaceWith) {
        setName(section.getString("name").replace(lookFor, replaceWith));
        setLore(section.getStringList("lore").stream().map(s -> s.replace(lookFor, replaceWith)).collect(Collectors.toList()));
        return this;
    }
}
