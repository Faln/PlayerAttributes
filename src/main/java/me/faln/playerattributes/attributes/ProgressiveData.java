package me.faln.playerattributes.attributes;

import java.math.BigDecimal;

public interface ProgressiveData {

    void increment(BigDecimal amount);

    void incrementSingle();

}
