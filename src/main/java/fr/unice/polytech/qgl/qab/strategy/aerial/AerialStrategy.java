package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State0;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State1;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.States;

/**
 * @version 9.12.2015
 */
public class AerialStrategy implements IAerialStrategy {
    private ResponseState response;
    private Map map;
    private State state;

    public AerialStrategy() {
        map = new Map();
        state = State0.getInstance();
    }

    public Action makeDecision(Context context){
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        state =  state.getState(context, map);
        return state.responseState(context, map);
    }

    public Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return (new Stop());
        }
        return null;
    }
}
