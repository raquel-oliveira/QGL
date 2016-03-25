package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Map;

/**
 * Version 02/03/2016.
 */
public class Transform extends Action {
    private static final String ACTION_TRANSFORM = "transform";
    private static final String ACTION = "action";
    private Map<PrimaryType, Integer> recipe;

    /**
     * Constructor to transform given the values of the recipe.
     * @param recipe
     */
    public Transform(Map<PrimaryType, Integer> recipe, Context context){
        this.recipe = recipe;
        //Decrease primary amounts used to create. Does not matter if the number of production is > 0 or not.
        for(java.util.Map.Entry<PrimaryType, Integer> ingredientRecipe : recipe.entrySet()) {
            PrimaryResource primary = new PrimaryResource(ingredientRecipe.getKey());
            context.decreaseAmountOfCollectedResources(primary, ingredientRecipe.getValue());
        }
    }

    @Override
    public boolean isValid(JSONObject jsonObject){
        if (jsonObject.has(ACTION)) {
            String action = jsonObject.getString(ACTION);
            if (!(ACTION_TRANSFORM).equals(action))
                return false;

            JSONObject params = jsonObject.getJSONObject(PARAMETERS);

            for (ManufacturedType type: ManufacturedType.values()) {
                if (params.has(type.toString()))
                    return false;
            }
        } else {
            return false;
        }

        return true;
    }

    @Override
    public String formatResponse() {
        JSONObject response = new JSONObject();
        response.put(ACTION_PARAMS, ACTION_TRANSFORM);

        JSONObject parameters = new JSONObject();
        for (Map.Entry<PrimaryType, Integer> resources: recipe.entrySet()) {
            PrimaryType type = resources.getKey();
            Integer amount = resources.getValue();

            parameters.put(type.name(), amount.toString());
        }

        response.put(PARAMETERS, parameters);
        return response.toString();
    }

}