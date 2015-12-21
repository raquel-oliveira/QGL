package fr.unice.polytech.qgl.qab.exception;

/**
 * @version 17/12/15.
 */
public class PositionOutOfMapRange extends Exception {
    public PositionOutOfMapRange(){
        super();
    }

    public PositionOutOfMapRange(String message){
        super(message);
    }
}
