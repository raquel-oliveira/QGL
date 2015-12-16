package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
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
        if (head.equals(Direction.SOUTH) && moveTo.equals(Direction.EAST)) {
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.NORTH));
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.SOUTH));
        }
        else if (head.equals(Direction.SOUTH) && moveTo.equals(Direction.WEST)) {
            actions.add(new Heading(Direction.WEST));
            actions.add(new Heading(Direction.NORTH));
            actions.add(new Heading(Direction.WEST));
            actions.add(new Heading(Direction.SOUTH));
        }
        else if (head.equals(Direction.NORTH) && moveTo.equals(Direction.EAST)) {
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.SOUTH));
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.NORTH));
        }
        else if (head.equals(Direction.NORTH) && moveTo.equals(Direction.WEST)) {
            actions.add(new Heading(Direction.WEST));
            actions.add(new Heading(Direction.SOUTH));
            actions.add(new Heading(Direction.WEST));
            actions.add(new Heading(Direction.NORTH));
        }
        else if (head.equals(Direction.SOUTH) && moveTo.equals(Direction.SOUTH)) {
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.NORTH));
            actions.add(new Echo(Direction.NORTH));
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.SOUTH));
            actions.add(new Echo(Direction.SOUTH));
        }
        else if (head.equals(Direction.NORTH) && moveTo.equals(Direction.NORTH)) {
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.SOUTH));
            actions.add(new Heading(Direction.EAST));
            actions.add(new Heading(Direction.SOUTH));
        }
    }

    private void choiceTurnHorizontal(Direction head, Map map, Direction moveTo) {
        if (!head.equals(moveTo))
            actions.add(new Heading(moveTo));
        else
            actions.add(new Heading(Direction.randomSideDirection(head)));
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
        if (!head.equals(moveTo))
            actions.add(new Heading(moveTo));
        else
            actions.add(new Heading(Direction.randomSideDirection(head)));
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
