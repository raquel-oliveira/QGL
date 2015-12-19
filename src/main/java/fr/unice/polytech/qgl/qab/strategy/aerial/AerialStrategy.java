package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.AerialState;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.Initialize;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.StateMediator;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;

/**
 * @version 9.12.2015
 */
public class AerialStrategy implements IAerialStrategy {
    private ResponseState response;
    private Map map;
    private AerialState state;
    private StateMediator stateMediator;

    public AerialStrategy() {
        map = new Map();
        state = Initialize.getInstance();
        stateMediator = StateMediator.getInstance();
    }

    public Action makeDecision(Context context) throws PositionOutOfMapaRange {
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        state =  state.getState(context, map, stateMediator);
        return state.responseState(context, map, stateMediator);
    }

    private Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return new Stop();
        }
        return null;
    }
}
