package fr.unice.polytech.qgl.qab.exception;

/**
 * @version 17/12/15.
 */
public class PositionOutOfMapaRange extends Exception {
    public PositionOutOfMapaRange(){
        super();
    }

    public PositionOutOfMapaRange(String message){
        super(message);
    }
}
