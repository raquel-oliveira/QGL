package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.response.EchoResponse;
import fr.unice.polytech.qgl.qab.response.ExploreResponse;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.response.ScanResponse;
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
    private Direction direction;
    private List<Creek> creeks;
    private ScanResponse scanResponse;
    private GlimpseResponse glimpseResponse;
    private ExploreResponse exploreResponse;
    private EchoResponse echoResponse;

    /**
     * Discovery's constructor
     */
    public Discovery() {
        this.direction = null;
        creeks = new ArrayList<>();
        glimpseResponse = new GlimpseResponse();
        exploreResponse = new ExploreResponse();
        echoResponse = new EchoResponse();
        scanResponse = new ScanResponse();
    }

    /**
     * Discobery's constructor
     * @param direction indicate the direction of the discobery
     */
    public Discovery(Direction direction) {
        this.direction = direction;
        creeks = new ArrayList<>();
        glimpseResponse = new GlimpseResponse();
        exploreResponse = new ExploreResponse();
        echoResponse = new EchoResponse();
    }

    /**
     * Method that set the values patterns in variables.
     * In some cases is necessary return to initial state.
     */
    public void setUp() {
        this.direction = null;
    }

    /**
     * Get the direction of the last discovery
     * @return discovery direction
     */
    public Direction getDirection() { return direction; }

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
     * Get the data of the echo response
     * @return data of the echo response
     */
    public EchoResponse getEchoResponse() {
        return echoResponse;
    }

    /**
     * Get the data of the scan response
     * @return data of the scan response
     */
    public ScanResponse getScanResponse() {
        return scanResponse;
    }

    /**
     * Set Direction of the last discovery.
     * @param direction value of the direction
     */
    public void setDirection(Direction direction) { this.direction = direction; }

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
     * Set last echo response
     * @param echoResponse value of the echo response
     */
    public void setEchoResponse(EchoResponse echoResponse) {
        this.echoResponse = echoResponse;
    }

    /**
     * Set last explore response
     * @param exploreResponse value of the explore response
     */
    public void setExploreResponse(ExploreResponse exploreResponse) {
        this.exploreResponse = exploreResponse;
    }

    /**
     * Set last scan response
     * @param scanResponse value of the explore response
     */
    public void setScanResponse(ScanResponse scanResponse) {
        this.scanResponse = scanResponse;
    }
}