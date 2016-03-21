package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Exploit;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ResponseHandler;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @version 29/12/15.
 */
public class ResponseHandlerTest {
    private ResponseHandler responseHandler;

    @Before
    public void defineContext() {
        responseHandler = new ResponseHandler();
    }

    @Test
    public void testReadDataFly() throws NegativeBudgetException {
        Context context = new Context();
        context.setBudget(100);
        context.setLastDiscovery(new Discovery());

        String data = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        responseHandler.readData(data, new Fly(), context);

        assertEquals(98, context.getBudget());
        assertTrue(context.getStatus());
    }

    @Test
    public void testBadStatus() throws NegativeBudgetException {
        Context context = new Context();
        context.setBudget(100);
        context.setLastDiscovery(new Discovery());

        String data = "{ \"cost\": 2, \"extras\": {}, \"status\": \"ERROR\" }";
        responseHandler.readData(data, new Fly(), context);

        assertEquals(98, context.getBudget());
        assertFalse(context.getStatus());
    }

    @Test
    public void testReadDataEcho() throws NegativeBudgetException {
        Context context = new Context();
        context.setBudget(100);
        context.setLastDiscovery(new Discovery());

        String data = "{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"GROUND\" }, \"status\": \"OK\" }";
        responseHandler.readData(data, new Echo(Direction.NORTH), context);

        assertEquals(99, context.getBudget());
        assertEquals(2, context.getLastDiscovery().getEchoResponse().getRange());
        assertEquals(Found.GROUND, context.getLastDiscovery().getEchoResponse().getFound());
        assertTrue(context.getStatus());
    }

    @Test
    public void testReadDataScan() throws NegativeBudgetException {
        Context context = new Context();
        context.setBudget(100);
        context.setLastDiscovery(new Discovery());

        String data = "{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": [\"123\"]}, \"status\": \"OK\"}";
        responseHandler.readData(data, new Scan(), context);

        assertEquals(98, context.getBudget());
        assertEquals(Biomes.class, context.getLastDiscovery().getScanResponse().getBiomes().get(0).getClass());
        assertEquals(Biomes.class, context.getLastDiscovery().getScanResponse().getBiomes().get(1).getClass());
        assertEquals("123", context.getLastDiscovery().getCreeks().get(0).getIdCreek());
        assertTrue(context.getStatus());
    }

    @Test
    public void testReadDataExploit() throws NegativeBudgetException {
        Context context = new Context();
        context.setBudget(1000);
        context.setLastDiscovery(new Discovery());

        String data = "{ \"cost\": 3, \"extras\": {\"amount\": 9}, \"status\": \"OK\" }";
        responseHandler.readData(data, new Exploit(new PrimaryResource(PrimaryType.FISH)), context);

        assertEquals(997, context.getBudget());
        assertEquals(9, context.getLastDiscovery().getExploitResponse().getAmount());
        assertEquals(PrimaryType.FISH.toString(), context.getLastDiscovery().getExploitResponse().getResource().getName());
        assertTrue(context.getStatus());
    }

    @Test
    public void testReadDataScout() throws NegativeBudgetException {
        Context context = new Context();
        context.setBudget(1000);
        context.setLastDiscovery(new Discovery());

        String data = "{ \"cost\": 5, \"extras\": { \"altitude\": 1, \"resources\": [\"FUR\", \"WOOD\"] }, \"status\": \"OK\" }";
        responseHandler.readData(data, new Scout(Direction.WEST), context);

        assertEquals(995, context.getBudget());
        assertEquals(Direction.WEST, context.getLastDiscovery().getScoutResponse().getDirection());
        assertEquals(true, context.getLastDiscovery().getScoutResponse().found("FUR"));
        assertEquals(true, context.getLastDiscovery().getScoutResponse().found("WOOD"));
        assertEquals(false, context.getLastDiscovery().getScoutResponse().found("FISH"));
        assertEquals(PrimaryType.FUR, context.getLastDiscovery().getScoutResponse().getResources().get(0));
        assertEquals(PrimaryType.WOOD, context.getLastDiscovery().getScoutResponse().getResources().get(1));
        assertTrue(context.getStatus());
    }

    @Test (expected = NegativeBudgetException.class)
    public void testBadCost() throws NegativeBudgetException {
        Context context = new Context();
        context.setBudget(100);
        context.setLastDiscovery(new Discovery());

        String data = "{ \"cost\": 101, \"extras\": {}, \"status\": \"OK\" }";
        responseHandler.readData(data, new Fly(), context);
        assertTrue(context.getStatus());
    }
}
