package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 07/02/16.
 * Class responsible by analyze the context and return specific informations.
 */
public class ContextAnalyzer {

    /**
     * Check if the explorers are in the ocean (or next to)
     * @param context
     * @return
     */
    public boolean isOcean(Context context) {
        List<Biomes> thirdTile = new ArrayList<>();
        Biomes fourth = Biomes.ALPINE;

        if (context.getLastDiscovery().getGlimpseResponse().hasResponse()) {
            thirdTile = context.getLastDiscovery().getGlimpseResponse().getThirdTile();
            fourth = context.getLastDiscovery().getGlimpseResponse().getFourthTile();
        }

        return thirdTile.contains(Biomes.OCEAN) || fourth.equals(Biomes.OCEAN);
    }

    /**
     * Method that return a set of primary type resources that were
     * founded after explore a tile and are on the contract
     * @param context datas about the context of the simulation
     * @return list of resources founded
     */
    public List<PrimaryType> resourceAnalyzerScout(Context context) {
        List<ContractItem> contract = context.getContracts();
        List<PrimaryType> resources = new ArrayList<>();

        for (ContractItem item: contract) {
            if (item.resource().isPrimary() &&
                    context.getLastDiscovery().getScoutResponse().found(item.resource().getName()) &&
                    !resources.contains(PrimaryType.valueOf(item.resource().getName()))) {
                resources.add(PrimaryType.valueOf(item.resource().getName()));
            }
            else if (!item.resource().isPrimary()) {
                List<PrimaryType> tmp = new ArrayList<>();
                tmp.addAll(addResourcesScout(context, resources, item));
                resources.clear();
                resources.addAll(tmp);
            }
        }
        return resources;
    }

    private static List<PrimaryType> addResourcesScout(Context context, List<PrimaryType> resources, ContractItem item) {
        for (PrimaryType itemRecipe: ((ManufacturedResource) item.resource()).getRecipe(0).keySet()) {
            if(context.getLastDiscovery().getScoutResponse().found(itemRecipe.name()) &&
                    !resources.contains(itemRecipe)) {
                resources.add(itemRecipe);
            }
        }
        return resources;
    }

    /**
     * This method will check the resources in the contract and take the
     * biomes that are more likely to have them. After, it will see in each
     * tile what the glimpse returned, and will check if there are some of these
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
        List<HashMap<Biomes, Double>> initialTiles = gr.getInitialTiles();
        // if for each tile there is one or more good biome
        List<Boolean> goodTiles = new ArrayList<>();

        int indexTile = 0;
        boolean findGoodBiome = false;

        // two first tiles
        for (HashMap<Biomes, Double> tile: initialTiles) {
            for (Biomes key : tile.keySet()) {
                findGoodBiome = findGoodBiome(contract, key);
                if (findGoodBiome)
                    break;
            }
            goodTiles.add(indexTile, findGoodBiome);
            findGoodBiome = false;
            indexTile++;
        }

        List<Biomes> thirdTile = gr.getThirdTile();
        for (Biomes key : thirdTile) {
            findGoodBiome = findGoodBiome(contract, key);
            if (findGoodBiome)
                break;
        }
        goodTiles.add(indexTile, findGoodBiome);
        indexTile++;

        Biomes fourth = gr.getFourthTile();
        findGoodBiome = findGoodBiome(contract, fourth);
        goodTiles.add(indexTile, findGoodBiome);

        return goodTiles;
    }

    private static boolean findGoodBiome(List<ContractItem> contract, Biomes biome) {
        for (ContractItem item: contract) {
            if (item.resource().getBiome().contains(biome))
                return true;
        }
        return false;
    }

    public static Direction setDirectionHorizontal(Context context, Map map) {
        Direction d1;
        if (context.current().getNextPosition().getX() > map.getLastPositionGround().getX())
            d1 = Direction.EAST;
        else
            d1 = Direction.WEST;
        return d1;
    }

    public static Direction setDirectionVertical(Context context, Map map) {
        Direction dir;
        if (context.current().getNextPosition().getY() > map.getLastPositionGround().getY())
            dir = Direction.SOUTH;
        else
            dir = Direction.NORTH;
        return dir;
    }
}
