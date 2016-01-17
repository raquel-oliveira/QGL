package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * Created by quent on 17/01/2016.
 */
public class Exit extends GroundState{

    private static Exit instance;

    private Exit(){
        super();

    }

    public static Exit getInstance() {

        if (instance == null)
            instance = new Exit();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map, StateManager stateManager) throws PositionOutOfMapRange {
        return Exit.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateManager stateManager) throws IndexOutOfBoundsComboAction {
        return new Stop();
    }
}
