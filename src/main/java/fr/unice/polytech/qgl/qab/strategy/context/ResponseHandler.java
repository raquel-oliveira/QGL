package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.json.JSONObject;

/**
 * @version 8.12.2016
 */
public class ResponseHandler {

    public ResponseHandler() {}

    public static Context readData(String data, Action takeAction, Context contextIsland) {
        JSONObject jsonObj = new JSONObject(data);

        contextIsland.setStatus((jsonObj.getString("status").compareToIgnoreCase("ok") == 0)? true:false);
        contextIsland.setBudget(contextIsland.getBudget() - jsonObj.getInt("cost"));

        if(takeAction instanceof Echo)
            readEcho(contextIsland, takeAction, jsonObj);

        return contextIsland;
    }

    private static void readEcho(Context context, Action takeAction, JSONObject jsonObj) {
        Discovery discovery = new Discovery();
        if (jsonObj.getJSONObject("extras").has("found"))
            discovery.setFound(Found.fromString(jsonObj.getJSONObject("extras").getString("found")));
        if (jsonObj.getJSONObject("extras").has("range"))
            discovery.setRange(jsonObj.getJSONObject("extras").getInt("range"));

        initializaSize(context, (Echo) takeAction, discovery);
    }

    // TODO: look after, maybe, we need change this and put in other class
    private static void initializaSize(Context context, Echo takeAction, Discovery discovery) {
        if (discovery.getFound().equals(Found.OUT_OF_RANGE)) {
            if (takeAction.getDirection().isVertical()) {
                if (context.getHeight() == 0)
                    context.setHeight(discovery.getRange());
                else
                    context.setHeight(discovery.getRange() + 1);
            } else {
                if (context.getWidth() == 0)
                    context.setWidth(discovery.getRange());
                else
                    context.setWidth(discovery.getRange() + 1);
            }
        }
    }
}
