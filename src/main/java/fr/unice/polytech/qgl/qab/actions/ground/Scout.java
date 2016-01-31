package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;


public class Scout extends Action {
    private static final String ACTION_SCOUT = "scout";

    public Scout(Direction direction){
        super();
        this.direction = direction;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            if (!(ACTION_SCOUT).equals(action))
                return false;
            if(!checkParameter(jsonObj))
                return false;
        } else return false;
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"scout\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    public Direction getDirection(){
        return this.direction;
    }
}