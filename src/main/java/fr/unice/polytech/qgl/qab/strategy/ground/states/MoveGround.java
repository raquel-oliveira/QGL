package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboMoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 20/02/16.
 */
public class MoveGround extends GroundState {

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (context.current().getComboAction().isEmpty()) {
            updateContext(context);
            context.current().setIndexTile(0);
            context.current().setLastAction(null);
            return new GlimpseTheGround();
        } else {
            return new MoveGround();
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboMoveTo());
            context.setHeading(context.getHeading());
            context.current().getComboAction().defineActions(context.getHeading(), 2);
        }

        act = context.current().getComboAction().remove(0);
        context.current().setLastAction(act);
        return act;
    }

    /**
     * Method to updata the context
     * @param context
     */
    private static void updateContext(Context context) {
        context.current().setLastAction(null);
        context.current().setComboAction(null);
    }
}
