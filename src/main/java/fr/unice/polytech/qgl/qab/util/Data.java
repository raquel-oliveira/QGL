package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.ActionAerial;
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
        this.act = new ActionAerial();
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
