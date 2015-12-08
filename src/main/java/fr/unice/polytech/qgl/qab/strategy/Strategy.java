package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.ActionAerial;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.context.DataResults;
import fr.unice.polytech.qgl.qab.actions.ground.ActionGround;
import fr.unice.polytech.qgl.qab.util.enums.ActionBot;
import fr.unice.polytech.qgl.qab.util.enums.Phase;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * Classe responsible for represent the Strategy to management the making decision.
 *
 * @version 8.12.2016
 */
public class Strategy implements IStrategy {
    // object responsible for choice the best action to the plane
    private Action aplane;
    // object responsible for choice the best action int the ground
    private Action aground;
    // current phase that the game is
    private Phase phase;
    // object that represent the game map
    private Map map;
    // object that stock the information relating to the game
    private Context context;
    // object that save the current action
    private Action currentAction;

    public Strategy() {
        aplane = new ActionAerial();
        aground = new ActionGround();
        phase = Phase.AERIAL;
        map = new Map();
        currentAction = null;
        context = new Context();
    }

    /**
     * Method called to make the decision.
     * @return the best action chosen
     */
    public String makeDecision() {
        Action act;
        if (phase.equals(Phase.AERIAL)) {
            act = aplane.makeDecision(map, context);
            if (act.equals(ActionBot.LAND)) phase = Phase.GROUND;
        } else {
            act = aground.makeDecision(map, context);
            if (act.equals(ActionBot.LAND)) phase = Phase.AERIAL;
        }
        currentAction = act;
        return act.formatResponse();
    }

    /**
     * Method the read and analyse the string returned.
     * @param data information that the engine returned
     */
    public void readResults(String data) {
        context = DataResults.readData(data, currentAction, context);
        updateMap();
    }

    /**
     * Method responsible to read and save the context gave int the begging of the simulation.
     * @param contextData the context gave in the begging of the simulation.
     */
    public void initializeContext(String contextData) {
        context.saveContext(contextData);
    }

    /**
     * Method to update the map after the object receive the engine response.
     */
    // TODO: look this
    public void updateMap() {
        if (context.getHeight() != 0 || context.getWidth() != 0) {
            map.initializeMap(context.getHeight(), context.getWidth());
        }
        if (context.getHeight() != 0 && context.getWidth() != 0) {
            map.initializeTiteOcean(new Position(0, 0));
        }
    }
}
