package me.faln.playerattributes.armor;

public enum ArmorType {

    LEATHER(2.5),
    IRON(5.0),
    GOLD(5.5),
    DIAMOND(7.0),
    NETHERITE(9.0);

    private final double percentNegation;

    ArmorType(final double percentNegation) {
        this.percentNegation = percentNegation;
    }

    public double getNegation() {
        return this.percentNegation;
    }
}
