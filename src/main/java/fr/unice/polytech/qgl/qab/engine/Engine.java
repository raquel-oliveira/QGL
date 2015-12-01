package fr.unice.polytech.qgl.qab.engine;

import fr.unice.polytech.qgl.qab.engine.Action;
import fr.unice.polytech.qgl.qab.engine.aerial.ActionAerial;
import fr.unice.polytech.qgl.qab.engine.ground.ActionGround;
import fr.unice.polytech.qgl.qab.enums.ActionBot;
import fr.unice.polytech.qgl.qab.enums.Direction;
import fr.unice.polytech.qgl.qab.enums.Phase;

/**
 * Created by gabriela on 01/12/15.
 */
public class Engine {
    Action aplane;
    Action aground;
    Phase phase;

    public Engine() {
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
