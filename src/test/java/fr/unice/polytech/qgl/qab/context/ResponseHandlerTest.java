package fr.unice.polytech.qgl.qab.context;

/**
 * Created by huang on 07/02/16.
 */
import static org.junit.Assert.*;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Transform;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.HandlerResponse;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import scala.util.control.TailCalls;

import java.util.HashMap;

public class ResponseHandlerTest {
    HandlerResponse r;

    @Before
    public void defineContext() {
        r = new HandlerResponse();
    }

    @Test
    public void TestreadDataFromEcho() throws NegativeBudgetException {
        String data = "{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }";

        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";

        Context c1 = new Context();
        c1.read(context);
        Context c2 = r.readData(data, new Echo(Direction.EAST), c1 );
        assertEquals(999,c2.getBudget());
       // assertEquals(Found.GROUND, c2.getLastDiscovery().getFound());
        //assertEquals(2, c2.getLastDiscovery().getRange());
    }

    @Test
    public void TestreadDataFromScan() throws NegativeBudgetException{
        String data = "{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}";

        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        Context c1 = new Context();
        c1.read(context);
        Context c2 = r.readData(data, new Scan(), c1 );
        assertEquals(998, c2.getBudget());
        //assertEquals(Biomes.GLACIER, c2.getLastDiscovery().getBiomes().get(0));
        //assertEquals(Biomes.ALPINE, c2.getLastDiscovery().getBiomes().get(1));

    }

    @Test
    public void TestreadDataFromGlimpse() throws NegativeBudgetException{
        String data = "{ \n" +
                "  \"cost\": 3,\n" +
                "  \"extras\": {\n" +
                "    \"asked_range\": 4,\n" +
                "    \"report\": [\n" +
                "      [ [ \"BEACH\", 59.35 ], [ \"OCEAN\", 40.65 ] ],\n" +
                "      [ [ \"OCEAN\", 70.2  ], [ \"BEACH\", 29.8  ] ],\n" +
                "      [ \"OCEAN\", \"BEACH\" ],\n" +
                "      [ \"OCEAN\" ]\n" +
                "    ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}  ";

        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        Context c1 = new Context();
        c1.read(context);
        Context c2 = r.readData(data, new Glimpse(Direction.EAST, 2), c1 );
        assertEquals(997, c2.getBudget());
        Double d1 = c2.getLastDiscovery().getGlimpseResponse().getInitialTiles().get(0).get(Biomes.BEACH);
        assertEquals((Double)59.35, d1);

    }

    @Test
    public void TestreadDataFromExplore() throws NegativeBudgetException{
        String data = "{\n" +
                "  \"cost\": 5,\n" +
                "  \"extras\": {\n" +
                "    \"resources\": [\n" +
                "      { \"amount\": \"HIGH\", \"resource\": \"FUR\", \"cond\": \"FAIR\" },\n" +
                "      { \"amount\": \"LOW\", \"resource\": \"WOOD\", \"cond\": \"HARSH\" }\n" +
                "    ],\n" +
                "    \"pois\": [ \"creek-id\" ]\n" +
                "  },\n" +
                "  \"status\": \"OK\"\n" +
                "}\n";

        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";

        Context c1 = new Context();
        c1.read(context);
        Context c2 = r.readData(data, new Explore(), c1 );
        assertEquals(995, c2.getBudget());
    }

    @Test
    public void TestreadDataFromTransform() throws NegativeBudgetException {
        String data = "{ \"cost\": 5," +
                " \"extras\": { \"production\": 1, \"kind\": \"GLASS\" }," +
                "\"status\": \"OK\" }";
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";

        Context c1 = new Context();
        c1.read(context);
        Context c2 = r.readData(data, new Transform(new HashMap<>(), c1), c1);
        assertEquals(995, c2.getBudget());

    }

    @Test
    public void TestreadDataFromScout() throws NegativeBudgetException {
        String data = "{ \"cost\": 5," +
                " \"extras\": { \"altitude\": 1, \"resources\": [\"FUR\", \"WOOD\"] }, " +
                "\"status\": \"OK\" }";
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";

        Context c1 = new Context();
        c1.read(context);
        Context c2 = r.readData(data, new Scout(Direction.EAST), c1);
        assertEquals(995, c2.getBudget());

    }

}
