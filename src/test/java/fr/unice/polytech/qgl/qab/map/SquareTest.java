package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.square.Square;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by huang on 27/02/16.
 */
public class SquareTest {
    private Square square;

    @Before
    public void defineSquare(){
        square = new Square();
    }

    @Test
    public void testSetBiome(){
        square.setBiome(Biomes.BEACH);
        assertEquals(Biomes.BEACH,square.getBiome());

        square.setBiome(Biomes.ALPINE);
        assertEquals(Biomes.ALPINE,square.getBiome());

        square.setBiome(Biomes.LAKE);
        assertEquals(Biomes.LAKE,square.getBiome());

    }
}
