package me.faln.playerattributes.attributes;

import me.faln.playerattributes.attributes.types.DamageAttribute;
import me.faln.playerattributes.attributes.types.DefenseAttribute;
import me.faln.playerattributes.attributes.types.ResistanceAttribute;

import java.util.function.Supplier;

public enum AttributeType {

    DAMAGE(DamageAttribute::new),
    DEFENSE(DefenseAttribute::new),
    RESISTANCE(ResistanceAttribute::new);

    private final Attribute attribute;

    AttributeType(Supplier<? extends Attribute> clazz) {
        this.attribute = clazz.get();
    }

    public Attribute get() {
        return this.attribute;
    }

}
