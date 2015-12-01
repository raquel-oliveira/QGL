package fr.unice.polytech.qgl.qab.actions;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import org.json.JSONObject;

/**
 * Class to represent the action Echo.
 *
 * @version 4.9
 */
public class Echo extends ActionPlane {
    public Echo() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.ECHO));
        }
        return false;
    }

    /**
     * Method to verify the better option to make the Echo
     * @param head
     * @return
     */
    public Direction whereEcho(Direction head, ActionBot action) {
        if (action == null)
            return head;
        else if (head.isHorizontal()) {
            if (!environment.containsKey(Direction.NORTH))
                return Direction.NORTH;
            if (!environment.containsKey(Direction.SOUTH))
                return Direction.SOUTH;
        }
        else if (head.isVertical()){
            if (!environment.containsKey(Direction.WEST))
                return Direction.WEST;
            if (!environment.containsKey(Direction.EAST))
                return Direction.EAST;
        }
        return null;
    }

    /**
     * Method to check if make the Echo is a action possible.
     * @param action the last action made.
     * @param head   direction of plane head
     * @return
     */
    public boolean canEcho(ActionBot action, Direction head) {
        if (action == null || action != null && action.equals(ActionBot.HEADING))
            return true;
        return false;
    }
}
