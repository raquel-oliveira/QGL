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
    private ArrayList<String> biomes;
    private ArrayList<String> creeks;
    private Direction direction;
    private boolean status;

    public Explorer() {
        men = 0;
        budget = 0;
        heading = null;
        contracts = new HashMap<String, Integer>();
        takeAction = null;
        plane = new ActionPlane();
        biomes = new ArrayList<String>();
        creeks = new ArrayList<String>();
        direction =  null;
        status = true;
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

        heading = direction = Direction.fromString(jsonObj.getString("heading"));
    }

    /**
     * Method responsible for take decisions, invoked each time the bot must decide which action must be played.
     * @return for now, we always return the same action: stopping the game
     */
    int cont = 0;
    public String takeDecision() {
        if (plane.canStop(heading.toString(), budget, status, takeAction)) {
            takeAction = "stop";
            return "{ \"action\": \"" + takeAction + "\" }";
        }
        else if (plane.canEcho(takeAction, heading.toString())) {
            takeAction = "echo";
            direction = heading;
            return "{ \"action\": \""+ takeAction +"\", \"parameters\": { \"direction\": \"" + heading.toString() + "\" } }";
        }
        else if (plane.canHeading(heading.toString(), takeAction)) {
            String dirHeading  = plane.whereHeading(heading);
            if (dirHeading.compareToIgnoreCase("ECHO") == 0) {
                String dirEcho = plane.whereEcho(heading, takeAction);
                takeAction = "echo";
                direction = Direction.fromString(dirEcho);
                return "{ \"action\": \""+ takeAction +"\", \"parameters\": { \"direction\": \"" + dirEcho + "\" } }";
            }
            takeAction = "heading";
            heading = Direction.fromString(dirHeading);
            plane.resetEnvironment(); 
            return "{ \"action\": \""+ takeAction +"\", \"parameters\": { \"direction\": \""+dirHeading+"\" } }";
        }
        takeAction = "fly";
        plane.fly(heading.toString());
        return "{ \"action\": \"" + takeAction + "\" }";
    }

    /**
     * The acknowledgeResults(String) method is invoked right after the takeDecision() method.
     * It provides the results of the action when applied.
     * @param results information returned after as result of the engine action
     */
    public void acknowledgeResults(String results) {
        JSONObject jsonObj = new JSONObject(results);

        status = (jsonObj.getString("status").compareToIgnoreCase("ok") == 0)? true:false;
        budget = budget - jsonObj.getInt("cost");

        if (takeAction.compareToIgnoreCase("ECHO") == 0) {
            String found = null;
            Integer range = null;

            if (jsonObj.getJSONObject("extras").has("found"))
                found = jsonObj.getJSONObject("extras").getString("found");
            if (jsonObj.getJSONObject("extras").has("range"))
                range = jsonObj.getJSONObject("extras").getInt("range");

            if (found.compareToIgnoreCase("GROUND") == 0)
                plane.setGround(direction.toString(), range);
            else
                plane.setOutOfRange(direction.toString(), range);
        }
        
        if(takeAction.compareToIgnoreCase("SCAN") == 0) {
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