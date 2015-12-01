package fr.unice.polytech.qgl.qab.engine.aerial;

import java.util.Map;
import java.util.HashMap;

import fr.unice.polytech.qgl.qab.engine.Action;
import fr.unice.polytech.qgl.qab.engine.common.Stop;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.enums.Found;
import org.json.JSONObject;

/**
 * Class that represents the Actions of Plane phase.
 *
 * @version 4.9
 */
public class ActionAerial extends Action {
    protected static Map<Direction, Discovery> environment = new HashMap<>();
    protected static final int BUDGET_MIN = 100;
    private ActionBot takeAction;
    private Direction direction;

    @Override
    public boolean isValid(JSONObject jsonObj) {
        return false;
    };

    /**
     * Constructor of Class ActionAerial.
     */
    public ActionAerial() {
        takeAction = null;
        direction = null;
    }

    private boolean hasGround(Direction direction) {
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            Discovery result = environment.get(direction);
            return (result.found.equals(Found.GROUND));
        }
        return false;
    }

    private static boolean hasOutOfRange(Direction direction) {
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            Discovery result = environment.get(direction);
            return (result.found.equals(Found.OUT_OF_RANGE));
        }
        return false;
    }

    /**
     * Method to set the range if there is GROUND in one direction
     * @param direction define the direction to set the ground founded
     * @param range
     */
    public void setGround(Direction direction, int range) {
        Discovery dataGround = new Discovery(Found.GROUND, range);
        environment.put(direction, dataGround);
    }

    /**
     * Method to set the range if there is limit to the map in one direction
     * @param direction define the direction to set the out_of_range founded
     * @param range
     */
    public void setOutOfRange(Direction direction, int range) {
        Discovery dataOutOfRange = new Discovery(Found.OUT_OF_RANGE, range);
        environment.put(direction, dataOutOfRange);
    }

    /**
     * Method to return the range to OUT_OF_RANGE
     * @param direction define the direction to get the out_or_range
     * @return range until the map limit
     */
    public static int rangeOutOfRange(Direction direction) {
        if (hasOutOfRange(direction))
            return environment.get(direction).range;
        return -1;
    }

    /**
     * Method to reset the environment.
     */
    public void resetEnvironment() {
        environment = new HashMap<Direction, Discovery>();
    }

    /**
     * Method to give the better direction.
     * @param direction
     * @return
     */
    public Direction betterDirection(Direction direction) {
        Direction dir1, dir2;
        if (direction.isHorizontal()) {
            dir1 = Direction.NORTH; dir2 = Direction.SOUTH;
        } else {
            dir1 = Direction.EAST; dir2 = Direction.WEST;
        }

        if (environment.get(dir1).found.equals(Found.GROUND))
            return dir1;
        else if (environment.get(dir2).found.equals(Found.GROUND))
            return dir2;

        return null;
    }

    public String makeDecision(Direction head, int budget, Boolean status) {
        if (direction == null) { direction = head; }

        if (Stop.canStop(direction, budget, status)) {
            takeAction = ActionBot.STOP;
            return "{ \"action\": \"" + takeAction.toString() + "\" }";
        }
        else if (Echo.canEcho(takeAction, direction)) {
            takeAction = ActionBot.ECHO;
            return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \"" + direction.toString() + "\" } }";
        }
        else if (Heading.canHeading(direction, takeAction)) {
            Direction dirHeading  = Heading.whereHeading(direction);
            if (dirHeading == null || (dirHeading != null && dirHeading.equals(ActionBot.ECHO))) {
                Direction dirEcho = Echo.whereEcho(dirHeading, takeAction);
                takeAction = ActionBot.ECHO;
                direction = dirEcho;
                return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \"" + dirEcho + "\" } }";
            }
            takeAction = ActionBot.HEADING;
            direction = dirHeading;
            resetEnvironment();
            return "{ \"action\": \""+ takeAction.toString() +"\", \"parameters\": { \"direction\": \""+ dirHeading +"\" } }";
        }
            takeAction = ActionBot.FLY;
            Fly.fly(direction);
            takeAction = ActionBot.STOP;
            return "{ \"action\": \"" + takeAction.toString() + "\" }";
    }

    protected class Discovery {
        protected Found found;
        protected int range;

        Discovery(Found found, int range) {
            this.found = found;
            this.range = range;
        }
    }
}
