package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 13.12.2015.
 */
public class MakeLand extends State {
    public static MakeLand instance;

    protected MakeLand() { }

    public static MakeLand getInstance() {
        if (instance == null)
            instance = new MakeLand();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        return MakeLand.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        return new Stop();
    }
}
