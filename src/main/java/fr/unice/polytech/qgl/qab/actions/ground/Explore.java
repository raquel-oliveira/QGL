package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import org.json.JSONObject;

public class Explore implements Action {
    public Explore(){
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.EXPLORE));
        }
        return false;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"explore\" }";
    }
}
