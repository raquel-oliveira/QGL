package fr.unice.polytech.qgl.qab.actions.aerial.combo;

import fr.unice.polytech.qgl.qab.actions.aerial.Echo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * Class to represent the set of actions Echo.
 *
 * @version 9.12.2015
 */
public class ComboEchos extends Combo {

    public ComboEchos() {
        this.actions = new ArrayList<>();
    }

    /**
     * Choice the direction to three echos.
     *
     * @param head direction of the head
     */
    public void defineComboEchos(Direction head) {
        if (head.isHorizontal()) {
            if (head.compareTo(Direction.EAST) == 0) {
                actions.add(new Echo(Direction.EAST));
            } else {
                actions.add(new Echo(Direction.WEST));
            }
            actions.add(new Echo(Direction.NORTH));
            actions.add(new Echo(Direction.SOUTH));
        }
        if (head.isVertical()) {
            if (head.compareTo(Direction.NORTH) == 0) {
                actions.add(new Echo(Direction.NORTH));
            } else {
                actions.add(new Echo(Direction.SOUTH));
            }
            actions.add(new Echo(Direction.WEST));
            actions.add(new Echo(Direction.EAST));
        }
    }

}
