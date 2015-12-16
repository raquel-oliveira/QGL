package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.combo.ComboFlyScan;
<<<<<<< HEAD:src/main/java/fr/unice/polytech/qgl/qab/strategy/aerial/States/ScanTheGround.java
=======
import fr.unice.polytech.qgl.qab.actions.common.Land;
>>>>>>> 2c181fb4386ceea0c6d2ca43014aae2fbeded184:src/main/java/fr/unice/polytech/qgl/qab/strategy/aerial/States/State3.java
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.Biome;

import java.util.ArrayList;

/**
 * @version 12/12/15.
 */
public class ScanTheGround extends AerialState {
    public static ScanTheGround instance;

    private ComboFlyScan actionCombo;
    private UpdaterMap updaterMap;

    protected ScanTheGround() {
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
    public AerialState getState(Context context, Map map) {
        if (lastAction instanceof fr.unice.polytech.qgl.qab.actions.common.Land)
            return MakeLand.getInstance();

        if (context.getLastDiscovery().containsBiome(new Biome("OCEAN")) &&
                (context.getLastDiscovery().getBiomes().size() == 1)) {
            context.getLastDiscovery().setBiomes(new ArrayList<>());
            return ReturnBack.getInstance();
        }

        updateContext(context, map);
        return ScanTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map) {
        Action act;

        if (!context.getLastDiscovery().getCreeks().isEmpty()) {
            act = new fr.unice.polytech.qgl.qab.actions.common.Land(context.getLastDiscovery().getCreeks().get(0).getIdCreek(), 1);
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
