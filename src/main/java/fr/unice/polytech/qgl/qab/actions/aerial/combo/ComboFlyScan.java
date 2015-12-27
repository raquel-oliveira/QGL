package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Scan;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * Class to represent the set of fly and scan
 * Goal: find a creek
 * @version 12.12.2015.
 */
public class ComboFlyScan extends Combo{

    public ComboFlyScan() {
        this.actions = new ArrayList<>();
    }

    /**
     * Set the actions (fly and scan)
     */
    public void defineActions() {
        actions.add(new Fly());
        actions.add(new Scan());
    }
}
