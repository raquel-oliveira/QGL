package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.util.enums.Found;

/**
 * @version 12/12/15.
 */
public class ScanTheGround extends AerialState {

    private static final int SCAN_RATIO = 2;

    public static ScanTheGround getInstance() {
        return new ScanTheGround();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        if (!context.getLastDiscovery().getCreeks().isEmpty())
            return Finish.getInstance();

        if (returnBack(context)) {
            context.action().setLastAction(null);
            return ReturnBack.getInstance();
        }

        if (needFlyUntil(context, stateMediator)) {
            context.action().setComboAction(null);
            return FlyUntil.getInstance();
        }

        return ScanTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        // we check if the plane is out of the ground (after scan), so make a echo
        if (context.action().getLastAction() instanceof Scan) {
            act = getEcho(context);
            if (act != null)
                return act;
        }

        // if action com is null or empty, we set the combo fly + scan
        if (context.action().getComboAction() == null || context.action().getComboAction().isEmpty()) {
            context.action().setComboAction(new ComboFlyScan());
            context.action().getComboAction().defineActions();
            if (!context.getLastDiscovery().getScanResponse().foundOcean()) {
                if (context.action().getContScan() != SCAN_RATIO) {
                    context.action().getComboAction().remove(1);
                } else {
                    context.action().setContScan(0);
                }
                context.action().setContScan(context.action().getContScan() + 1);
            }
        }

        act = context.action().getComboAction().get(0);
        context.action().setLastAction(act);
        context.action().getComboAction().remove(0);

        return act;
    }

    private boolean needFlyUntil(Context context, StateMediator sm) {
        if (context.action().getLastAction() instanceof Echo && context.getLastDiscovery().getScanResponse().outOfGround()) {
            sm.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange() + 1);
            return true;
        }
        return false;
    }

    private Action getEcho(Context context) {
        Action act;
        if (context.getLastDiscovery().getScanResponse().outOfGround()) {
            act = new Echo(context.getHeading());
            context.action().setLastAction(act);
            return act;
        }
        return null;
    }

    private boolean returnBack(Context context) {
        if (context.action().getLastAction() instanceof Echo &&
                context.getLastDiscovery().getScanResponse().outOfGround() &&
                context.getLastDiscovery().getCreeks().isEmpty() &&
                context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE)) {

                context.getLastDiscovery().getScanResponse().setUpBiomes();
                return true;
        }
        return false;
    }
}
