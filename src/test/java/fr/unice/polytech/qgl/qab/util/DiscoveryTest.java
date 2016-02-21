package fr.unice.polytech.qgl.qab.util;

import fr.unice.polytech.qgl.qab.response.EchoResponse;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 4.9
 */
public class DiscoveryTest {
    private Discovery d;

    @Test
    public void testInitializeGround() {
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.GROUND, Direction.SOUTH, 10);
        d = new Discovery();
        d.setEchoResponse(echoResponse);
        assertEquals(Found.GROUND, d.getEchoResponse().getFound());
        assertEquals(10, d.getEchoResponse().getRange());
        assertEquals(Direction.SOUTH, d.getEchoResponse().getDirection());
    }

    @Test
    public void testInitializeOutOfRange() {
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.NORTH, 10);
        d = new Discovery();
        d.setEchoResponse(echoResponse);

        assertEquals(Found.OUT_OF_RANGE, d.getEchoResponse().getFound());
        assertEquals(10, d.getEchoResponse().getRange());
        assertEquals(Direction.NORTH, d.getEchoResponse().getDirection());
    }

    @Test
    public void testChangeRange() {
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.EAST, 10);
        d = new Discovery();
        d.setEchoResponse(echoResponse);

        assertEquals(Found.OUT_OF_RANGE, d.getEchoResponse().getFound());
        assertEquals(10, d.getEchoResponse().getRange());
        assertEquals(Direction.EAST, d.getEchoResponse().getDirection());
    }
}
