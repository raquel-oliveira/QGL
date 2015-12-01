package fr.unice.polytech.qgl.qab.engine.aerial;

import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.enums.Found;
import org.json.JSONObject;

/**
 * Class to represent the action heading.
 *
 * @version 4.9
 */
public class Heading extends ActionAerial {
    public Heading() {
        super();
    }

    @Override
    public boolean isValid(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            ActionBot act = ActionBot.fromString(jsonObj.getString("action"));
            return (act.equals(ActionBot.HEADING));
        }
        return false;
    }

    public static Direction whereHeading(Direction direction) {
        Direction dir1, dir2;
        if (direction.isHorizontal()) {
            dir1 = Direction.NORTH; dir2 = Direction.SOUTH;
        } else {
            dir1 = Direction.EAST; dir2 = Direction.WEST;
        }

        if (!environment.containsKey(dir1) || !environment.containsKey(dir2)) return null;
        else if (environment.get(dir1).found.equals(Found.GROUND))
            return dir1;
        else if (environment.get(dir2).found.equals(Found.GROUND))
            return dir2;
        else if(environment.get(dir1).found.equals(Found.OUT_OF_RANGE) &&
                environment.get(dir1).found.equals(Found.OUT_OF_RANGE))
            return (environment.get(dir1).range > environment.get(dir2).range)?dir1:dir2;
        return null;
    }

    /**
     * Method to check if is possible make hading
     * @param direction
     * @return
     */
    public static boolean canHeading(Direction direction, ActionBot action) {
        if (rangeOutOfRange(direction) < 2 && !action.equals(ActionBot.HEADING))
            return true;
        return false;
    }
}
