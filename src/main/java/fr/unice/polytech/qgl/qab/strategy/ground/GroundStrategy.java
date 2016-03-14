package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Transform;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;
import fr.unice.polytech.qgl.qab.strategy.ground.states.GroundState;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.states.FindTile;

/**
 * @version 09/12/15.
 * Class that implements the strategy of the ground phase
 */
public class GroundStrategy implements IGroundStrategy {
    private GroundState state;
    private int limitBudget;
    private final int MIN_NB_BUDGET_TO_TRANSFORME = 100;

    /**
     * GroundStrategy's constructor.
     */
    public GroundStrategy() {
        state = GroundStateFactory.buildState(GroundStateType.FINDTILE);
        limitBudget = 400;
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
     * Take the value of budgets needed before call the action stop.
     */
    public int getLimitBudget(){
        return limitBudget;
    }


    /**
     * Method that checks the context to know if it is the moment to stop or to transform.
     * @param context datas about the simulation context
     * @return stop if the budget is less than 100 and null if the simulation can continue
     */
    private Action contextAnalyzer(Context context) {
        //If all contracts are filled or there is with low quantity of budgets
        if(context.contractsAreComplete() || context.getBudget() < getLimitBudget()){
            return new Stop();
        }
        //TODO: Go to state of TransformeResource, but returns a action
        if(context.getBudget() < getLimitBudget() + MIN_NB_BUDGET_TO_TRANSFORME){

        }

        return null;
    }

}
