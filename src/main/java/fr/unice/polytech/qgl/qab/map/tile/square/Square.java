package fr.unice.polytech.qgl.qab.map.tile.square;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;

/**
 * Created by huang on 24/02/16.
 *
 * class that represent square in the tile
 */
public class Square {
    private Biomes biome;

    /**
     * Square's constructor
     */
    public Square(){
        biome = null;
    }

    /**
     * Get the biome in this square
     * @return biome in this square
     */
    public Biomes getBiome(){
        return biome;
    }

    /**
     * Set the biome of this square
     * @param biome
     */
    public void setBiome(Biomes biome){
        this.biome = biome;
    }
}
