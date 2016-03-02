package fr.unice.polytech.qgl.qab.actions.simple.ground;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Version 02/03/2016.
 */
public class Transform extends Action{
    private static final String ACTION_TRANSFORM = "transform";
    private Map<PrimaryType, Integer> recipe;
    private List<String> recipeIngredients;

    /**
     * Constructor to transform' given the values of the recipe.
     * @param recipe
     */
    public Transform(Map<PrimaryType, Integer> recipe){
        super();
        this.recipe = recipe;
        for (PrimaryType name: recipe.keySet()){
            recipeIngredients.add(name.name());
        }
    }

    /**
     * Constructor transfom to create 1(ONE) resource manufactured.
     * @param resource
     */
    public Transform(ManufacturedResource resource){
        super();
        this.recipe = resource.getRecipe(1);
        for (PrimaryType name: recipe.keySet()){
            recipeIngredients.add(name.name());
        }
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
    public String formatResponse(){
        //TODO: check this
        return "{ \"action\": \"transform\", \"parameters\": {" + recipeIngredients.get(0) +":"+ recipe.get(recipeIngredients.get(0))+ "} }";
    }

}
