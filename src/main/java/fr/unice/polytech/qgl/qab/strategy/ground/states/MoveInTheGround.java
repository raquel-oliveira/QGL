package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
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
    private List<MoveTo> movemove;

    /**
     * MoveInTheGround's constructor
     */
    private MoveInTheGround() {
        super();
        this.lastAction = null;
        contextAnalyzer = new ContextAnalyzer();
        resources = new ArrayList<>();
        indexTile = 0;
        movemove = null;
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
        if (!resources.isEmpty() && resources.get(indexTile))
            return ExploreTile.getInstance();
        else
            return MoveInTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (lastAction == null) {
            act = new Glimpse(context.getHeading(), 4);
            lastAction = act;
            return act;
        }

        if (contextAnalyzer.shouldChangeStop(context) && movemove == null)
            return new Stop();

        if (contextAnalyzer.shouldChangeDirection(context) && movemove == null)
            changeDirection(context);

        if (movemove != null && !movemove.isEmpty()) {
            act = movemove.remove(0);
            lastAction = act;
            context.setHeading(act.getDirection());
            if (movemove.isEmpty()) {
                new ArrayList<>();
            }
            return act;
        }

        for (int i = indexTile + 1; i < resources.size(); i++) {
            if (resources.get(i)) {
                act = new MoveTo(context.getHeading());
                lastAction = act;
                indexTile++;
                return act;
            }
        }

        // if the program arive here, so, we need move, because thare are nothing until here
        act = new MoveTo(context.getHeading());
        indexTile = 0;
        lastAction = null;
        movemove = null;
        return act;
    }

    /**
     * This method will set the direction that the explorers need to move.
     * @param context datas about the context of the simulation
     */
    private void changeDirection(Context context) {
        if (movemove == null) movemove = new ArrayList<>();
        Direction dir = Direction.EAST;
        if (context.getHeading().isHorizontal()) {
            if (context.getHeading().equals(Direction.EAST))
                dir = Direction.WEST;
            else
                dir = Direction.EAST;
        } else if (context.getHeading().isVertical()) {
            if (context.getHeading().equals(Direction.NORTH))
                dir = Direction.SOUTH;
            else
                dir = Direction.SOUTH;
        }
        movemove.add(new MoveTo(dir));
        movemove.add(new MoveTo(dir));
        movemove.add(new MoveTo(dir));
        movemove.add(new MoveTo(Direction.randomSideDirection(context.getHeading())));
    }
}
