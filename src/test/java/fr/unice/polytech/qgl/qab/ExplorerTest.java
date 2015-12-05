package fr.unice.polytech.qgl.qab;

import java.util.HashMap;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import fr.unice.polytech.qgl.qab.ActionPlane.Discovery;

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
    
    @Test
    public void testEchoSendGround(){
    	 String context = "{ \n" +
                 "  \"men\": 12,\n" +
                 "  \"budget\": 1000,\n" +
                 "  \"contracts\": [\n" +
                 "    { \"amount\": 600, \"resource\": \"WOOD\" },\n" +
                 "    { \"amount\": 200, \"resource\": \"GLASS\" }\n" +
                 "  ],\n" +
                 "  \"heading\": \"W\"\n" +
                 "}\n";
    	 String results = "{ \n" +
                 "  \"cost\": 1,\n" +
                 "  \"extras\": [\n" +
                 "    { \"range\": 2, \"found\": \"GROUND\" },\n" +
                 "  ],\n" +
                 "  \"status\": \"OK\"\n" +
                 "}\n";
         e.initialize(context);
         e.acknowledgeResults(results);

         ActionPlane plane = e.getPlane();
         Direction d = e.getDirection();
         Discovery dis1 = plane.new Discovery(Found.GROUND,2);
         Discovery dis2 = plane.getEnvironment().get(d);
         
         assertEquals(dis1,dis2);
         	 
    	 
    }
    
    
}
