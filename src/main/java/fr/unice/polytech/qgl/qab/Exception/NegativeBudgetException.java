package fr.unice.polytech.qgl.qab.exception;

/**
 * @version 16/12/15.
 */
public class NegativeBudgetException extends Exception {
    public NegativeBudgetException(){
        super();
    }

    public NegativeBudgetException(String message){
        super(message);
    }
}
