package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapaRange;
import fr.unice.polytech.qgl.qab.strategy.IStrategy;
import fr.unice.polytech.qgl.qab.strategy.Strategy;

/**
 * Class that represents the bot in the game.
 * Description based on documentation (http://ace-design.github.io/island/bot/)
 *
 * @version 4.9
 */
public class Explorer implements IExplorerRaid {
    // strategy with the bot actions
    private IStrategy strategy;

    /**
     * Constructor
     */
    public Explorer() {
        strategy = new Strategy();
    }

    /**
     * Method to initiate the game. It is invoked right after the initialization.
     * @param context assignment (modeled as a JSON data structure) with the main information to initiate the game.
     */
    public void initialize(String context) {
        strategy.initializeContext(context);
    }

    /**
     * Method responsible for take decisions, invoked each time the bot must decide which action must be played.
     * @return for now, we always return the same action: stopping the game
     */
    public String takeDecision() {
        try {
            return strategy.makeDecision();
        } catch (PositionOutOfMapaRange positionOutOfMapaRange) {
            return (new Stop()).toString();
        }
    }

    /**
     * The acknowledgeResults(String) method is invoked right after the takeDecision() method.
     * It provides the results of the action when applied.
     * @param results information returned after as result of the strategy action
     */
    public void acknowledgeResults(String results) {
         strategy.readResults(results);
    }
}