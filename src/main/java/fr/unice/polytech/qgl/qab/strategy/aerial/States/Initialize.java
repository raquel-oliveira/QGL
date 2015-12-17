package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboEchos;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 10.12.2015
 */
public class Initialize extends AerialState {
    private static Initialize instance;

    private ComboEchos actionCombo;
    private UpdaterMap updaterMap;

    private Initialize() {
        super();
        updaterMap = new UpdaterMap();
        actionCombo = null;
    }

    public static Initialize getInstance() {
        if (instance == null)
            instance = new Initialize();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map) throws PositionOutOfMapaRange {
        if (actionCombo != null && actionCombo.isEmpty()) {
            updateContext(context, map);
            if (context.getLastDiscovery().getFound().isEquals(Found.GROUND)) {
                context.getLastDiscovery().setFound(Found.UNDEFINED);
                return FlyUntil.getInstance();
            }
            return FindGround.getInstance();
        }
        return Initialize.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map) {
        if (actionCombo == null) {
            actionCombo = new ComboEchos();
            actionCombo.defineComboEchos(context.getHeading());
        }

        Action act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);


        if (context.getLastDiscovery() != null) {
            updaterMap.initializeDimensions(context, (Echo) act);
        }

        return act;
    }

    private void updateContext(Context context, Map map) throws PositionOutOfMapaRange {
        updaterMap.initializeDimensions(context, (Echo)lastAction);
        updaterMap.update(context, map);
        updaterMap.setFirstPosition(context, map);
    }
}