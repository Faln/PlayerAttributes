package me.faln.playerattributes.armor;

public enum ArmorType {

    LEATHER("leather",2.5),
    IRON("iron",5.0),
    GOLD("gold",5.5),
    DIAMOND("diamond",7.0),
    NETHERITE("netherite",9.0);

    private final String material;
    private final double percentNegation;

    ArmorType(final String material, final double percentNegation) {
        this.percentNegation = percentNegation;
        this.material = material;
    }

    public double getNegation() {
        return this.percentNegation;
    }

    public String getMaterial() {
        return this.material;
    }

}
