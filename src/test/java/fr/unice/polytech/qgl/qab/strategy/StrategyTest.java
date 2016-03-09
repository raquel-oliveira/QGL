package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StrategyTest {
    Strategy strategy;

    @Before
    public void defineContext() throws NegativeBudgetException {
        strategy = new Strategy();
    }

    @Test
    public void testStop() throws IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        assertEquals(new Stop().formatResponse(), strategy.makeDecision());
    }

    @Test
    public void testReadContextFewBudged() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        setContextFewBudged();

        String result = strategy.makeDecision();
        assertEquals(new Stop().formatResponse(), result);
    }

    @Test
    public void testReadContext() throws NegativeBudgetException, IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        setContextEnoughBudged();

        String action = strategy.makeDecision();
        assertEquals(new Echo(Direction.WEST).formatResponse(), action);

        String result = "{ \"cost\": 1, \"extras\": { \"range\": 5, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Echo(Direction.SOUTH).formatResponse(), action);

        result = "{ \"cost\": 1, \"extras\": { \"range\": 15, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Echo(Direction.NORTH).formatResponse(), action);

        result = "{ \"cost\": 1, \"extras\": { \"range\": 5, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Echo(Direction.SOUTH).formatResponse(), action);

        result = "{ \"cost\": 1, \"extras\": { \"range\": 5, \"found\": \"GROUND\" }, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Heading(Direction.SOUTH).formatResponse(), action);

        result = "{ \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Scan().formatResponse(), action);

        result = "{\"cost\": 2, \"extras\": { \"biomes\": [\"BEACH\"], \"creeks\": [id]}, \"status\": \"OK\"}";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Fly().formatResponse(), action);

        result = "{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Scan().formatResponse(), action);

        result = "{\"cost\": 2, \"extras\": { \"biomes\": [\"OCEAN\"], \"creeks\": [id]}, \"status\": \"OK\"}";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Echo(Direction.SOUTH).formatResponse(), action);

        result = "{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Heading(Direction.WEST).formatResponse(), action);

        result = "{ \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Heading(Direction.NORTH).formatResponse(), action);

        result = "{ \"cost\": 4, \"extras\": {}, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Echo(Direction.NORTH).formatResponse(), action);

        result = "{ \"cost\": 1, \"extras\": { \"range\": 10, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertEquals(new Land("id", 1).formatResponse(), action);

        result = "{ \"cost\": 15, \"extras\": { }, \"status\": \"OK\" }";
        strategy.readResults(result);

        action = strategy.makeDecision();
        assertTrue(new MoveTo(Direction.NORTH).formatResponse().equals(action) ||
                new Scout(Direction.EAST).formatResponse().equals(action));
    }

    public void setContextFewBudged() throws NegativeBudgetException {
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 100,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        strategy.initializeContext(context);
    }

    public void setContextEnoughBudged() throws NegativeBudgetException {
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 10000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        strategy.initializeContext(context);
    }
}
