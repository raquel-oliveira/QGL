package fr.unice.polytech.qgl.qab.resource.Manufactured;

import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedResource;
import fr.unice.polytech.qgl.qab.resources.manufactured.ManufacturedType;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.ceil;
import static org.junit.Assert.assertEquals;

/**
 * @version 01/02/16.
 */
public class ManufacturedResourceTest {
    ManufacturedResource manufacturedResource;
    double marginError;

    @Ignore
    public void defineContext() {
        manufacturedResource = new ManufacturedResource(ManufacturedType.GLASS);
    }

    @Ignore
    public void testGetName() {
        assertEquals(ManufacturedType.GLASS.toString(), manufacturedResource.getName());

        manufacturedResource = new ManufacturedResource(ManufacturedType.INGOT);
        assertEquals(ManufacturedType.INGOT.toString(), manufacturedResource.getName());

        manufacturedResource = new ManufacturedResource(ManufacturedType.LEATHER);
        assertEquals(ManufacturedType.LEATHER.toString(), manufacturedResource.getName());

        manufacturedResource = new ManufacturedResource(ManufacturedType.PLANK);
        assertEquals(ManufacturedType.PLANK.toString(), manufacturedResource.getName());

        manufacturedResource = new ManufacturedResource(ManufacturedType.RUM);
        assertEquals(ManufacturedType.RUM.toString(), manufacturedResource.getName());
    }

    @Ignore
    public void testRecipe() {
        Map<PrimaryType,Integer> map = manufacturedResource.getRecipe(10);

        int amountRecipe = 10;
        Map<PrimaryType,Integer> mapDefault = new HashMap<>();
        mapDefault.put(PrimaryType.QUARTZ, (int) ceil( 10 * amountRecipe * marginError));
        mapDefault.put(PrimaryType.WOOD, (int) ceil( 5 * amountRecipe * marginError));

        assertEquals(mapDefault, map);

        manufacturedResource = new ManufacturedResource(ManufacturedType.INGOT);
        map = manufacturedResource.getRecipe(10);
        mapDefault = new HashMap<>();
        mapDefault.put(PrimaryType.WOOD, (int) ceil( 5 * amountRecipe * marginError));

        assertEquals(mapDefault, map);

        manufacturedResource = new ManufacturedResource(ManufacturedType.LEATHER);
        map = manufacturedResource.getRecipe(10);
        mapDefault = new HashMap<>();
        mapDefault.put(PrimaryType.FUR, (int) ceil( 3 * amountRecipe * marginError));

        assertEquals(mapDefault, map);

        manufacturedResource = new ManufacturedResource(ManufacturedType.PLANK);
        map = manufacturedResource.getRecipe(10);
        mapDefault = new HashMap<>();
        mapDefault.put(PrimaryType.WOOD, (int) (ceil((amountRecipe/4 + ((amountRecipe % 4 == 0)? 0: 1)) * marginError)));

        assertEquals(mapDefault, map);


        manufacturedResource = new ManufacturedResource(ManufacturedType.RUM);
        map = manufacturedResource.getRecipe(10);
        mapDefault = new HashMap<>();
        mapDefault.put(PrimaryType.SUGAR_CANE, (int) ceil( 10 * amountRecipe * marginError));
        mapDefault.put(PrimaryType.FRUITS, (int) ceil(amountRecipe * marginError));

        assertEquals(mapDefault, map);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testBagArgument() {
        manufacturedResource = new ManufacturedResource(ManufacturedType.RUM);
        Map<PrimaryType,Integer> map = manufacturedResource.getRecipe(-10);
    }
}