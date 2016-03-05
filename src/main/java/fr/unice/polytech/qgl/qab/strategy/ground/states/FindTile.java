package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboMoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @version 29/02/16.
 */
public class FindTile extends GroundState {

    public FindTile() {
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        if (context.current().getComboAction() != null &&
                context.current().getComboAction().isEmpty() &&
                context.current().getStop() == 3) {
            context.current().setComboAction(null);
            context.current().setStop(0);
            return new ScoutTile();
        }
        return new FindTile();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Direction d1, d2;

        if (context.current().getStop() == 0) {
            // take the position with interesting biomes for the contract
            List<Position> goodPositions = map.getGoodPositions(context);

            if (goodPositions == null || goodPositions.isEmpty())
                return new Stop();

            Position postClose = map.positionClose(goodPositions, map.getLastPositionGround());
            context.current().setNextPosition(postClose);

            // Didn't find more tiles
            if (context.current().getNextPosition() == null)
                return new Stop();

            // Set tile as visible
            map.setTileVisited(context.current().getNextPosition());

            // direction to horizontal movement
            d1 = setDirectionHorizontal(context, map);

            // create action combo
            context.current().setComboAction(new ComboMoveTo());
            // distance X between two points
            int distx = calcDistX(context.current().getNextPosition(), map.getLastPositionGround());
            // update the coordenate X
            updatePositionX(distx, map, d1);

            // distance between tow points * 3 (squares in a tile)
            context.current().getComboAction().defineActions(d1, distx * 3);

            context.current().setStop(1);

            // if the combo is empty, when the currente tile is has the biome of interest
            if (context.current().getComboAction().isEmpty()) {
                context.current().setStop(2);
                // set flag as true, this flag define if the
                /*if (map.getLastPositionGround().getX() > map.getWidth()/2) {
                    updatePositionX(1, map, Direction.WEST);
                    return new Scout(Direction.WEST);
                } else {
                    updatePositionX(1, map, Direction.EAST);
                    return new Scout(Direction.EAST);
                }*/
            }
        }
        Action act;

        if (context.current().getNextPosition().getX() > 50 || context.current().getNextPosition().getY() > 50)
            return new Stop();

        if (context.current().getStop() == 1 && !context.current().getComboAction().isEmpty()) {
            // get a action of the combo
            act = context.current().getComboAction().get(0);
            context.current().getComboAction().remove(0);
            return act;
        }

        // if get stop is true
        if (context.current().getComboAction().isEmpty() &&
                ((context.current().getStop() == 1) || (context.current().getStop() == 2))) {
            d2 = setDirectionVertical(context, map);

            // set a new action combo
            context.current().setComboAction(new ComboMoveTo());
            // distance Y between two points
            int distY = calcDistY(context.current().getNextPosition(), map.getLastPositionGround());
            updatePositionY(distY, map, d2);

            context.current().getComboAction().defineActions(d2, distY * 3); // remove -1
            context.current().setStop(3);
        }

        if (context.current().getStop() == 3 && !context.current().getComboAction().isEmpty()) {
            // get a action of the combo
            act = context.current().getComboAction().get(0);
            context.current().getComboAction().remove(0);
            return act;
        }

        if (map.getLastPositionGround().getX() > map.getWidth()/2) {
            //updatePositionX(1, map, Direction.WEST);
            return new Scout(Direction.WEST);
        } else {
            updatePositionX(1, map, Direction.WEST);
            return new Scout(Direction.EAST);
        }
    }

    private Direction setDirectionHorizontal(Context context, Map map) {
        Direction d1;
        if (context.current().getNextPosition().getX() > map.getLastPositionGround().getX())
            d1 = Direction.EAST;
        else
            d1 = Direction.WEST;
        return d1;
    }

    private Direction setDirectionVertical(Context context, Map map) {
        Direction dir;
        if (context.current().getNextPosition().getY() > map.getLastPositionGround().getX())
            dir = Direction.SOUTH;
        else
            dir = Direction.NORTH;
        return dir;
    }

    private int calcDistX(Position p, Position lastPosition) {
        return Math.abs(p.getX() - lastPosition.getX());
    }

    private int calcDistY(Position p, Position lastPosition) {
        return Math.abs(p.getY() - lastPosition.getY());
    }

    private void updatePositionX(int x, Map map, Direction dir) {
        if (dir.equals(Direction.EAST))
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() + x);
        else
            map.getLastPositionGround().setX(map.getLastPositionGround().getX() - x);
    }

    private void updatePositionY(int y, Map map, Direction dir) {
        if (dir.equals(Direction.NORTH))
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() - y);
        else
            map.getLastPositionGround().setY(map.getLastPositionGround().getY() + y);
    }
}
