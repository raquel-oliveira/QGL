package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboScoutTile;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 01/03/16.
 */
public class ScoutTile extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    public ScoutTile() {
        contextAnalyzer = new ContextAnalyzer();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (context.current().getLastAction() instanceof MoveTo
                &&  !contextAnalyzer.analyzerScout(context).isEmpty()) {
            return new ExploreTile();
        }
        /*
        TODO: Return to initilize.
        if (context.current().getComboAction().isEmpty()) {
            context.current().setComboAction(null);
            return new FindTile();
        }
        */
        return new ScoutTile();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboScoutTile());
            context.current().getComboAction().defineActions();
        }

        if (context.current().getComboAction().isEmpty()) {
            context.current().setComboAction(null);
            return new Stop();
        }

        if (context.current().getComboAction().isEmpty())
            return new MoveTo(context.getHeading());

        Action action = context.current().getComboAction().get(0);
        context.current().getComboAction().remove(0);
        context.current().setLastAction(action);
        return action;
    }
}
