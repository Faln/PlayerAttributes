package me.faln.playerattributes.attributes;

import java.math.BigDecimal;

public abstract class Attribute implements ProgressiveData {

    protected BigDecimal value = BigDecimal.ONE;

    public BigDecimal get() {
        return this.value;
    }

    @Override
    public void increment(BigDecimal amount) {
        this.value = this.value.add(amount);
    }

    @Override
    public void incrementSingle() {
        this.value = this.value.add(BigDecimal.ONE);
    }

}
