package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Heading;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 9.12.2015
 */
public class ComboReturn {
    private List<Action> actions;

    public ComboReturn() {
        actions = new ArrayList<>();
    }

    public void defineHeading(Direction head, Map map, Direction moveTo) {
        if (head.isHorizontal()) {
            turnHorizontal(head, map, moveTo);
        }
        if (head.isVertical()) {
            turnVertial(head, map, moveTo);
        }
    }

    private void turnVertial(Direction head, Map map, Direction moveTo) {
        Direction head_tmp = head;
        choiceTurnHorizontal(head, map, moveTo);
        choiceLastTurn(head_tmp);
    }

    private void choiceTurnHorizontal(Direction head, Map map, Direction moveTo) {
        /*int distanceEast = map.distanceOutOfRange(map.getLastPosition(), Direction.EAST);
        int distanceWest = map.distanceOutOfRange(map.getLastPosition(), Direction.WEST);
        if (distanceEast > distanceWest) {
            actions.add(new Heading(Direction.WEST));
        } else {
            actions.add(new Heading(Direction.EAST));
        }*/
        actions.add(new Heading(moveTo));
    }

    private void turnHorizontal(Direction head, Map map, Direction moveTo) {
        Direction head_tmp = head;
        choiceTurnVertical(head, map, moveTo);
        choiceLastTurn(head_tmp);
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

    private void choiceTurnVertical(Direction head, Map map, Direction moveTo) {
        /*int distanceNorth = map.distanceOutOfRange(map.getLastPosition(), Direction.NORTH);
        int distanceSouth = map.distanceOutOfRange(map.getLastPosition(), Direction.SOUTH);
        if (distanceNorth > distanceSouth) {
            actions.add(new Heading(Direction.NORTH));
        } else {
            actions.add(new Heading(Direction.NORTH));
        }*/
        actions.add(new Heading(moveTo));
    }

    public List<Action> getActions() {
        return actions;
    }
    /**
     * Method that check if the action list is empty
     * @return true if the list is empty, false if not
     */
    public boolean isEmpty() {
        return actions.isEmpty();
    }
    /**
     * Return one action in a defined index.
     * @param index of the action to return
     * @return action chosen
     */
    public Action get(int index) {
        return actions.get(index);
    }
    /**
     * Method to remove a item that is in the index gave.
     * @param index to remove item
     * @return the item removed
     */
    public Action remove(int index) {
        return actions.remove(index);
    }
}
