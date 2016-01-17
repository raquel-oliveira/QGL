package fr.unice.polytech.qgl.qab.util.enums;

/**
 * Created by Quentin Prod'Homme on 17/01/2016.
 */
public enum ManufacturedResources {
    GLASS("GLASS"),
    INGOT("INGOT"),
    LEATHER("LEATHER"),
    PLANK("PLANK"),
    RUM("RUM");

    private String name = "";

    ManufacturedResources(String name) {
        this.name = name;
    }
}
