package fr.unice.polytech.qgl.qab.response;

import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


/**
 * @version 07/02/16.
 */
public class ExploreResponseTest {
    ExploreResponse exploreResponse;

    @Before
    public void defineContext() {
        exploreResponse = new ExploreResponse();
    }

    @Test
    public void testAddResource() {
        List<String> resources = new ArrayList<>();
        resources.add("TEST");
        resources.add("FISH");
        resources.add("TEST");
        exploreResponse.addResource(resources);

        PrimaryResource resource = new PrimaryResource(PrimaryType.FISH);
        assertEquals(true, exploreResponse.contains(resource.getResource()));
    }
}
