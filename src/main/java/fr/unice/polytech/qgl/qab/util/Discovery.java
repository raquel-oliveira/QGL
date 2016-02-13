package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.response.ExploreResponse;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 4.9
 *
 * Class that represents the information discovered by the actions of the bot.
 */
public class Discovery {
    private Found found;
    private int range;
    private Direction direction;
    private List<Biomes> biomes;
    private List<Creek> creeks;
    private GlimpseResponse glimpseResponse;
    private ExploreResponse exploreResponse;

    /**
     * Descovery's constructor.
     */
    public Discovery() {
        this.found = Found.UNDEFINED;
        this.range = 0;
        this.direction = null;
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
        glimpseResponse = new GlimpseResponse();
        exploreResponse = new ExploreResponse();
    }

    /**
     * Discovery's constructor.
     * @param found the data founded (GROUND or OUT_OF_RANGE)
     * @param range the range until the data founded
     * @param direction the direction of the data founded
     */
    public Discovery(Found found, int range, Direction direction) {
        this.found = found;
        this.range = range;
        this.direction = direction;
        biomes = new ArrayList<>();
        creeks = new ArrayList<>();
        glimpseResponse = new GlimpseResponse();
        exploreResponse = new ExploreResponse();
    }

    /**
     * Method to initialize variables
     */
    public void setUp() {
        this.found = Found.UNDEFINED;
        this.range = 0;
        this.direction = null;
        biomes = new ArrayList<>();
    }

    /**
     * Get the value founded (GROUND or OUT_OF_RANGE)
     * @return value founded
     */
    public Found getFound() { return found; }

    /**
     * Get range value of the last discovery
     * @return range value
     */
    public int getRange() { return range; }

    /**
     * Get the direction of the last discovery
     * @return discovery direction
     */
    public Direction getDirection() { return direction; }

    /**
     * Get the set of biomes founded
     * @return set of biomes
     */
    public List<Biomes> getBiomes() { return biomes; }

    /**
     * Get the set of creeks founded
     * @return set of creeks
     */
    public List<Creek> getCreeks() { return creeks; }

    /**
     * Get the data of the glimpse response
     * @return data of the glimpse response
     */
    public GlimpseResponse getGlimpseResponse() {
        return glimpseResponse;
    }

    /**
     * Get the data of the explore response
     * @return data of the explore response
     */
    public ExploreResponse getExploreResponse() {
        return exploreResponse;
    }

    /**
     * Set the range of the last discovery founded
     * @param range of the last discovery founded
     */
    public void setRange(int range) { this.range = range; }

    /**
     * Set the data founded (GROUND or OUT_OF_RANGE)
     * @param found data founded
     */
    public void setFound(Found found) { this.found = found; }

    /**
     * Set the direction of the last discovery
     * @param direction of the last discovery
     */
    public void setDirection(Direction direction) { this.direction = direction; }

    /**
     * Set the list of biomes founded
     * @param biomes list of biomes founded
     */
    public void setBiomes(List<Biomes> biomes) { this.biomes = biomes; }

    /**
     * Set the list of creeks founde
     * @param creeks list of creeks founded
     */
    public void setCreeks(List<Creek> creeks) { this.creeks = creeks; }

    /**
     * Set glimpse response
     * @param glimpseResponse glimpse response
     */
    public void setGlimpseResponse(GlimpseResponse glimpseResponse) {
        this.glimpseResponse = glimpseResponse;
    }

    /**
     * Set explore response
     * @param exploreResponse explore response
     */
    public void setExploreResponse(ExploreResponse exploreResponse) {
        this.exploreResponse = exploreResponse;
    }

    /**
     * Check if there is a specifical biome in the list of the last biomes founded
     * @param bio that we need check
     * @return true it's in the the list, false if not
     */
    public boolean containsBiome(Biomes bio) {
        for (Biomes b: biomes) {
            if (b.equals(bio))
                return true;
        }
        return false;
    }

}