package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;

/**
 * @version 4.9
 */
public interface IStrategy {
    String makeDecision() throws PositionOutOfMapRange, IndexOutOfBoundsComboAction;

    void readResults(String data) throws NegativeBudgetException;

    void initializeContext(String context) throws NegativeBudgetException;
}
