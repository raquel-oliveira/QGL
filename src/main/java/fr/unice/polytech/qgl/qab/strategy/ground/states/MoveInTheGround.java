package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Exploit;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.Resource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 31/12/15.
 */
public class MoveInTheGround extends GroundState {
    private static MoveInTheGround instance;
    private int indexTile;
    private List<Boolean> resources;
    private List<PrimaryType> resourcesAnalyzer;
    private ContextAnalyzer contextAnalyzer;

    private MoveInTheGround() {
        super();
        this.lastAction = null;
        indexTile = 0;
        resources = new ArrayList<>();
        resourcesAnalyzer = new ArrayList<>();
        contextAnalyzer = new ContextAnalyzer();
    }

    public static MoveInTheGround getInstance() {
        if (instance == null)
            instance = new MoveInTheGround();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        return MoveInTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (contextAnalyzer.shouldStop(context))
            return new Stop();

        if (indexTile == 4) {
            act = new Glimpse(context.getHeading(), 4);
            lastAction = act;
            indexTile = 0;
            return act;
        }

        if (lastAction == null) {
            act = new Glimpse(context.getHeading(), 4);
            lastAction = act;
            return act;
        } else if (lastAction instanceof Glimpse) {
            resources = contextAnalyzer.biomeAnalyzer(context);
            for (int i = 0; i < resources.size(); i++) {
                if (resources.get(i)) {
                    if (i == 0) {
                        act = new Explore();
                        lastAction = act;
                        return act;
                    } else {
                        act = new MoveTo(context.getHeading());
                        lastAction = act;
                        indexTile++;
                        return act;
                    }
                }
            }
            act = new MoveTo(context.getHeading());
            lastAction = act;
            indexTile++;
            return act;
        } else if (lastAction instanceof Explore) {
            resourcesAnalyzer = contextAnalyzer.resourceAnalyzer(context);
            if (!resources.isEmpty()) {
                Resource res = new PrimaryResource(resourcesAnalyzer.get(0));
                resourcesAnalyzer.remove(0);
                act = new Exploit(res);
                lastAction = act;
                return act;
            } else return new Stop();
        } else if (lastAction instanceof MoveTo) {
            if (resources.get(indexTile)) {
                act = new Explore();
                lastAction = act;
                return act;
            } else {
                act = new MoveTo(context.getHeading());
                lastAction = act;
                indexTile++;
                return act;
            }
        } else if (lastAction instanceof Exploit) {
            if (!resourcesAnalyzer.isEmpty()) {
                Resource res = new PrimaryResource(resourcesAnalyzer.get(0));
                resourcesAnalyzer.remove(0);
                act = new Exploit(res);
                lastAction = act;
                return act;
            }
            act = new MoveTo(context.getHeading());
            lastAction = act;
            indexTile++;
            return act;
        }
        return new Stop();
    }
}
