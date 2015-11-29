package fr.unice.polytech.qgl.qab;

import java.util.HashMap;
import java.util.Random;

/**
 * Class that represents the Actions of Plane phase.
 *
 * @version 4.9
 */
public class ActionPlane {
    private HashMap<String, Discovery> environment; // N, ["GROUND", "4"]


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
     * @param direction define the direction to set the ground founded
     * @param range
     */
    public void setGround(String direction, int range) {
        Discovery dataGround =  new Discovery("GROUND", range);
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
     * @param direction define the direction to set the out_of_range founded
     * @param range
     */
    public void setOutOfRange(String direction, int range) {
        Discovery dataOutOfRange =  new Discovery("OUT_OF_RANGE", range);
        environment.put(direction.toUpperCase(), dataOutOfRange);
    }

    private int rangeGround(String direction) {
        if (hasGround(direction))
            return environment.get(direction).range;
        return -1;
    }

    /**
     * Method to return the range to OUT_OF_RANGE
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
     * @param direction
     * @return
     */
    public boolean canHeading(String direction, String action) {
        return (hasOutOfRange(direction) && action.compareToIgnoreCase("HEADING") != 0);
    }

    /**
     * Method to reset the environment.
     */
    public void resetEnvironment() {
        environment = new HashMap<String, Discovery>();
    }

    /**
     * Method to check if make the Echo is a action possible.
     * @param takeAction the last action made.
     * @return
     */
    public boolean canEcho(String takeAction, String head) {
        // if the last action was different that
        // if the plane is above the ground (to check when the plane get out)
        // don't do if the plane saw the ground but don't is above it
        if (takeAction.compareToIgnoreCase("ECHO") != 0 ||
                (hasGround(head) && rangeGround(head) == 0))
            return false;
        return true;
    }

    /**
     * Method to check if make the Scan is a action possible.
     * @param head the head direction.
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

    /**
     * Method to give me the direction to make heading
     * @param head the direction of bot head
     * @return the direction to make a heading (or, say that is necessary make ECHO)
     */
    public String directionToHeading(Direction head) {
        if (head.isHorizontal())
            return betterDirection("N", "S");
        else if (head.isVertical())
            return betterDirection("E", "W");

        return "ECHO";
    }

    private String betterDirection(String dir1, String dir2) {
        if (environment.containsKey(dir1) && hasGround(dir1)) return dir1;
        else if (environment.containsKey(dir2) && hasGround(dir2)) return dir2;
        else {
            Random rand = new Random();
            int direction = rand.nextInt(2);

            if (direction == 0) {
                if (environment.containsKey(dir1)) {
                    Discovery result = environment.get(dir1);
                    if (result.range >= 2)
                        return dir1;
                }
            }

            if (environment.containsKey(dir2)) {
                Discovery result = environment.get(dir2);
                if (result.range >= 2)
                    return dir2;
            }
        }
        return "ECHO";
    }

    /**
     * Method to verify the better option to make the Echo
     * @param head
     * @return
     */
    public String whereEcho(Direction head) {
        if (head.isHorizontal()) {
            if (possiblesEcho("N")) return "N";
            else return "S";
        }
        else {
            if (possiblesEcho("W")) return "W";
            else return "E";
        }
    }

    private boolean possiblesEcho(String direction) {
        if (environment.isEmpty() || !environment.containsKey(direction)) return true;
        return false;
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
