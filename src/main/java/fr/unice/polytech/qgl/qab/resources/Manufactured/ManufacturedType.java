package fr.unice.polytech.qgl.qab.resources.Manufactured;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.PrimaryType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Quentin Prod'Homme on 17.01.2016.
 * Raquel Lopes de Oliveira update at 01.02.2016
 */
public enum ManufacturedType {
    GLASS(),
    INGOT(),
    LEATHER(),
    PLANK(),
    RUM();

    private Set<Biomes> biomes = new HashSet<>();

    HashMap<PrimaryType, Integer> recipe = new HashMap<>();

    ManufacturedType() {
        PrimaryType res;
        switch (this){
            case GLASS:
                res = PrimaryType.QUARTZ;
                biomes.addAll(res.getBiome());

                res = PrimaryType.WOOD;
                biomes.addAll(res.getBiome());
                break;

            case INGOT:
                res = PrimaryType.ORE;
                biomes.addAll(res.getBiome());

                res = PrimaryType.WOOD;
                biomes.addAll(res.getBiome());
                break;

            case LEATHER:
                res = PrimaryType.FUR;
                biomes.addAll(res.getBiome());
                break;

            case PLANK:
                res = PrimaryType.WOOD;
                biomes.addAll(res.getBiome());
                break;

            case RUM:
                res = PrimaryType.SUGAR_CANE;
                biomes.addAll(res.getBiome());
                res = PrimaryType.FRUITS;

                biomes.addAll(res.getBiome());
                break;
        }
    }

    public Set<Biomes> getBiome(){
        return biomes;
    }

    public HashMap<PrimaryType, Integer> getRecipe(int amountRecipe){
        if (amountRecipe < 1)
            new Exception("Invalid value\n");

        switch (this){
            case GLASS:
                recipe.put(PrimaryType.QUARTZ, 5*amountRecipe);
                recipe.put(PrimaryType.WOOD, 10*amountRecipe);
                break;

            case INGOT:
                recipe.put(PrimaryType.WOOD, 5*amountRecipe);
                break;

            case LEATHER:
                recipe.put(PrimaryType.FUR, 3*amountRecipe);
                break;

            case PLANK:;
                recipe.put(PrimaryType.WOOD, amountRecipe/4 + ((amountRecipe % 4 == 0)? 0: 1));
                break;

            case RUM:
                recipe.put(PrimaryType.SUGAR_CANE, 10*amountRecipe);
                recipe.put(PrimaryType.FRUITS, amountRecipe);
                break;
        }

        return recipe;
    }
}