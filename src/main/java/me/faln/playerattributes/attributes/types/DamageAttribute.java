package me.faln.playerattributes.attributes.types;

import me.faln.playerattributes.attributes.Attribute;

import java.math.BigDecimal;

public class DamageAttribute extends Attribute {

    public DamageAttribute() {
    }

    public DamageAttribute(BigDecimal amount) {
        super.value = amount;
    }

}
