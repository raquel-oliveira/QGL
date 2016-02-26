package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * @version 24/02/16.
 */
public class GlimpseTheOcean extends GroundState {

    /**
     * GlimpseTheGround's constructor
     */
    private GlimpseTheOcean() {
    }

    /**
     * Method to get the instance of the GlimpseTheGround class
     * @return instance of the GlimpseTheGround class
     */
    public static GlimpseTheOcean getInstance() {
        return new GlimpseTheOcean();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {

        // if the glimpse is finished, return
        if (context.current().getIndexTile() == 0) {
            context.setHeading(Direction.inverse(context.getHeading()));
            context.current().moveUntil(4);
            return MoveUntilTile.getInstance();
        }
        // if the glimpse if not finished yet, make explore
        else if (isGoodToExplore(context)) {
            return ExploreTile.getInstance();
        }

        // if any situations before happen, keep move
        return GlimpseTheOcean.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        act = new MoveTo(context.getHeading());
        context.current().setLastAction(act);
        context.current().incrementIndexTile();

        return act;
    }

    /**
     * This method just check if the current tile it's good to explore (and exploit)
     * @param context
     * @return if the tile it's good to explore
     */
    private boolean isGoodToExplore(Context context) {
        return !context.current().getGoodTiles().isEmpty()
                && context.current().getGoodTiles().get(context.current().getIndexTile());
    }

    /**
     * Method to updata the context
     * @param context
     */
    private void updateContext(Context context) {
        context.current().setComboAction(null);
    }
}