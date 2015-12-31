package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;

/**
 * @version 16/12/15.
 */
public class Budget {
    private int initial;
    private int remaining;

    public Budget(int initial) throws NegativeBudgetException {
        if (initial < 0)
            throw new NegativeBudgetException("The initial value for the budget can not be negative");
        this.initial = initial;
        this.remaining = initial;
    }

    public int initial() {
        return this.initial;
    }

    public int remaining() {
        return this.remaining;
    }

    public void spend(int cost) throws NegativeBudgetException {
        int temporaryRemaining = this.remaining() - cost;
        if(temporaryRemaining < 0)
            throw new NegativeBudgetException("The value spent can no be more than the remaining in the Budget.");
        remaining = temporaryRemaining;
    }
}
