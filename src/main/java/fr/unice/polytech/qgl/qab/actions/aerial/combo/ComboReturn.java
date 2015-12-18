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
            turnVertical(head, map, moveTo);
        }
    }

    private void turnVertical(Direction head, Map map, Direction moveTo) {
        if (head.isEquals(Direction.SOUTH) && moveTo.isEquals(Direction.EAST)) {
            turnEastUp();
            actions.add(new Echo(Direction.NORTH));
            turnEastDown();
        }
        else if (head.isEquals(Direction.SOUTH) && moveTo.isEquals(Direction.WEST)) {
            turnWestUp();
            actions.add(new Echo(Direction.NORTH));
            turnWestDown();
            actions.add(new Echo(Direction.SOUTH));
        }
        else if (head.isEquals(Direction.NORTH) && moveTo.isEquals(Direction.EAST)) {
            turnEastDown();
            actions.add(new Echo(Direction.SOUTH));
            turnEastUp();
            actions.add(new Echo(Direction.NORTH));
        }
        else if (head.isEquals(Direction.NORTH) && moveTo.isEquals(Direction.WEST)) {
            turnWestDown();
            actions.add(new Echo(Direction.SOUTH));
            turnWestUp();
            actions.add(new Echo(Direction.NORTH));
        }
        else if (head.isEquals(Direction.SOUTH) && moveTo.isEquals(Direction.SOUTH)) {
            turnEastUp();
            actions.add(new Echo(Direction.NORTH));
            turnEastDown();
            actions.add(new Echo(Direction.SOUTH));
        }
        else if (head.isEquals(Direction.NORTH) && moveTo.isEquals(Direction.NORTH)) {
            turnEastDown();
            actions.add(new Echo(Direction.SOUTH));
            turnEastDown();
            actions.add(new Echo(Direction.SOUTH));
        }
    }

    private void choiceTurnHorizontal(Direction head, Map map, Direction moveTo) {
        if (!head.isEquals(moveTo))
            actions.add(new Heading(moveTo));
        else
            actions.add(new Heading(Direction.randomSideDirection(head)));
    }

    private void turnHorizontal(Direction head, Map map, Direction moveTo) {
        if (head.isEquals(Direction.EAST) && moveTo.isEquals(Direction.SOUTH)) {
            turnDowntoWest();
            actions.add(new Echo(Direction.WEST));
            turnDowntoEast();
            actions.add(new Echo(Direction.EAST));
        }
        else if (head.isEquals(Direction.WEST) && moveTo.isEquals(Direction.SOUTH)) {
            turnDowntoEast();
            actions.add(new Echo(Direction.EAST));
            turnDowntoWest();
            actions.add(new Echo(Direction.WEST));
        }
        else if (head.isEquals(Direction.SOUTH) && moveTo.isEquals(Direction.EAST)) {
            turnEastUp();
            actions.add(new Echo(Direction.NORTH));
            turnEastDown();
            actions.add(new Echo(Direction.SOUTH));
        }
        else if (head.isEquals(Direction.SOUTH) && moveTo.isEquals(Direction.WEST)) {
            turnWestUp();
            actions.add(new Echo(Direction.NORTH));
            turnWestDown();
            actions.add(new Echo(Direction.SOUTH));
        }
    }

    private void turnDowntoEast() {
        actions.add(new Heading(Direction.SOUTH));
        actions.add(new Heading(Direction.EAST));
    }

    private void turnDowntoWest() {
        actions.add(new Heading(Direction.SOUTH));
        actions.add(new Heading(Direction.WEST));
    }

    private void turnUptoEast() {
        actions.add(new Heading(Direction.NORTH));
        actions.add(new Heading(Direction.EAST));
    }

    private void turnUptoWest() {
        actions.add(new Heading(Direction.NORTH));
        actions.add(new Heading(Direction.WEST));
    }

    private void turnEastUp() {
        actions.add(new Heading(Direction.EAST));
        actions.add(new Heading(Direction.NORTH));
    }

    private void turnEastDown() {
        actions.add(new Heading(Direction.EAST));
        actions.add(new Heading(Direction.SOUTH));
    }

    private void turnWestUp() {
        actions.add(new Heading(Direction.WEST));
        actions.add(new Heading(Direction.NORTH));
    }

    private void turnWestDown() {
        actions.add(new Heading(Direction.WEST));
        actions.add(new Heading(Direction.SOUTH));
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
