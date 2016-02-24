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

/**
 * @version 07/02/16.
 *
 * State responsable by move the explorer in the ground.
 * In this state we can make the move_to and glimpse.
 */
public class GlimpseTheGround extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    /**
     * GlimpseTheGround's constructor
     */
    private GlimpseTheGround() {
        contextAnalyzer = new ContextAnalyzer();
    }

    /**
     * Method to get the instance of the GlimpseTheGround class
     * @return instance of the GlimpseTheGround class
     */
    public static GlimpseTheGround getInstance() {
        return new GlimpseTheGround();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {

        if (contextAnalyzer.isOcean(context)) {
            return MoveGround.getInstance();
        }

        // if the glimpse response was not good :/
        if (context.current().getLastAction() instanceof Glimpse && !contextAnalyzer.goodGlimpse(context)) {
            updateContext(context);
            return ChoiceASide.getInstance();
        }

        // analize the glimpse response and get the resources
        if (context.current().getLastAction() instanceof Glimpse)
            context.current().setGoodTiles(contextAnalyzer.biomeAnalyzer(context));

        // if we have the good tiles and the current tile it's good, explore it
        if (isGoodToExplore(context)) {
            updateContext(context);
            context.current().setIndexTile((context.current().getIndexTile() + 1) % 4);
            return ExploreTile.getInstance();
        }

        // if any situations before happen, keep move
        return GlimpseTheGround.getInstance();

    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        // if any action was made, we made the glimpse first
        if (context.current().getIndexTile() == 0) {
            act = new Glimpse(context.getHeading(), 4);
            context.current().setLastAction(act);
            return act;
        }

        act = new MoveTo(context.getHeading());
        context.current().setLastAction(act);
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
