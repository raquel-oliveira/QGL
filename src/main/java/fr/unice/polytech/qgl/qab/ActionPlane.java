package fr.unice.polytech.qgl.qab;

import java.util.HashMap;
import java.util.Random;

/**
 * Class that represents the Actions of Plane phase.
 *
 * @version 4.8
 */
public class ActionPlane {
    private HashMap<String, String[]> environment; // N, ["GROUND", "4"]

    /**
     * Constructor of Class ActionPlane.
     */
    public ActionPlane() {
        environment = new HashMap<String, String[]>();
    }

    private boolean hasGround(String direction) {
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            String[] result = environment.get(direction);
            return (result[0].compareToIgnoreCase("GROUND") == 0) ? true : false;
        }
        return false;
    }

    /**
     * Method to set the range if there is GROUND in one direction
     * @param direction define the direction to set the ground founded
     * @param range
     */
    public void setGround(String direction, String range) {
        String[] dataGround = {"GROUND", range};
        environment.put(direction.toLowerCase(), dataGround);
    }

    private boolean hasOutOfRange(String direction) {
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            String[] result = environment.get(direction);
            return (result[0].compareToIgnoreCase("OUT_OF_RANGE") == 0) ? true : false;
        }
        return false;
    }

    /**
     * Method to set the range if there is limit to the map in one direction
     * @param direction define the direction to set the out_of_range founded
     * @param range
     */
    public void setOutOfRange(String direction, String range) {
        String[] dataGround = {"OUT_OF_RANGE", range};
        environment.put(direction.toLowerCase(), dataGround);
    }

    private int rangeGround(String direction) {
        String[] result = environment.get(direction);
        if (hasGround(direction))
            return Integer.parseInt(result[1]);
        return -1;
    }

    /**
     * Method to return the range to OUT_OF_RANGE
     * @param direction define the direction to get the out_or_range
     * @return
     */
    public int rangeOutOfRange(String direction) {
        String[] result;
        if (!environment.isEmpty()) {
            if (environment.containsKey(direction)) {
                result = environment.get(direction);
                if (hasOutOfRange(direction))
                    return Integer.parseInt(result[1]);
            }
        }
        return -1;
    }

    /**
     * Method
     * @param direction
     * @return
     */
    public boolean makeHeading(String direction, String action) {
        return (hasOutOfRange(direction) && action.compareToIgnoreCase("HEADING") != 0);
    }

    /**
     * Method to reset the environment.
     */
    public void resetEnvironment() {
        environment = new HashMap<String, String[]>();
    }

    /**
     * Method to check if make the Echo is a action possible.
     * @param takeAction the last action made.
     * @return
     */
    public boolean makeEcho(String takeAction, String head) {
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
    public boolean makeScan(String head, String takeAction) {
        if (!environment.isEmpty() && environment.containsKey(head)) {
            String[] result = environment.get(head);
            if (result[0].compareToIgnoreCase("GROUND") == 0 && (takeAction.compareToIgnoreCase("SCAN") != 0))
                if (Integer.parseInt(result[1]) == 0) return true;
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
        else if ((environment.containsKey(dir1) && hasOutOfRange(dir1)) &&
                (environment.containsKey(dir2) && hasOutOfRange(dir2))) {
            String[] envN = environment.get(dir1);
            String[] envS = environment.get(dir2);
            return (Integer.parseInt(envN[1]) > Integer.parseInt(envS[1]))? dir1:dir2;
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

    /**
     * Method to do the changes necassaries when the plane fly
     * @param head direction of bot head
     */
    public void fly(String head) {
        if (!environment.isEmpty() && environment.containsKey(head)) {
            String[] result = environment.get(head);
            int range = Integer.parseInt(result[1]);
            result[1] = Integer.toString(range - 1);
            environment.put(head, result);
        }
    }
}
