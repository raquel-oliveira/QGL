package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Scan;
import fr.unice.polytech.qgl.qab.strategy.Strategy;
import fr.unice.polytech.qgl.qab.util.Biome;
import fr.unice.polytech.qgl.qab.util.Creek;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 8.12.2016
 */
public class ResponseHandler {

    private Discovery discovery;

    public ResponseHandler() {
        discovery = new Discovery();
    }

    public Context readData(String data, Action takeAction, Context contextIsland) {
        JSONObject jsonObj = new JSONObject(data);

        contextIsland.setStatus((jsonObj.getString("status").compareToIgnoreCase("ok") == 0)? true:false);
        contextIsland.setBudget(contextIsland.getBudget() - jsonObj.getInt("cost"));

        if (takeAction instanceof Echo)
            contextIsland = readDataFromEcho(contextIsland, jsonObj);

        if (takeAction instanceof Scan)
            contextIsland = readDataFromScan(contextIsland, jsonObj);

        return contextIsland;
    }

    private Context readDataFromEcho(Context context, JSONObject jsonObj) {
        if (jsonObj.getJSONObject("extras").has("found"))
            discovery.setFound(Found.fromString(jsonObj.getJSONObject("extras").getString("found")));
        if (jsonObj.getJSONObject("extras").has("range"))
            discovery.setRange(jsonObj.getJSONObject("extras").getInt("range"));

        context.setLastDiscovery(discovery);
        return context;
    }

    private Context readDataFromScan(Context context, JSONObject jsonObj) {
        List<Biome> biomes = new ArrayList<>();
        List<Creek> creeks = new ArrayList<>();
        JSONArray bio = null;
        JSONArray crk = null;

        if(jsonObj.getJSONObject("extras").has("biomes"))
            bio = jsonObj.getJSONObject("extras").getJSONArray("biomes");
        if(jsonObj.getJSONObject("extras").has("creeks"))
            crk = jsonObj.getJSONObject("extras").getJSONArray("creeks");

        if(bio != null){
            for(Object b : bio)
                biomes.add(new Biome(b.toString()));
        }

        if(crk != null){
            for(Object c : crk)
                creeks.add(new Creek(Integer.parseInt(c.toString())));
        }

        discovery.setBiomes(biomes);
        discovery.setCreeks(creeks);

        return context;
    }
}
