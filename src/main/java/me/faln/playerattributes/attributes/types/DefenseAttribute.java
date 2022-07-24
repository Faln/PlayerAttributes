package me.faln.playerattributes.attributes.types;

import me.faln.playerattributes.attributes.Attribute;

import java.math.BigDecimal;

public class DefenseAttribute extends Attribute {

    public DefenseAttribute() {
    }

    public DefenseAttribute(BigDecimal amount) {
        super.value = amount;
    }

}
