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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @version 07/02/16.
 *
 * State responsable by explore the specific tile of the ground.
 * In this state we can make the explore and exploit of the tile.
 */
public class ExploreTile extends GroundState {
    private static final Logger LOGGER = LogManager.getLogger(ExploreTile.class);
    private static ExploreTile instance;
    private ContextAnalyzer contextAnalyzer;
    private List<PrimaryType> resourcesAnalyzer;
    //private java.util.Map<Resource, Integer> resourcesAnalyzer;

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

        if(context.contractsAreComplete()){
            act = new Stop();
            lastAction = act;
            return act;
        }

        if (lastAction == null) {
            act = new Explore();
            lastAction = act;
            return act;
        }

        if (lastAction instanceof Explore)
            resourcesAnalyzer = contextAnalyzer.resourceAnalyzer(context);

        if (resourcesAnalyzer != null && !resourcesAnalyzer.isEmpty()) {
            java.util.Map<String, Integer> collectedResource = context.getCollectedResources();
            PrimaryResource res = new PrimaryResource(resourcesAnalyzer.get(0));
            String resource = res.getName();
            if(!collectedResource.containsKey(resource)){
                act = new Exploit(res);
                resourcesAnalyzer.remove(0);
                lastAction = act;
                return act;
            }
            else if(collectedResource.get(resource) < context.getAcumulatedAmount(res)) {
                act = new Exploit(res);
                resourcesAnalyzer.remove(0);
                lastAction = act;
                return act;
            }
            else{
                resourcesAnalyzer.remove(0);
            }
        }
        //context.setHeading(Direction.randomSideDirection(context.getHeading()));
        return new MoveTo(context.getHeading());

    }
}
