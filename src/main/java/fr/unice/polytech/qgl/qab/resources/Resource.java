package fr.unice.polytech.qgl.qab.resources;

/**
 * @version 16/12/15.
 */
public class Resource {
    private String name;
    private int amount;
    private int contract;

    public Resource(String name) {
        this.name = name;
        this.amount = 0;
        this.contract = 0;
    }

    public Resource(String name, int amount, int contract) {
        this.name = name;
        this.amount = amount;
        this.contract = contract;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setContract(int contract) {
        this.contract = contract;
    }

    public int getContract() {
        return contract;
    }

    public void increment(int amount) {
        this.amount += amount;
    }
}
