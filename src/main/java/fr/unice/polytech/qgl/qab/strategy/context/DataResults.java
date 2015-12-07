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

        if(takeAction instanceof Echo)
            readEcho(contextIsland, takeAction, jsonObj);

        return contextIsland;
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
        if (found.equals(Found.OUT_OF_RANGE)) {
            if (takeAction.getDirection().isVertical()) {
                context.setHeight(range + 1);
            } else {
                context.setWidth(range + 1);
            }
        } else {
            if (takeAction.getDirection().isVertical()) {
                context.setWidth(range + 1);
            } else {
                context.setHeight(range + 1);
            }
        }
    }
}
