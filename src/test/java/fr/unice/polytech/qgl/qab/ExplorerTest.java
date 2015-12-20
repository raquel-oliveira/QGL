package fr.unice.polytech.qgl.qab;

import fr.unice.polytech.qgl.qab.exception.InitializeException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * The test class ExplorerTest for the class Test.
 *
 * @version 8.12.2016
 */
public class ExplorerTest {

    private Explorer e;

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
    @Test//(expected = InitializeException.class)
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
        // TODO: see this test
        //e.initialize(context);
        //JSONObject jsonObj = new JSONObject(e.takeDecision());
        //assertEquals("stop", jsonObj.getString("action"));
    }

    /**
     * Test to check how the program works when the budget is negative.
     */
    @Test //TODO:test wrong
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
}
