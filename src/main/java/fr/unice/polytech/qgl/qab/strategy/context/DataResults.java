package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.json.JSONObject;

/**
 * @version 8.12.2016
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
    // TODO: look after, maybe, we need change this and put in other class
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
