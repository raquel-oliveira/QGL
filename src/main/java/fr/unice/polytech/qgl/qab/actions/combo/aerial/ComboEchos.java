package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * Class to represent the set of (3) actions Echo.
 * The three echos are defined to be for the three possibilites.
 * Of the 4 directions, the only one impossible to do the echo
 * is the direction oposite to the heading.
 * ex: Heading = NORTH;
 * ECHOS = NORTH, EAST, WEST (impossibility to do SOUTH).
 *
 * @version 9.12.2015
 */
public class ComboEchos extends Combo {

    public ComboEchos() {
        this.actions = new ArrayList<>();
    }

    /**
     * Set a combo(ArrayList) of echos
     *
     * @param head direction of the head
     */
    @Override
    public void defineActions(Direction head) {
        if (head.isHorizontal()) {
            if (head.compareTo(Direction.EAST) == 0) {
                actions.add(new Echo(Direction.EAST));
            } else {
                actions.add(new Echo(Direction.WEST));
            }
            actions.add(new Echo(Direction.SOUTH));
            actions.add(new Echo(Direction.NORTH));
        }
        if (head.isVertical()) {
            if (head.compareTo(Direction.NORTH) == 0) {
                actions.add(new Echo(Direction.NORTH));
            } else {
                actions.add(new Echo(Direction.SOUTH));
            }
            actions.add(new Echo(Direction.EAST));
            actions.add(new Echo(Direction.WEST));
        }
    }
}
