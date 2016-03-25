package fr.unice.polytech.qgl.qab.resources.manufactured;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.resources.Resource;
import static java.lang.Math.ceil;

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
    private boolean transformed = false;
    private static final double marginError = 10/9;

    public boolean isTransformed() {
        return transformed;
    }

    /**
     *
     * @param transformed true if it was already TRIED to transform
     */
    public void setTransformed(boolean transformed) {
        this.transformed = transformed;
    }

    /**
     * ManufacturedResource's constructor
     * @param resource
     */
    public ManufacturedResource(ManufacturedType resource) {
        this.resource = resource;
        setBiomes();
    }

    public ManufacturedType getType(){
        return resource;
    }

    @Override
    public String getName() {
        return resource.toString();
    }

    private void setBiomes() {
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
                recipe.put(PrimaryType.QUARTZ, (int)(ceil((10 * amountRecipe * marginError))));
                recipe.put(PrimaryType.WOOD, (int) (ceil(5 * amountRecipe * marginError)));
                break;

            case INGOT:
                recipe.put(PrimaryType.ORE, (int)(ceil(5 * amountRecipe * marginError)));
                recipe.put(PrimaryType.WOOD, (int)(ceil(5 * amountRecipe * marginError)));
                break;

            case LEATHER:
                recipe.put(PrimaryType.FUR, (int)(ceil(3 * amountRecipe * marginError)));
                break;

            case PLANK:;
                recipe.put(PrimaryType.WOOD, (int) (ceil((amountRecipe/4 + ((amountRecipe % 4 == 0)? 0: 1)) * marginError)));
                break;

            case RUM:
                recipe.put(PrimaryType.SUGAR_CANE, (int) (ceil(10 * amountRecipe * marginError)));
                recipe.put(PrimaryType.FRUITS, (int) (ceil(amountRecipe * marginError)));
                break;

            default:
                break;
        }
        return recipe;
    }

    public boolean isPrimary() {
        return false;
    }

    public static double getMarginError() {
        return marginError;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ManufacturedResource that = (ManufacturedResource) o;

        return resource == that.resource;

    }

    @Override
    public int hashCode() {
        return resource != null ? resource.hashCode() : 0;
    }
}