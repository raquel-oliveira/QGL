package fr.unice.polytech.qgl.qab.resources;


import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;

import java.util.HashSet;
import java.util.Set;

/**
 * @version 16/12/15.
 *
 * Class that represent the resources.
 */
public abstract class Resource <T extends Enum<T> & ResourceType> {
    protected T resource;
    protected Set<Biomes> biomes = new HashSet<>();


    public Resource(T resource) {
        this.resource = resource;
        setBiomes();
    }

    /**
     * Method that return the resource name
     * @return resource name
     */
    abstract public String getName();

    /**
     * Method the return the set of biomes that can produce this resource
     * @return set of biomes that can produce this resource
     */
    public Set<Biomes> getBiome(){
        return biomes;
    }

    abstract void setBiomes();

    public T getType(){
       return resource;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resource)) return false;

        Resource<?> resource1 = (Resource<?>) o;

        return resource.equals(resource1.resource);

    }

    @Override
    public int hashCode() {
        return resource.hashCode();
    }
}
