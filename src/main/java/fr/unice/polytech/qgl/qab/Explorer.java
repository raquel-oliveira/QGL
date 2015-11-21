package fr.unice.polytech.qgl.qab;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

import eu.ace_design.island.bot.IExplorerRaid;
/**
 * Class that represents the bot in the game.
 * Description based on documentation (http://ace-design.github.io/island/bot/)
 *
 * @version 21.11.2015
 */
public class Explorer implements IExplorerRaid{
    private int men, budget;
    private String heading;
    private HashMap<String, Integer> contracts;
    private String takeAction;
    private boolean foundOut;
    private int rangeOut;
    private boolean foundGroud;
    private int rangeGroud;
    private ArrayList<String> biomes;

    public Explorer() {
        men = 0;
        budget = 0;
        heading = "";
        contracts = new HashMap<String, Integer>();
        takeAction = "echo";
        foundOut = false;
        rangeOut = -1;
        foundGroud = false;
        rangeGroud = -1;
        biomes = new ArrayList<String>();
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

        heading = (String)jsonObj.get("heading");

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
        // if in the range": 0, "found": "OUT_OF_RANGE". Stop doing everything.
        if(rangeOut==0 & foundOut){
            return "{ \"action\": \"stop\" }";
        }
        if(takeAction.compareToIgnoreCase("echo") == 0)
            return takeAction = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + heading + "\" } }";
        else if (takeAction.compareToIgnoreCase("fly") == 0)
            return "{ \"action\": \"fly\" }";
        else
            return "{ \"action\": \"stop\" }";
        if ()
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
        JSONArray extras = jsonObj.getJSONArray("extras");

        if (status.compareToIgnoreCase("ok") != 0)
            takeAction = "stop";

        budget = budget - cost;

        if (takeAction.compareToIgnoreCase("ECHO") == 0) {
            int range = extras.getJSONObject(0).getInt("range");
            String found = extras.getJSONObject(0).getString("found");

            if (found.compareToIgnoreCase("GROUND") == 0) {
                foundGroud = true;
                rangeGroud = range;
            } else if (found.compareToIgnoreCase("OUT_OF_RANGE") == 0) {
                foundOut = true;
                rangeOut = range;
            }
        }
        else if (takeAction.compareToIgnoreCase("SCAN") == 0) {
            JSONArray bio = extras.getJSONObject(0).getJSONArray("biomes");
            JSONArray creeks = extras.getJSONObject(0).getJSONArray("creeks");
        }
    }
}