package fr.unice.polytech.qgl.qab;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * The test class ExplorerTest for the class Test.
 * @version 14.11.2015
 */
public class ExplorerTest {

    Explorer e;

    @Before
    public void defineContext() {
        e = new Explorer();
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 10000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        e.initialize(context);
    }

    // Test a specific scenario with most functions being used
    // Not unit testing because we will have to change the structure of the bot to allow unit testing
    @Test
    public void takeDecisionTest() {
        String s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"echo\", \"parameters\": { \"direction\": \"W\" } }"));
        e.acknowledgeResults("{ \"cost\": 1, \"extras\": { \"range\": 4, \"found\": \"GROUND\" }, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"scan\" }"));
        e.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"scan\" }"));
        e.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"scan\" }"));
        e.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"scan\" }"));
        e.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"echo\", \"parameters\": { \"direction\": \"W\" } }"));
        e.acknowledgeResults("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"GROUND\" }, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"scan\" }"));
        e.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"echo\", \"parameters\": { \"direction\": \"W\" } }"));
        e.acknowledgeResults("{ \"cost\": 1, \"extras\": { \"range\": 0, \"found\": \"GROUND\" }, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"scan\" }"));
        e.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"echo\", \"parameters\": { \"direction\": \"W\" } }"));
        e.acknowledgeResults("{ \"cost\": 1, \"extras\": { \"range\": 2, \"found\": \"OUT_OF_RANGE\" }, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"scan\" }"));
        e.acknowledgeResults("{\"cost\": 2, \"extras\": { \"biomes\": [\"GLACIER\", \"ALPINE\"], \"creeks\": []}, \"status\": \"OK\"}");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"fly\" }"));
        e.acknowledgeResults("{ \"cost\": 2, \"extras\": {}, \"status\": \"OK\" }");

        s = e.takeDecision();
        assertTrue(s.equals("{ \"action\": \"stop\" }"));
    }
}
