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
    private Action takeAction;
    private ActionPlane plane;


	private ArrayList<String> biomes;
    private ArrayList<String> creeks;
    private Direction direction;
    private boolean status;

    private ArrayList<Direction> comboECHO;

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
        comboECHO = new ArrayList<>();
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
    public String takeDecision() {
        try {

            if (plane.canStop(heading, budget, status)) {
                takeAction = Action.STOP;
                return "{ \"action\": \"" + takeAction.toString() + "\" }";
            }
            else if (!comboECHO.isEmpty()) {
                takeAction = Action.ECHO;
                Direction dir = comboECHO.get(0);
                comboECHO.remove(0);
                return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \"" + dir.toString() + "\" } }";
            }
            else if (plane.canEcho(takeAction, heading)) {
                takeAction = Action.ECHO;
                direction = heading;
                return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \"" + heading.toString() + "\" } }";
            }
            else if (plane.canHeading(heading)) {
                Direction dirHeading  = plane.whereHeading(heading);
                if (dirHeading == null) {
                    Direction dirEcho = plane.whereEcho(heading, takeAction);
                    takeAction = Action.ECHO;
                    direction = dirEcho;
                    return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \"" + dirEcho + "\" } }";
                }
                takeAction = Action.HEADING;
                heading = dirHeading;
                plane.resetEnvironment();
                return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \""+ dirHeading +"\" } }";
            }
        /*else if(takeAction== Action.FLY){
            takeAction = Action.SCAN;
            return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \"" + heading.toString() + "\" } }";

        }*/
            takeAction = Action.FLY;
            plane.fly(heading);
            return "{ \"action\": \"" + takeAction.toString() + "\" }";
        }
        catch (Exception e)
        {
            return "{ \"action\": \"" + Action.STOP.toString() + "\" }";
        }
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

        if (takeAction.equals(Action.ECHO)) {
            Found found = null;
            Integer range = null;

            if (jsonObj.getJSONObject("extras").has("found"))
                found = Found.fromString(jsonObj.getJSONObject("extras").getString("found"));
            if (jsonObj.getJSONObject("extras").has("range"))
                range = jsonObj.getJSONObject("extras").getInt("range");

            if (found.equals(Found.GROUND))
                plane.setGround(direction, range);
            else
                plane.setOutOfRange(direction, range);
        }

        if(takeAction.equals(Action.SCAN)) {
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
    
    public Direction getDirection() {
		return direction;
	}

	public ActionPlane getPlane() {
		return plane;
	}
}