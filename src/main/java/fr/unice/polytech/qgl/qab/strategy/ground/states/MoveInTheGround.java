package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.actions.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.states.StateMediator;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 31/12/15.
 */
public class MoveInTheGround extends GroundState {
    private static MoveInTheGround instance;

    private MoveInTheGround() {
        super();
        this.lastAction = null;
    }

    public static MoveInTheGround getInstance() {
        if (instance == null)
            instance = new MoveInTheGround();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map, StateMediator stateMediator) throws PositionOutOfMapRange {
        return null;
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        if (lastAction == null)
            return new MoveTo(context.getHeading());
        else
            return new Stop();
    }
}
