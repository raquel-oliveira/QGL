package fr.unice.polytech.qgl.qab.enums;

import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Gabriela
 * @version 4.9
 */
public class FoundTest {
    private Found found;

    @Test
    public void testFoundGround() {
        found = Found.GROUND;
        assertEquals(Found.GROUND, found);
    }

    @Test
    public void testFoundOutOfRange() {
        found = Found.OUT_OF_RANGE;
        assertEquals(Found.OUT_OF_RANGE, found);
    }

    @Test
    public void testToStringGround() {
        found = Found.GROUND;
        String foundString = found.toString();
        assertTrue(foundString.equalsIgnoreCase("ground"));
    }

    @Test
    public void testToStringOutOfRange() {
        found = Found.OUT_OF_RANGE;
        String foundString = found.toString();
        assertTrue(foundString.equalsIgnoreCase("out_of_range"));
    }


    @Test
    public void testEqualsGround() throws Exception {
        found = Found.GROUND;
        assertTrue(found.equals(Found.GROUND));
    }

    @Test
    public void testEqualsOutOfRange() throws Exception {
        found = Found.OUT_OF_RANGE;
        assertTrue(found.equals(Found.OUT_OF_RANGE));
    }

    @Test
    public void testFromStringGround() throws Exception {
        found = Found.valueOf("ground");
        assertTrue(found.equals(Found.GROUND));
    }

    @Test
    public void testFromStringOutOfRange() throws Exception {
        found = Found.valueOf("out_of_range");
        assertTrue(found.equals(Found.OUT_OF_RANGE));
    }

    @Test
    public void testFromStringNotExists() {
        found = Found.valueOf("error");
        assertEquals(null, found);
    }

    @Test
    public void testFromStringNothing() {
        found = Found.valueOf("");
        assertEquals(null, found);
    }

    @Test(expected = NullPointerException.class)
    public void testToStringBeforeInitialize() {
        found.toString();
    }
}