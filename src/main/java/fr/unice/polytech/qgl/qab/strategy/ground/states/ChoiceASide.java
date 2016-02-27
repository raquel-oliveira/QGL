package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboGlimpse;
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

        if (!goodTile(context).isEmpty() ||
                (context.current().getComboAction() != null &&
                        context.current().getComboAction().isEmpty())) {
            context.setHeading(context.current().getLastAction().getDirection());
            context.current().setIndexTile(0);
            updateContext(context);
            return GlimpseTheGround.getInstance();
        } else if (contextAnalyzer.isOcean(context)) {
            return ChoiceASide.getInstance();
        }
        return ChoiceASide.getInstance();
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
            context.current().setComboAction(new ComboGlimpse());
            context.current().getComboAction().defineActions(context.getHeading());
        }

        if (context.current().getComboAction().isEmpty())
            return new Stop();

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
