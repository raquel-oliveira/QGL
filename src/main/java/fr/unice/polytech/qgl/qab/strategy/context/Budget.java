package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.InitializeException;
import fr.unice.polytech.qgl.qab.exception.NegativeException;

/**
 * @version 16/12/15.
 */
public class Budget {
    private int initial;
    private int remaining;

    public Budget(int initial) throws InitializeException {
        if (initial < 0) throw new InitializeException();
        this.initial = initial;
        this.remaining = initial;
    }

    public int initial() {
        return this.initial;
    }

    public int remaining() {
        return this.remaining;
    }

    public void spend(int cost) throws NegativeException {
        int temporaryRemaining = this.remaining() - cost;
        if(temporaryRemaining < 0)
            throw new NegativeException("The value spent can no be more than the remaining in the Budget.");
        remaining = temporaryRemaining;
    }
}
