package me.faln.playerattributes.objects.user;

import lombok.Getter;
import lombok.Setter;
import me.faln.playerattributes.PlayerAttributes;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.attributes.types.DamageAttribute;
import me.faln.playerattributes.attributes.types.DefenseAttribute;
import me.faln.playerattributes.attributes.types.ResistanceAttribute;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.util.UUID;

@Getter @Setter
public class User {

    private final PlayerAttributes plugin;
    private final UUID id;

    private UserLevel level;
    private BigDecimal currentExp;
    private int points;
    private DamageAttribute damage;
    private DefenseAttribute defense;
    private ResistanceAttribute resistance;

    public User(final PlayerAttributes plugin, final UUID id) {
        this.plugin = plugin;
        this.id = id;
    }

    public User applyDefault() {
        this.level = plugin.getLevelCache().get(0);
        this.points = 0;
        this.currentExp = BigDecimal.ZERO;
        this.damage = new DamageAttribute();
        this.defense = new DefenseAttribute();
        this.resistance = new ResistanceAttribute();
        return this;
    }

    public User addExp(final BigDecimal amount) {

        final long total = this.currentExp.add(amount).longValueExact();

        if (this.level.getRequiredExp().longValueExact() <= total) {

            if (this.isMaxLevel()) {
                this.setCurrentExp(BigDecimal.ZERO);
                this.level = this.plugin.getLevelCache().getMaxLevel();
                return this;
            }

            final long extra = total - this.level.getRequiredExp().longValueExact();

            this.setCurrentExp(extra == 0.0 ? BigDecimal.ZERO : BigDecimal.valueOf(extra));
            this.level = this.plugin.getLevelCache().get(this.level.getId() + 1);
        }

        this.currentExp = this.currentExp.add(amount);
        return this;
    }

    public User setCurrentExp(final BigDecimal amount) {
        this.currentExp = amount;
        return this;
    }

    public User setLevel(final int level) {
        this.level = this.plugin.getLevelCache().get(level);
        return this;
    }

    public User setDamage(final BigDecimal damage) {
        this.damage.set(damage);
        return this;
    }

    public User setDefense(final BigDecimal defense) {
        this.defense.set(defense);
        return this;
    }

    public User setResistance(final BigDecimal resistance) {
        this.resistance.set(resistance);
        return this;
    }

    public User setPoints(final int points) {
        this.points = points;
        return this;
    }

    public void increment(final AttributeType type, @Nullable BigDecimal amount) {
        final BigDecimal num = amount == null ? BigDecimal.ONE : amount;
        switch (type) {
            case DAMAGE:
                this.damage.increment(num);
                break;
            case DEFENSE:
                this.defense.increment(num);
                break;
            case RESISTANCE:
                this.resistance.increment(num);
                break;
        }
    }

    public BigDecimal get(final AttributeType type) {
        switch(type) {
            case DAMAGE:
                return this.damage.get();
            case DEFENSE:
                return this.defense.get();
            case RESISTANCE:
                return this.resistance.get();
            default:
                return BigDecimal.ONE;
        }
    }

    public boolean isMaxLevel() {
        return this.level.getId() + 1 > this.plugin.getLevelCache().getMax();
    }

    public Player toPlayer() {
        return Bukkit.getPlayer(id);
    }

    @Override
    public String toString() {
        return "[" + id.toString() + "] " +
                "{Level: " + level + "}, " +
                "{Damage: " + damage + "}, " +
                "{Defense: " + defense + "}, " +
                "{Resistance: " + resistance + "}.";
    }


}
