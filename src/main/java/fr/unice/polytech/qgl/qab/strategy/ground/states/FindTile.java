package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboMoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
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
public class FindTile extends GroundState {
    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (context.current().getComboAction() != null &&
                context.current().getComboAction().isEmpty() &&
                context.current().getStop()) {
            context.current().setComboAction(null);
            return new ScoutTile();
        }
        return new FindTile();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        List<Position> goodPositions = map.getGoodPositions(context);
        Position position = map.positionClose(goodPositions);

        if (position == null)
            return new Stop();

        map.setTileVisited(position);
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
            context.current().getComboAction().defineActions(d1, calcDistX(position, map.getLastPosition()) * 3);

            if (context.current().getComboAction().isEmpty()) {
                context.current().setStop(true);
                if (map.getLastPosition().getX()/2 > map.getWidth())
                    return new MoveTo(Direction.WEST);
                else
                    return new MoveTo(Direction.EAST);
            }
        }

        Action act = context.current().getComboAction().get(0);
        context.current().getComboAction().remove(0);

        if (context.current().getComboAction().isEmpty())
            context.current().setStop(true);

        if (context.current().getStop()) {
            context.current().setComboAction(new ComboMoveTo());
            context.current().getComboAction().defineActions(d2, calcDistY(position, map.getLastPosition()) * 3);
        }

        return act;
    }

    private int calcDistX(Position p, Position lastPosition) {
        return Math.abs(p.getX() - lastPosition.getX());
    }

    private int calcDistY(Position p, Position lastPosition) {
        return Math.abs(p.getY() - lastPosition.getY());
    }
}
