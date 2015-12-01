package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Found;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by gabriela on 01/12/15.
 */
public class DataResults {

    public DataResults() {}

    public Context readData(String data, ActionBot takeAction, Context contextIsland) {
        JSONObject jsonObj = new JSONObject(data);

        contextIsland.setStatus((jsonObj.getString("status").compareToIgnoreCase("ok") == 0)? true:false);
        contextIsland.setBudget(contextIsland.getBudget() - jsonObj.getInt("cost"));

        if (takeAction.equals(ActionBot.ECHO)) {
            Found found = null;
            Integer range = null;

            if (jsonObj.getJSONObject("extras").has("found"))
                found = Found.fromString(jsonObj.getJSONObject("extras").getString("found"));
            if (jsonObj.getJSONObject("extras").has("range"))
                range = jsonObj.getJSONObject("extras").getInt("range");

            /*if (found.equals(Found.GROUND))
                aPlane.setGround(directionPlane, range);
            else
                aPlane.setOutOfRange(directionPlane, range);*/
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
        return contextIsland;
    }
}
