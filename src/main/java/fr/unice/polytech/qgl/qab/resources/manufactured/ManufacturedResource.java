package fr.unice.polytech.qgl.qab.resources.manufactured;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.resources.Resource;

import java.util.*;

/**
 * @version 01/02/16.
 *
 * Class that represent the manufactured resources
 */
public class ManufacturedResource implements Resource {
    private ManufacturedType resource;
    private EnumMap<PrimaryType, Integer> recipe = new EnumMap<PrimaryType, Integer>(PrimaryType.class);
    private Set<Biomes> biomes = new HashSet<>();

    /**
     * ManufacturedResource's constructor
     * @param resource
     */
    public ManufacturedResource(ManufacturedType resource) {
        this.resource = resource;
        setBiomes();
    }

    @Override
    public String getName() {
        return resource.toString();
    }

    @Override
    public void setBiomes() {
        PrimaryResource res;
        switch (resource){
            case GLASS:
                res = new PrimaryResource(PrimaryType.QUARTZ);
                biomes.addAll(res.getBiome());
                res =  new PrimaryResource(PrimaryType.WOOD);
                biomes.addAll(res.getBiome());
                break;
            case INGOT:
                res =  new PrimaryResource(PrimaryType.ORE);
                biomes.addAll(res.getBiome());
                res =  new PrimaryResource(PrimaryType.WOOD);
                biomes.addAll(res.getBiome());
                break;
            case LEATHER:
                res = new PrimaryResource(PrimaryType.FUR);
                biomes.addAll(res.getBiome());
                break;

            case PLANK:
                res = new PrimaryResource(PrimaryType.WOOD);
                biomes.addAll(res.getBiome());
                break;

            case RUM:
                res = new PrimaryResource(PrimaryType.SUGAR_CANE);
                biomes.addAll(res.getBiome());
                res = new PrimaryResource(PrimaryType.FRUITS);
                biomes.addAll(res.getBiome());
                break;
            default:
                break;
        }
    }

    @Override
    public Set<Biomes> getBiome(){
        return biomes;
    }

    /**
     * Return the "recipe" to making this manufactured resource
     * @param amountRecipe
     * @return
     */
    public Map<PrimaryType, Integer> getRecipe(int amountRecipe){
        if (amountRecipe < 0)
            throw new IllegalArgumentException("Not allow negative value");

        switch (resource){
            case GLASS:
                recipe.put(PrimaryType.QUARTZ, 10 * amountRecipe);
                recipe.put(PrimaryType.WOOD, 5 * amountRecipe);
                break;

            case INGOT:
                recipe.put(PrimaryType.WOOD, 5 * amountRecipe);
                break;

            case LEATHER:
                recipe.put(PrimaryType.FUR, 3 * amountRecipe);
                break;

            case PLANK:;
                recipe.put(PrimaryType.WOOD, amountRecipe/4 + ((amountRecipe % 4 == 0)? 0: 1));
                break;

            case RUM:
                recipe.put(PrimaryType.SUGAR_CANE, 10 * amountRecipe);
                recipe.put(PrimaryType.FRUITS, amountRecipe);
                break;

            default:
                break;
        }
        return recipe;
    }

   
}
