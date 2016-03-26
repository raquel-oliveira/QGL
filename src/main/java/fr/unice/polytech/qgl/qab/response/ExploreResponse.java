package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represent the explore response structure
 * @version 06/02/16.
 */
public class ExploreResponse {
    private List<List<String>> resources;

    /**
     * ExploreResponse's constructor
     */
    public ExploreResponse() {
        resources = new ArrayList<>();
    }

    /**
     * Method that add resource in the set of resources
     * @param dataResource item to add in the set of resources
     */
    public void addResource(List<String> dataResource) {
        resources.add(dataResource);
    }

    /**
     * Method that checks if there is a resource specific in the set.
     * @param resource which will be searched
     * @return true if founded, false if not
     */
    public boolean contains(PrimaryType resource) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).get(1).equals(resource.name()))
                return true;
        }
        return false;
    }
}
