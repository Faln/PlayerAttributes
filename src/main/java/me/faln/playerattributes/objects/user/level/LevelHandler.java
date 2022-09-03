package me.faln.playerattributes.objects.user.level;

import me.faln.playerattributes.objects.user.User;

import java.math.BigDecimal;

public interface LevelHandler {

    void increaseExp(final User user, final BigDecimal excessExp);

}
