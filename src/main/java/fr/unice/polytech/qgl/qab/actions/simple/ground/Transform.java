package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.json.JSONObject;

import java.util.Map;

/**
 * Version 02/03/2016.
 */
public class Transform extends Action {
    private static final String ACTION_TRANSFORM = "transform";
    private Map<PrimaryType, Integer> recipe;

    /**
     * Constructor to transform given the values of the recipe.
     * @param recipe
     */
    public Transform(Map<PrimaryType, Integer> recipe){
        this.recipe = recipe;
    }

    @Override
    public boolean isValid(JSONObject jsonObject){
        if (jsonObject.has(ACTION)) {
            String action = jsonObject.getString(ACTION);
            if (!(ACTION_TRANSFORM).equals(action))
                return false;
        }
        else return false;

        return true;
    }

    @Override
    public String formatResponse() {
        JSONObject response = new JSONObject();
        response.put("action", "transform");

        JSONObject parameters = new JSONObject();
        for (Map.Entry<PrimaryType, Integer> resources: recipe.entrySet()) {
            PrimaryType type = resources.getKey();
            Integer amount = resources.getValue();

            parameters.put(type.name(), amount.toString());
        }

        response.put("parameters", parameters);
        return response.toString();
    }

}
