package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class that represents the bot in the game.
 * Description based on documentation (http://ace-design.github.io/island/bot/)
 *
 * @version 4.8
 */
public class Explorer implements IExplorerRaid{
    private int men, budget;
    private Direction heading;
    private HashMap<String, Integer> contracts;
    private String takeAction;
    private ActionPlane plane;
    private int dir;
    private ArrayList<String> biomes;
    private ArrayList<String> creeks;

    public Explorer() {
        men = 0;
        budget = 0;
        heading = null;
        contracts = new HashMap<String, Integer>();
        takeAction = null;
        plane = new ActionPlane();
        dir = 0;
        biomes = new ArrayList<String>();
        creeks = new ArrayList<String>();
    }

    /**
     * Method to initiate the game. It is invoked right after the initialization.
     * @param context assignment (modeled as a JSON data structure) with the main information to initiate the game.
     */
    public void initialize(String context) {
        JSONObject jsonObj = new JSONObject(context);

        men = jsonObj.getInt("men");
        budget = jsonObj.getInt("budget");

        JSONArray cont = jsonObj.getJSONArray("contracts");

        for (int i = 0; i < cont.length();  i++) {
            String key = cont.getJSONObject(i).getString("resource");
            int value = cont.getJSONObject(i).getInt("amount");
            contracts.put(key, value);
        }

       heading = Direction.fromString(jsonObj.getString("heading"));
    }

    /**
     * Method responsible for take decisions, invoked each time the bot must decide which action must be played.
     * @return for now, we always return the same action: stopping the game
     */
    public String takeDecision() {
        if ((plane.rangeOutOfRange(heading.toString()) <= 1 && plane.rangeOutOfRange(heading.toString()) >= 0) || budget <= 100){
            takeAction = "STOP";
            return "{ \"action\": \"stop\" }";
        }
        else if (takeAction == null || plane.makeEcho(takeAction, heading.toString())) {
            takeAction = "ECHO";
            return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \""+ heading.toString().toUpperCase() +"\" } }";
        }
        /*else if (plane.makeScan(heading.toString(), takeAction)){
        	takeAction = "SCAN";
        	return "{ \"action\": \"scan\" }";
        }*/
        else if (plane.makeHeading(heading.toString(), takeAction)) {
            takeAction = "HEADING";
            plane.resetEnvironment();
            if (dir == 0) {
                heading = Direction.fromString("s");
                dir = 1;
                return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"S\" } }";
            } else if (dir == 1) {
                heading = Direction.fromString("e");
                dir = 0;
                return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"E\" } }";
            }
        }
        /*else if (plane.makeHeading(heading.toString())) {
            String resul = plane.directionToHeading(heading);
            if (resul.compareToIgnoreCase("ECHO") == 0) {
                resul = plane.whereEcho(heading);
                if (resul.compareToIgnoreCase("FLY") == 0) {
                    takeAction = "FLY";
                    plane.fly(heading.toString());
                    return "{ \"action\": \"fly\" }";
                }
                takeAction = "ECHO";
                return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \""+ resul.toUpperCase() +"\" } }";
            }
            takeAction = "HEADING";
            plane.resetEnvironment();
            heading = Direction.fromString(resul);
            return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \""+ resul.toUpperCase() +"\" } }";
        }*/
        takeAction = "FLY";
        plane.fly(heading.toString());
        return "{ \"action\": \"fly\" }";
    }

    /**
     * The acknowledgeResults(String) method is invoked right after the takeDecision() method.
     * It provides the results of the action when applied.
     * @param results information returned after as result of the engine action
     */
    public void acknowledgeResults(String results) {
        JSONObject jsonObj = new JSONObject(results);
        int cost = jsonObj.getInt("cost");
        String status = jsonObj.getString("status");

        if (status.compareToIgnoreCase("ok") != 0)
            takeAction = "stop";

        budget = budget - cost;

        if (takeAction.compareToIgnoreCase("ECHO") == 0) {
            String found = null;
            Integer range = null;

            if (jsonObj.getJSONObject("extras").has("found"))
                found = jsonObj.getJSONObject("extras").getString("found");
            if (jsonObj.getJSONObject("extras").has("range"))
                range = jsonObj.getJSONObject("extras").getInt("range");

            if (found.compareToIgnoreCase("GROUND") == 0)
                plane.setGround(heading.toString(), Integer.toString(range));
            else
                plane.setOutOfRange(heading.toString(), Integer.toString(range));
        }
        
        if(takeAction.compareToIgnoreCase("SCAN")== 0) {
        	String bio = null;
        	String crk = null;
        	
        	if(jsonObj.getJSONObject("extras").has("biomes"))
        		bio = jsonObj.getJSONObject("extras").getString("biomes");
        	if(jsonObj.getJSONObject("extras").has("creeks"))
        		crk = jsonObj.getJSONObject("extras").getString("creeks");
        	
        	if(bio != null && !bio.isEmpty()){
        		String[] biomelist = bio.split(",");
        		for(String b : biomelist)
        			biomes.add(b);
        	}
        	
        	if(crk != null && !crk.isEmpty()){
        		String[] creeklist = crk.split(",");
        		for(String c : creeklist)
        			creeks.add(c);
        	}
        		
        }
    }

}