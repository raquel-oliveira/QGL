package fr.unice.polytech.qgl.qab.strategy.context.utils;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.ground.*;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Creek;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.response.*;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
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
 * Class responsible for handling the answers after make an current
 */
public class ResponseHandler {

    public static final String RESOURCES = "resources";
    private Discovery discovery;

    private static final String EXTRAS = "extras";
    private static final String FOUND = "found";
    private static final String RANGE = "range";
    private static final String BIOMES = "biomes";
    private static final String CREEKS = "creeks";
    private static final String AMOUNT = "amount";
    private static final String PRODUCTION = "production";
    private static final String KIND = "kind";


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
     * @param contextIsland context data of the action simulation
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
        } else if (takeAction instanceof Scan) {
            tempContext = readDataFromScan(contextIsland, jsonObj);
        } else if (takeAction instanceof Fly) {
            tempContext.getLastDiscovery().setUpEcho();
        } else if (takeAction instanceof Glimpse) {
            tempContext = readDataFromGlimpse(contextIsland, jsonObj);
        } else if (takeAction instanceof Explore) {
            tempContext = readDataFromExplore(contextIsland, jsonObj);
        } else if (takeAction instanceof Exploit){
            tempContext = readDataFromExploit(contextIsland, jsonObj, takeAction);
        } else if (takeAction instanceof Scout) {
            tempContext = readDataFromScout(contextIsland, jsonObj, takeAction);
        } else if (takeAction instanceof Transform){
            tempContext = readDataFromTransform(contextIsland, jsonObj);
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
            found = Found.valueOf(jsonObj.getJSONObject(EXTRAS).getString(FOUND));
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

        List<HashMap<Biomes, Double>> initialTiles = new ArrayList<>();
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
                initialTiles.add(tile);
            }

            JSONArray jarray = jsonArray.getJSONArray(2);
            for (int i = 0; i < jarray.length(); i++) {
                third.add(Biomes.valueOf(jarray.getString(i)));
            }

            fourth = Biomes.valueOf(jsonArray.getJSONArray(3).getString(0));
        }

        glimpseResponse.setInitialTiles(initialTiles);
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

        if(jsonObj.getJSONObject(EXTRAS).has(RESOURCES)) {
            JSONArray resources = jsonObj.getJSONObject(EXTRAS).getJSONArray(RESOURCES);
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
        if(jsonObj.getJSONObject(EXTRAS).has(PRODUCTION)){
            int amount = jsonObj.getJSONObject(EXTRAS).getInt(PRODUCTION);
            exploit.addData(((Exploit)takeAction).getResource(),amount);
            context.addCollectedResources(exploit.getResource(), exploit.getAmount());
        }

        discovery.setExploiteResponse(exploit);
        context.setLastDiscovery(discovery);
        return context;
    }

    /**
     * Method tha read response from scout
     * @param contextIsland context data of the current simulation
     * @param jsonObj json with the response
     * @return context updated
     */
    private Context readDataFromScout(Context contextIsland, JSONObject jsonObj, Action action) {
        ScoutResponse scout = new ScoutResponse();
        List<PrimaryType> res = new ArrayList<>();

        if (jsonObj.getJSONObject(EXTRAS).has(RESOURCES)) {
            JSONArray resources = jsonObj.getJSONObject(EXTRAS).getJSONArray(RESOURCES);
            res = new ArrayList<>();
            for (int i = 0; i < resources.length(); i++) {
                res.add(PrimaryType.valueOf(resources.getString(i)));
            }
        }

        scout.setDirection(action.getDirection());
        scout.setResources(res);

        discovery.setScoutResponse(scout);
        contextIsland.setLastDiscovery(discovery);

        return contextIsland;
    }

    private Context readDataFromTransform(Context context,  JSONObject jsonObj){
        List<ContractItem> contracts = context.getContracts();
        TransformResponse transform = new TransformResponse();

        if(jsonObj.getJSONObject(EXTRAS).has(PRODUCTION) && jsonObj.getJSONObject(EXTRAS).has(KIND)) {
            int amount = jsonObj.getJSONObject(EXTRAS).getInt(AMOUNT);
            String resource = jsonObj.getJSONObject(EXTRAS).getString(KIND);
            //Add Data
            transform.addData(new ManufacturedResource(ManufacturedType.fromString(resource)), amount);
            //Add the transformed resource in collected resources
            ManufacturedResource transformedResource = new ManufacturedResource(ManufacturedType.fromString(resource));
            context.addCollectedResources(transformedResource, amount);
            //Delete primary resources transformed (Update collected Resources)
            for(java.util.Map.Entry<PrimaryType, Integer> ingredientRecipe : ((ManufacturedResource) contracts.get(contracts.indexOf(resource)).resource()).getRecipe(amount).entrySet()) {
                PrimaryResource primary = new PrimaryResource(ingredientRecipe.getKey());
                context.decreaseAmountOfCollectedResources(primary, ingredientRecipe.getValue());
            }

        }

        discovery.setTransformResponse(transform);
        context.setLastDiscovery(discovery);
        return context;

    }
}
