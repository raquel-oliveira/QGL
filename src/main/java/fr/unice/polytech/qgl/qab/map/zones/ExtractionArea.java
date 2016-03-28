package fr.unice.polytech.qgl.qab.map.zones;

import fr.unice.polytech.qgl.qab.map.tile.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 28/03/16.
 */
public class ExtractionArea {
    List<Zone> zones;

    public ExtractionArea() {
        this.zones = new ArrayList<>();
    }

    public void createAreas(List<Position> goodPositions) {
        List<Position> positionCopy = new ArrayList<>();
        positionCopy.addAll(goodPositions);

        int indexZone = 0;
        zones.add(new Zone());

        while (!positionCopy.isEmpty()) {
            zones.get(indexZone++).createZone(positionCopy, positionCopy.remove(0));
        }
    }
}
