package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.AerialState;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.Initialize;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.StateMediator;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 9.12.2015
 */
public class AerialStrategy implements IAerialStrategy {
    private Map map;
    private AerialState state;
    private StateMediator stateMediator;

    public AerialStrategy(Context context) {
        map = new Map();
        state = Initialize.getInstance(context);
        stateMediator = StateMediator.getInstance();
    }

    @Override
    public Action makeDecision(Context context) throws IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        state =  state.getState(context, map, stateMediator);
        return state.responseState(context, map, stateMediator);
    }

    private static Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return new Stop();
        }
        return null;
    }
}
