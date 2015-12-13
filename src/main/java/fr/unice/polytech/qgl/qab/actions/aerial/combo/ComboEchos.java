package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the set of actions Echo.
 *
 * @version 9.12.2015
 */
public class ComboEchos {
    private List<Action> actions;

    public ComboEchos() {
        actions = new ArrayList<>();
    }

    /**
     * Choice the direction to three echos.
     * @param head direction of the head
     */
    public void defineComboEchos(Direction head) {
        if (head.isHorizontal()) {
            actions.add(new Echo(Direction.NORTH));
            actions.add(new Echo(Direction.SOUTH));
            if (head.compareTo(Direction.EAST) == 0) {
                actions.add(new Echo(Direction.EAST));
            } else {
                actions.add(new Echo(Direction.WEST));
            }
        }
        if (head.isVertical()) {
            actions.add(new Echo(Direction.WEST));
            actions.add(new Echo(Direction.EAST));
            if (head.compareTo(Direction.NORTH) == 0) {
                actions.add(new Echo(Direction.NORTH));
            } else {
                actions.add(new Echo(Direction.SOUTH));
            }
        }
    }

    /**
     * Method to return the list of the actions (echos).
     * @return actions list
     */
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
