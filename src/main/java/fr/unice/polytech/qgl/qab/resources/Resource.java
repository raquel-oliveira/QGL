package fr.unice.polytech.qgl.qab.resources;


import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.Set;

/**
 * @version 16/12/15.
 *
 * Class that represent the resources.
 */
public interface Resource {

    /**
     * Method that return the resource name
     * @return resource name
     */
    String getName();

    /**
     * Method the return the set of biomes that can produce this resource
     * @return set of biomes that can produce this resource
     */
    Set<Biomes> getBiome();

    /**
     * Method that define the set of biomes that can produce this resource
     */
    void setBiomes();
}
