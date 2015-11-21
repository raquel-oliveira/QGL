package fr.unice.polytech.qgl.qab;

import org.json.JSONObject;
import org.json.JSONArray;

import java.util.HashMap;

import eu.ace_design.island.bot.IExplorerRaid;
/**
 * Class that represents the bot in the game.
 * Description based on documentation (http://ace-design.github.io/island/bot/)
 *
 * @version 13.11.2015
 */
public class Explorer implements IExplorerRaid{
    private int men, budget;
    private String heading;
    private HashMap<String, Integer> contracts;
    private String takeAction;

    public Explorer() {
        men = 0;
        budget = 0;
        heading = "";
        contracts = new HashMap<String, Integer>();
        takeAction = "{ \"action\": \"fly\" }";
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
        return takeAction;
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
            return;

        budget = budget - cost;

        if (budget > 15) {
            if (takeAction.contains("fly") == true)
                takeAction = "{ \"action\": \"echo\", \"parameters\": { \"direction\": \"" + heading + "\" } }";
            else takeAction = "{ \"action\": \"fly\" }";
        }
        else
            takeAction = "stop";
    }
}