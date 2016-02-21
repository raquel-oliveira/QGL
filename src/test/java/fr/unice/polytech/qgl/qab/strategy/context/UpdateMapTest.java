package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.response.EchoResponse;
import fr.unice.polytech.qgl.qab.util.Discovery;
import fr.unice.polytech.qgl.qab.util.enums.Direction;
import fr.unice.polytech.qgl.qab.util.enums.Found;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @version 28/12/15.
 */
public class UpdateMapTest {
    UpdaterMap updaterMap;
    Map map;

    @Before
    public void defineContext() {
        updaterMap = new UpdaterMap();
        map = new Map();

        Position position = new Position(1, 1);
        map.setLastPosition(position);
    }

    @Test
    public void testInitializeDimensionsHeight() throws NegativeBudgetException {
        Context context = new Context();
        context.setFirstHead(Direction.NORTH);
        context.setHeading(Direction.NORTH);

        Discovery discovery = new Discovery();
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.NORTH, 9);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        Map map = new Map();

        updaterMap.initializeDimensions(context, map);
        assertEquals(10, map.getHeight());

        discovery = new Discovery();
        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.WEST, 9);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        updaterMap.initializeDimensions(context, map);
        assertEquals(9, map.getWidth());

        discovery = new Discovery();
        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.EAST, 5);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        updaterMap.initializeDimensions(context, map);
        assertEquals(15, map.getWidth());
    }

    @Test
    public void testInitializeDimensionsWidth() throws NegativeBudgetException, PositionOutOfMapRange {
        Context context = new Context();
        context.setFirstHead(Direction.EAST);
        context.setHeading(Direction.EAST);

        Discovery discovery = new Discovery();
        EchoResponse echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.EAST, 10);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        Map map = new Map();

        updaterMap.initializeDimensions(context, map);
        assertEquals(11, map.getWidth());

        discovery = new Discovery();
        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.NORTH, 9);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        updaterMap.initializeDimensions(context, map);
        assertEquals(9, map.getHeight());

        discovery = new Discovery();
        echoResponse = new EchoResponse();
        echoResponse.addData(Found.OUT_OF_RANGE, Direction.SOUTH, 5);
        discovery.setEchoResponse(echoResponse);
        context.setLastDiscovery(discovery);

        updaterMap.initializeDimensions(context, map);
        assertEquals(15, map.getHeight());

    }

    @Test
    public void testUpdateLastPositionYSouth() throws NegativeBudgetException {
        Context context = new Context();
        context.setHeading(Direction.SOUTH);
        context.setFirstHead(Direction.SOUTH);

        updaterMap.updateLastPositionFly(context, map);

        Position lastPosition = map.getLastPosition();

        assertEquals(1, lastPosition.getX());
        assertEquals(2, lastPosition.getY());
    }

    @Test
    public void testUpdateLastPositionYNorth() throws NegativeBudgetException {
        Context context = new Context();
        context.setHeading(Direction.NORTH);
        context.setFirstHead(Direction.NORTH);

        updaterMap.updateLastPositionFly(context, map);

        Position lastPosition = map.getLastPosition();

        assertEquals(1, lastPosition.getX());
        assertEquals(0, lastPosition.getY());
    }

    @Test
    public void testUpdateLastPositionXEast() throws NegativeBudgetException {
        Context context = new Context();
        context.setHeading(Direction.EAST);
        context.setFirstHead(Direction.EAST);

        updaterMap.updateLastPositionFly(context, map);

        Position lastPosition = map.getLastPosition();

        assertEquals(2, lastPosition.getX());
        assertEquals(1, lastPosition.getY());
    }

    @Test
    public void testUpdateLastPositionXWest() throws NegativeBudgetException {
        Context context = new Context();
        context.setHeading(Direction.WEST);
        context.setFirstHead(Direction.WEST);

        updaterMap.updateLastPositionFly(context, map);

        Position lastPosition = map.getLastPosition();

        assertEquals(0, lastPosition.getX());
        assertEquals(1, lastPosition.getY());
    }
}
