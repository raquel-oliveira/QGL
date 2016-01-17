package fr.unice.polytech.qgl.qab.actions.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

public class Explore implements Action {
    public Explore(){
        super();
    }

    //TODO
    @Override
    public boolean isValid(JSONObject jsonObj) {
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"explore\" }";
    }
}
