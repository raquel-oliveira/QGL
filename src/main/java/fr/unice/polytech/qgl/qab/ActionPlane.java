package fr.unice.polytech.qgl.qab;

import java.util.HashMap;
import fr.unice.polytech.qgl.qab.Action;
import java.util.Random;

/**
 * Class that represents the Actions of Plane phase.
 * @version 4.9
 */
public class ActionPlane  {
    private HashMap<Direction, Discovery> environment;

	private static final int BUDGET_MIN = 100;

    /**
     * Constructor of Class ActionPlane.
     */
    public ActionPlane() {
        environment = new HashMap<Direction, Discovery>();
    }

    private boolean hasGround(Direction direction) {
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            Discovery result = environment.get(direction);
            return (result.found.equals(Found.GROUND));
        }
        return false;
    }

    private boolean hasOutOfRange(Direction direction) {
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
     * @return
     */
    public int rangeOutOfRange(Direction direction) {
        if (hasOutOfRange(direction))
            return environment.get(direction).range;
        return -1;
    }

    /**
     * Method to check if is possible make hading
     * @param direction
     * @return
     */
    public boolean canHeading(Direction direction, Action action) {
        if (rangeOutOfRange(direction) < 2 && !action.equals(Action.HEADING))
            return true;
        return false;
    }

    /**
     * Method to reset the environment.
     */
    public void resetEnvironment() {
        environment = new HashMap<Direction, Discovery>();
    }

    /**
     * Method to check if make the Echo is a action possible.
     * @param action the last action made.
     * @param head   direction of plane head
     * @return
     */
    public boolean canEcho(Action action, Direction head) {
        if (action == null || action != null && action.equals(Action.HEADING))
            return true;
        return false;
    }

    /**
     * Method to check if make the Scan is a action possible.
     * @param head       the head direction.
     * @param takeAction the last action made.
     * @return
     */
    public boolean canScan(Direction head, Action takeAction) {
        if (!environment.isEmpty() && environment.containsKey(head)) {
            Discovery result = environment.get(head);
            if (result.found.equals(Found.GROUND) && takeAction.equals(Action.SCAN))
                if (result.range == 0) return true;
        }
        return false;
    }

    public Direction whereHeading(Direction direction) {
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

    /**
     * Method to verify the better option to make the Echo
     * @param head
     * @return
     */
    public Direction whereEcho(Direction head, Action action) {
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

    public boolean canStop(Direction direction, int budget, boolean status) {
        return (rangeOutOfRange(direction) == 0 || budget <= BUDGET_MIN || status == false);
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
    

    public HashMap<Direction, Discovery> getEnvironment() {
		return environment;
	}

    public class Discovery {
        private Found found;
        private int range;

        Discovery(Found found, int range) {
            this.found = found;
            this.range = range;
        }
    }
}
