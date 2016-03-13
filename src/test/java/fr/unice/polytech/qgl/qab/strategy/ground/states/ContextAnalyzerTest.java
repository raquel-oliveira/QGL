package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.ExploreResponse;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.Discovery;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @version 21/02/16.
 */
public class ContextAnalyzerTest {
    ContextAnalyzer contextAnalyzer;
    Context context;
    Discovery discovery;

    @Before
    public void defineContext() {
        contextAnalyzer = new ContextAnalyzer();
    }

    @Test
    public void testIsOcean() throws NegativeBudgetException {
        context = new Context();

        GlimpseResponse glimpseResponse = new GlimpseResponse();

        discovery = new Discovery();
        discovery.setGlimpseResponse(glimpseResponse);
        context.setLastDiscovery(discovery);

        glimpseResponse.setFourthTile(Biomes.ALPINE);
        List<Biomes> biomes = new ArrayList<>();
        biomes.add(Biomes.ALPINE);
        glimpseResponse.setThirdTile(biomes);

        assertEquals(false, contextAnalyzer.isOcean(context));

        glimpseResponse.setFourthTile(Biomes.OCEAN);
        glimpseResponse.setAskedRange(4);
        biomes = new ArrayList<>();
        biomes.add(Biomes.OCEAN);
        glimpseResponse.setThirdTile(biomes);

        assertEquals(true, contextAnalyzer.isOcean(context));
    }

    @Test
    public void testResourceAnalyzer() throws NegativeBudgetException {
        ExploreResponse exploreResponse = new ExploreResponse();
        List<String> list = new ArrayList<>();
        list.add("");
        list.add(PrimaryType.FISH.toString());
        exploreResponse.addResource(list);

        context = new Context();
        discovery = new Discovery();

        discovery.setExploreResponse(exploreResponse);
        context.setLastDiscovery(discovery);
        context.addContract(PrimaryType.FISH.toString(), 10);

        assertTrue(contextAnalyzer.resourceAnalyzer(context).contains(PrimaryType.FISH));

        exploreResponse = new ExploreResponse();
        list = new ArrayList<>();
        list.add("");
        list.add(PrimaryType.SUGAR_CANE.toString());
        exploreResponse.addResource(list);

        discovery.setExploreResponse(exploreResponse);
        context.setLastDiscovery(discovery);
        context.addContract(ManufacturedType.RUM.toString(), 10);
        assertTrue(contextAnalyzer.resourceAnalyzer(context).contains(PrimaryType.SUGAR_CANE));
    }

    @Test
    public void testBiomeAnalyzer() throws NegativeBudgetException {
        GlimpseResponse glimpseResponse = new GlimpseResponse();

        HashMap<Biomes, Double> bioms = new HashMap<>();
        bioms.put(Biomes.OCEAN, 50.0);
        List<HashMap<Biomes, Double>> initial_tiles = new ArrayList<>();
        initial_tiles.add(bioms);
        initial_tiles.add(bioms);
        List<Biomes> third = new ArrayList<>();
        third.add(Biomes.OCEAN);

        glimpseResponse.setInitialTiles(initial_tiles);
        glimpseResponse.setThirdTile(third);
        glimpseResponse.setFourthTile(Biomes.OCEAN);

        context = new Context();
        discovery = new Discovery();

        discovery.setGlimpseResponse(glimpseResponse);
        context.setLastDiscovery(discovery);
        context.addContract(PrimaryType.FISH.toString(), 10);

        List<Boolean> response = new ArrayList<>();
        response.add(true);
        response.add(true);
        response.add(true);
        response.add(true);

        assertEquals(response, contextAnalyzer.biomeAnalyzer(context));
    }
}
