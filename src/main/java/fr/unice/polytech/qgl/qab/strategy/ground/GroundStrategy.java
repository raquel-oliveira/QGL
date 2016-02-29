package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.ground.states.GlimpseTheGround;
import fr.unice.polytech.qgl.qab.strategy.ground.states.GroundState;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.states.Initialize;

/**
 * @version 09/12/15.
 * Class that implements the strategy of the ground phase
 */
public class GroundStrategy implements IGroundStrategy {
    private GroundState state;

    /**
     * GroundStrategy's constructor.
     */
    public GroundStrategy() {
        state = new Initialize();
    }

    @Override
    public Action makeDecision(Context context, Map map) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        state = state.getState(context, map);
        return state.responseState(context, map);

    }

    /**
     * Method that ckecks if the budget is less than 100
     * @param context datas about the simulation context
     * @return stop if the budget is less than 100 and null if the simulation can continue
     */
    private Action contextAnalyzer(Context context) {
        if (context.getBudget() < 400) {
            return new Stop();
        }
        return null;
    }
}
