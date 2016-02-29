package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboGlimpse;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * @version 20/02/16.
 */
public class ChoiceASide extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    public ChoiceASide() {
        this.contextAnalyzer = new ContextAnalyzer();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        goodDirection(context);

        if (context.current().getComboAction() != null && context.current().getComboAction().isEmpty()) {
            if (context.current().getDirectionWithoutOCEAN() != null)
                context.setHeading(context.current().getDirectionWithoutOCEAN());
            else
                context.setHeading(context.current().getLastAction().getDirection());
            updateContext(context);
            return new MoveGround();
        }

        if (contextAnalyzer.isOcean(context))
            return new ChoiceASide();

        if (goodTile(context)) {
            context.setHeading(context.current().getLastAction().getDirection());
            context.current().setIndexTile(0);
            updateContext(context);
            return new GlimpseTheGround();
        }

        return new ChoiceASide();
    }

    private void goodDirection(Context context) {
        Direction dir = null;
        if (!contextAnalyzer.isOcean(context) &&
                context.current().getLastAction().getDirection() != context.getHeading()) {
            dir = context.current().getLastAction().getDirection();
        }
        context.current().setDirectionWithoutOCEAN(dir);
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboGlimpse());
            context.current().getComboAction().defineActions(context.getHeading());
        }

        if (context.current().getComboAction().isEmpty())
            return new Stop();

        act = context.current().getComboAction().remove(0);
        context.current().setLastAction(act);
        return act;
    }

    /**
     * Return true if the glimpse found something good to the contract
     * @param context data context
     * @return
     */
    private boolean goodTile(Context context) {
        return contextAnalyzer.goodGlimpse(context);
    }


    /**
     * Method to updata the context
     * @param context
     */
    private static void updateContext(Context context) {
        context.current().setComboAction(null);
        context.current().setLastAction(null);
    }
}
