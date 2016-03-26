package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent the Scan response structure
 * @version 19/02/16.
 */
public class ScanResponse {
    private List<Biomes> biomes;

    /**
     * ScanResponse's constructor
     */
    public ScanResponse() {
        this.biomes = new ArrayList<>();
    }

    /**
     * Return the biomes found by the scan
     * @return the biomes found by the scan
     */
    public List<Biomes> getBiomes() {
        return biomes;
    }

    /**
     * Set the biomes found by the scan
     * @param biomes the biomes found by the scan
     */
    public void setBiomes(List<Biomes> biomes) {
        this.biomes = biomes;
    }

    /**
     * Return if the plane is out of the ground
     * @return if the plane is out of the ground
     */
    public boolean outOfGround() {
        return biomes.contains(Biomes.OCEAN) && biomes.size() == 1;
    }

    /**
     * Return if the scan found ocean
     * @return if the scan found ocean
     */
    public boolean foundOcean() {
        return biomes.contains(Biomes.OCEAN);
    }

    /**
     * Clean the array of biomes
     */
    public void setUpBiomes() {
        biomes = new ArrayList<>();
    }
}
