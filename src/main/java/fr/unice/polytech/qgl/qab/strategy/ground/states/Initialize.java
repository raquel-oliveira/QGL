package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboMoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.List;

/**
 * @version 29/02/16.
 */
public class Initialize extends GroundState {
    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        return new Initialize();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        List<Position> goodPositions = map.getGoodPositions(context);
        Position position = map.positionClose(goodPositions);
        Direction d1 = null, d2 = null;
        if (position != null) {
            if (position.getX() > map.getLastPosition().getX())
                d1 = Direction.EAST;
            else
                d1 = Direction.WEST;

            if (position.getY() > map.getLastPosition().getX())
                d2 = Direction.SOUTH;
            else
                d2 = Direction.NORTH;
        }

        if (context.current().getComboAction() == null) {
            context.current().setComboAction(new ComboMoveTo());
            context.current().getComboAction().defineActions(d1, calcDistX(position, map.getLastPosition()));
        }

        if (context.current().getComboAction() != null &&
                context.current().getComboAction().isEmpty()) {
            context.current().setComboAction(new ComboMoveTo());
            context.current().getComboAction().defineActions(d2, calcDistY(position, map.getLastPosition()));
            context.current().setStop(true);
        }

        if (context.current().getComboAction() != null &&
                context.current().getComboAction().isEmpty() &&
                context.current().getStop()) {
            return new Stop();
        }

        Action act = context.current().getComboAction().get(0);
        context.current().getComboAction().remove(0);
        return act;
    }

    private int calcDistX(Position p, Position lastPosition) {
        return Math.abs(p.getX() - lastPosition.getX());
    }

    private int calcDistY(Position p, Position lastPosition) {
        return Math.abs(p.getY() - lastPosition.getY());
    }
}
