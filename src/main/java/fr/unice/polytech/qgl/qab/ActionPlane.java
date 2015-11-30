package fr.unice.polytech.qgl.qab;

import java.util.HashMap;
import java.util.Random;

/**
 * Class that represents the Actions of Plane phase.
 *
 * @version 4.9
 */
public class ActionPlane {
    private HashMap<String, Discovery> environment; // N, ("GROUND", 4)

    private static final int BUDGET_MIN = 100;

    /**
     * Constructor of Class ActionPlane.
     */
    public ActionPlane() {
        environment = new HashMap<String, Discovery>();
    }

    private boolean hasGround(String direction) {
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            Discovery result = environment.get(direction);
            return (result.found.compareToIgnoreCase("GROUND") == 0) ? true : false;
        }
        return false;
    }

    /**
     * Method to set the range if there is GROUND in one direction
     *
     * @param direction define the direction to set the ground founded
     * @param range
     */
    public void setGround(String direction, int range) {
        Discovery dataGround = new Discovery("GROUND", range);
        environment.put(direction.toUpperCase(), dataGround);
    }

    private boolean hasOutOfRange(String direction) {
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            Discovery result = environment.get(direction);
            return (result.found.compareToIgnoreCase("OUT_OF_RANGE") == 0) ? true : false;
        }
        return false;
    }

    /**
     * Method to set the range if there is limit to the map in one direction
     *
     * @param direction define the direction to set the out_of_range founded
     * @param range
     */
    public void setOutOfRange(String direction, int range) {
        Discovery dataOutOfRange = new Discovery("OUT_OF_RANGE", range);
        environment.put(direction.toUpperCase(), dataOutOfRange);
    }

    private int rangeGround(String direction) {
        if (hasGround(direction))
            return environment.get(direction).range;
        return -1;
    }

    /**
     * Method to return the range to OUT_OF_RANGE
     *
     * @param direction define the direction to get the out_or_range
     * @return
     */
    public int rangeOutOfRange(String direction) {
        if (hasOutOfRange(direction))
            return environment.get(direction).range;
        return -1;
    }

    /**
     * Method
     *
     * @param direction
     * @return
     */
    public boolean canHeading(String direction, String action) {
        return (rangeOutOfRange(direction) < 2 && action.compareToIgnoreCase("HEADING") != 0);
    }

    /**
     * Method to reset the environment.
     */
    public void resetEnvironment() {
        environment = new HashMap<String, Discovery>();
    }

    /**
     * Method to check if make the Echo is a action possible.
     *
     * @param action the last action made.
     * @param head   direction of plane head
     * @return
     */
    public boolean canEcho(String action, String head) {
        if (action == null || (action != null && action.compareToIgnoreCase("heading") == 0))
            return true;
        return false;
    }

    /**
     * Method to check if make the Scan is a action possible.
     *
     * @param head       the head direction.
     * @param takeAction the last action made.
     * @return
     */
    public boolean canScan(String head, String takeAction) {
        if (!environment.isEmpty() && environment.containsKey(head)) {
            Discovery result = environment.get(head);
            if (result.found.compareToIgnoreCase("GROUND") == 0 && (takeAction.compareToIgnoreCase("SCAN") != 0))
                if (result.range == 0) return true;
        }
        return false;
    }

    public String whereHeading(Direction direction) {
        String dir1, dir2;
        if (direction.isHorizontal()) {
            dir1 = "N"; dir2 = "S";
        } else {
            dir1 = "E"; dir2 = "W";
        }

        if (!environment.containsKey(dir1) || !environment.containsKey(dir2)) return "ECHO";
        else if (environment.get(dir1).found.compareToIgnoreCase("GROUND") == 0)
            return dir1;
        else if (environment.get(dir2).found.compareToIgnoreCase("GROUND") == 0)
            return dir2;
        else if(environment.get(dir1).found.compareToIgnoreCase("OUT_OF_RANGE") == 0 &&
                environment.get(dir1).found.compareToIgnoreCase("OUT_OF_RANGE") == 0)
            return (environment.get(dir1).range > environment.get(dir2).range)?dir1:dir2;
        return "ECHO";
    }

    public String betterDirection(Direction direction) {
        String dir1, dir2;
        if (direction.isHorizontal()) {
            dir1 = "N"; dir2 = "S";
        } else {
            dir1 = "E"; dir2 = "W";
        }

        if (environment.get(dir1).found.compareToIgnoreCase("GROUND") == 0)
            return dir1;
        else if (environment.get(dir2).found.compareToIgnoreCase("GROUND") == 0)
            return dir2;

        return "FLY";
    }

    /**
     * Method to verify the better option to make the Echo
     * @param head
     * @return
     */
    public String whereEcho(Direction head, String action) {
        if (action == null)
            return head.toString();
        else if (head.isHorizontal()) {
            if (!environment.containsKey("N"))
                return "N";
            if (!environment.containsKey("S"))
                return "S";
        }
        else if (head.isVertical()){
            if (!environment.containsKey("W"))
                return "W";
            if (!environment.containsKey("E"))
                return "E";
        }
        return "NOT";
    }

    public boolean canStop(String direction, int budget, boolean status, String action) {
        if (rangeOutOfRange(direction) == 0 || budget <= BUDGET_MIN || status == false) {
            return true;
        }
        return false;
    }

    /**
     * Method to do the changes necassaries when the plane fly
     * @param head direction of bot head
     */
    public void fly(String head) {
        if (!environment.isEmpty() && environment.containsKey(head)) {
            Discovery result = environment.get(head);
            result.range = result.range - 1;
            environment.put(head, result);
        }
    }

    public class Discovery {
        private String found;
        private int range;

        Discovery(String found, int range) {
            this.found = found;
            this.range = range;
        }
    }
}
