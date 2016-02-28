package fr.unice.polytech.qgl.qab.strategy.ground.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

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

        // analize the glimpse response and get the resources
        if (context.current().getLastAction() instanceof Glimpse) {
            context.current().setGoodTiles(contextAnalyzer.biomeAnalyzer(context));

            // check if the response of the glimpse was good or not
            if (contextAnalyzer.isOcean(context)) //  || !contextAnalyzer.goodGlimpse(context)
                return ChoiceASide.getInstance();
        }

        if (isGoodToExplore(context)) {
            return ExploreTile.getInstance();
        } else {
            return GlimpseTheGround.getInstance();
        }
    }

    @Override
    public Action responseState(Context context, Map map) throws IndexOutOfBoundsComboAction {
        Action act;

        if (context.current().getIndexTile() == 0) {
            act = new Glimpse(context.getHeading(), 4);
            context.current().setLastAction(act);

            context.current().incrementIndexTile();
            return act;
        }

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
                && context.current().getIndexTile() > 0
                && context.current().getGoodTiles().get(context.current().getIndexTile() - 1);
    }
}
