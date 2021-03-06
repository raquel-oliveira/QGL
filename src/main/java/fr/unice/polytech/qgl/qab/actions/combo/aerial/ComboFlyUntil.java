package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;

import java.util.ArrayList;

/**
 * Class to define a list of actions fly
 *
 * @version 12.12.2015
 */
public class ComboFlyUntil extends Combo {

    public ComboFlyUntil() {
        this.actions = new ArrayList<>();
    }

    /**
     * Add the number of times of fly to get in 'front'of the place choose
     *
     * @param range number of times to fly -1
     */
    @Override
    public void defineActions(int range) {
        for (int i = 0; i < range; i++) {
            actions.add(new Fly());
        }
    }

    @Override
    public boolean isEmpty() {
        return actions.isEmpty();
    }
}
