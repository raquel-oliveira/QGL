package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ContractItem;

import java.util.*;

/**
 * @version 07/02/16.
 * Class reponsible by analyze the context and return specific informations.
 */
public class ContextAnalyzer {

    /**
     * Check if the explorers are in the ocean (or next)
     * @param context
     * @return
     */
    public boolean isOcean(Context context) {
        List<Biomes> thirdTile;
        Biomes fourth;

        thirdTile = context.getLastDiscovery().getGlimpseResponse().getThirdTile();
        fourth = context.getLastDiscovery().getGlimpseResponse().getFourthTile();

        return thirdTile.contains(Biomes.OCEAN) || fourth.equals(Biomes.OCEAN);
    }

    /**
     * Method that return a set of primary type resources that were
     * founded after explore a tile and are on the contract
     * @param context datas about the context of the simulation
     * @return list of resources founded
     */
    public Map<PrimaryType, Integer> resourceAnalyzer(Context context) {
        Set<ContractItem> contract = context.getContracts();
        Map<PrimaryType, Integer> resources = new HashMap<PrimaryType, Integer>();

        for (ContractItem item: contract) {
            if (context.getLastDiscovery().getExploreResponse().contains(item.resource())) {
                PrimaryType name = PrimaryType.valueOf(item.resource().getName());
                if(resources.containsKey(name)){
                    if(item.amount() < resources.get(name)){
                        resources.put(name, resources.get(name)+1);
                    }
                }
                else{
                    resources.put(name, 0);

                }
            }
        }


        return resources;
    }

    /**
     * This method will check the resources in the contract and take what are the
     * biomes that are likely to have these resources. After will see in each
     * tile that the glimpse returned, and will check if there are some these
     * biomes in any tile, after will return a list of boolean values indicating
     * if some tile have the biomes.
     * @param context datas about the context of the simulation
     * @return list of boolean values indicating if some tile have the biomes
     */
    public List<Boolean> biomeAnalyzer(Context context) {
        // the glimpse response
        GlimpseResponse gr = context.getLastDiscovery().getGlimpseResponse();
        // the contract info
        Set<ContractItem> contract = context.getContracts();

        // the initial tiles info
        List<HashMap<Biomes, Double>> initialTiles = gr.getInitialTiles();
        // if for each tile there is one or more good biome
        List<Boolean> goodTiles = new ArrayList<>();

        int indexTile = 0;
        boolean findGoodBiome = false;

        // two firts tiles
        for (HashMap<Biomes, Double> tile: initialTiles) {
            for (Biomes key : tile.keySet()) {
                for (ContractItem item: contract) {
                    if (item.resource().getBiome().contains(key)) {
                        findGoodBiome = true;
                        break;
                    }
                }
                if (findGoodBiome)
                    break;
            }
            goodTiles.add(indexTile, findGoodBiome);
            findGoodBiome = false;
            indexTile++;
        }

        List<Biomes> thirdTile = gr.getThirdTile();
        for (Biomes key : thirdTile) {
            for (ContractItem item: contract) {
                if (item.resource().getBiome().contains(key)) {
                    findGoodBiome = true;
                    break;
                }
            }
            if (findGoodBiome)
                break;
        }
        goodTiles.add(indexTile, findGoodBiome);
        findGoodBiome = false;
        indexTile++;

        Biomes fourth = gr.getFourthTile();
        for (ContractItem item: contract) {
            if (item.resource().getBiome().contains(fourth)) {
                findGoodBiome = true;
                break;
            }
        }
        goodTiles.add(indexTile, findGoodBiome);

        return goodTiles;
    }

    public boolean goodGlimpse(Context context) {
        List<Boolean> responseGlimpse = biomeAnalyzer(context);
        if (responseGlimpse.contains(true))
            return true;
        return false;
    }
}
