package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * Created by gabriela on 09/12/15.
 */
public class GroundStrategy implements IGroundStrategy {
    // object responsible for choice the best action to the plane
    private Action aplane;
    // object that represent the game map in the aerial space
    private Map map;

    public Action makeDecision(Context context){
        return null;
    }
}