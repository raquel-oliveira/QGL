package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent the Scout response structure
 * @version 24/02/16.
 */
public class ScoutResponse {
    private List<PrimaryType> resources;
    private Direction direction;

    /**
     * ScoutResponse's constructor
     */
    public ScoutResponse() {
        resources = new ArrayList<>();
    }

    /**
     * Method that returns the direction of the scout
     * @return the direction of the scout
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Set the direction of the scout
     * @param direction direction of the scout
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Return a list of resources found by the scout
     * @return a list of resources found by the scout
     */
    public List<PrimaryType> getResources() {
        return resources;
    }

    /**
     * Set a list of resources found by the scout
     * @param resources list of resources found by the scout
     */
    public void setResources(List<PrimaryType> resources) {
        this.resources = resources;
    }

    /**
     * Check if there is a resource in the list of resources found by scout
     * @param resource resource to check if exists in the list of resources
     * @return if found the param resouce in the list
     */
    public boolean found(String resource) {
        for (PrimaryType type: resources) {
            if (type.toString().equalsIgnoreCase(resource))
                return true;
        }
        return false;
    }
}
