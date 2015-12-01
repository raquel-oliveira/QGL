package fr.unice.polytech.qgl.qab.actions;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.enums.Found;
import org.json.JSONObject;

/**
 * Class to represent the action scan.
 *
 * @version 4.9
 */
public class Scan extends ActionPlane {
    public Scan() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.SCAN));
        }
        return false;
    }

    /**
     * Method to check if make the Scan is a action possible.
     * @param head       the head direction.
     * @param takeAction the last action made.
     * @return
     */
    public boolean canScan(Direction head, ActionBot takeAction) {
        if (!environment.isEmpty() && environment.containsKey(head)) {
            Discovery result = environment.get(head);
            if (result.found.equals(Found.GROUND) && takeAction.equals(ActionBot.SCAN))
                if (result.range == 0) return true;
        }
        return false;
    }
}
