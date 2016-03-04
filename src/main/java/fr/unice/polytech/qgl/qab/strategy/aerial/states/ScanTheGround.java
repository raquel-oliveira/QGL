package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Scan;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.strategy.context.Context;
import fr.unice.polytech.qgl.qab.strategy.context.utils.UpdaterMap;
import fr.unice.polytech.qgl.qab.util.enums.Found;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @version 12/12/15.
 */
public class ScanTheGround extends AerialState {

    private static final int SCAN_RATIO = 2;

    private UpdaterMap updaterMap;

    public ScanTheGround() {
        updaterMap = new UpdaterMap();
    }

    @Override
    public AerialState getState(Context context, Map map, StateMediator stateMediator) {
        updateMapData(context, map);

        // if is necessary return back to the ground
        if (returnBack(context)) {
            updateContext(context);
            return new ReturnBack();
        }

        // if is necessary fly until the ground
        if (needFlyUntil(context, stateMediator)) {
            updateContext(context);
            return new FlyUntil();
        }

        return new ScanTheGround();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        // we check if the plane is out of the ground (after scan), so make a echo
        if (context.current().getLastAction() instanceof Scan) {
            act = getEcho(context);
            if (act != null)
                return act;
        }

        // if action combo is null or empty, we set the combo fly + scan
        if (context.current().getComboAction() == null || context.current().getComboAction().isEmpty()) {
            context.current().setComboAction(new ComboFlyScan());
            context.current().getComboAction().defineActions();

            removeScan(context);
        }

        act = context.current().getComboAction().get(0);
        context.current().setLastAction(act);
        context.current().getComboAction().remove(0);

        return act;
    }

    private void updateMapData(Context context, Map map) {
        // update the position in the map if the plane fly
        if (context.current().getLastAction() instanceof Fly) {
            updaterMap.updateLastPositionFly(context, map);
        } else if (context.current().getLastAction() instanceof Scan) {
            updaterMap.setBiomeTile(context, map);
            context.getLastDiscovery().setCreeks(new ArrayList<>());
        }
    }

    /**
     * Method to check if we can remove the scan.
     * With this, we can make alternate scan
     * @param context
     */
    private static void removeScan(Context context) {
        if (!context.getLastDiscovery().getScanResponse().foundOcean()) {
            if (context.current().getContScan() != SCAN_RATIO) {
                context.current().getComboAction().remove(1);
            } else {
                context.current().setContScan(0);
            }
            context.current().setContScan(context.current().getContScan() + 1);
        }
    }

    /**
     * Check if it's necessary make fly until
     * @param context data context of the simulation
     * @param sm state madiator
     * @return
     */
    private static boolean needFlyUntil(Context context, StateMediator sm) {
        if (context.current().getLastAction() instanceof Echo && context.getLastDiscovery().getScanResponse().outOfGround()) {
            sm.setRangeToGround(context.getLastDiscovery().getEchoResponse().getRange() + 1);
            return true;
        }
        return false;
    }

    /**
     * Return a echo action of the action list
     * @param context data context of the simulation
     * @return
     */
    private static Action getEcho(Context context) {
        Action act;
        if (context.getLastDiscovery().getScanResponse().outOfGround()) {
            act = new Echo(context.getHeading());
            context.current().setLastAction(act);
            return act;
        }
        return null;
    }

    /**
     * Check if it's necessario make a return back
     * @param context data context of the simulation
     * @return
     */
    private static boolean returnBack(Context context) {
        if (context.current().getLastAction() instanceof Echo &&
                context.getLastDiscovery().getScanResponse().outOfGround() &&
                context.getLastDiscovery().getEchoResponse().getFound().equals(Found.OUT_OF_RANGE)) {

                context.getLastDiscovery().getScanResponse().setUpBiomes();
                return true;
        }
        return false;
    }


    /**
     * Method to updata the context
     * @param context
     */
    private static void updateContext(Context context) {
        context.current().setComboAction(null);
        context.current().setLastAction(null);
    }
}
