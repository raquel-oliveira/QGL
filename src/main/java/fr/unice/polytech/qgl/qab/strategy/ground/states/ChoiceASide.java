package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboScout;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Exploit;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 20/02/16.
 */
public class ChoiceASide extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    public ChoiceASide() {
        this.contextAnalyzer = new ContextAnalyzer();
    }

    /**
     * Method to get the instance of the ChoiceASide class
     * @return instance of the ChoiceASide class
     */
    public static ChoiceASide getInstance() {
        return new ChoiceASide();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        tileInGround(context);

        if (!goodTile(context).isEmpty()) {
            context.current().setResourcesToExploit(goodTile(context));
            context.current().moveUntil(1);
            context.setHeading(context.current().getLastAction().getDirection());
            updateContext(context);
            return MoveUntilTile.getInstance();
        } else if (context.current().getComboAction().isEmpty()) {
            updateContext(context);
            context.setHeading(context.current().getDirectionWithoutOCEAN());
            return MoveGround.getInstance();
        } else {
            return ChoiceASide.getInstance();
        }
    }

    private void tileInGround(Context context) {
        Direction d = context.current().getLastAction().getDirection();
        if (!context.getLastDiscovery().getScoutResponse().found("FISH")) {
            context.current().setDirectionWithoutOCEAN(d);
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboScout());
            context.current().getComboAction().defineActions(context.getHeading());
        }

        act = context.current().getComboAction().remove(0);
        context.current().setLastAction(act);
        return act;
    }

    private List<PrimaryType> goodTile(Context context) {
        return contextAnalyzer.analyzerScout(context);
    }


    /**
     * Method to updata the context
     * @param context
     */
    private void updateContext(Context context) {
        context.current().setComboAction(null);
        context.current().setLastAction(null);
    }
}
