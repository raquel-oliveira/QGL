package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.context.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.strategy.IStrategy;
import fr.unice.polytech.qgl.qab.strategy.Strategy;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * Class that represents the bot in the game.
 *
 * @version 4.9
 * @see <a href="http://ace-design.github.io/island/bot/">Ace Design</a>.
 */
public class Explorer implements IExplorerRaid {
    // strategy with the bot actions
    private IStrategy strategy;
    private static final Logger LOGGER = LogManager.getLogger(Explorer.class);
    private static final String ERROR = "error";

    /**
     * Explorer's constructor.
     */
    public Explorer() {
        try {
            strategy = new Strategy();
        } catch (NegativeBudgetException e) {
            LOGGER.error(ERROR, e);
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
            LOGGER.error(ERROR, e);
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
        } catch (Exception e){
            LOGGER.error(ERROR,e);
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
            LOGGER.error(ERROR, e);
        }
    }
}