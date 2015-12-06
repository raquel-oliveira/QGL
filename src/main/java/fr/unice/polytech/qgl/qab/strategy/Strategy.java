package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.ActionAerial;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.strategy.context.DataResults;
import fr.unice.polytech.qgl.qab.actions.ground.ActionGround;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Phase;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 4.9
 */
public class Strategy implements IStrategy {
    private Action aplane;
    private Action aground;
    private Phase phase;
    private Map map;
    private Context context;
    private Action takeAction;

    public Strategy() {
        aplane = new ActionAerial();
        aground = new ActionGround();
        phase = Phase.AERIAL;
        map = new Map();
        takeAction = null;
        context = new Context();
    }

    public String makeDecision() {
        Action act;
        if (phase.equals(Phase.AERIAL)) {
            act = aplane.makeDecision(map, context);
            if (act.equals(ActionBot.LAND)) phase = Phase.GROUND;
        } else {
            act = aground.makeDecision(map, context);
            if (act.equals(ActionBot.LAND)) phase = Phase.AERIAL;
        }
        takeAction = act;
        return act.formatResponse();
    }

    public void readResults(String data) {
        context = DataResults.readData(data, takeAction, context);
    }

    public void initializeContext(String contextData) {
        context.saveContext(contextData);
    }
}
