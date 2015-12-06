package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Found;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Class responsible for read and
 */
public class DataResults {

    public DataResults() {}

    public static Context readData(String data, Action takeAction, Context contextIsland) {
        JSONObject jsonObj = new JSONObject(data);

        contextIsland.setStatus((jsonObj.getString("status").compareToIgnoreCase("ok") == 0)? true:false);
        contextIsland.setBudget(contextIsland.getBudget() - jsonObj.getInt("cost"));

        if(takeAction.equals(ActionBot.ECHO))
            readEcho(contextIsland, takeAction, jsonObj);
        else if (takeAction.equals(ActionBot.SCAN)) {
            readScan(jsonObj);
        }
        return contextIsland;
    }

    private static void readScan(JSONObject jsonObj) {
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

    private static void readEcho(Context context, Action takeAction, JSONObject jsonObj) {
        Found found = null;
        Integer range = null;
        if (jsonObj.getJSONObject("extras").has("found"))
            found = Found.fromString(jsonObj.getJSONObject("extras").getString("found"));
        if (jsonObj.getJSONObject("extras").has("range"))
            range = jsonObj.getJSONObject("extras").getInt("range");

        initializaSize(context, (Echo) takeAction, found, range);
    }

    private static void initializaSize(Context context, Echo takeAction, Found found, Integer range) {
        if (found.compareTo(Found.OUT_OF_RANGE) == 0 &&
                context.getHeading().compareTo(takeAction.getDirection()) == 0) {
            if (context.getHeading().isVertical()) {
                context.setHeight(range + 1);
            } else {
                context.setWidth(range + 1);
            }
        } else {
            if (context.getHeading().isVertical())
                context.setWidth(context.getWidth() + range + 1);
            else context.setHeight(context.getHeight() + range + 1);
        }
    }
}
