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
     * Discovery's constructor
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
     * Discobery's constructor
     * @param found the object that represents the data that was founded (GROUND or OUT_OF_RANGE)
     * @param range the range until the data founded
     * @param direction indicate the direction of the discobery
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
     * Method that set the values patterns in variables.
     * In some cases is necessary return to initial state.
     */
    public void setUp() {
        this.found = Found.UNDEFINED;
        this.range = 0;
        this.direction = null;
        biomes = new ArrayList<>();
    }

    /**
     * Get Found's object (GROUND or OUT_OF_RANGE)
     * @return found's object
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
     * Get the set of biomes founded in the last scan discovery
     * @return set of biomes
     */
    public List<Biomes> getBiomes() { return biomes; }

    /**
     * Get creeks of the last scan discovery
     * @return creeks values
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
     * Set a value in the Found attr.
     * @param found value founded in the tile (GROUND or OUT_OF_RANGE)
     */
    public void setFound(Found found) { this.found = found; }

    /**
     * Set Direction of the last discovery.
     * @param direction value of the direction
     */
    public void setDirection(Direction direction) { this.direction = direction; }

    /**
     * Set biomes of the last discovery (scan response)
     * @param biomes values of the last discovery
     */
    public void setBiomes(List<Biomes> biomes) { this.biomes = biomes; }

    /**
     * Set creeks of the last discovery (scan response)
     * @param creeks values of the last discovery
     */
    public void setCreeks(List<Creek> creeks) { this.creeks = creeks; }

    /**
     * Set last glimpse response
     * @param glimpseResponse value of the glimpse response
     */
    public void setGlimpseResponse(GlimpseResponse glimpseResponse) {
        this.glimpseResponse = glimpseResponse;
    }

    /**
     * Set last explore response
     * @param exploreResponse value of the explore response
     */
    public void setExploreResponse(ExploreResponse exploreResponse) {
        this.exploreResponse = exploreResponse;
    }

    /**
     * Method that check if there is a specifical biome in the list
     * of the last biomes founded.
     * @param bio that will be search
     * @return true if founded, or false if not
     */
    public boolean containsBiome(Biomes bio) {
        for (Biomes b: biomes) {
            if (b.equals(bio))
                return true;
        }
        return false;
    }
}