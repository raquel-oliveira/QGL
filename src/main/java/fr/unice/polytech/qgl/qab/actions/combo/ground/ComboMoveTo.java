package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * @version 01/02/16.
 *
 * Class that represent the combo of action move.
 */
public class ComboMoveTo extends Combo {
    public ComboMoveTo() {
        this.actions = new ArrayList<>();
    }

    /**
     * Gave a number of range, this method add in the list of action a set of move_to
     * @param direction move_to direction
     * @param range move_to range
     */
    public void defineActions(Direction direction, int range) {
        for (int i = 0; i < range; i++)
            actions.add(new MoveTo(direction));
    }
}
