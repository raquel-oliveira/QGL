package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * @version 01/02/16.
 */
public class ComboMoveTo extends Combo {
    public ComboMoveTo() {
        this.actions = new ArrayList<>();
    }

    /**
     * @param direction
     * @param range
     */
    public void defineActions(Direction direction, int range) {
        for (int i = 0; i < range; i++)
            actions.add(new MoveTo(direction));
    }
}
