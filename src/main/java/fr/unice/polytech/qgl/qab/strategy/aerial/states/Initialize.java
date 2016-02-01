package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboEchos;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
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
    public AerialState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange {
        if (actionCombo != null && !stateMediator.shouldGoToTheCorner())
            updaterMap.initializeDimensions(context, map);

        if (actionCombo != null && actionCombo.isEmpty() && stateMediator.shouldGoToTheCorner()) {
            actionCombo = null;
            return GoToTheCorner.getInstance();
        } else if (actionCombo != null && actionCombo.isEmpty() && !stateMediator.shouldGoToTheCorner())
            return FindGround.getInstance();
        return Initialize.getInstance();
    }

    @Override
    public Action responseState(Context context,  Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        if (actionCombo == null) {
            actionCombo = new ComboEchos();
            actionCombo.defineComboEchos(context.getHeading());
        }

        Action act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);

        if (context.getLastDiscovery() != null && !stateMediator.shouldGoToTheCorner()) {
            if (context.getLastDiscovery().getFound().isEquals(Found.GROUND))
                stateMediator.setGoToTheCorner(true) ;
            else
                stateMediator.setRangeToGround(context.getLastDiscovery().getRange());
        }

        if (stateMediator.shouldGoToTheCorner()) {
            if (context.getLastDiscovery().getRange() > stateMediator.getRangeToTheCorner()) {
                stateMediator.setRangeToTheCorner(context.getLastDiscovery().getRange());
                stateMediator.setDirectionToTheCorner(context.getLastDiscovery().getDirection());
            }
        }

        return act;
    }
}