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
        return true;
    }

    @Override
    public String formatResponse() {
        return "{ \"action\": \"land\", \"parameters\": { \"creek\": \""+id+"\", \"people\": "+people+" }}";
    }
}
