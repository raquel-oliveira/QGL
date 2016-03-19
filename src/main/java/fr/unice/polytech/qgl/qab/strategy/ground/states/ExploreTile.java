package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboExploreTile;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;

/**
 * @version 01/03/16.
 */
public class ExploreTile extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    public ExploreTile() {
        contextAnalyzer = new ContextAnalyzer();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (context.current().getLastAction() instanceof Explore) {
            context.current().setResourcesToExploit(contextAnalyzer.resourceAnalyzer(context), context);

            if (!context.current().getResourcesToExploit().isEmpty())
                return GroundStateFactory.buildState(GroundStateType.EXPLOITTILE);
        }

        if (context.current().getComboAction().isEmpty())
            return GroundStateFactory.buildState(GroundStateType.FINDTILE);

        return GroundStateFactory.buildState(GroundStateType.EXPLORETILE);
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboExploreTile());
            context.current().getComboAction().defineActions();
        }

        if (context.current().getComboAction().isEmpty()) {
            return new Scout(context.getHeading());
        }

        Action action = context.current().getComboAction().get(0);
        context.current().getComboAction().remove(0);
        context.current().setLastAction(action);
        return action;
    }
}
