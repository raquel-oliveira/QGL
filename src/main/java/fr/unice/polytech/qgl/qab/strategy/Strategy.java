package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.NegativeBudgetException;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.MapHandler;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.aerial.AerialStrategy;
import fr.unice.polytech.qgl.qab.strategy.aerial.IAerialStrategy;
import fr.unice.polytech.qgl.qab.strategy.context.utils.ResponseHandler;
import fr.unice.polytech.qgl.qab.strategy.ground.GroundStrategy;
import fr.unice.polytech.qgl.qab.strategy.ground.IGroundStrategy;
import fr.unice.polytech.qgl.qab.util.enums.Phase;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

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
    // handle map
    private MapHandler mapHandler;

    public Strategy() throws NegativeBudgetException {
        context = new Context();
        aerialStrategy = new AerialStrategy();
        groundStrategy = new GroundStrategy();
        phase = Phase.AERIAL;
        currentAction = null;
        responseHandler = new ResponseHandler();
        map = new Map();
        mapHandler = new MapHandler();
    }

    @Override
    public String makeDecision() throws PositionOutOfMapRange, IndexOutOfBoundsComboAction {
        Action act;
        if (phase.equals(Phase.AERIAL)) {
            act = aerialStrategy.makeDecision(context, map);
            if (act instanceof Land) {
                phase = Phase.GROUND;
                context.updateToGround();
                mapHandler.completMap(map);
                //writeLog(map);
            }
        } else {
            act = groundStrategy.makeDecision(context, map);
            if (act instanceof Land) {
                phase = Phase.AERIAL;
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

    private void writeLog(Map map) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/main/resource/output/logPosition.json", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        writer.println("The first line");

        for (Position p : map.getTiles().keySet()) {
            writer.println(p.getX() + " " + p.getY());
        }
        writer.close();
    }
}
