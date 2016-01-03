package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
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
        try {
            strategy = new Strategy();
        } catch (NegativeBudgetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to initiate the game. It is invoked right after the initialization.
     * @param context assignment (modeled as a JSON data structure) with the main information to initiate the game.
     */
    @Override
    public void initialize(String context) {
        try {
            strategy.initializeContext(context);
        } catch (NegativeBudgetException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method responsible for take decisions, invoked each time the bot must decide which action must be played.
     * @return for now, we always return the same action: stopping the game
     */
    @Override
    public String takeDecision() {
        try {
            return strategy.makeDecision();
        } catch (PositionOutOfMapRange positionOutOfMapaRange) {
            return (new Stop()).toString();
        } catch (IndexOutOfBoundsComboAction indexOutOfBoundsComboAction) {
            return (new Stop()).toString();
        }
    }

    /**
     * The acknowledgeResults(String) method is invoked right after the takeDecision() method.
     * It provides the results of the action when applied.
     * @param results information returned after as result of the strategy action
     */
    @Override
    public void acknowledgeResults(String results) {
        try {
            strategy.readResults(results);
        } catch (NegativeBudgetException e) {
            e.printStackTrace();
        }
    }
}