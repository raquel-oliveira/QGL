package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.combo.aerial.ComboFlyScan;
import fr.unice.polytech.qgl.qab.actions.simple.common.Land;
import fr.unice.polytech.qgl.qab.exception.IndexOutOfBoundsComboAction;
import fr.unice.polytech.qgl.qab.map.Map;
import fr.unice.polytech.qgl.qab.map.tile.Biomes;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * @version 12/12/15.
 */
public class ScanTheGround extends AerialState {
    private static ScanTheGround instance;

    private Combo actionCombo;

    private ScanTheGround() {
        super();
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
            return Finish.getInstance();

        if (context.getLastDiscovery().containsBiome(Biomes.OCEAN) &&
                context.getLastDiscovery().getBiomes().size() == 1 &&
                    context.getLastDiscovery().getCreeks().isEmpty()) {
            context.getLastDiscovery().setBiomes(new ArrayList<>());
            return ReturnBack.getInstance();
        }
        return ScanTheGround.getInstance();
    }

    @Override
    public Action responseState(Context context, Map map, StateMediator stateMediator) throws IndexOutOfBoundsComboAction {
        Action act;

        if (!context.getLastDiscovery().getCreeks().isEmpty()) {
            act = new Land(context.getLastDiscovery().getCreeks().get(0).getIdCreek(), 1);
            lastAction = act;
            return act;
        }

        Random rnd = new Random();
        if (actionCombo == null || actionCombo.isEmpty()) {
            actionCombo = new ComboFlyScan();
            ((ComboFlyScan)actionCombo).defineActions();
            if(rnd.nextBoolean()){
                actionCombo.remove(1);
            }
        }

        act = actionCombo.get(0);
        actionCombo.remove(0);

        return act;
    }
}
