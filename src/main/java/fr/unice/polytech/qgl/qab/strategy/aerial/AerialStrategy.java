package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State0;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseState;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * @version 9.12.2015
 */
public class AerialStrategy implements IAerialStrategy {
    private ResponseState response;
    // object that represent the game map in the aerial space
    private Map map;
    private State0 state0;
    private boolean state0out;
    private UpdaterMap updaterMap;

    public AerialStrategy() {
        map = new Map();
        state0 = new State0();
        state0out = true;
        updaterMap = new UpdaterMap();
    }

    public Action makeDecision(Context context){
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        while (state0out) {
            if (context.getLastDiscovery() != null)
                updaterMap.initializeDimensions(context, (Echo)response.getAction());
            response = state0.responseState(context);
            if (response.getStatus()) {
                state0out = false;
                updaterMap.update(context, map);
            }
            return response.getAction();
        }
        updaterMap.initializeDimensions(context, (Echo)response.getAction());
        updaterMap.update(context, map);

        return new Stop();
    }

    public Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return (new Stop());
        }
        return null;
    }
}
