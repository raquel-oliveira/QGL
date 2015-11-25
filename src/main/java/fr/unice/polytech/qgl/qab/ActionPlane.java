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
        String[] result = environment.get(direction);
        return (result[0].compareToIgnoreCase("GROUND") == 0)? true:false;
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
        if (takeAction.compareToIgnoreCase("ECHO") != 0 && !hasOutOfRange(head))
            return true;
        return false;
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
     * @return
     */
    public String directionToHeading(Direction head) {
        Random rand = new Random();
        int direction = rand.nextInt(2);
        if (head.isHorizontal()) {
            if (direction == 1 && possiblesToHeading("N")) return "N";
            else if (direction == 0 && possiblesToHeading("S")) return "S";
        }
        else if (head.isVertical()) {
            if (direction == 1 && possiblesToHeading("W")) return "W";
            else if (direction == 0 && possiblesToHeading("E")) return "E";
        }
        return "ECHO";
    }

    private boolean possiblesToHeading(String direction) {
        String[] result;
        if (!environment.isEmpty() && environment.containsKey(direction)) {
            result = environment.get(direction);
            if (result[0].compareToIgnoreCase("OUT_OF_RANGE") == 0) {
                if (Integer.parseInt(result[1]) >= 2)
                    return true;
            }
        }
        return false;
    }

    /**
     * Method to verify the better option to make the Echo
     * @param head
     * @return
     */
    public String whereEcho(Direction head) {
        Random rand = new Random();
        int direction = rand.nextInt(2);
        if (head.isHorizontal()) {
            if (direction == 1 && possiblesEcho("N")) return "N";
            else if (direction == 0 && possiblesEcho("S")) return "S";
        }
        else {
            if (direction == 1 && possiblesEcho("W")) return "W";
            else if (direction == 0 && possiblesEcho("E")) return "E";
        }
        return "FLY";
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
