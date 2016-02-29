package fr.unice.polytech.qgl.qab.strategy.context.utils;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;

/**
 * @version 16/12/15.
 *
 * Class that represents the Budged received at the beginning of Simulation
 */
public class Budget {
    private int initial;
    private int remaining;

    /**
     * Budget's constructor
     * @param initial
     * @throws NegativeBudgetException
     */
    public Budget(int initial) throws NegativeBudgetException {
        if (initial < 0)
            throw new NegativeBudgetException("The initial value for the budget can not be negative");
        this.initial = initial;
        this.remaining = initial;
    }

    /**
     * Get the initial value
     * @return initial value
     */
    public int initial() {
        return this.initial;
    }

    /**
     * Get the remaining value
     * @return remaining value
     */
    public int remaining() {
        return this.remaining;
    }

    /**
     * Change the value of the remaining value
     * @param cost value to be removed from the remaining value
     * @throws NegativeBudgetException
     */
    public void spend(int cost) throws NegativeBudgetException {
        int temporaryRemaining = this.remaining() - cost;
        if(temporaryRemaining < 0)
            throw new NegativeBudgetException("The value spent can no be more than the remaining in the Budget.");
        remaining = temporaryRemaining;
    }
}
