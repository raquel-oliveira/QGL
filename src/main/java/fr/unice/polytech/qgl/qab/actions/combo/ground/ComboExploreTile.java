package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Explore;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * @version 01/03/16.
 */
public class ComboExploreTile extends Combo {

    public ComboExploreTile() {
        this.actions = new ArrayList<>();
    }

    /**
     * Gave a number of range, this method add in the list of action a set of scout
     */
    @Override
    public void defineActions() {
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.EAST));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.SOUTH));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.WEST));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.WEST));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.NORTH));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.NORTH));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.EAST));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.EAST));
        actions.add(new Explore());

        actions.add(new MoveTo(Direction.SOUTH));
        actions.add(new MoveTo(Direction.WEST));
    }
}
