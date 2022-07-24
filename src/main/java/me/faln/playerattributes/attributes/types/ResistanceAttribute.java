package me.faln.playerattributes.attributes.types;

import me.faln.playerattributes.attributes.Attribute;

import java.math.BigDecimal;

public class ResistanceAttribute extends Attribute {

    public ResistanceAttribute() {
    }

    public ResistanceAttribute(BigDecimal amount) {
        super.value = amount;
    }
}
