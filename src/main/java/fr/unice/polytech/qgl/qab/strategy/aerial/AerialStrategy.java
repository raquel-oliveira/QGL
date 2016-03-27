package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.AerialState;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.StateMediator;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateFactory;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.factory.AerialStateType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * Class that represents the aerial strategy.
 *
 * @version 9.12.2015
 */
public class AerialStrategy implements IAerialStrategy {
    private AerialState state;
    private StateMediator stateMediator;

    /**
     * Aerial Strategy constructor.
     */
    public AerialStrategy() {
        state = AerialStateFactory.buildState(AerialStateType.INITIALIZE);
        stateMediator = StateMediator.getInstance();
    }

    @Override
    public Action makeDecision(Context context, Map map) throws IndexOutOfBoundsComboAction, PositionOutOfMapRange {
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        state =  state.getState(context, map, stateMediator);
        return state.responseState(context, map, stateMediator);
    }

    /**
     * Methodo that make a analyze of the budget, and return stop if it's not enough
     * @param context data context of the simulation
     * @return the stop action if the budget is not enought or null if the simulation can continue
     */
    private static Action contextAnalyzer(Context context) {
        if (context.getBudget() < 400) {
            return new Stop();
        }
        return null;
    }
}
