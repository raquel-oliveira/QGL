package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Created by quent on 01/01/2016.
 */
public class Move implements Action {
    private Direction direction;


    public Move(Direction direction){
        super();
        this.direction = direction;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.MOVE));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"move\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    public Direction getDirection(){
        return this.direction;
    }
}
