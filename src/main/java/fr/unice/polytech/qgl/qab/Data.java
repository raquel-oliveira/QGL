package fr.unice.polytech.qgl.qab;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Class that represents the data json in the simulator.
 *
 * @version 4.8
 */
public class Data {
    private String action;
    private String direction;

    /**
     * Contructor of Data class
     */
    public Data() {
        this.action = "";
        this.direction = "";
    }

    /**
     * Method to set the values of data.
     * @param jsonObj
     * @return true if did't occurred, false if did
     */
    public boolean setData(JSONObject jsonObj) {
        if (isValide(jsonObj)) {
            action = jsonObj.getString("action");
            if (action.compareToIgnoreCase("echo") == 0 || action.compareToIgnoreCase("heading") == 0)
                direction = jsonObj.getJSONObject("parameters").getString("direction");
            return true;
        }
        return false;
    }

    /**
     * Method to check if the object json is valid
     * @param jsonObj
     * @return true if the data json is valid
     */
    public static boolean isValide(JSONObject jsonObj) {
        if (jsonObj.has("action")) {
            String act = jsonObj.getString("action");
            if (act.compareToIgnoreCase("fly") == 0) return true;
            else if (act.compareToIgnoreCase("scan") == 0) return true;
            else if (act.compareToIgnoreCase("echo") == 0 || act.compareToIgnoreCase("heading") == 0) {
                if (jsonObj.has("parameters")) {
                    JSONObject parameters = jsonObj.getJSONObject("parameters");
                    if (!parameters.has("direction")) return false;
                    String dir = jsonObj.getJSONObject("parameters").getString("direction");
                    if (dir.compareToIgnoreCase("N") == 0 ||
                            dir.compareToIgnoreCase("S") == 0 ||
                            dir.compareToIgnoreCase("W") == 0 ||
                            dir.compareToIgnoreCase("E") == 0)
                        return true;

                } else return false;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        final Data data = (Data)obj;

        if (this.action.compareToIgnoreCase(data.action) != 0) return false;
        if (this.action.compareToIgnoreCase("echo") != 0 || this.action.compareToIgnoreCase("heading") != 0) {
            if (this.direction.compareToIgnoreCase("N") != 0 && this.direction.compareToIgnoreCase("S") != 0 &&
                    this.direction.compareToIgnoreCase("W") != 0 && this.direction.compareToIgnoreCase("E") != 0)
            return false;
        }

        return true;
    }

}
