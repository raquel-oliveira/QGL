package fr.unice.polytech.qgl.qab.map.zones;

import fr.unice.polytech.qgl.qab.map.tile.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 28/03/16.
 */
public class Zone {
    private List<Position> positions;

    public Zone() {
        this.positions = new ArrayList<>();
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void createZone(List<Position> goodPositions, Position current) {
        positions.add(current);

        List<Position> listTempPosition = new ArrayList<>();
        listTempPosition.addAll(positions);

        Position neighbor = findNeighbor(goodPositions);
        while (neighbor != null) {
            if (neighbor != null)
                positions.add(neighbor);
            neighbor = findNeighbor(goodPositions);
        }
    }

    private Position findNeighbor(List<Position> goodPositions) {
        Position neighbor = null;
        int index = 0;
        for (Position positionToanalyze: goodPositions) {
            for (Position pos: positions) {
                if (isNeighbor(pos, positionToanalyze)) {
                    neighbor = positionToanalyze;
                    break;
                }
            }
            if (neighbor != null) break;
            index++;
        }
        if (neighbor != null)
            goodPositions.remove(index);

        return neighbor;
    }

    public boolean isNeighbor(Position current, Position toAnalyze) {
        // left top
        if (toAnalyze.getX() == current.getX() - 1  && toAnalyze.getY() == current.getY() - 1)
            return true;
        // left middle
        if (toAnalyze.getX() == current.getX() - 1  && toAnalyze.getY() == current.getY())
            return true;
        // left bottom
        if (toAnalyze.getX() == current.getX() - 1  && toAnalyze.getY() == current.getY() + 1)
            return true;
        // top
        if (toAnalyze.getX() == current.getX()  && toAnalyze.getY() == current.getY() - 1)
            return true;
        // bottom
        if (toAnalyze.getX() == current.getX() && toAnalyze.getY() == current.getY() + 1)
            return true;
        // right top
        if (toAnalyze.getX() == current.getX() + 1  && toAnalyze.getY() == current.getY() - 1)
            return true;
        // right middle
        if (toAnalyze.getX() == current.getX() + 1  && toAnalyze.getY() == current.getY())
            return true;
        // right bottom
        if (toAnalyze.getX() == current.getX() + 1  && toAnalyze.getY() == current.getY() + 1)
            return true;
        return false;
    }
}
