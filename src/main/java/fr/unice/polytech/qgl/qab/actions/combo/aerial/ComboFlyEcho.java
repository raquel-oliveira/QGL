package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Fly;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * Class to represent the set of actions Fly and 2 echos.
 * The Echos are defined to directions at right angles(perpendicular)
 * to the heading.
 * @version 11.12.2015
 */
public class ComboFlyEcho extends Combo {

    public ComboFlyEcho() {
        this.actions = new ArrayList<>();
    }

    /**
     * Set the actions
     * Actions: Fly and 2 echos from dinstintc directions
     * perpendiculars to heading.
     * @param head direction of the head
     */
    public void defineActions(Direction head) {
        actions.add(new Fly());
        if (head.isHorizontal()) {
            actions.add(new Echo(Direction.NORTH));
            actions.add(new Echo(Direction.SOUTH));
        }
        if (head.isVertical()) {
            actions.add(new Echo(Direction.WEST));
            actions.add(new Echo(Direction.EAST));
        }
    }
}
