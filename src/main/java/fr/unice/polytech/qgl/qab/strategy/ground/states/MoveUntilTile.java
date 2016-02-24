package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboMoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 24/02/16.
 */
public class MoveUntilTile extends GroundState {

    /**
     * Method to get the instance of the ExploreTile class
     * @return instance of ExploreTile
     */
    public static MoveUntilTile getInstance() {
        return new MoveUntilTile();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (context.current().getComboAction().isEmpty()) {
            return ExploreTile.getInstance();
        } else {
            return MoveUntilTile.getInstance();
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboMoveTo());
            context.current().getComboAction().defineActions(context.getHeading(), context.current().moveUntil());
        }

        act = context.current().getComboAction().remove(0);
        context.current().setLastAction(act);
        return act;
    }
}
