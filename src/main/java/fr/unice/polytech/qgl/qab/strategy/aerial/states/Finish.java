package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 13.12.2015.
 */
public class Finish extends AerialState {
    private static Finish instance;

    public static Finish getInstance() {
        if (instance == null)
            instance = new Finish();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        return Finish.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) {
        return new Stop();
    }
}
