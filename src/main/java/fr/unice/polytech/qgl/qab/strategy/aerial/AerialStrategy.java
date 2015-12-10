package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State0;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;

/**
 * @version 9.12.2015
 */
public class AerialStrategy implements IAerialStrategy {
    private ResponseState response;
    // object that represent the game map in the aerial space
    private Map map;
    private State0 state0;
    private boolean state0out;

    public AerialStrategy() {
        map = new Map();
        state0 = new State0();
        state0out = true;
    }

    public Action makeDecision(Context context){
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        while (state0out) {
            response = state0.responseState(context);
            if (response.getStatus()) {
                state0out = false;
            }
            return response.getAction();
        }

        updateMap(context);

        return new Stop();
    }

    public Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return (new Stop());
        }
        return null;
    }

    /**
     * Method to update the map after the object receive the engine response.
     * TODO: Maybe, this method dont shoud be here
     * Create class responsible to change the map
     */
    public void updateMap(Context context) {
        if (context.getHeight() != 0 && context.getWidth() != 0) {
            map.initializeMap(context.getHeight(), context.getWidth(), true, true);
        } else if (context.getHeight() != 0 && context.getWidth() == 0) {
            map.initializeMap(context.getHeight(), 1, true, false);
        } else if (context.getHeight() == 0 && context.getWidth() != 0) {
            map.initializeMap(1, context.getWidth(), false, true);
        }
    }
}
