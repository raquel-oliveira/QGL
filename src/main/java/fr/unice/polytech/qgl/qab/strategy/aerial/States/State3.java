package fr.unice.polytech.qgl.qab.strategy.aerial.States;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.common.Land;
import fr.unice.polytech.qgl.qab.actions.common.Stop;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.Biome;

/**
 * @version 12/12/15.
 */
public class State3 extends State {
    public static State3 instance;

    private ComboFlyScan actionCombo;
    private Action lastAction;
    private UpdaterMap updaterMap;

    protected State3() {
        updaterMap = new UpdaterMap();
        lastAction = new Fly();
        actionCombo = null;
    }

    public static State3 getInstance() {
        if (instance == null)
            instance = new State3();
        return instance;
    }

    @Override
    public State getState(Context context, Map map) {
        if (lastAction instanceof Land)
            return State4.getInstance();
        updateContext(context, map);
        return State3.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act = null;

        if (!context.getLastDiscovery().getCreeks().isEmpty()) {
            act = new Land(context.getLastDiscovery().getCreeks().get(0).getIdCreek(), 1);
            lastAction = act;
            return act;
        }

        if (context.getLastDiscovery().containsBiome(new Biome("OCEAN"))) {
            act = new Stop();
            lastAction = act;
            return act;
        }

        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyScan();
            actionCombo.defineActions(context.getHeading());
        }

        act = actionCombo.get(0);
        actionCombo.remove(0);

        return act;
    }

    private void updateContext(Context context, Map map) {
        updaterMap.updateLastPositionFly(context, map);
    }
}
