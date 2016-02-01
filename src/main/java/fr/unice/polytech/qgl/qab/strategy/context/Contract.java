package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.resources.Resource;

/**
 * @version 16/12/15.
 */
public class Contract {
    private Resource resource;
    private int amount;
    private int accumulated;

    public Contract(Resource resource, int amount) throws NegativeBudgetException {
        if (amount < 0)
            throw new NegativeBudgetException("The value to initial amount to the resource can not be negative.");
        this.resource = resource;
        this.amount = amount;
        this.accumulated = 0;
    }

    public int amount() {
        return this.amount;
    }

    public int accumulated() {
        return this.accumulated;
    }

    public Resource resource() {
        return this.resource;
    }

    public void collect(int amount) throws NegativeBudgetException {
        int temporaryAmount = this.accumulated() + amount;
        if(temporaryAmount < 0)
            throw new NegativeBudgetException("The value collected can no be negative.");
        this.accumulated = temporaryAmount;
    }

}
