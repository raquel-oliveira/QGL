package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ContractItem;
import org.apache.xpath.operations.Bool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 07/02/16.
 * Class reponsible by analyze the context and return specific informations.
 */
public class ContextAnalyzer {

    public boolean isOcean(Context context) {
        HashMap<Biomes, Double> initialTiles1, initialTiles2;
        List<Biomes> thirdTile;
        Biomes fourth;

        initialTiles1 = context.getLastDiscovery().getGlimpseResponse().getInitialTiles().get(0);
        initialTiles2 = context.getLastDiscovery().getGlimpseResponse().getInitialTiles().get(1);
        thirdTile = context.getLastDiscovery().getGlimpseResponse().getThirdTile();
        fourth = context.getLastDiscovery().getGlimpseResponse().getFourthTile();

        return (thirdTile.contains(Biomes.OCEAN) || fourth.equals(Biomes.OCEAN));
    }

    /**$
     * Analize if the bot should stop.
     * This method will see if it's necessary stop the simulation after recive
     * the response of the glimpse action
     * @param context datas about the context of the simulation
     * @return true if necessary, and false if not
     */
    public boolean shouldChangeStop(Context context) {
        if (!context.getLastDiscovery().getGlimpseResponse().getInitialTiles().isEmpty()) {
            HashMap<Biomes, Double> initial_tiles = context.getLastDiscovery().getGlimpseResponse().getInitialTiles().get(0);
            if (initial_tiles.containsKey(Biomes.OCEAN) && initial_tiles.get(Biomes.OCEAN) >= 90)
                    return true;
        }
        return false;
    }

    /**
     * Method that return a set of primary type resources that were
     * founded after explore a tile and are on the contract
     * @param context datas about the context of the simulation
     * @return list of resources founded
     */
    public List<PrimaryType> resourceAnalyzer(Context context) {
        List<ContractItem> contract = context.getContracts();
        List<PrimaryType> resources = new ArrayList<>();

        for (ContractItem item: contract) {
            if (context.getLastDiscovery().getExploreResponse().contains(item.resource())) {
                resources.add(PrimaryType.valueOf(item.resource().getName()));
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
        List<ContractItem> contract = context.getContracts();

        // the initial tiles info
        List<HashMap<Biomes, Double>> initial_tiles = gr.getInitialTiles();
        // if for each tile there is one or more good biome
        List<Boolean> goodTiles = new ArrayList<>();

        int index_tile = 0;
        boolean find_good_biome = false;

        // two firts tiles
        for (HashMap<Biomes, Double> tile: initial_tiles) {
            for (Biomes key : tile.keySet()) {
                for (ContractItem item: contract) {
                    if (item.resource().getBiome().contains(key)) {
                        find_good_biome = true;
                        break;
                    }
                } if (find_good_biome)
                    break;
            }
            goodTiles.add(index_tile, find_good_biome);
            find_good_biome = false;
            index_tile++;
        }

        List<Biomes> third_tile = gr.getThirdTile();
        for (Biomes key : third_tile) {
            for (ContractItem item: contract) {
                if (item.resource().getBiome().contains(key)) {
                    find_good_biome = true;
                    break;
                }
            } if (find_good_biome)
                break;
        }
        goodTiles.add(index_tile, find_good_biome);
        find_good_biome = false;
        index_tile++;

        Biomes fourth = gr.getFourthTile();
        for (ContractItem item: contract) {
            if (item.resource().getBiome().contains(fourth)) {
                find_good_biome = true;
                break;
            }
        }
        goodTiles.add(index_tile, find_good_biome);

        return goodTiles;
    }

    public boolean goodGlimpse(Context context) {
        List<Boolean> responseGlimpse = biomeAnalyzer(context);
        if (responseGlimpse.contains(true))
            return true;
        return false;
    }
}
