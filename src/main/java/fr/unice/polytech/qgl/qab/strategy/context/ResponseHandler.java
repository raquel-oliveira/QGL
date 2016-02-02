package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 8/12/16
 */
public class ResponseHandler {

    private Discovery discovery;

    private static final String EXTRAS = "extras";
    private static final String FOUND = "found";
    private static final String RANGE = "range";
    private static final String BIOMES = "biomes";
    private static final String CREEKS = "creeks";

    public ResponseHandler() {
        discovery = new Discovery();
    }

    public Context readData(String data, Action takeAction, Context contextIsland) throws NegativeBudgetException {
        Context tempContext = contextIsland;
        JSONObject jsonObj = new JSONObject(data);

        tempContext.setStatus(jsonObj.getString("status").compareToIgnoreCase("ok") == 0);
        tempContext.setBudget(contextIsland.getBudget() - jsonObj.getInt("cost"));

        if (takeAction instanceof Echo) {
            tempContext = readDataFromEcho(tempContext, jsonObj);
            tempContext.getLastDiscovery().setDirection(((Echo) takeAction).getDirection());
        } else if (takeAction instanceof Scan) {
            tempContext = readDataFromScan(tempContext, jsonObj);
        } else if (takeAction instanceof Fly) {
            tempContext.getLastDiscovery().setUp();
        }

        return tempContext;
    }

    private Context readDataFromEcho(Context context, JSONObject jsonObj) {
        if (jsonObj.getJSONObject(EXTRAS).has(FOUND))
            discovery.setFound(Found.fromString(jsonObj.getJSONObject(EXTRAS).getString(FOUND)));
        if (jsonObj.getJSONObject(EXTRAS).has(RANGE))
            discovery.setRange(jsonObj.getJSONObject(EXTRAS).getInt(RANGE));

        context.setLastDiscovery(discovery);
        return context;
    }

    private Context readDataFromScan(Context context, JSONObject jsonObj) {
        List<Biomes> biomes = new ArrayList<>();
        List<Creek> creeks = new ArrayList<>();
        JSONArray bio = null;
        JSONArray crk = null;

        if(jsonObj.getJSONObject(EXTRAS).has(BIOMES))
            bio = jsonObj.getJSONObject(EXTRAS).getJSONArray(BIOMES);
        if(jsonObj.getJSONObject(EXTRAS).has(CREEKS))
            crk = jsonObj.getJSONObject(EXTRAS).getJSONArray(CREEKS);

        if(bio != null){
            for(Object b : bio)
                biomes.add(Biomes.valueOf((String) b));
        }

        if(crk != null){
            for(Object c : crk)
                creeks.add(new Creek(c.toString()));
        }

        discovery.setBiomes(biomes);
        discovery.setCreeks(creeks);
        context.setLastDiscovery(discovery);

        return context;
    }
}
