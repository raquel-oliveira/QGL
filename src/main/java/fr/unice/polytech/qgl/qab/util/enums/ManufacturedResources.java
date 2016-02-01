package fr.unice.polytech.qgl.qab.util.enums;

import fr.unice.polytech.qgl.qab.resources.Resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Quentin Prod'Homme on 17.01.2016.
 * Raquel Lopes de Oliveira update at 01.02.2016
 */
public enum ManufacturedResources {
    GLASS(),
    INGOT(),
    LEATHER(),
    PLANK(),
    RUM();

    private Set<Biomes> biomes = new HashSet<>();

    HashMap<Resources, Integer> recipe = new HashMap<>();

    ManufacturedResources() {
        Resources res;
        switch (this){
            case GLASS:
                res = Resources.QUARTZ;
                biomes.addAll(res.getBiome());

                res = Resources.WOOD;
                biomes.addAll(res.getBiome());
                break;

            case INGOT:
                res = Resources.ORE;
                biomes.addAll(res.getBiome());

                res = Resources.WOOD;
                biomes.addAll(res.getBiome());
                break;

            case LEATHER:
                res = Resources.FUR;
                biomes.addAll(res.getBiome());
                break;

            case PLANK:
                res = Resources.WOOD;
                biomes.addAll(res.getBiome());
                break;

            case RUM:
                res = Resources.SUGAR_CANE;
                biomes.addAll(res.getBiome());
                res = Resources.FRUITS;

                biomes.addAll(res.getBiome());
                break;
        }
    }

    public Set<Biomes> getBiome(){
        return biomes;
    }

    public HashMap<Resources, Integer> getRecipe(int amountRecipe){
        if (amountRecipe < 1)
            new Exception("Invalid value\n");

        switch (this){
            case GLASS:
                recipe.put(Resources.QUARTZ, 5*amountRecipe);
                recipe.put(Resources.WOOD, 10*amountRecipe);
                break;

            case INGOT:
                recipe.put(Resources.WOOD, 5*amountRecipe);
                break;

            case LEATHER:
                recipe.put(Resources.FUR, 3*amountRecipe);
                break;

            case PLANK:;
                recipe.put(Resources.WOOD, amountRecipe/4 + ((amountRecipe % 4 == 0)? 0: 1));
                break;

            case RUM:
                recipe.put(Resources.SUGAR_CANE, 10*amountRecipe);
                recipe.put(Resources.FRUITS, amountRecipe);
                break;
        }

        return recipe;
    }
}