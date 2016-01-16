package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.strategy.aerial.AerialStrategy;
import fr.unice.polytech.qgl.qab.strategy.aerial.IAerialStrategy;
import fr.unice.polytech.qgl.qab.strategy.context.ResponseHandler;
import fr.unice.polytech.qgl.qab.strategy.ground.GroundStrategy;
import fr.unice.polytech.qgl.qab.strategy.ground.IGroundStrategy;
import fr.unice.polytech.qgl.qab.util.enums.Phase;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.actions.common.Land;

/**
 * Class responsible for represent the Strategy to management the making decision.
 *
 * @version 8/12/16
 */
public class Strategy implements IStrategy {
    // object responsible for choice the best action to the plane
    private IAerialStrategy aerialStrategy;
    // object responsible for choice the best action int the ground
    private IGroundStrategy groundStrategy;
    // current phase that the game is
    private Phase phase;
    // object that stock the information relating to the game
    private Context context;
    // object that save the current action
    private Action currentAction;
    // object to read the response
    private ResponseHandler responseHandler;

    public Strategy() throws NegativeBudgetException {
        aerialStrategy = new AerialStrategy();
        groundStrategy = new GroundStrategy();
        phase = Phase.AERIAL;
        currentAction = null;
        context = new Context();
        responseHandler = new ResponseHandler();
    }

    /**
     * Method called to make the decision.
     * @return the best action chosen
     */
    public String makeDecision() throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        Action act;
        if (phase.isEquals(Phase.AERIAL)) {
            act = aerialStrategy.makeDecision(context);
            if (act instanceof Land)
                phase = Phase.GROUND;
        } else {
            return new Stop().formatResponse();
        }
        currentAction = act;
        return act.formatResponse();
    }

    /**
     * Method the read and analyse the string returned.
     * @param data information that the engine returned
     */
    public void readResults(String data) throws NegativeBudgetException {
        context = responseHandler.readData(data, currentAction, context);
    }

    /**
     * Method responsible to read and save the context gave int the begging of the simulation.
     * @param contextData the context gave in the begging of the simulation.
     */
    public void initializeContext(String contextData) throws NegativeBudgetException {
        context.read(contextData);
    }
}
