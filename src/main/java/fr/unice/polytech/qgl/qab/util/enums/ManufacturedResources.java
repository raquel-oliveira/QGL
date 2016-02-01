package fr.unice.polytech.qgl.qab.util.enums;

import fr.unice.polytech.qgl.qab.resources.Resource;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Quentin Prod'Homme on 17/01/2016.
 * Raquel Lopes de Oliveira update at 01.02.2016
 */
public enum ManufacturedResources {
    GLASS,
    INGOT,
    LEATHER,
    PLANK,
    RUM;

    private Set<Biomes> biomes = new HashSet<>();

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
}