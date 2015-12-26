package fr.unice.polytech.qgl.qab.util.enums;

import fr.unice.polytech.qgl.qab.util.enums.Biomes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Raquel on 20/12/2015.
 */
public enum Resources {
    FISH("FISH"),
    FLOWER("FLOWER"),
    FRUITS("FRUITS"),
    FUR("FUR"),
    ORE("ORE"),
    QUARTZ("QUARTZ"),
    SUGAR_CANE("SUGAR_CANE"),
    WOOD("WOOD"),

    GLASS("GLASS"),
    INGOT("INGOT"),
    LEATHER("LEATHER"),
    PLANK("PLANK"),
    RUM("RUM");

    private String name = "";
    private List<Biomes> biomes = new ArrayList<>();

    Resources(String name) {
        this.name = name;
        if (this.name == "GLASS") {
            biomes.add(Biomes.OCEAN);
            biomes.add(Biomes.LAKE);
        }
    }

    public boolean isManufactured(){
        return this == GLASS || this == INGOT || this == LEATHER || this == PLANK || this == RUM;
    }


    /**
     * Method to return the list of the actions (echos).
     * @return actions list
     */
    public List<Biomes> getActions() {
        return biomes;
    }
    /**
     * Method that check if the action list is empty
     * @return true if the list is empty, false if not
     */
    public boolean isEmpty() {
        return biomes.isEmpty();
    }

    /**
     * Return one action in a defined index.
     * @param index of the action to return
     * @return action chosen
     */
    public Biomes get(int index) {
        return biomes.get(index);
    }

    /**
     * Method to remove a item that is in the index gave.
     * @param index to remove item
     * @return the item removed
     */
    public Biomes remove(int index) {
        return biomes.remove(index);
    }

    @Override
    public String toString() {
        return name;
    }

}
