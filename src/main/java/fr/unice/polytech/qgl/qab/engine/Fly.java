package fr.unice.polytech.qgl.qab.engine;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action fly.
 *
 * @version 4.9
 */
public class Fly extends ActionPlane {

    public Fly() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.FLY));
        }
        return false;
    }

    /**
     * Method to do the changes necassaries when the plane fly
     * @param head direction of bot head
     */
    public void fly(Direction head) {
        if (!environment.isEmpty() && environment.containsKey(head)) {
            Discovery result = environment.get(head);
            result.range = result.range - 1;
            environment.put(head, result);
        }
    }
}
