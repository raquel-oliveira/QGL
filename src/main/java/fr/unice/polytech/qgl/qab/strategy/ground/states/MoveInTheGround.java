package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboMoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Exploit;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.response.GlimpseResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ContractItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 31/12/15.
 */
public class MoveInTheGround extends GroundState {
    private static MoveInTheGround instance;

    private ComboMoveTo actionCombo;

    private MoveInTheGround() {
        super();
        this.lastAction = null;
        actionCombo = null;
    }

    public static MoveInTheGround getInstance() {
        if (instance == null)
            instance = new MoveInTheGround();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map, StateManager stateManager) throws PositionOutOfMapRange {
        return MoveInTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateManager stateManager) throws IndexOutOfBoundsComboAction {
        Action act;

        if (lastAction == null) {
            act = new Glimpse(context.getHeading(), 4);
            lastAction = act;
            return act;
        } else if (lastAction instanceof Glimpse) {
            List<Boolean> resources = biomeAnalyzer(context);
            for (int i = 0; i < resources.size(); i++) {
                if (i == 0 && resources.get(i)) {
                    act = new Explore();
                    lastAction = act;
                    return act;
                }
            }
        } else if (lastAction instanceof Explore) {
            List<PrimaryType> resources = resourceAnalyzer(context);
            if (!resources.isEmpty()) {
                Resource res = new PrimaryResource(resources.get(0));
                act = new Exploit(res);
                lastAction = act;
                return act;
            } else return new Stop();
        }
        return new Stop();
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
        List<HashMap<Biomes, Double>> initial_tiles = gr.getInitial_tiles();
        // if for each tile there is one or more good biome
        List<Boolean> goodTiles = new ArrayList<>();

        int index_tile = 0;
        boolean find_good_biome = false;

        // two firts tiles
        for (HashMap<Biomes, Double> tile: initial_tiles) {
            for (Biomes key : tile.keySet()) {
                for (ContractItem item: contract) {
                    if (item.resource().getBiome().contains(key)) {
                        //goodTiles.add(index_tile, true);
                        find_good_biome = true;
                        break;
                    }
                } if (find_good_biome) break;
            }
            goodTiles.add(index_tile, find_good_biome);
            find_good_biome = false;
            index_tile++;
        }

        List<Biomes> third_tile = gr.getThird_tile();
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

        Biomes fourth = gr.getFourth_tile();
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
