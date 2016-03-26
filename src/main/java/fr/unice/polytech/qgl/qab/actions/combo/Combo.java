package fr.unice.polytech.qgl.qab.actions.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.List;

/**
 * Class to represent the set of actions
 *
 * @version 18.12.2015
 */
public abstract class Combo {
    protected List<Action> actions;


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
    public Action get(int index) throws IndexOutOfBoundsComboAction {
        if (index < 0 || index > actions.size())
            throw new IndexOutOfBoundsComboAction("Index invalid");
        if (actions.isEmpty())
            throw new IndexOutOfBoundsComboAction("Index invalid");
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

    /**
     * Methodo to define a action with a direction.
     * @param dir direction of the action
     */
    public void defineActions(Direction dir) {

    }

    /**
     * Methodo to define a action with a range.
     * @param range range of the action
     */
    public void defineActions(int range) {

    }

    /**
     * Methodo to define a simple action
     */
    public void defineActions() {

    }

    /**
     * Methodo to define a action with a direction of the head and to move to.
     * @param head direction of the head
     * @param moveTo direction to move to
     */
    public void defineActions(Direction head, Direction moveTo) {

    }

    /**
     * Methodo to define a action with a direction and range.
     * @param direction direction of the action
     * @param range range of the action
     */
    public void defineActions(Direction direction, int range) {

    }
}
