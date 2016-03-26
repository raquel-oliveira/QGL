package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * Class that represent the combo of action scout.
 * @version 24/02/16.
 */
public class ComboScout extends Combo {
    public ComboScout() {
        this.actions = new ArrayList<>();
    }

    /**
     * Gave a number of range, this method add in the list of action a set of scout
     * @param direction move_to direction
     */
    @Override
    public void defineActions(Direction direction) {
        Direction dir = Direction.randomSideDirection(direction);
        actions.add(new Scout(dir));
        actions.add(new Scout(Direction.inverse(dir)));
        actions.add(new Scout(direction));
    }
}
