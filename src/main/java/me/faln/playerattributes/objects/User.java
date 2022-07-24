package me.faln.playerattributes.objects;

import lombok.Getter;
import lombok.Setter;
import me.faln.playerattributes.attributes.AttributeType;
import me.faln.playerattributes.attributes.types.DamageAttribute;
import me.faln.playerattributes.attributes.types.DefenseAttribute;
import me.faln.playerattributes.attributes.types.ResistanceAttribute;

import javax.annotation.Nullable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

@Getter @Setter
public class User {

    private final UUID id;

    private BigInteger level;

    private DamageAttribute damage;
    private DefenseAttribute defense;
    private ResistanceAttribute resistance;

    public User(final UUID id, final BigInteger level) {
        this.id = id;
        this.level = level;
    }

    public User applyDefault() {
        this.level = BigInteger.ONE;
        this.damage = new DamageAttribute(BigDecimal.ONE);
        this.defense = new DefenseAttribute(BigDecimal.ONE);
        this.resistance = new ResistanceAttribute(BigDecimal.ONE);
        return this;
    }

    public void increment(final AttributeType type, @Nullable BigDecimal amount) {
        final BigDecimal num = amount == null ? BigDecimal.ONE : amount;
        switch(type) {
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

    @Override
    public String toString() {
        return "[" + id.toString() + "] " +
                "Level: " + level + ", " +
                "Damage: " + damage + ", " +
                "Defense: " + defense + ", " +
                "Resistance: " + resistance + ".";
    }


}
