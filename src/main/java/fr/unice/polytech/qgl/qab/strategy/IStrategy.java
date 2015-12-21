package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;

/**
 * @version 4.9
 */
public interface IStrategy {
    String makeDecision() throws PositionOutOfMapRange;

    void readResults(String data);

    void initializeContext(String context) throws NegativeBudgetException;
}
