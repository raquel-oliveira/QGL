package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;


public class Scout extends Action {
    private static final String ACTION_SCOUT = "scout";

    public Scout(Direction direction){
        super();
        this.dirAction = direction;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION_PARAMS)) {
            String action = jsonObj.getString(ACTION_PARAMS);
            if (!(ACTION_SCOUT).equals(action))
                return false;
            if(!checkParameter(jsonObj))
                return false;
        } else return false;
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"scout\", \"parameters\": { \"direction\": \"" + dirAction + "\" } }";
    }

    @Override
    public Direction getDirection(){
        return this.dirAction;
    }
}