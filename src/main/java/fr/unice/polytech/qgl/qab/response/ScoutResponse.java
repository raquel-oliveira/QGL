package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 24/02/16.
 */
public class ScoutResponse {
    private int altitude;
    private List<PrimaryType> resources;

    private Direction direction;

    public ScoutResponse() {
        altitude = 0;
        resources = new ArrayList<>();
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
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

    public boolean found(String resource) {
        for (PrimaryType type: resources) {
            if (type.toString().equalsIgnoreCase(resource))
                return true;
        }
        return false;
    }
}
