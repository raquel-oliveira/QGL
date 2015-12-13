package fr.unice.polytech.qgl.qab.actions.common;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import org.json.JSONObject;

/**
 * @version 12.12.2015.
 */
public class Land implements Action {
    private int id;
    private int people;

    public Land(int id, int people) {
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
