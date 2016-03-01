package fr.unice.polytech.qgl.qab.map;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.map.tile.Tile;
import fr.unice.polytech.qgl.qab.map.tile.TileType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ContractItem;

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
    // check if I have the final height and width
    private boolean definedHeight, definedWidth;

    // constante message
    private static final String ERROR_MSG = "Value out of map range!";

    /**
     * Map's constructor
     */
    public Map() {
        tiles = new HashMap<>();
        height = -1;
        width = -1;
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

    public void addBiome(Position position, List<Biomes> biomes) {
        Tile newTile = new Tile();
        newTile.setBiomesPredominant(biomes);
        //newTile.setVisit(true);
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

    /**
     * Set the last position visited
     * @param position mas position visited
     */
    public void setLastPosition(Position position) {
        lastPosition = position;
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
            if (!value.wasVisited()) {
                for (ContractItem item : context.getContracts()) {
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

    public Position positionClose(List<Position> goodPositions) {
        Position good = null;
        double distance = -1;
        for (Position p: goodPositions) {
            double distX = Math.abs(p.getX() - lastPosition.getX());
            double distY = Math.abs(p.getY() - lastPosition.getY());
            double pow = Math.pow(distX, 2) + Math.pow(distY, 2);
            double distFinal = Math.sqrt(pow);
            if (distance == -1) {
                distance = distFinal;
                good = p;
            }
            else if (distFinal < distance) {
                distance = distFinal;
                good = p;
            }
        }
        return good;
    }

    public void setTileVisited(Position position) {
        Tile tmp = tiles.get(position);
        tmp.setVisit(true);
        tiles.put(position, tmp);
    }
}