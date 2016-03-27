package fr.unice.polytech.qgl.qab.strategy.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;
import fr.unice.polytech.qgl.qab.strategy.ground.states.GroundState;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @version 09/12/15.
 * Class that implements the strategy of the ground phase
 */
public class GroundStrategy implements IGroundStrategy {
    private static final Logger LOGGER = LogManager.getLogger(GroundStrategy.class);

    private GroundState state;
    private static final int BUDGET_MIN = 100;
    private static final int STOP = 1;
    private static final int TRANSFORME = 2;


    /**
     * GroundStrategy's constructor.
     */
    public GroundStrategy() {
        state = GroundStateFactory.buildState(GroundStateType.FIND_TILE);
    }

    @Override
    public Action makeDecision(Context context, Map map) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        int statusContext = contextAnalyzer(context);
        switch (statusContext) {
            case STOP:
                return new Stop();
            case TRANSFORME:
                state = GroundStateFactory.buildState(GroundStateType.TRANSFORM);
                return state.responseState(context, map);
            default:
                state = state.getState(context, map);
                return state.responseState(context, map);
        }
    }

    /**
     * Take the value of budgets needed before call the action stop.
     */
    public int getLimitBudget(){
        return BUDGET_MIN;
    }


    /**
     * Method that checks the context to know if it is the moment to stop or to transform.
     * @param context datas about the simulation context
     * @return 1 if the action its to Stop
     * @return 2 if the state to do its to transform
     * @return 0 if it can continue.
     */
    private int contextAnalyzer(Context context) {
        // If all contracts are filled or there is with low quantity of budgets
        if (context.getContracts().contractsAreComplete() || context.getBudget() < getLimitBudget()){
            LOGGER.info("Should stop, low budget.");
            return STOP;
        }

        // Make transform if it's possible to transform all manufactured resources that wasn't transformed.
        if(context.getContracts().enoughToTransformAll()){
            LOGGER.info("Can transform all: " +  context.getContracts().enoughToTransformAll());
            return TRANSFORME;
        }

        return 0;
    }
}