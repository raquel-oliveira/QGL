package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.qgl.qab.engine.Action;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.engine.ActionPlane;
import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.enums.Found;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class that represents the bot in the game.
 * Description based on documentation (http://ace-design.github.io/island/bot/)
 *
 * @version 4.8
 */
public class Explorer implements IExplorerRaid{
    private Action action;
    private Context contextIsland;
    private ActionBot takeAction;
    private Direction direction;

    public Explorer() {
        takeAction = null;
        action = new ActionPlane();
        direction =  null;
        contextIsland = new Context();
    }

    /**
     * Method to initiate the game. It is invoked right after the initialization.
     * @param context assignment (modeled as a JSON data structure) with the main information to initiate the game.
     */
    public void initialize(String context) {
        JSONObject jsonObj = new JSONObject(context);

        contextIsland.men = jsonObj.getInt("men");
        contextIsland.budget = jsonObj.getInt("budget");

        JSONArray cont = jsonObj.getJSONArray("contracts");

        for (int i = 0; i < cont.length();  i++) {
            String key = cont.getJSONObject(i).getString("resource");
            int value = cont.getJSONObject(i).getInt("amount");
            contextIsland.contracts.put(key, value);
        }

        contextIsland.heading = direction = Direction.fromString(jsonObj.getString("heading"));
    }

    /**
     * Method responsible for take decisions, invoked each time the bot must decide which action must be played.
     * @return for now, we always return the same action: stopping the game
     */
    public String takeDecision() {
        return action.makeDecision().toString();
    }

    /**
     * The acknowledgeResults(String) method is invoked right after the takeDecision() method.
     * It provides the results of the action when applied.
     * @param results information returned after as result of the engine action
     */
    public void acknowledgeResults(String results) {
        JSONObject jsonObj = new JSONObject(results);

        contextIsland.status = (jsonObj.getString("status").compareToIgnoreCase("ok") == 0)? true:false;
        contextIsland.budget = contextIsland.budget - jsonObj.getInt("cost");

        if (takeAction.equals(ActionBot.ECHO)) {
            Found found = null;
            Integer range = null;

            if (jsonObj.getJSONObject("extras").has("found"))
                found = Found.fromString(jsonObj.getJSONObject("extras").getString("found"));
            if (jsonObj.getJSONObject("extras").has("range"))
                range = jsonObj.getJSONObject("extras").getInt("range");

            /*if (found.equals(Found.GROUND))
                action.setGround(direction, range);
            else
                action.setOutOfRange(direction, range);*/
        }

        if(takeAction.equals(ActionBot.SCAN)) {
            ArrayList<String> biomes = new ArrayList<String>();
            ArrayList<String> creeks = new ArrayList<String>();
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
    public class Context {
        public int men, budget;
        private boolean status;
        private HashMap<String, Integer> contracts;
        private Direction heading;

        Context() {
            men = 0;
            budget = 0;
            status = false;
            contracts = new HashMap<String, Integer>();
            heading = null;
        }
    }
}