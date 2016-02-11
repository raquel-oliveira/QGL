package fr.unice.polytech.qgl.qab.resources;


import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.Set;

/**
 * @version 16/12/15.
 */
public interface Resource {
    String getName();

    Set<Biomes> getBiome();

    void setBiomes();
}
