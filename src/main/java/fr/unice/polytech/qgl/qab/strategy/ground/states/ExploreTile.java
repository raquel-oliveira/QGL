package fr.unice.polytech.qgl.qab.strategy.ground.states;


import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Exploit;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.List;

/**
 * @version 07/02/16.
 *
 * State responsable by explore the specific tile of the ground.
 * In this state we can make the explore and exploit of the tile.
 */
public class ExploreTile extends GroundState {
    private static ExploreTile instance;
    private ContextAnalyzer contextAnalyzer;
    private List<PrimaryType> resourcesAnalyzer;

    /**
     * ExploreTile's contructor
     */
    private ExploreTile() {
        super();
        this.lastAction = null;
        contextAnalyzer = new ContextAnalyzer();
        resourcesAnalyzer = null;
    }

    /**
     * Method to get the instance of the ExploreTile class
     * @return instance of ExploreTile
     */
    public static ExploreTile getInstance() {
        if (instance == null)
            instance = new ExploreTile();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (resourcesAnalyzer != null && resourcesAnalyzer.isEmpty()) {
            lastAction = null;
            resourcesAnalyzer = null;
            return MoveInTheGround.getInstance();
        } else
            return ExploreTile.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;
        if (lastAction == null) {
            act = new Explore();
            lastAction = act;
            return act;
        }

        if (lastAction instanceof Explore)
            resourcesAnalyzer = contextAnalyzer.resourceAnalyzer(context);

        if (resourcesAnalyzer != null && !resourcesAnalyzer.isEmpty()) {
            Resource res = new PrimaryResource(resourcesAnalyzer.get(0));
            resourcesAnalyzer.remove(0);
            act = new Exploit(res);
            lastAction = act;
            return act;
        }

        return new MoveTo(Direction.randomSideDirection(context.getHeading()));
    }
}
