package fr.unice.polytech.qgl.qab.actions.common;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * @version 12.12.2015.
 */
public class Land implements Action {
    private String id;
    private int people;

    public Land(String id, int people) {
        super();
        this.id = id;
        this.people = people;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            String action = jsonObj.getString("action");
            if (!("land").equals(action))
                return false;
        } else return false;

        if (jsonObj.has("parameters")) {
            if (!jsonObj.getJSONObject("parameters").has("creek")) return false;
            if (!jsonObj.getJSONObject("parameters").has("people")) return false;
        } else return false;
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"land\", \"parameters\": { \"creek\": \""+id+"\", \"people\": "+people+" }}";
    }
}
