package fr.unice.polytech.qgl.qab.util.enums;

import fr.unice.polytech.qgl.qab.resources.Resource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Quentin Prod'Homme on 17/01/2016.
 * Raquel Lopes de Oliveira update at 01.02.2016
 */
public enum ManufacturedResources {
    GLASS(),
    INGOT(),
    LEATHER(),
    PLANK(),
    RUM();

    private Set<Biomes> biomes = new HashSet<>();
    private int qtdResources;

    HashMap<Resources, Integer> recipeTmp = new HashMap<>();
    ManufacturedResources() {
        Resources res;
        switch (this){
            case GLASS:
                res = Resources.QUARTZ;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, 5*qtdResources);

                res = Resources.WOOD;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, 10*qtdResources);
                break;
            case INGOT:
                res = Resources.ORE;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, 5*qtdResources);
                res = Resources.WOOD;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, 5*qtdResources);
                break;
            case LEATHER:
                res = Resources.FUR;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, 3*qtdResources);
                break;
            case PLANK:
                res = Resources.WOOD;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, qtdResources/4 + ((qtdResources % 4 == 0)? 0: 1));
                break;
            case RUM:
                res = Resources.SUGAR_CANE;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, 10*qtdResources);
                res = Resources.FRUITS;
                biomes.addAll(res.getBiome());
                recipeTmp.put(res, qtdResources);
                break;
        }
    }

    public int getQtdResources(){
        return qtdResources;
    }

    public void setQtdResources(int qtd){
        qtdResources = qtd;
    }

    public Set<Biomes> getBiome(){
        return biomes;
    }
    
}