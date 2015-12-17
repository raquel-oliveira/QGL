package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;

/**
 * @version 4.9
 */
public interface IStrategy {
    String makeDecision() throws PositionOutOfMapaRange;

    void readResults(String data);

    void initializeContext(String context);
}
