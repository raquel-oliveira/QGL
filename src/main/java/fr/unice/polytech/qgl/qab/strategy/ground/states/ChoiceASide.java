package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @version 20/02/16.
 */
public class ChoiceASide extends GroundState {
    private static ChoiceASide instance;
    List<Glimpse> glimpses;
    private ContextAnalyzer contextAnalyzer;

    /**
     * MoveInTheGround's constructor
     */
    private ChoiceASide() {
        super();
        this.lastAction = null;
        glimpses = null;
        contextAnalyzer = new ContextAnalyzer();
    }

    /**
     * Method to get the instance of the ChoiceASide class
     * @return instance of the ChoiceASide class
     */
    public static ChoiceASide getInstance() {
        if (instance == null)
            instance = new ChoiceASide();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (glimpses != null) {
            if (contextAnalyzer.isOcean(context)) {
                return ChoiceASide.getInstance();
            } else {
                context.setHeading(lastAction.getDirection());
                return MoveInTheGround.getInstance();
            }
        }
        return ChoiceASide.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (glimpses == null) {
            glimpses = new ArrayList<>();
            glimpses.add(new Glimpse(Direction.inverse(context.getHeading()), 4));
            Direction dirRandom = Direction.randomSideDirection(context.getHeading());
            glimpses.add(new Glimpse(dirRandom, 4));
            glimpses.add(new Glimpse(Direction.inverse(dirRandom), 4));
        }

        if (glimpses.isEmpty()) return new Stop();

        act = glimpses.remove(0);
        lastAction = act;
        return act;
    }
}
