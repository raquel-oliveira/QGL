package fr.unice.polytech.qgl.qab.resource.Primary;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @version 01/02/16.
 */
public class PrimaryResourceTest {
    PrimaryResource primaryResource;

    @Before
    public void defineContext() {
        primaryResource = new PrimaryResource(PrimaryType.FRUITS);
    }

    @Test
    public void testGetName() {
        assertEquals(PrimaryType.FRUITS.toString(), primaryResource.getName());
    }

    @Test
    public void testBiomesFruits() {
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.TROPICAL_RAIN_FOREST));
        assertTrue(biomes.contains(Biomes.TROPICAL_SEASONAL_FOREST));
    }

    @Test
    public void testBiomesFish() {
        primaryResource = new PrimaryResource(PrimaryType.FISH);
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.OCEAN));
        assertTrue(biomes.contains(Biomes.LAKE));
    }

    @Test
    public void testBiomesFlower() {
        primaryResource = new PrimaryResource(PrimaryType.FLOWER);
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.ALPINE));
        assertTrue(biomes.contains(Biomes.GLACIER));
        assertTrue(biomes.contains(Biomes.MANGROVE));
    }

    @Test
    public void testBiomesFur() {
        primaryResource = new PrimaryResource(PrimaryType.FUR);
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.GRASSLAND));
        assertTrue(biomes.contains(Biomes.TEMPERATE_RAIN_FOREST));
    }

    @Test
    public void testBiomesOre() {
        primaryResource = new PrimaryResource(PrimaryType.ORE);
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.ALPINE));
        assertTrue(biomes.contains(Biomes.SUB_TROPICAL_DESERT));
        assertTrue(biomes.contains(Biomes.TEMPERATE_DESERT));
    }

    @Test
    public void testBiomesQuartz() {
        primaryResource = new PrimaryResource(PrimaryType.QUARTZ);
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.BEACH));
        assertTrue(biomes.contains(Biomes.SUB_TROPICAL_DESERT));
        assertTrue(biomes.contains(Biomes.TEMPERATE_DESERT));
    }

    @Test
    public void testBiomesSugar() {
        primaryResource = new PrimaryResource(PrimaryType.SUGAR_CANE);
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.TROPICAL_RAIN_FOREST));
        assertTrue(biomes.contains(Biomes.TROPICAL_SEASONAL_FOREST));
    }

    @Test
    public void testBiomesWood() {
        primaryResource = new PrimaryResource(PrimaryType.WOOD);
        Set<Biomes> biomes = primaryResource.getBiome();
        assertTrue(biomes.contains(Biomes.MANGROVE));
        assertTrue(biomes.contains(Biomes.TEMPERATE_DECIDUOUS_FOREST));
        assertTrue(biomes.contains(Biomes.TEMPERATE_RAIN_FOREST));
        assertTrue(biomes.contains(Biomes.TROPICAL_RAIN_FOREST));
        assertTrue(biomes.contains(Biomes.TROPICAL_SEASONAL_FOREST));
    }
}
