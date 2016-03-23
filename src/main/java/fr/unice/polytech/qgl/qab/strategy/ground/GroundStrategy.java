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
import org.apache.commons.logging.Log;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @version 09/12/15.
 * Class that implements the strategy of the ground phase
 */
public class GroundStrategy implements IGroundStrategy {
    private static final Logger LOGGER = LogManager.getLogger(GroundStrategy.class);

    private GroundState state;
    private int limitBudget;
    private static final int BUDGET_TO_TRANSFORME = 100;

    /**
     * GroundStrategy's constructor.
     */
    public GroundStrategy() {
        state = GroundStateFactory.buildState(GroundStateType.FIND_TILE);
        limitBudget = 400;
    }

    @Override
    public Action makeDecision(Context context, Map map) throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        int statusContext = contextAnalyzer(context);
        switch (statusContext) {
            case 1:
                return new Stop();
            case 2:
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
        return limitBudget;
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
        if (context.contractsAreComplete() || context.getBudget() < getLimitBudget() || transformFinished(context)){
            LOGGER.error("Should stop, low budget.");
            return 1;
        }

        /* Make transform if:
        * If there are enough (primary) resources to make transform or
        * if all primary resources were not completed to make the manufactured, but will try to transform the maximum
        * before stops.
        * */
        if(!(context.getResourcesToCreate() == null)){
            //Enough to transform all the manufactured without take resources rewerved to contract of type Primary
            if(context.enoughToTransformAll()){
                return 2;
            }
            //Time to transform
            if(context.getBudget() <= getLimitBudget() + BUDGET_TO_TRANSFORME){
                LOGGER.info("Money says: TIME TO TRANSFORM");
                //But if there is not a single contract to fill, continue
                if(!context.enoughToTransform()){
                    LOGGER.info("Can not transform even a single contract, come back what it was doing");
                    return 0;
                }
                else{
                    LOGGER.info("Can transform at least one");
                    return 2;
                }
            }
        }
        return 0;
    }

    private boolean transformFinished(Context context) {
        return (context.getResourcesToCreate() != null && context.getResourcesToCreate().isEmpty());
    }
}
