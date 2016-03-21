package fr.unice.polytech.qgl.qab.exception.action;

import fr.unice.polytech.qgl.qab.exception.AccessException;

/**
 * @version 27/12/15.
 */
public class IndexOutOfBoundsComboAction extends AccessException {
    public IndexOutOfBoundsComboAction(String message){
        super(message);
    }
}
