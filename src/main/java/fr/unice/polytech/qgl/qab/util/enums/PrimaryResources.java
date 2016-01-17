package fr.unice.polytech.qgl.qab.util.enums;

/**
 * @version 17/01/2016.
 */
public enum PrimaryResources {
    FISH("FISH"),
    FLOWER("FLOWER"),
    FRUITS("FRUITS"),
    FUR("FUR"),
    ORE("ORE"),
    QUARTZ("QUARTZ"),
    SUGAR_CANE("SUGAR_CANE"),
    WOOD("WOOD");

    private String name = "";

    PrimaryResources(String name) {
        this.name = name;
    }
}
