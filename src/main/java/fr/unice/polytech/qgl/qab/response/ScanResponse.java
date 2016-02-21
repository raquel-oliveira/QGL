package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 19/02/16.
 */
public class ScanResponse {
    private List<Biomes> biomes;

    public ScanResponse() {
        this.biomes = new ArrayList<>();
    }

    public List<Biomes> getBiomes() {
        return biomes;
    }

    public void setBiomes(List<Biomes> biomes) {
        this.biomes = biomes;
    }

    public boolean outOfGround() {
        return (biomes.contains(Biomes.OCEAN) && biomes.size() == 1);
    }

    public boolean foundOcean() {
        return (biomes.contains(Biomes.OCEAN));
    }

    public void setUpBiomes() {
        biomes = new ArrayList<>();
    }
}
