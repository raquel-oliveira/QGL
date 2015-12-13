package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import org.json.JSONObject;

/**
 * Created by prodhommebloup on 13/12/15.
 */
public class Glimpse implements Action {
    private Direction direction;
    private int range;

    public Glimpse(Direction direction,int range){
        super();
        this.direction = direction;
        this.range = range;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.GLIMPSE));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"glimpse\", \"parameters\": { \"direction\": \"" + direction + "\", \"range\": "+range+" } }";
    }

    public Direction getDirection(){
        return this.direction;
    }

    public int getRange(){
        return this.range;
    }

}
