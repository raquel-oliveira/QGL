package fr.unice.polytech.qgl.qab;

import org.json.JSONObject;
import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Class that represents the bot in the game.
 * Description based on documentation (http://ace-design.github.io/island/bot/)
 *
 * @version 21.11.2015
 */
public class Explorer implements IExplorerRaid{
    private int men, budget;
    private Direction heading;
    private Direction directionFound;
    private HashMap<String, Integer> contracts;
    private String takeAction;
    private boolean foundOut;
    private int rangeOut;
    private boolean foundGroud;
    private int rangeGroud;
    private ArrayList<String> biomes;
    private ArrayList<String> creeks;
    private String currentAction;

    public Explorer() {
        men = 0;
        budget = 0;
        heading = Direction.EAST;
        contracts = new HashMap<String, Integer>();
        takeAction = null;
        foundOut = false;
        rangeOut = -1;
        directionFound = null;
        foundGroud = false;
        rangeGroud = -1;
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

        heading = (Direction.valueOf((String) jsonObj.getString("heading")));

        System.out.println("men: " + men);
        System.out.println("budget: " + budget);
        System.out.println("contracts: " + contracts);
        System.out.println("heading: " + heading);

    }

    /**
     * Method responsible for take decisions, invoked each time the bot must decide which action must be played.
     * @return for now, we always return the same action: stopping the game
     */
    public String takeDecision() {
        Random gerador = new Random();
        int direction = 0;
        String dir;

        // if in the range": 0, "found": "OUT_OF_RANGE". Stop doing everything.
        if (rangeOut == 0 && foundOut){
            takeAction = "STOP";
            return "{ \"action\": \"stop\" }";
        }
        else if (budget <= 100 ) {
            takeAction = "STOP";
            return "{ \"action\": \"stop\" }";
        }
        else if ((takeAction == null || takeAction.compareToIgnoreCase("scan") == 0) && !foundOut && rangeGroud <= 0) {
            directionFound = heading;
            takeAction = "ECHO";
            return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + heading + "\" } }";
        }
        else if (takeAction.compareToIgnoreCase("fly") == 0 || takeAction.compareToIgnoreCase("heading") == 0){
        	takeAction = "SCAN";
        	return "{ \"action\": \"scan\" }";
        }
        else if (foundOut && rangeOut ==  1) { // if the plane found the out_range
            if (!(heading.toString().equalsIgnoreCase(directionFound.toString()))) { // if the plane made the echo before
                if ((foundOut && (rangeOut > 1)) || foundGroud) { // if the plane found that is ok to change
                    foundGroud = foundOut = false;
                    rangeGroud =  rangeOut = -1;
                    takeAction = "HEADING";
                    return "{ \"action\": \"heading\", \"parameters\": { \"direction\": \"" + directionFound.toString() + "\" } }";
                }
            }
            if (heading.isVertical() ) {
                direction = gerador.nextInt(2);
                dir = (direction == 1)?"W":"E";
                directionFound = Direction.valueOf(dir);
                heading = directionFound; // change the direction of heading
                takeAction = "HEADING";
                return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + directionFound.toString() + "\" } }";
            }
            else if (heading.isHorizontal()){
                direction = gerador.nextInt(2);
                dir = (direction == 1)?"N":"S";
                directionFound = Direction.valueOf(dir);
                heading = directionFound; // change the direction of heading
                takeAction = "HEADING";
                return "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + directionFound.toString() + "\" } }";
            }
        }
        else {
            takeAction = "FLY";
            if (foundOut)
                rangeOut--;
            if (foundGroud)
                rangeGroud--;

            return "{ \"action\": \"fly\" }";
        }
//        return null;
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
            int range = 0;
            String found = "";
            if (jsonObj.getJSONObject("extras").has("range"))
                range = jsonObj.getJSONObject("extras").getInt("range");

            if (jsonObj.getJSONObject("extras").has("found"))
                found = jsonObj.getJSONObject("extras").getString("found");

            if (found.compareToIgnoreCase("GROUND") == 0) {
                foundGroud = true;
                rangeGroud = range;
            } else if (found.compareToIgnoreCase("OUT_OF_RANGE") == 0) {
                foundOut = true;
                rangeOut = range;
            }
        }
//        else if (takeAction.compareToIgnoreCase("SCAN") == 0) {
//            String bios = null;
//            String cks = null;
//
//            if (jsonObj.getJSONObject("extras").has("biomes"))
//                bios = jsonObj.getJSONObject("extras").getString("biomes");
//
//            if (jsonObj.getJSONObject("extras").has("creeks"))
//                bios = jsonObj.getJSONObject("extras").getString("creeds");
//
//            if(bios != null && !bios.isEmpty()) {
//                String[] listBios = bios.split(",");
//                for (String b : listBios)
//                    biomes.add(b);
//            }
//            if(cks != null && !cks.isEmpty()) {
//                String[] listCks = cks.split(",");
//                for (String c : listCks)
//                    creeks.add(c);
//            }
//        }
    }
    public int getRangeOut(){
        return rangeOut;
    }
    public boolean isFoundOut(){
        return foundOut;
    }
    public String getTakeAction(){
        return takeAction;
    }
}