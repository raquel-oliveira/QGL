package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 06/02/16.
 *
 * Class that represent the explore response structure
 */
public class ExploreResponse {
    List<List<String>> resources;

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
    public boolean contains(Resource resource) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).get(1).equals(resource.getName()))
                return true;
        }
        return false;
    }
}
