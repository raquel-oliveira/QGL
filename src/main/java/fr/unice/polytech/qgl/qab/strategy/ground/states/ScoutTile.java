package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboScoutTile;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.response.ScoutResponse;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;

/**
 * This GroundState represents the state to make the scout in the tile.
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
                &&  !contextAnalyzer.resourceAnalyzerScout(context).isEmpty()) {

            context.current().setResourcesToExploit(contextAnalyzer.resourceAnalyzerScout(context), context);

            if (!context.current().getResourcesToExploit().isEmpty()) {
                context.getLastDiscovery().setScoutResponse(new ScoutResponse());
                return GroundStateFactory.buildState(GroundStateType.EXPLOIT_TILE);
            }
        }

        if (context.current().getComboAction().isEmpty()) {
            return GroundStateFactory.buildState(GroundStateType.FIND_TILE);
        }
        return GroundStateFactory.buildState(GroundStateType.SCOUT_TILE);
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        if (context.getContracts().contractsAreComplete()){
            return new Stop();
        }

        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboScoutTile());
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