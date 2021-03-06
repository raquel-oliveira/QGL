package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.exception.AccessException;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;

/**
 * @version 4.9
 */
public interface IStrategy {

    /**
     * Method called to make the decision.
     * @return the best action chosen
     */
    String makeDecision() throws AccessException;

    /**
     * Method the read and analyse the string returned.
     * @param data information that the engine returned
     */
    void readResults(String data) throws NegativeBudgetException;

    /**
     * Method responsible to read and save the context gave int the begging of the simulation.
     * @param context the context gave in the begging of the simulation.
     */
    void initializeContext(String context) throws NegativeBudgetException;
}
