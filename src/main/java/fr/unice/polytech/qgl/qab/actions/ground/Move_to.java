package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;


public class Move_to implements Action {
    private Direction direction;


    public Move_to(Direction direction){
        super();
        this.direction = direction;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.MOVE_TO));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"move_to\", \"parameters\": { \"direction\": \"" + direction + "\" } }";
    }

    public Direction getDirection(){
        return this.direction;
    }
}
