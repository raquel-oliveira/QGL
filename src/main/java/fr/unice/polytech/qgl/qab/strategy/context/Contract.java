package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.exception.InitializeException;
import fr.unice.polytech.qgl.qab.exception.NegativeException;
import fr.unice.polytech.qgl.qab.resources.Resource;

/**
 * @version 16/12/15.
 */
public class Contract {
    private Resource resource;
    private int amount;
    private int accumulated;

    public Contract(Resource resource, int amount) throws InitializeException {
        if (amount < 0) throw new InitializeException("The value to initial amount to the resource can not be negative.");
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

    public Resource resource() { return this.resource; }

    public void collect(int amount) throws NegativeException {
        int temporaryAmount = this.accumulated() - this.amount();
        if(temporaryAmount < 0)
            throw new NegativeException("The value collected can no be negative.");
        this.amount = temporaryAmount;
    }

}
