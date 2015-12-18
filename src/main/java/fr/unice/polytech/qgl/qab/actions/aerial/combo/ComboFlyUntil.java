package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.aerial.Fly;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to define a list of actions fly
 *
 * @version 12.12.2015
 */
public class ComboFlyUntil extends Combo{

    public ComboFlyUntil() {
        this.actions = new ArrayList<>();
    }

    /**
     * Add the number of times of fly to get in 'front'of the place choose
     *
     * @param range number of times to fly -1
     */
    public void defineComboFlyUntil(int range) {
        for (int i = 1; i < range; i++) {
            actions.add(new Fly());
        }
    }
}
