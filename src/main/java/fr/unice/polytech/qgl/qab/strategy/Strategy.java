package fr.unice.polytech.qgl.qab.strategy;

import fr.unice.polytech.qgl.qab.strategy.aerial.ActionAerial;
import fr.unice.polytech.qgl.qab.strategy.ground.ActionGround;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.enums.Phase;
import fr.unice.polytech.qgl.qab.map.Map;

/**
 * @version 4.9
 */
public class Strategy implements IStrategy {
    Action aplane;
    Action aground;
    Phase phase;
    Map map;

    public Strategy() {
        aplane = new ActionAerial();
        aground = new ActionGround();
        phase = Phase.AERIAL;
    }

    public String makeDecision(Direction heading, int budget, Boolean status) {
        String act;

        if (phase.equals(Phase.AERIAL)) {
            act = aplane.makeDecision(heading, budget, status);
            if (act.equals(ActionBot.LAND)) phase = Phase.GROUND;
        } else {
            act = aground.makeDecision(heading, budget, status);
            if (act.equals(ActionBot.LAND)) phase = Phase.AERIAL;
        }

        return act;
    }
}
