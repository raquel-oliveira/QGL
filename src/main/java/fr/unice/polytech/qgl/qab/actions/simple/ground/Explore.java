package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

public class Explore extends Action {
    private static final String ACTION_EXPLORE = "explore";

    public Explore() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            if (!(ACTION_EXPLORE).equals(action))
                return false;
        }
        else return false;

        return true;
    }

    @Override
    public String formatResponse () {
        return "{ \"action\": \"explore\" }";
    }

}