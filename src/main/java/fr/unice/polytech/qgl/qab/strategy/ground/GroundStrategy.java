package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.ground.states.Exit;
import fr.unice.polytech.qgl.qab.strategy.ground.states.GroundState;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.states.MoveInTheGround;
import fr.unice.polytech.qgl.qab.strategy.ground.states.StateManager;

/**
 * @version 09/12/15.
 */
public class GroundStrategy implements IGroundStrategy {
    // object that represent the game map in the aerial space
    private Map map;
    private GroundState state;
    private StateManager stateManager;

    public GroundStrategy(){
        state = MoveInTheGround.getInstance();
        map = new Map();
        stateManager = StateManager.getInstance();
    }

    public Action makeDecision(Context context) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        state =  state.getState(context, map, stateManager);
        return state.responseState(context, map, stateManager);
    }

    private Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return new Stop();
        }
        return null;
    }
}
