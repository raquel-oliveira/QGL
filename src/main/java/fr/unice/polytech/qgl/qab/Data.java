package fr.unice.polytech.qgl.qab;

import fr.unice.polytech.qgl.qab.engine.Action;
import fr.unice.polytech.qgl.qab.engine.ActionPlane;
import fr.unice.polytech.qgl.qab.enums.Direction;
import org.json.JSONObject;

/**
 * Class that represents the data json in the simulator.
 *
 * @version 4.8
 */
public class Data {
    private static Action act;
    private Direction direction;

    /**
     * Contructor of Data class
     */
    public Data() {
        this.act = new ActionPlane();
        this.direction = null;
    }

    /**
     * Method to check if the object json is valid
     * @param jsonObj object json to check if is valid
     * @return true if the data json is valid
     */
    public static boolean isValide(JSONObject jsonObj) {
        return act.isValid(jsonObj);
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

}
