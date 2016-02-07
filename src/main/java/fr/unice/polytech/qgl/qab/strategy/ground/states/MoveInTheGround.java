package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 07/02/16.
 */
public class MoveInTheGround extends GroundState {
    private static MoveInTheGround instance;
    private ContextAnalyzer contextAnalyzer;
    private List<Boolean> resources;
    private int indexTile;

    private MoveInTheGround() {
        super();
        this.lastAction = null;
        contextAnalyzer = new ContextAnalyzer();
        resources = new ArrayList<>();
        indexTile = 0;
    }

    public static MoveInTheGround getInstance() {
        if (instance == null)
            instance = new MoveInTheGround();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        resources = contextAnalyzer.biomeAnalyzer(context);
        if (!resources.isEmpty() && resources.get(indexTile))
            return ExploreTile.getInstance();
        else
            return MoveInTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (contextAnalyzer.shouldStop(context))
            return new Stop();

        if (lastAction == null) {
            act = new Glimpse(context.getHeading(), 4);
            lastAction = act;
            return act;
        }

        for (int i = indexTile + 1; i < resources.size(); i++) {
            if (resources.get(i)) {
                act = new MoveTo(context.getHeading());
                lastAction = act;
                indexTile++;
                return act;
            }
        }

        // if the program arive here, so, we need move, because thare are nothing until here
        act = new MoveTo(context.getHeading());
        indexTile = 0;
        lastAction = null;

        return act;
    }
}
