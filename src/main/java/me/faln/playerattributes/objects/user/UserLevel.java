package me.faln.playerattributes.objects.user;

import lombok.Getter;
import lombok.Setter;
import me.faln.playerattributes.objects.rewards.Reward;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@Getter @Setter
public class UserLevel {

    private final int id;
    private final BigDecimal requiredExp;
    private final String permission;
    private BigDecimal damageCap;
    private BigDecimal defenseCap;
    private BigDecimal resistanceCap;

    private List<Reward> rewards = new LinkedList<>();

    public UserLevel(final int id, final BigDecimal requiredExp, final String permission) {
        this.id = id;
        this.requiredExp = requiredExp;
        this.permission = permission;
    }

    public UserLevel setDamageCap(final BigDecimal value) {
        this.damageCap = value;
        return this;
    }

    public UserLevel setDefenseCap(final BigDecimal value) {
        this.defenseCap = value;
        return this;
    }

    public UserLevel setResistanceCap(final BigDecimal value) {
        this.resistanceCap = value;
        return this;
    }

    public UserLevel setRewards(final List<Reward> value) {
        this.rewards = value;
        return this;
    }

}
