package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class to handle with the map information
 * @version 06/03/16.
 */
public class MapHandler {
    private Map map_tmp;

    public MapHandler() {
        map_tmp = new Map();
    }

    /**
     * Update the last position when the explorers move
     * @param x position x
     * @param map current map data
     * @param dir move direction
     */
    public static void updatePositionX(int x, Map map, Direction dir) {
        if (dir.equals(Direction.EAST))
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() + x);
        else
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() - x);
    }

    /**
     * Update the last position when the explorers move
     * @param y position y
     * @param map current map data
     * @param dir move direction
     */
    public static void updatePositionY(int y, Map map, Direction dir) {
        if (dir.equals(Direction.NORTH))
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() - y);
        else
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() + y);
    }

    /**
     * Calcule the distance between two coordenates x
     * @param p postion p
     * @param lastPosition last postion of the explorers
     * @return return the distance
     */
    public static int calcDistX(Position p, Position lastPosition) {
        return Math.abs(p.getX() - lastPosition.getX());
    }

    /**
     * Calcule the distance between two coordenates y
     * @param p postion p
     * @param lastPosition last postion of the explorers
     * @return return the distance
     */
    public static int calcDistY(Position p, Position lastPosition) {
        return Math.abs(p.getY() - lastPosition.getY());
    }

    /**
     * Get the distance between two positions
     * @param current current position
     * @param p position to go
     * @return the distance
     */
    public static int getDistance(Position current, Position p) {
        int distX = getDistX(current, p);
        int distY = getDistY(current, p);
        return distX + distY;
    }

    /**
     * Get the Distance of the axis Y between two positions
     * @param current current position
     * @param p second position
     * @return the distance of the axis Y
     */
    public static int getDistY(Position current, Position p) {
        return Math.abs(p.getY() - current.getY());
    }

    /**
     * Get the Distance of the axis X between two positions
     * @param current current position
     * @param p second position
     * @return the distance of the axis X
     */
    public static int getDistX(Position current, Position p) {
        return Math.abs(p.getX() - current.getX());
    }

    /**
     * Method that will analize the map and complete the tile with the most probable biomes
     * @param mapCurrent
     */
    public void completMap(Map mapCurrent) {
        map_tmp.copy(mapCurrent);

        HashMap<Position, Tile> tmpTiles = new HashMap<>();
        tmpTiles.putAll(map_tmp.getTiles());

        for (Position p: tmpTiles.keySet()) {
            Tile t = tmpTiles.get(p);
            List<Biomes> comums = analizeTiles(t, p, mapCurrent);
            if (comums != null) {
                setBiomaComum(p, mapCurrent, comums);
                mapCurrent.copy(map_tmp);
            }
        }
    }

    private List<Biomes> analizeTiles(Tile t, Position p, Map map) {
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
        return null;
    }

    private void setBiomaComum(Position p, Map map, List<Biomes> comuns) {
        //Tile t1 = map.getTile(new Position(p.getX() + 1, p.getY()));
        //Tile t2 = map.getTile(new Position(p.getX() + 2, p.getY() + 1));
        //Tile t3 = map.getTile(new Position(p.getX() + 1, p.getY() + 2));
        //Tile t4 = map.getTile(new Position(p.getX(), p.getY() + 1));
        Tile t5 = map.getTile(new Position(p.getX() + 1, p.getY() + 1));

        /*if (t1 == null) {
            map_tmp.addBiome(new Position(p.getX() + 1, p.getY()), comuns, new ArrayList<>());
        }

        if (t2 == null) {
            map_tmp.addBiome(new Position(p.getX() + 2, p.getY() + 1), comuns, new ArrayList<>());
        }

        if (t3 == null) {
            map_tmp.addBiome(new Position(p.getX() + 1, p.getY() + 2), comuns, new ArrayList<>());
        }

        if (t4 == null) {
            map_tmp.addBiome(new Position(p.getX(), p.getY() + 1), comuns, new ArrayList<>());
        }*/

        if (t5 == null) {
            map_tmp.addBiome(new Position(p.getX() + 1, p.getY() + 1), comuns, new ArrayList<>());
        }
    }
}
