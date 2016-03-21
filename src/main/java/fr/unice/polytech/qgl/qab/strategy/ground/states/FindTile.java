package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.ground.ComboMoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.exception.action.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.map.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.MapHandler;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateFactory;
import fr.unice.polytech.qgl.qab.strategy.ground.factory.GroundStateType;
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
                context.current().getStatus() == 3) {
            context.current().setComboAction(null);
            context.current().setStatus(0);
            return GroundStateFactory.buildState(GroundStateType.SCOUT_TILE);
        }
        return GroundStateFactory.buildState(GroundStateType.FIND_TILE);
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Direction d1, d2;

        if (context.current().getStatus() == 0) {
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
            d1 = ContextAnalyzer.setDirectionHorizontal(context, map);

            // create action combo
            context.current().setComboAction(new ComboMoveTo());
            // distance X between two points
            int distx = MapHandler.calcDistX(context.current().getNextPosition(), map.getLastPositionGround());
            // update the coordenate X
            MapHandler.updatePositionX(distx, map, d1);

            // distance between tow points * 3 (squares in a tile)
            context.current().getComboAction().defineActions(d1, distx * 3);

            context.current().setStatus(1);

            // if the combo is empty, when the currente tile is has the biome of interest
            if (context.current().getComboAction().isEmpty()) {
                context.current().setStatus(2);
            }
        }
        Action act;

        if (context.current().getStatus() == 1 && !context.current().getComboAction().isEmpty()) {
            // get a action of the combo
            act = context.current().getComboAction().get(0);
            context.current().getComboAction().remove(0);
            return act;
        }

        // if get stop is true
        if (context.current().getComboAction().isEmpty() &&
                ((context.current().getStatus() == 1) || (context.current().getStatus() == 2))) {
            d2 = ContextAnalyzer.setDirectionVertical(context, map);

            // set a new action combo
            context.current().setComboAction(new ComboMoveTo());
            // distance Y between two points
            int distY = MapHandler.calcDistY(context.current().getNextPosition(), map.getLastPositionGround());
            MapHandler.updatePositionY(distY, map, d2);

            context.current().getComboAction().defineActions(d2, distY * 3);
            context.current().setStatus(3);
        }

        if (context.current().getStatus() == 3 && !context.current().getComboAction().isEmpty()) {
            // get a action of the combo
            act = context.current().getComboAction().get(0);
            context.current().getComboAction().remove(0);
            return act;
        }

        if (map.getLastPositionGround().getX() > map.getWidth()/2) {
            return new Scout(Direction.WEST);
        } else {
            return new Scout(Direction.EAST);
        }
    }
}
