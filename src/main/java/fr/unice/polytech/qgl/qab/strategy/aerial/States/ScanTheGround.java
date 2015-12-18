package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.common.Land;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.map.tile.Biome;

import java.util.ArrayList;

/**
 * @version 12/12/15.
 */
public class ScanTheGround extends AerialState {
    private static ScanTheGround instance;

    private ComboFlyScan actionCombo;
    private UpdaterMap updaterMap;

    private ScanTheGround() {
        super();
        updaterMap = new UpdaterMap();
        this.lastAction = new Fly();
        actionCombo = null;
    }

    public static ScanTheGround getInstance() {
        if (instance == null)
            instance = new ScanTheGround();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (lastAction instanceof Land)
            return MakeLand.getInstance();

        if (context.getLastDiscovery().containsBiome(new Biome("OCEAN")) &&
                (context.getLastDiscovery().getBiomes().size() == 1)) {
            context.getLastDiscovery().setBiomes(new ArrayList<>());
            return ReturnBack.getInstance();
        }
        return ScanTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) {
        Action act;

        if (!context.getLastDiscovery().getCreeks().isEmpty()) {
            act = new Land(context.getLastDiscovery().getCreeks().get(0).getIdCreek(), 1);
            lastAction = act;
            return act;
        }

        if (actionCombo != null && actionCombo.isEmpty() && !(lastAction instanceof Echo)) {
            act = new Echo(context.getHeading());
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
