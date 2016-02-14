package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 06/02/16.
 */
public class ExploreResponse {
    List<List<String>> resources;

    public ExploreResponse() {
        resources = new ArrayList<>();
    }

    public void addResource(List<String> dataResource) {
        resources.add(dataResource);
    }

    public boolean contains(Resource resource) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).get(1).equals(resource.getName()))
                return true;
        }
        return false;
    }
}