package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.map.Map;

/**
 * @version 4.9
 */
public interface IStrategy {
    public String makeDecision();

    public void readResults(String data);

    public void initializeContext(String context);
}
