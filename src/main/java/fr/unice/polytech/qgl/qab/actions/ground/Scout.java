package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;


public class Scout extends Action{

    public Scout(Direction direction){
        super();
        setDirection(direction);
    }

    //TODO
    @Override
    public boolean isValid(JSONObject jsonObj) {
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"scout\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

}