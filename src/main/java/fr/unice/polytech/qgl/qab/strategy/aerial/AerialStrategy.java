package fr.unice.polytech.qgl.qab.strategy.aerial;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.IState;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State0;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State1;
import fr.unice.polytech.qgl.qab.strategy.aerial.States.State2;
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
    private IState IState;
    private States currentState;
    private UpdaterMap updaterMap;

    public AerialStrategy() {
        map = new Map();
        IState = State0.getInstance();
        currentState = States.STATE0;
        updaterMap = new UpdaterMap();
    }

    public Action makeDecision(Context context){
        if (contextAnalyzer(context) != null) {
            return contextAnalyzer(context);
        }

        while (currentState.equals(States.STATE0)) {
            if (context.getLastDiscovery() != null)
                updaterMap.initializeDimensions(context, (Echo)response.getAction());

            response = IState.responseState(context);

            if (response.getStatus()) {
                currentState = States.TRANSITION;
                updaterMap.update(context, map);
            }
            return response.getAction();
        }

        if (currentState.equals(States.TRANSITION)) {
            updaterMap.initializeDimensions(context, (Echo) response.getAction());
            updaterMap.update(context, map);
            updaterMap.setFirstPosition(context, map);
            currentState = States.STATE1;
            IState = State1.getInstance();
        }

        while (currentState.equals(States.STATE1)) {
            response = IState.responseState(context);
            if (response.getAction() instanceof Fly) {
                updaterMap.updateLastPositionFly(context, map);
            }

            if (response.getStatus()) {
                currentState = States.TRANSITION;
                updaterMap.update(context, map);
                currentState = States.STATE2;
                IState = State2.getInstance();
            }
            return response.getAction();
        }
        updaterMap.update(context, map);

        response = IState.responseState(context);

        return response.getAction();
    }

    public Action contextAnalyzer(Context context) {
        if (context.getBudget() < 100) {
            return (new Stop());
        }
        return null;
    }
}
