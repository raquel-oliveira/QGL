package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.json.JSONObject;

/**
 *
 */
public class ActionGround implements Action {
    @Override
    public boolean isValid(JSONObject jsonObj) {
        return false;
    }

    @Override
    public Action makeDecision(Map map, Context context) { return null; }

    @Override
    public String formatResponse() {
        return null;
    }
}
