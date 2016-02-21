package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 07/02/16.
 *
 * State responsable by move the explorer in the ground.
 * In this state we can make the move_to and glimpse.
 */
public class MoveInTheGround extends GroundState {
    private static MoveInTheGround instance;
    private ContextAnalyzer contextAnalyzer;
    private List<Boolean> resources;
    private int indexTile;

    /**
     * MoveInTheGround's constructor
     */
    private MoveInTheGround() {
        super();
        this.lastAction = null;
        contextAnalyzer = new ContextAnalyzer();
        resources = new ArrayList<>();
        indexTile = 0;
    }

    /**
     * Method to get the instance of the MoveInTheGround class
     * @return instance of the MoveInTheGround class
     */
    public static MoveInTheGround getInstance() {
        if (instance == null)
            instance = new MoveInTheGround();
        return instance;
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        resources = contextAnalyzer.biomeAnalyzer(context);
        if (lastAction != null && contextAnalyzer.isOcean(context))
            return ChoiceASide.getInstance();
        else if (!resources.isEmpty() && resources.get(indexTile))
            return ExploreTile.getInstance();
        else
            return MoveInTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        // if any action was made, we made the glimpse first
        if (lastAction == null) {
            act = new Glimpse(context.getHeading(), 4);
            lastAction = act;
            return act;
        }

        // we can check if the response of the glimpse was good
        // if not, we can change of the direction, for now, this is random
        // but after we can use the scout to choice the bast side
        if (lastAction instanceof Glimpse && contextAnalyzer.goodGlimpse(context)) {
            act = new MoveTo(Direction.randomSideDirection(context.getHeading()));
            indexTile = 0;
            lastAction = null;
            return act;
        }

        // we can move in the squares that the glimpse saw
        for (int i = indexTile + 1; i < resources.size(); i++) {
            if (resources.get(i)) {
                act = new MoveTo(context.getHeading());
                lastAction = act;
                indexTile++;
                return act;
            }
        }

        // if the program arive here, so, we need move, because there are nothing until here
        act = new MoveTo(context.getHeading());
        indexTile = 0;
        lastAction = null;
        return act;
    }
}
