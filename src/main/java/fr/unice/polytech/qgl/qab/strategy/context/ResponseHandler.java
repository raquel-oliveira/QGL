package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import fr.unice.polytech.qgl.qab.util.enums.TypeBiome;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
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
        } else if (takeAction instanceof Glimpse) {
            tempContext = readDataFromGlimpse(contextIsland, jsonObj);
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

    private Context readDataFromGlimpse(Context context, JSONObject jsonObj) {
        GlimpseResponse glimpseResponse = new GlimpseResponse();

        if(jsonObj.getJSONObject(EXTRAS).has("asked_range")) {
            int range = jsonObj.getJSONObject(EXTRAS).getInt("asked_range");
            glimpseResponse.setAsked_range(range);
        }

        JSONArray jsonArray;

        List<HashMap<Biomes, Double>> initial_tiles = new ArrayList<>();
        List<Biomes> third = new ArrayList<>();
        Biomes fourth = null;

        if(jsonObj.getJSONObject(EXTRAS).has("report")) {
            jsonArray = jsonObj.getJSONObject(EXTRAS).getJSONArray("report");
            for (int j = 0; j < 2; j++) {
                HashMap<Biomes, Double> tile = new HashMap<>();
                JSONArray jarray = jsonArray.getJSONArray(j);
                for (int i = 0; i < jarray.length(); i++) {
                    JSONArray item = jarray.getJSONArray(i);
                    String bio = item.getString(0);
                    double percentage = item.getDouble(1);
                    tile.put(Biomes.valueOf(bio), percentage);
                }
                initial_tiles.add(tile);
            }

            JSONArray jarray = jsonArray.getJSONArray(2);
            for (int i = 0; i < jarray.length(); i++) {
                third.add(Biomes.valueOf(jarray.getString(i)));
            }

            fourth = Biomes.valueOf(jsonArray.getJSONArray(3).getString(0));
        }

        glimpseResponse.setInitial_tiles(initial_tiles);
        glimpseResponse.setThird_tile(third);
        glimpseResponse.setFourth_tile(fourth);

        discovery.setGlimpseResponse(glimpseResponse);
        context.setLastDiscovery(discovery);

        return context;
    }
}
