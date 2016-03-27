package fr.unice.polytech.qgl.qab.exception.map;

import fr.unice.polytech.qgl.qab.exception.AccessException;

/**
 * @version 17/12/15.
 */
public class PositionOutOfMapRange extends AccessException {
    public PositionOutOfMapRange(String message){
        super(message);
    }
}
