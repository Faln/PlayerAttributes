package me.faln.playerattributes.attributes;

import java.math.BigDecimal;

public abstract class Attribute {

    protected BigDecimal value = BigDecimal.ONE;

    public void set(final BigDecimal value) {
        this.value = value;
    }

    public void increment(final BigDecimal value) {
        this.value = this.value.add(value);
    }

    public BigDecimal get() {
        return this.value;
    }

}
