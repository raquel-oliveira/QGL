package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @version 06/03/16.
 */
public class MapHandler {
    private Map mapTmp;

    public MapHandler() {
        mapTmp = new Map();
    }

    public static void updatePositionX(int x, Map map, Direction dir) {
        if (dir.equals(Direction.EAST))
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() + x);
        else
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() - x);
    }

    public static void updatePositionY(int y, Map map, Direction dir) {
        if (dir.equals(Direction.NORTH))
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() - y);
        else
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() + y);
    }

    public static int calcDistX(Position p, Position lastPosition) {
        return Math.abs(p.getX() - lastPosition.getX());
    }

    public static int calcDistY(Position p, Position lastPosition) {
        return Math.abs(p.getY() - lastPosition.getY());
    }

    public static double getDistance(Position current, Position p) {
        double distX = getDistX(current, p);
        double distY = getDistY(current, p);
        return distX + distY;
    }

    public static double getDistY(Position current, Position p) {
        return (double) Math.abs(p.getY() - current.getY());
    }

    public static double getDistX(Position current, Position p) {
        return (double) Math.abs(p.getX() - current.getX());
    }

    public void completMap(Map mapCurrent) {
        mapTmp.copy(mapCurrent);

        HashMap<Position, Tile> tmpTiles = new HashMap<>();
        tmpTiles.putAll(mapTmp.getTiles());

        for (java.util.Map.Entry<Position, Tile> entry : tmpTiles.entrySet()) {
            Position p = entry.getKey();
            Tile t = entry.getValue();
            List<Biomes> comums = analizeTiles(t, p, mapCurrent);
            if (comums != null) {
                setBiomaComum(p, mapCurrent, comums);
                mapCurrent.copy(mapTmp);
            }
        }
    }

    private static List<Biomes> analizeTiles(Tile t, Position p, Map map) {
        Tile t1 = map.getTileOverride(new Position(p.getX() + 2, p.getY()));
        Tile t2 = map.getTileOverride(new Position(p.getX() + 2, p.getY() + 2));
        Tile t3 = map.getTileOverride(new Position(p.getX(), p.getY() + 2));
        List<Biomes> comums = new ArrayList<>();
        comums.addAll(t.getBiomesPredominant());

        if (t1 != null && t2 != null && t3 != null) {
            comums.retainAll(t1.getBiomesPredominant());
            comums.retainAll(t2.getBiomesPredominant());
            comums.retainAll(t3.getBiomesPredominant());

            if (!comums.isEmpty()) {
                return comums;
            }
        }
        return Collections.emptyList();
    }

    private void setBiomaComum(Position p, Map map, List<Biomes> comuns) {
        Tile t1 = map.getTile(new Position(p.getX() + 1, p.getY()));
        Tile t2 = map.getTile(new Position(p.getX() + 2, p.getY() + 1));
        Tile t3 = map.getTile(new Position(p.getX() + 1, p.getY() + 2));
        Tile t4 = map.getTile(new Position(p.getX(), p.getY() + 1));
        Tile t5 = map.getTile(new Position(p.getX() + 1, p.getY() + 1));

        if (t1 == null) {
            mapTmp.addBiome(new Position(p.getX() + 1, p.getY()), comuns, new ArrayList<>());
        }

        if (t2 == null) {
            mapTmp.addBiome(new Position(p.getX() + 2, p.getY() + 1), comuns, new ArrayList<>());
        }

        if (t3 == null) {
            mapTmp.addBiome(new Position(p.getX() + 1, p.getY() + 2), comuns, new ArrayList<>());
        }

        if (t4 == null) {
            mapTmp.addBiome(new Position(p.getX(), p.getY() + 1), comuns, new ArrayList<>());
        }

        if (t5 == null) {
            mapTmp.addBiome(new Position(p.getX() + 1, p.getY() + 1), comuns, new ArrayList<>());
        }
    }
}
