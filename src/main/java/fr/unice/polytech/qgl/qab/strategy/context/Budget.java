package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.InitializeException;
import fr.unice.polytech.qgl.qab.exception.NegativeException;

/**
 * @version 16/12/15.
 */
public class Budget {
    private static Budget instance;
    private int initial;
    private int remaining;

    public static Budget getInstance(int initial, int remaining) throws InitializeException {
        if (instance == null)
            instance = new Budget(initial, remaining);
        return instance;
    }

    private Budget(int initial, int remaining) throws InitializeException {
        if (initial < 0) throw new InitializeException("The value to initial budget can not be negative.");
        this.initial = initial;
        this.remaining = remaining;
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
