package fr.unice.polytech.qgl.qab;

import eu.ace_design.island.bot.IExplorerRaid;
import fr.unice.polytech.qgl.qab.engine.Engine;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.Context;
import fr.unice.polytech.qgl.qab.util.DataResults;
import org.json.JSONObject;

/**
 * Class that represents the bot in the game.
 * Description based on documentation (http://ace-design.github.io/island/bot/)
 *
 * @version 4.9
 */
public class Explorer implements IExplorerRaid {
    private Engine engine;
    private Context contextIsland;
    private DataResults dataResults;
    private ActionBot tookAction;

    public Explorer() {
        engine = new Engine();
        tookAction = null;
        contextIsland = new Context();
        dataResults = new DataResults();
    }

    /**
     * Method to initiate the game. It is invoked right after the initialization.
     * @param context assignment (modeled as a JSON data structure) with the main information to initiate the game.
     */
    public void initialize(String context) {
        contextIsland.saveContext(context);
    }

    /**
     * Method responsible for take decisions, invoked each time the bot must decide which action must be played.
     * @return for now, we always return the same action: stopping the game
     */
    public String takeDecision() {
        String result = engine.makeDecision(contextIsland.getHeading(), contextIsland.getBudget(), contextIsland.getStatus());
        JSONObject jsonObj = new JSONObject(result);
        tookAction = ActionBot.fromString(jsonObj.getString("action"));
        return result;
    }

    /**
     * The acknowledgeResults(String) method is invoked right after the takeDecision() method.
     * It provides the results of the action when applied.
     * @param results information returned after as result of the engine action
     */
    public void acknowledgeResults(String results) {
        contextIsland = dataResults.readData(results, tookAction, contextIsland);
    }
}