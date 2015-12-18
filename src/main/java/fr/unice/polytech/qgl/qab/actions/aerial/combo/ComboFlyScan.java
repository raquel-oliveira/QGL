package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;
import fr.unice.polytech.qgl.qab.actions.aerial.Scan;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;
import java.util.List;

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
     * @param head direction of the head
     */
    public void defineActions(Direction head) {
        actions.add(new Fly());
        actions.add(new Scan());
    }
}
