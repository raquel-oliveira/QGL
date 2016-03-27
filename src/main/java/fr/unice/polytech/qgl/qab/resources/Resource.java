package fr.unice.polytech.qgl.qab.resources;


import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.Set;

/**
 *
 * Class that represent the resources.
 * @version 27/03/16.
 */
public interface Resource <T extends Enum<T> & ResourceType> {

    /**
     * Method that return the resource name
     * @return resource name
     */
    public String getName();

    /**
     * Method the return the set of biomes that can produce this resource
     * @return set of biomes that can produce this resource
     */
    public Set<Biomes> getBiome();

    /**
     * Return the resource type.
     * @return resource type
     */
    public T getType();

    @Override
    public boolean equals(Object o);

    @Override
    public int hashCode();

}
