package fr.unice.polytech.qgl.qab;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
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
    }

    /**
     * Test to check how the program works when
     * initializing the Explorer with context invalid.
     */
    @Test(expected = org.json.JSONException.class)
    public void testJsonExceptionInitialize() {
        String context = "{ \n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "}\n";
        e.initialize(context);
    }

    /**
     * Test to check how the program works when the budget is 0.
     */
    @Test
    public void testStopWithoutBudget() {
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 0,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        e.initialize(context);
        JSONObject jsonObj = new JSONObject(e.takeDecision());
        assertEquals("stop", jsonObj.getString("action"));
    }

    /**
     * Test to check how the program works when the budget is negative.
     */
    @Test
    public void testStopWithBudgetNegative() {
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": -1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        e.initialize(context);
        JSONObject jsonObj = new JSONObject(e.takeDecision());
        assertEquals("stop", jsonObj.getString("action"));
    }

    /**
     * Test to check how the program works when the budget is negative.
     */
    @Test
    public void testEchoLikeFirstAction() {
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        e.initialize(context);
        JSONObject jsonObj = new JSONObject(e.takeDecision());
        assertEquals("echo", jsonObj.getString("action"));
    }

    @Test
    public void testTakeDecisionReturn() {
        String context = "{ \n" +
                "  \"men\": 12,\n" +
                "  \"budget\": 1000,\n" +
                "  \"contracts\": [\n" +
                "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                "  ],\n" +
                "  \"heading\": \"W\"\n" +
                "}\n";
        e.initialize(context);

        JSONObject jsonObj = new JSONObject(e.takeDecision());
        assertEquals(true, Data.isValide(jsonObj));
    }
}
