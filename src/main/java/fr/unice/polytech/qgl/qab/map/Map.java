package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.tile.*;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.contracts.ContractItem;
import java.util.*;

/**
 * @version 8.12.2016
 *
 * Class that represent the map of the simulation
 */
public class Map {
    private HashMap<Position, Tile> tiles;
    private int height;
    private int width;

    // save the last plane position
    private Position lastPosition;
    private Position lastPositionGround;
    // check if I have the final height and width
    private boolean definedHeight, definedWidth;
    private Position creekLand;

    // constante message
    private static final String ERROR_MSG = "Value out of map range!";

    /**
     * Map's constructor
     */
    public Map() {
        tiles = new HashMap<>();
        height = -1;
        width = -1;
        creekLand = null;
    }
    public Position getCreekLand() {
        return creekLand;
    }

    public void setCreekLand(Position creekLand) {
        this.creekLand = creekLand;
    }

    /**
     * Method that initialize the height of the map
     * @param height value height
     * @param definedHeight if is the final value, or just a part of the dimention
     */
    public void initializeHeightMap(int height, boolean definedHeight) {
        if (!this.definedHeight) {
            this.height += (this.height == - 1)?height + 1:height;
            this.definedHeight = definedHeight;
        }
    }

    /**
     * Method that initialize the width map
     * @param width value width
     * @param definedWidth if is the final value, or just a part of the dimention
     */
    public void initializeWidthMap(int width, boolean definedWidth) {
        if (!this.definedWidth) {
            this.width += (this.width == - 1)?width + 1:width;
            this.definedWidth = definedWidth;
        }
    }

    /**
     * If I need initialize a tile as ocean
     * @param position
     * @throws PositionOutOfMapRange
     */
    public void initializeTile(Position position, TileType type) throws PositionOutOfMapRange {
        if (position.getX() >= width || position.getY() >= height)
            throw new PositionOutOfMapRange(ERROR_MSG);
        if (position.getX() < 0 || position.getY() < 0)
            throw new PositionOutOfMapRange("It's not possible values negatives to the positions!");
        lastPosition = position;
        tiles.put(position, new Tile(type));
    }

    public void addBiome(Position position, List<Biomes> biomes, List<Creek> creeks) {
        Tile newTile = new Tile();
        newTile.setBiomesPredominant(biomes);
        newTile.setCreek(creeks);
        if (biomes.contains(Biomes.OCEAN) && biomes.size() == 1)
            newTile.setType(TileType.OCEAN);
        else
            newTile.setType(TileType.GROUND);
        tiles.put(position, newTile);
    }

    /**
     * Get the map height
     * @return map height
     */
    public int getHeight() {
        return height;
    }

    /**
     * Get the map width
     * @return map width
     */
    public int getWidth() {
        return width;
    }

    /**
     * Get the type of the tile
     * @param position of the tile
     * @return the tile type
     */
    public TileType getTileType(Position position) {
        return tiles.get(position).getType();
    }

    /**
     * Get the last position visited
     * @return last position visited
     */
    public Position getLastPosition() {
        return lastPosition;
    }

    public Position getLastPositionGround() {
        return lastPositionGround;
    }

    /**
     * Set the last position visited
     * @param position mas position visited
     */
    public void setLastPosition(Position position) {
        lastPosition = position;
    }

    public void setLastPositionGround(Position lastPositionGround) {
        this.lastPositionGround = lastPositionGround;
    }

    /**
     * Check if the set of tiles is empty
     * @return
     */
    public boolean isEmpty() {
        return tiles.isEmpty();
    }

    public List<Position> getGoodPositions(Context context) {
        List<Position> goodPositions = new ArrayList<>();
        for(java.util.Map.Entry<Position, Tile> entry : tiles.entrySet()) {
            Position key = entry.getKey();
            Tile value = entry.getValue();
            // check if tile was visited
            if (!value.wasVisited()) {
                for (ContractItem item : context.getContracts().getItems()) {
                    Set<Biomes> listTmp = new HashSet<>();
                    listTmp.addAll(item.resource().getBiome());
                    listTmp.retainAll(value.getBiomesPredominant());

                    if (!listTmp.isEmpty()) {
                        goodPositions.add(key);
                    }
                }
            }
        }
        return goodPositions;
    }

    /**
     * Return the position more close of the current position
     * @param positionList
     * @param current
     * @return
     */
    public Position positionClose(List<Position> positionList, Position current) {
        Position good = null;
        int distance = -1;
        for (Position p: positionList) {
            int distFinal = MapHandler.getDistance(current, p);
            if (distance == -1) {
                distance = distFinal;
                good = p;
            } else if (distFinal < distance) {
                distance = distFinal;
                good = p;
            }
        }
        return good;
    }

    /**
     * Set a specifical tile as visited
     * @param position
     */
    public void setTileVisited(Position position) {
        Tile tmpTile = tiles.get(position);
        tmpTile.setVisit(true);
        tiles.put(position, tmpTile);
    }

    /**
     * Get the best creek
     * @param context
     */
    public void getBestCreek(Context context) {
        // positions that have interesting biomes for the contract
        List<Position> goodPositions = getGoodPositions(context);
        int dist = 0;

        for(java.util.Map.Entry<Position, Tile> entry : tiles.entrySet()) {
            Position currentPosition = entry.getKey();
            Tile tile = entry.getValue();
            // check if there are creek in this tile
            if (!tile.getCreek().isEmpty()) {
                // I give the position of this tile and the position more close
                int distance = MapHandler.getDistance(currentPosition, positionClose(goodPositions, currentPosition));
                if (dist == 0) {
                    dist = distance;
                    creekLand = currentPosition;
                }
                else if (distance < dist) {
                    dist = distance;
                    creekLand = currentPosition;
                }
            }
        }
        lastPositionGround = creekLand;
    }

    public Tile getTile(Position p) {
        return tiles.get(p);
    }

    public Tile getTileOverride(Position p) {
        for (HashMap.Entry<Position, Tile> tile:tiles.entrySet()) {
            Position posit = tile.getKey();
            Tile t = tile.getValue();
            if (p.getX() == posit.getX() && p.getY() == posit.getY())
                return t;
        }
        return null;
    }

    public HashMap<Position, Tile> getTiles() {
        return tiles;
    }

    public void copy(Map map) {
        tiles.putAll(map.getTiles());
    }
}