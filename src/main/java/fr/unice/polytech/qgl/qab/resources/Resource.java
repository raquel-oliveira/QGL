package fr.unice.polytech.qgl.qab.resources;


import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;

import java.util.Set;

/**
 * @version 16/12/15.
 *
 * Class that represent the resources.
 */
public abstract class Resource <T extends ResourceType> {

    /**
     * Method that return the resource name
     * @return resource name
     */
    public abstract String getName();

    /**
     * Method the return the set of biomes that can produce this resource
     * @return set of biomes that can produce this resource
     */
    public abstract Set<Biomes> getBiome();

    public abstract T getType();

}
