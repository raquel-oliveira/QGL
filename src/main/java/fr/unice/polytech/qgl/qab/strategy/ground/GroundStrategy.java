package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.ground.states.MoveInTheGround;
import fr.unice.polytech.qgl.qab.strategy.ground.states.GroundState;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 09/12/15.
 */
public class GroundStrategy implements IGroundStrategy {
    // object that represent the game map in the aerial space
    private Map map;
    private GroundState state;

    public GroundStrategy(){
        state = MoveInTheGround.getInstance();
        map = new Map();
    }

    @Override
    public Action makeDecision(Context context) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        state =  state.getState(context, map);
        return state.responseState(context, map);
    }

    private Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return new Stop();
        }
        return null;
    }
}
