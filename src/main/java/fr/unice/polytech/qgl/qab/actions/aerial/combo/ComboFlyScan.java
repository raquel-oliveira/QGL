package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Scan;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the set of fly and scan
 * Goal: find a creek
 * @version 12.12.2015.
 */
public class ComboFlyScan {
    private List<Action> actions;

    public ComboFlyScan() {
        actions = new ArrayList<>();
    }

    /**
     * Set the actions (fly and scan)
     * @param head direction of the head
     */
    public void defineActions(Direction head) {
        actions.add(new Fly());
        actions.add(new Scan());
    }

    /**
     * Method to return the list of the actions (fly or scan).
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
