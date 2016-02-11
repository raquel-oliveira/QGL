package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ContractItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 07/02/16.
 */
public class ContextAnalyzer {
    public boolean shouldChangeDirection(Context context) {
        if (!context.getLastDiscovery().getGlimpseResponse().getInitialTiles().isEmpty()) {
            HashMap<Biomes, Double> initial_tiles1 = context.getLastDiscovery().getGlimpseResponse().getInitialTiles().get(0);
            HashMap<Biomes, Double> initial_tiles2 = context.getLastDiscovery().getGlimpseResponse().getInitialTiles().get(1);

            if (initial_tiles1.containsKey(Biomes.valueOf("OCEAN")) || initial_tiles2.containsKey(Biomes.valueOf("OCEAN"))) {
                double value1 = 0, value2 = 0;
                if (initial_tiles1.containsKey(Biomes.valueOf("OCEAN"))) {
                    value1 = initial_tiles1.get(Biomes.valueOf("OCEAN"));
                } else if (initial_tiles1.containsKey(Biomes.valueOf("OCEAN"))) {
                    value2 = initial_tiles2.get(Biomes.valueOf("OCEAN"));
                }
                if (value1 < 70)
                    return true;
                else if (value1 <= 70 && value2 <= 100)
                    return true;
                else if (value2 > 70)
                    return true;
            }
        }
        return false;
    }

    public boolean shouldChangeStop(Context context) {
        if (!context.getLastDiscovery().getGlimpseResponse().getInitialTiles().isEmpty()) {
            HashMap<Biomes, Double> initial_tiles = context.getLastDiscovery().getGlimpseResponse().getInitialTiles().get(0);
            if (initial_tiles.containsKey(Biomes.valueOf("OCEAN"))) {
                if (initial_tiles.get(Biomes.valueOf("OCEAN")) >= 90)
                    return true;
            }
        }
        return false;
    }

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
                } if (find_good_biome) break;
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
            } if (find_good_biome) break;
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
}
