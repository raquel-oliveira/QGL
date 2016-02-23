package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * @version 24/02/16.
 */
public class ComboScout extends Combo {
    public ComboScout() {
        this.actions = new ArrayList<>();
    }

    /**
     * Gave a number of range, this method add in the list of action a set of move_to
     * @param direction move_to direction
     */
    public void defineActions(Direction direction) {
        actions.add(new MoveTo(direction));
        Direction dir = Direction.randomSideDirection(direction);
        actions.add(new MoveTo(dir));
        actions.add(new MoveTo(Direction.inverse(dir)));
    }
}
