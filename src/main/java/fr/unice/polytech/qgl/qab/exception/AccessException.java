package fr.unice.polytech.qgl.qab.exception;

/**
 * Exception called when the the program try a invalide acess in some structure (eg map, list, etc).
 * @version 21/03/16.
 */
public class AccessException extends Exception {
    public AccessException(String message){
        super(message);
    }
}
