package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.response.*;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 4.9
 *
 * Class that represents the information discovered by the actions of the bot.
 */
public class Discovery {
    private List<Creek> creeks;
    private ScanResponse scanResponse;
    private GlimpseResponse glimpseResponse;
    private ExploreResponse exploreResponse;
    private EchoResponse echoResponse;
    private ExploitResponse exploitResponse;
    private ScoutResponse scoutResponse;

    /**
     * Discovery's constructor
     */
    public Discovery() {
        creeks = new ArrayList<>();
        glimpseResponse = new GlimpseResponse();
        exploreResponse = new ExploreResponse();
        echoResponse = new EchoResponse();
        scanResponse = new ScanResponse();
        exploitResponse = new ExploitResponse();
        scoutResponse = new ScoutResponse();
    }

    /**
     * Method that set the values patterns in variables.
     * In some cases is necessary return to initial state.
     */
    public void setUpEcho() {
        this.echoResponse = new EchoResponse();
    }

    /**
     * Get creeks of the last scan discovery
     * @return creeks values
     */
    public List<Creek> getCreeks() {
        return creeks;
    }

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
     * Get the data of the exploit response
     * @return data of the exploit response
     */
    public ExploitResponse getExploitResponse() {
        return exploitResponse;
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
     * Set creeks of the last discovery (scan response)
     * @param creeks values of the last discovery
     */
    public void setCreeks(List<Creek> creeks) {
        this.creeks = creeks;
    }

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
     * Set last exploit response
     * @param exploitResponse value of the explore response
     */
    public void setExploiteResponse(ExploitResponse exploitResponse) {
        this.exploitResponse = exploitResponse;
    }

    /**
     * Set last scan response
     * @param scanResponse value of the explore response
     */
    public void setScanResponse(ScanResponse scanResponse) {
        this.scanResponse = scanResponse;
    }

    public void setScoutResponse(ScoutResponse scoutResponse) {
        this.scoutResponse = scoutResponse;
    }

    public ScoutResponse getScoutResponse() {
        return scoutResponse;
    }
}