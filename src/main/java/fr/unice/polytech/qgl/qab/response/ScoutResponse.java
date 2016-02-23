package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 24/02/16.
 */
public class ScoutResponse {
    private int altitude;
    private List<PrimaryType> resources;

    public ScoutResponse() {
        altitude = 0;
        resources = new ArrayList<>();
    }

    public List<PrimaryType> getResources() {
        return resources;
    }

    public void setAltitude(int altitude) {
        this.altitude = altitude;
    }

    public void setResources(List<PrimaryType> resources) {
        this.resources = resources;
    }
}
