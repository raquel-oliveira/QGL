package fr.unice.polytech.qgl.qab.strategy.ground.states;


import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

import java.util.List;

/**
 * @version 07/02/16.
 *
 * State responsable by explore the specific tile of the ground.
 * In this state we can make the explore and exploit of the tile.
 */
public class ExploreTile extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    /**
     * ExploreTile's contructor
     */
    public ExploreTile() {
        contextAnalyzer = new ContextAnalyzer();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        context.current().setResourcesToExploit(contextAnalyzer.resourceAnalyzer(context), context);

        if (context.current().getResourcesToExploit().isEmpty()) {
            return new ScoutTile();
        } else {
            return new ExploitTile();
        }
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act;

        if (context.contractsAreComplete()){
            act = new Stop();
            context.current().setLastAction(act);
            return act;
        }

        act = new Explore();
        context.current().setLastAction(act);
        return act;
    }
}