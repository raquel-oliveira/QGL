package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Glimpse;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * @version 27/02/16.
 */
public class ComboGlimpse extends Combo {

    public ComboGlimpse() {
        this.actions = new ArrayList<>();
    }

    /**
     * Gave a number of range, this method add in the list of action a set of glimpse
     * @param direction glimpse direction
     */
    public void defineActions(Direction direction) {
        Direction dirRandom = Direction.randomSideDirection(direction);
        actions.add(new Glimpse(dirRandom, 4));
        actions.add(new Glimpse(Direction.inverse(dirRandom), 4));
        actions.add(new Glimpse(Direction.inverse(direction), 4));
    }
}
