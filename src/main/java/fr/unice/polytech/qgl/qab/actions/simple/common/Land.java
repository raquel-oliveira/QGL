package fr.unice.polytech.qgl.qab.actions.simple.common;

import fr.unice.polytech.qgl.qab.actions.Action;
import org.json.JSONObject;

/**
 * @version 12.12.2015.
 */
public class Land extends Action {
    private static final String ACTION_LAND = "land";
    private String id;
    private int people;

    public Land(String id, int people) {
        super();
        this.id = id;
        this.people = people;
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has(ACTION)) {
            String action = jsonObj.getString(ACTION);
            if (!(ACTION_LAND).equals(action))
                return false;
        } else return false;

        if (jsonObj.has(PARAMETERS)) {
            if (!jsonObj.getJSONObject(PARAMETERS).has("creek")) {
                return false;
            }
            if (!jsonObj.getJSONObject(PARAMETERS).has("people")){
                return false;
            }
        } else return false;
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"land\", \"parameters\": { \"creek\": \""+id+"\", \"people\": "+people+" }}";
    }
}
