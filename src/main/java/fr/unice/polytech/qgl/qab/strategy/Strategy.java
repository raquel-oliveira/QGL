package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.aerial.AerialStrategy;
import fr.unice.polytech.qgl.qab.strategy.aerial.IAerialStrategy;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ResponseHandler;
import fr.unice.polytech.qgl.qab.strategy.ground.GroundStrategy;
import fr.unice.polytech.qgl.qab.strategy.ground.IGroundStrategy;
import fr.unice.polytech.qgl.qab.util.enums.Phase;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

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
    // map
    private Map map;

    public Strategy() throws NegativeBudgetException {
        context = new Context();
        aerialStrategy = new AerialStrategy();
        groundStrategy = new GroundStrategy();
        phase = Phase.AERIAL;
        currentAction = null;
        responseHandler = new ResponseHandler();
        map = new Map();
    }

    @Override
    public String makeDecision() throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        Action act;
        if (phase.equals(Phase.AERIAL)) {
            act = aerialStrategy.makeDecision(context, map);
            if (act instanceof Land) {
                phase = Phase.GROUND;
                context.updateToGround();
            }
        } else {
            act = groundStrategy.makeDecision(context, map);
            if (act instanceof Land) {
                phase = Phase.GROUND;
                context.updateToAerial();
            }
        }
        currentAction = act;
        return act.formatResponse();
    }

    @Override
    public void readResults(String data) throws NegativeBudgetException {
        context = responseHandler.readData(data, currentAction, context);
    }

    @Override
    public void initializeContext(String contextData) throws NegativeBudgetException {
        context.read(contextData);
    }
}
