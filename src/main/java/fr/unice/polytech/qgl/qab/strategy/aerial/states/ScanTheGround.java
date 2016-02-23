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
            context.setLastAction(null);
            return ReturnBack.getInstance();
        }

        if (needFlyUntil(context, stateMediator)) {
            context.setComboAction(null);
            return FlyUntil.getInstance();
        }

        return ScanTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        // we check if the plane is out of the ground (after scan), so make a echo
        if (context.getLastAction() instanceof Scan) {
            act = getEcho(context);
            if (act != null)
                return act;
        }

        // if action com is null or empty, we set the combo fly + scan
        if (context.getComboAction() == null || context.getComboAction().isEmpty()) {
            context.setComboAction(new ComboFlyScan());
            context.getComboAction().defineActions();
            if (!context.getLastDiscovery().getScanResponse().foundOcean()) {
                if (context.getContScan() != SCAN_RATIO) {
                    context.getComboAction().remove(1);
                } else {
                    context.setContScan(0);
                }
                context.setContScan(context.getContScan() + 1);
            }
        }

        act = context.getComboAction().get(0);
        context.setLastAction(act);
        context.getComboAction().remove(0);

        return act;
    }

    private boolean needFlyUntil(Context context, StateMediator sm) {
        if (context.getLastAction() instanceof Echo && context.getLastDiscovery().getScanResponse().outOfGround()) {
            sm.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange() + 1);
            return true;
        }
        return false;
    }

    private Action getEcho(Context context) {
        Action act;
        if (context.getLastDiscovery().getScanResponse().outOfGround()) {
            act = new Echo(context.getHeading());
            context.setLastAction(act);
            return act;
        }
        return null;
    }

    private boolean returnBack(Context context) {
        if (context.getLastAction() instanceof Echo &&
                context.getLastDiscovery().getScanResponse().outOfGround() &&
                context.getLastDiscovery().getCreeks().isEmpty() &&
                context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE)) {

                context.getLastDiscovery().getScanResponse().setUpBiomes();
                return true;
        }
        return false;
    }
}
