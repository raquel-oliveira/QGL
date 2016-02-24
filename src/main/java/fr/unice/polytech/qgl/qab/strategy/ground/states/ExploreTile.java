package fr.unice.polytech.qgl.qab.strategy.ground.states;


import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.exception.PositionOutOfMapRange;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

/**
 * @version 07/02/16.
 *
 * State responsable by explore the specific tile of the ground.
 * In this state we can make the explore and exploit of the tile.
 */
public class ExploreTile extends GroundState {
    private ContextAnalyzer contextAnalyzer;

    /**
     * ExploreTile's contructor
     */
    private ExploreTile() {
        contextAnalyzer = new ContextAnalyzer();
    }

    /**
     * Method to get the instance of the ExploreTile class
     * @return instance of ExploreTile
     */
    public static ExploreTile getInstance() {
        return new ExploreTile();
    }

    @Override
    public GroundState getState(Context context, Map map) throws PositionOutOfMapRange {
        context.current().setResourcesToExploit(contextAnalyzer.resourceAnalyzer(context));

        if (context.current().getResourcesToExploit().isEmpty()) {
            return GlimpseTheGround.getInstance();
        } else {
            return ExploreTile.getInstance();
        }
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act;

        act = new Explore();
        context.current().setLastAction(act);
        return act;
    }
}