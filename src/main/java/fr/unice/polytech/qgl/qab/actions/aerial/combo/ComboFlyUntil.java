package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 12/12/15.
 */
public class ComboFlyUntil {
    private List<Action> actions;

    public ComboFlyUntil() {
        actions = new ArrayList<>();
    }

    public void defineComboFlyUntil(int range) {
        for(int i = 0; i < range; i++ ){
            actions.add(new Fly());
        }
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