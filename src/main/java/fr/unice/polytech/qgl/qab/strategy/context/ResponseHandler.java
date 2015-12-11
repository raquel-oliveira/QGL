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

        if (takeAction instanceof Echo)
            contextIsland = readDataFromEcho(contextIsland, jsonObj);

        return contextIsland;
    }

    private static Context readDataFromEcho(Context context, JSONObject jsonObj) {
        Discovery discovery = new Discovery();
        if (jsonObj.getJSONObject("extras").has("found"))
            discovery.setFound(Found.fromString(jsonObj.getJSONObject("extras").getString("found")));
        if (jsonObj.getJSONObject("extras").has("range"))
            discovery.setRange(jsonObj.getJSONObject("extras").getInt("range"));

        context.setLastDiscovery(discovery);
        return context;
    }
}
