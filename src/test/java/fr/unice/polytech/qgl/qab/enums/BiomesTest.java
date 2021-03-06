package fr.unice.polytech.qgl.qab.enums;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.util.enums.TypeBiome;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @version 17/12/2015.
 */
public class BiomesTest {
    private Biomes biome;
    private Biomes biome2;

    @Test
    public void testBiomeOcean() {
        biome = Biomes.OCEAN;
        assertEquals(Biomes.OCEAN, biome);
    }

    @Test
    public void testIsCommon() {
        biome = Biomes.OCEAN;
        assertEquals(true, biome.isCommon());
        biome = Biomes.LAKE;
        assertEquals(true, biome.isCommon());
        biome = Biomes.BEACH;
        assertEquals(true, biome.isCommon());
        biome = Biomes.GRASSLAND;
        assertEquals(true, biome.isCommon());
    }

    @Test
    public void testIsNotCommon() {
        biome = Biomes.MANGROVE ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.TROPICAL_RAIN_FOREST ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.TROPICAL_SEASONAL_FOREST ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.TEMPERATE_DECIDUOUS_FOREST ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.TEMPERATE_DESERT ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.TEMPERATE_RAIN_FOREST ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.TAIGA ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.SNOW ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.TUNDRA ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.ALPINE  ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.GLACIER ;
        assertEquals(false, biome.isCommon());
        biome = Biomes.SHRUBLAND;
        assertEquals(false, biome.isCommon());
        biome = Biomes.SUB_TROPICAL_DESERT;
        assertEquals(false, biome.isCommon());
    }

    @Test
    public void testIsTropical() {
        biome = Biomes.OCEAN;
        assertEquals(false, biome.isTropical());
        biome = Biomes.LAKE;
        assertEquals(false, biome.isTropical());
        biome = Biomes.BEACH;
        assertEquals(false, biome.isTropical());
        biome = Biomes.GRASSLAND;
        assertEquals(false, biome.isTropical());
        biome = Biomes.MANGROVE;
        assertEquals(true, biome.isTropical());
        biome = Biomes.TROPICAL_RAIN_FOREST;
        assertEquals(true, biome.isTropical());
        biome = Biomes.TROPICAL_SEASONAL_FOREST;
        assertEquals(true, biome.isTropical());
    }

    @Test
    public void testIsNotTropical() {
        biome = Biomes.TEMPERATE_DECIDUOUS_FOREST ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.TEMPERATE_DESERT ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.TEMPERATE_RAIN_FOREST ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.TAIGA ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.SNOW ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.TUNDRA ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.ALPINE  ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.GLACIER ;
        assertEquals(false, biome.isTropical());
        biome = Biomes.SHRUBLAND;
        assertEquals(false, biome.isTropical());
        biome = Biomes.SUB_TROPICAL_DESERT;
        assertEquals(false, biome.isTropical());
    }

    @Test
    public void testIsTemperate() {
        biome = Biomes.OCEAN;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.LAKE;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.BEACH;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.GRASSLAND;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.MANGROVE;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.TROPICAL_RAIN_FOREST;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.TROPICAL_SEASONAL_FOREST;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.TEMPERATE_DECIDUOUS_FOREST;
        assertEquals(true, biome.isTemperate());
        biome = Biomes.TEMPERATE_DESERT;
        assertEquals(true, biome.isTemperate());
        biome = Biomes.TEMPERATE_RAIN_FOREST;
        assertEquals(true, biome.isTemperate());
    }

    @Test
    public void testIsNotTemperate() {
        biome = Biomes.TAIGA ;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.SNOW ;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.TUNDRA ;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.ALPINE  ;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.GLACIER ;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.SHRUBLAND;
        assertEquals(false, biome.isTemperate());
        biome = Biomes.SUB_TROPICAL_DESERT;
        assertEquals(false, biome.isTemperate());
    }

