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
 * @version 07/02/16.
 *
 * State responsable by move the explorer in the ground.
 * In this state we can make the move_to and glimpse.
 */
public class MoveInTheGround extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    /**
     * MoveInTheGround's constructor
     */
    private MoveInTheGround() {
        contextAnalyzer = new ContextAnalyzer();
    }

    /**
     * Method to get the instance of the MoveInTheGround class
     * @return instance of the MoveInTheGround class
     */
    public static MoveInTheGround getInstance() {
        return new MoveInTheGround();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        // analize the glimpse response and get the resources
        context.current().setGoodTiles(contextAnalyzer.biomeAnalyzer(context));

        // if the explorers are in the ocean
        if (context.current().getLastAction() != null && contextAnalyzer.isOcean(context)) {
            return ChoiceASide.getInstance();
        }
        // if we have the good tiles and the current tile it's good, explore it
        else if (findTileToExplore(context)) {
            updateContext(context);
            return ExploreTile.getInstance();
        }
        // if any situations before happen, keep move
        else {
            return MoveInTheGround.getInstance();
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        // if any action was made, we made the glimpse first
        if (context.current().getLastAction() == null) {
            act = new Glimpse(context.getHeading(), 4);
            context.current().setLastAction(act);
            return act;
        }

        // we can check if the response of the glimpse was good
        // if not, we can change of the direction, for now, this is random
        // but after we can use the scout to choice the bast side
        if (context.current().getLastAction() instanceof Glimpse && contextAnalyzer.goodGlimpse(context)) {
            act = new MoveTo(Direction.randomSideDirection(context.getHeading()));
            context.current().setIndexTile(0);
            context.current().setLastAction(null);
            return act;
        }

        // we can move in the squares that the glimpse saw
        for (int i = context.current().getIndexTile() + 1; i < context.current().getGoodTiles().size(); i++) {
            if (context.current().getGoodTiles().get(i)) {
                act = new MoveTo(context.getHeading());
                context.current().setLastAction(act);
                context.current().setIndexTile(context.current().getIndexTile() + 1);
                return act;
            }
        }

        // if the program arive here, so, we need move, because there are nothing until here
        act = new MoveTo(context.getHeading());
        context.current().setIndexTile(0);
        context.current().setLastAction(null);
        return act;
    }

    /**
     * This method just check if the current tile it's good to explore (and exploit)
     * @param context
     * @return if the tile it's good to explore
     */
    private boolean findTileToExplore(Context context) {
        return !context.current().getGoodTiles().isEmpty()
                && context.current().getGoodTiles().get(context.current().getIndexTile());
    }

    /**
     * Method to updata the context
     * @param context
     */
    private void updateContext(Context context) {
        context.current().setLastAction(null);
    }
}
