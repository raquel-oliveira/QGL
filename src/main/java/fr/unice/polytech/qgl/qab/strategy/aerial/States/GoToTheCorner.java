package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;

/**
 * @version 17/12/15.
 */
public class GoToTheCorner extends AerialState {
    private static GoToTheCorner instance;

    private ComboFlyUntil actionCombo;
    private UpdaterMap updaterMap;

    private GoToTheCorner() {
        super();
        actionCombo = null;
        updaterMap = new UpdaterMap();
    }

    public static GoToTheCorner getInstance() {
        if (instance == null)
            instance = new GoToTheCorner();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapaRange {
        return null;
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) {
        return new Stop();
    }
}