    @Test
    public void testIsNotNordic() {
        biome = Biomes.OCEAN;
        assertEquals(false, biome.isNordic());
        biome = Biomes.LAKE;
        assertEquals(false, biome.isNordic());
        biome = Biomes.BEACH;
        assertEquals(false, biome.isNordic());
        biome = Biomes.GRASSLAND;
        assertEquals(false, biome.isNordic());
        biome = Biomes.MANGROVE;
        assertEquals(false, biome.isNordic());
        biome = Biomes.TROPICAL_RAIN_FOREST;
        assertEquals(false, biome.isNordic());
        biome = Biomes.TROPICAL_SEASONAL_FOREST;
        assertEquals(false, biome.isNordic());
        biome = Biomes.TEMPERATE_DECIDUOUS_FOREST;
        assertEquals(false, biome.isNordic());
        biome = Biomes.TEMPERATE_DESERT;
        assertEquals(false, biome.isNordic());
        biome = Biomes.TEMPERATE_RAIN_FOREST;
        assertEquals(false, biome.isNordic());
        biome = Biomes.SHRUBLAND;
        assertEquals(false, biome.isNordic());
        biome = Biomes.SUB_TROPICAL_DESERT;
        assertEquals(false, biome.isNordic());
    }
    @Test
    public void testIsNordic() {
        biome = Biomes.TAIGA ;
        assertEquals(true, biome.isNordic());
        biome = Biomes.SNOW ;
        assertEquals(true, biome.isNordic());
        biome = Biomes.TUNDRA ;
        assertEquals(true, biome.isNordic());
        biome = Biomes.ALPINE  ;
        assertEquals(true, biome.isNordic());
        biome = Biomes.GLACIER ;
        assertEquals(true, biome.isNordic());
    }

    @Test
    public void testIsNotSubTropical() {
        biome = Biomes.OCEAN;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.LAKE;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.BEACH;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.GRASSLAND;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.MANGROVE;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.TROPICAL_RAIN_FOREST;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.TROPICAL_SEASONAL_FOREST;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.TEMPERATE_DECIDUOUS_FOREST;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.TEMPERATE_DESERT;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.TEMPERATE_RAIN_FOREST;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.TAIGA;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.SNOW;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.TUNDRA;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.ALPINE;
        assertEquals(false, biome.isSubTropical());
        biome = Biomes.GLACIER;
        assertEquals(false, biome.isSubTropical());
    }

    @Test
    public void testIsSubTropical() {
        biome = Biomes.SHRUBLAND;
        assertEquals(true, biome.isSubTropical());
        biome = Biomes.SUB_TROPICAL_DESERT;
        assertEquals(true, biome.isSubTropical());
    }

    @Test(expected = NullPointerException.class)
    public void testToStringBeforeInitialize() {
        biome.toString();
    }

    @Test (expected = NullPointerException.class)
    public void testObjectNull() {
        biome = null;
        biome.typeBiome();
    }

    @Test
    public void testToString() {
        biome = biome.OCEAN;
        assertTrue("OCEAN".equals(biome.toString()));
    }

    @Test
    public void testIsEqual() {
        biome = Biomes.ALPINE;
        biome2 = Biomes.ALPINE;
        assertTrue(biome.equals(biome2));
    }

    @Test
    public void testTypeBiomeCommon() {
        biome = Biomes.BEACH;
        assertEquals(biome.typeBiome(), TypeBiome.COMMON);
    }

    @Test
    public void testTypeBiomeNordic() {
        biome = Biomes.TAIGA;
        assertEquals(biome.typeBiome(), TypeBiome.NORDIC);
    }

    @Test
    public void testTypeBiomeSubTropical() {
        biome = Biomes.SHRUBLAND;
        assertEquals(biome.typeBiome(), TypeBiome.SUBTROPICAL);
    }

    @Test
    public void testTypeBiomeTropical() {
        biome = Biomes.MANGROVE;
        assertEquals(biome.typeBiome(), TypeBiome.TROPICAL);
    }

    @Test
    public void testTypeBiomeTemperate() {
        biome = Biomes.TEMPERATE_DECIDUOUS_FOREST;
        assertEquals(biome.typeBiome(), TypeBiome.TEMPERATE);
    }
}
