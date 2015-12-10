package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 9.12.2015
 */
public class comboReturn  {
    private List<Heading> actions;

    public comboReturn() {
        actions = new ArrayList<>();
    }

    // TODO: making return back vertical
    public void defineHeading(Direction head, Map map) {
        if (head.isHorizontal()) {
            turnHorizontal(head, map);
        }
        if (head.isVertical()) {
            turnVertial(head, map);
        }
    }

    private void turnVertial(Direction head, Map map) {
        choiceTurnHorizontal(head, map);
        choiceLastTurn(head);
    }

    private void choiceTurnHorizontal(Direction head, Map map) {
        int response = map.hasTileVisitedWestEast();
        if (response == 0) {
            int distanceEast = map.distanceOutOfRange(map.getLastPosition(), Direction.EAST);
            int distanceWest = map.distanceOutOfRange(map.getLastPosition(), Direction.WEST);
            if (distanceEast > distanceWest) {
                actions.add(new Heading(Direction.EAST));
            } else {
                actions.add(new Heading(Direction.WEST));
            }
        } else if (response == 1) {
            actions.add(new Heading(Direction.EAST));
        } else if (response == 2) {
            actions.add(new Heading(Direction.WEST));
        } else {
            actions.add(new Heading(Direction.randomSideDirection(head)));
        }
    }

    /**
     * see if the plane flew in one of the sides.
     * if not, if what is the side that is more distant of the out_of_range
     * @param head
     * @param map
     */
    private void turnHorizontal(Direction head, Map map) {
        choiceTurnVertical(head, map);
        choiceLastTurn(head);
    }

    private void choiceLastTurn(Direction head) {
        if (head.isHorizontal()) {
            if (head.compareTo(Direction.EAST) == 0)
                actions.add(new Heading(Direction.WEST));
            else
                actions.add(new Heading(Direction.EAST));
        } else {
            if (head.compareTo(Direction.NORTH) == 0)
                actions.add(new Heading(Direction.SOUTH));
            else
                actions.add(new Heading(Direction.NORTH));
        }
    }

    private void choiceTurnVertical(Direction head, Map map) {
        int response = map.hasTileVisitedNothSouth();
        if (response == 0) {
            int distanceNorth = map.distanceOutOfRange(map.getLastPosition(), Direction.NORTH);
            int distanceSouth = map.distanceOutOfRange(map.getLastPosition(), Direction.SOUTH);
            if (distanceNorth > distanceSouth) {
                actions.add(new Heading(Direction.NORTH));
            } else {
                actions.add(new Heading(Direction.NORTH));
            }
        } else if (response == 1) {
            actions.add(new Heading(Direction.SOUTH));
        } else if (response == 2) {
            actions.add(new Heading(Direction.NORTH));
        } else {
            actions.add(new Heading(Direction.randomSideDirection(head)));
        }
    }
}
