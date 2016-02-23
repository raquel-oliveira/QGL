package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Exploit;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.response.*;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 8/12/16
 *
 * Class responsible for handling the answers after make an action
 */
public class ResponseHandler {

    private Discovery discovery;

    private static final String EXTRAS = "extras";
    private static final String FOUND = "found";
    private static final String RANGE = "range";
    private static final String BIOMES = "biomes";
    private static final String CREEKS = "creeks";
    private static final String AMOUNT = "amount";


    /**
     * ResponseHandler's constructor
     */
    public ResponseHandler() {
        discovery = new Discovery();
    }

    /**
     * Method that read the response data gave a string json and update the context.
     * @param data string json with the response
     * @param takeAction the action taken for a given response
     * @param contextIsland context data of the current simulation
     * @return context updated
     * @throws NegativeBudgetException
     */
    public Context readData(String data, Action takeAction, Context contextIsland) throws NegativeBudgetException {
        Context tempContext = contextIsland;
        JSONObject jsonObj = new JSONObject(data);

        tempContext.setStatus(jsonObj.getString("status").compareToIgnoreCase("ok") == 0);
        tempContext.setBudget(contextIsland.getBudget() - jsonObj.getInt("cost"));

        if (takeAction instanceof Echo) {
            tempContext = readDataFromEcho(contextIsland, jsonObj, takeAction);
            tempContext.getLastDiscovery().setDirection((takeAction).getDirection());
        } else if (takeAction instanceof Scan) {
            tempContext = readDataFromScan(contextIsland, jsonObj);
        } else if (takeAction instanceof Fly) {
            tempContext.getLastDiscovery().setUp();
        } else if (takeAction instanceof Glimpse) {
            tempContext = readDataFromGlimpse(contextIsland, jsonObj);
        } else if (takeAction instanceof Explore) {
            tempContext = readDataFromExplore(contextIsland, jsonObj);
        } else if (takeAction instanceof Exploit){
            tempContext = readDataFromExploit(contextIsland, jsonObj, takeAction);
        }
        return tempContext;
    }

    /**
     * Method tha read response from echo
     * @param context context data of the current simulation
     * @param jsonObj json with the response
     * @param takeAction
     * @return context updated
     */
    private Context readDataFromEcho(Context context, JSONObject jsonObj, Action takeAction) {
        EchoResponse echoResponse = new EchoResponse();
        Found found = null;
        int range = 0;

        if (jsonObj.getJSONObject(EXTRAS).has(FOUND))
            found = Found.fromString(jsonObj.getJSONObject(EXTRAS).getString(FOUND));
        if (jsonObj.getJSONObject(EXTRAS).has(RANGE))
            range = jsonObj.getJSONObject(EXTRAS).getInt(RANGE);

        echoResponse.addData(found, takeAction.getDirection(), range);
        discovery.setEchoResponse(echoResponse);

        context.setLastDiscovery(discovery);
        return context;
    }

    /**
     * Method tha read response from scan
     * @param context context data of the current simulation
     * @param jsonObj json with the response
     * @return context updated
     */
    private Context readDataFromScan(Context context, JSONObject jsonObj) {
        ScanResponse scanResponse = new ScanResponse();
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

        scanResponse.setBiomes(biomes);
        discovery.setScanResponse(scanResponse);
        discovery.setCreeks(creeks);
        context.setLastDiscovery(discovery);

        return context;
    }

    /**
     * Method tha read response from glimpse
     * @param context context data of the current simulation
     * @param jsonObj json with the response
     * @return context updated
     */
    private Context readDataFromGlimpse(Context context, JSONObject jsonObj) {
        GlimpseResponse glimpseResponse = new GlimpseResponse();

        if(jsonObj.getJSONObject(EXTRAS).has("asked_range")) {
            int range = jsonObj.getJSONObject(EXTRAS).getInt("asked_range");
            glimpseResponse.setAskedRange(range);
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

        glimpseResponse.setInitialTiles(initial_tiles);
        glimpseResponse.setThirdTile(third);
        glimpseResponse.setFourthTile(fourth);

        discovery.setGlimpseResponse(glimpseResponse);
        context.setLastDiscovery(discovery);

        return context;
    }

    /**
     * Method tha read response from explore
     * @param contextIsland context data of the current simulation
     * @param jsonObj json with the response
     * @return context updated
     */
    private Context readDataFromExplore(Context contextIsland, JSONObject jsonObj) {
        ExploreResponse explore = new ExploreResponse();

        if(jsonObj.getJSONObject(EXTRAS).has("resources")) {
            JSONArray resources = jsonObj.getJSONObject(EXTRAS).getJSONArray("resources");
            for (int i = 0; i < resources.length(); i++) {
                List<String> res = new ArrayList<>();
                JSONObject obj = resources.getJSONObject(i);
                res.add(obj.getString(AMOUNT));
                res.add(obj.getString("resource"));
                res.add(obj.getString("cond"));
                explore.addResource(res);
            }
        }

        discovery.setExploreResponse(explore);
        contextIsland.setLastDiscovery(discovery);

        return contextIsland;
    }


    private Context readDataFromExploit(Context context, JSONObject jsonObj, Action takeAction){
        ExploitResponse exploit = new ExploitResponse();
        if(jsonObj.getJSONObject(EXTRAS).has(AMOUNT)){
            int amount = jsonObj.getJSONObject(EXTRAS).getInt(AMOUNT);
            exploit.addData(((Exploit)takeAction).getResource(),amount);
        }

        discovery.setExploiteResponse(exploit);
        context.setLastDiscovery(discovery);
        return context;

    }
}