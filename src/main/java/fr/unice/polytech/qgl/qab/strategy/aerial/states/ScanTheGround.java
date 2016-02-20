package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyUntil;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.actions.simple.common.Stop;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Found;

import java.util.ArrayList;

/**
 * @version 12/12/15.
 */
public class ScanTheGround extends AerialState {
    private static ScanTheGround instance;

    private Combo actionCombo;
    private final static int SCAN_RATIO = 2;
    private int cont_scan;
    private ComboFlyUntil comboFlyUntil;

    private ScanTheGround() {
        super();
        this.lastAction = new Fly();
        actionCombo = null;
        cont_scan = 0;
        comboFlyUntil = null;
    }

    public static ScanTheGround getInstance() {
        if (instance == null)
            instance = new ScanTheGround();
        return instance;
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (lastAction instanceof Land)
            return Finish.getInstance();

        if (context.getLastDiscovery().getScanResponse().outOfGround() &&
                    context.getLastDiscovery().getCreeks().isEmpty()) {
            if (lastAction instanceof Echo &&
                    context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE)) {
                context.getLastDiscovery().getScanResponse().setUpBiomes();
                return ReturnBack.getInstance();
            }
        }
        return ScanTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        if (!context.getLastDiscovery().getCreeks().isEmpty()) {
            return getLand(context);
        }

        if (lastAction instanceof Scan) {
            act = getEcho(context);
            if (act != null) return act;
        } else if (lastAction instanceof Echo) {
            setFlyUntil(context);
        }

        if (comboFlyUntil != null && !comboFlyUntil.isEmpty()) {
            return getFly(context);
        }

        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyScan();
            ((ComboFlyScan)actionCombo).defineActions();
            if (!context.getLastDiscovery().getScanResponse().foundOcean()) {
                if (cont_scan != SCAN_RATIO) {
                    actionCombo.remove(1);
                } else cont_scan = 0;
                cont_scan++;
            }
        }

        act = actionCombo.get(0);
        lastAction = act;
        actionCombo.remove(0);

        return act;
    }

    private void setFlyUntil(Context context) {
        if (context.getLastDiscovery().getScanResponse().outOfGround()) {
            comboFlyUntil = new ComboFlyUntil();
            comboFlyUntil.defineComboFlyUntil(context.getLastDiscovery().getEchoResponse().getRange() + 1);
        }
    }

    private Action getEcho(Context context) {
        Action act;
        if (context.getLastDiscovery().getScanResponse().outOfGround()) {
            act = new Echo(context.getHeading());
            lastAction = act;
            return act;
        }
        return null;
    }

    private Action getFly(Context context) throws IndexOutOfBoundsComboAction {
        Action act;
        act = comboFlyUntil.get(0);
        lastAction = act;
        comboFlyUntil.remove(0);
        if (comboFlyUntil.isEmpty()) {
            actionCombo = null;
            context.getLastDiscovery().getScanResponse().setUpBiomes();
        }
        return act;
    }

    private Action getLand(Context context) {
        Action act;
        act = new Land(context.getLastDiscovery().getCreeks().get(0).getIdCreek(), 1);
        lastAction = act;
        return act;
    }
}
