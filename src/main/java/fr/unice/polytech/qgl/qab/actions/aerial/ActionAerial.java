package fr.unice.polytech.qgl.qab.actions.aerial;

import java.util.ArrayList;

import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.enums.ActionBot;

import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.json.JSONObject;

/**
 * Class that represents the Actions of Plane phase.
 *
 * @version 4.9
 */
public class ActionAerial extends Action {
    protected static final int BUDGET_MIN = 100;
    private ArrayList<Action> actionsCombo;

    @Override
    public boolean isValid(JSONObject jsonObj) {
        return false;
    }

    @Override
    public Action makeDecision(Map map, Context context) {
        if (map.isEmpty()) {
            echoCombo(context);
        }

        if (!actionsCombo.isEmpty()) {
            Action act = actionsCombo.get(0);
            actionsCombo.remove(0);
            if (act instanceof Echo)
                return act;
        }

        Action act = new Stop();
        return act;
    }

    private void echoCombo(Context context) {
        if (context.getHeading().isHorizontal()) {
            if (context.getHeading().compareTo(Direction.EAST) == 0) {
                actionsCombo.add(new Echo(Direction.EAST));
            } else {
                actionsCombo.add(new Echo(Direction.WEST));
            }
            actionsCombo.add(new Echo(Direction.NORTH));
            actionsCombo.add(new Echo(Direction.SOUTH));
        }
        if (context.getHeading().isVertical()) {
            if (context.getHeading().compareTo(Direction.NORTH) == 0) {
                actionsCombo.add(new Echo(Direction.NORTH));
            } else {
                actionsCombo.add(new Echo(Direction.SOUTH));
            }
            actionsCombo.add(new Echo(Direction.WEST));
            actionsCombo.add(new Echo(Direction.EAST));
        }
    }

    ;

    /**
     * Constructor of Class ActionAerial.
     */
    public ActionAerial() {
        actionsCombo = new ArrayList<>();
    }

    @Override
    public String formatResponse() { return null; };
}
