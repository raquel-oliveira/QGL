package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to define a list of actions fly
 *
 * @version 12.12.2015
 */
public class ComboFlyUntil {
    private List<Action> actions;

    public ComboFlyUntil() {
        actions = new ArrayList<>();
    }

    /**
     * Add the number of times of fly to get in 'front'of the place choose
     *
     * @param range number of times to fly -1
     */
    public void defineComboFlyUntil(int range) {
        for (int i = 1; i < range; i++) {
            actions.add(new Fly());
        }
    }

    /**
     * Method to return the list of the actions (fly).
     *
     * @return actions list
     */
    public List<Action> getActions() {
        return actions;
    }

    /**
     * Method that check if the action list is empty
     *
     * @return true if the list is empty, false if not
     */
    public boolean isEmpty() {
        return actions.isEmpty();
    }

    /**
     * Return one action in a defined index.
     *
     * @param index of the action to return
     * @return action chosen
     */
    public Action get(int index) {
        return actions.get(index);
    }

    /**
     * Method to remove a item that is in the index gave.
     *
     * @param index to remove item
     * @return the item removed
     */
    public Action remove(int index) {
        return actions.remove(index);
    }
}
