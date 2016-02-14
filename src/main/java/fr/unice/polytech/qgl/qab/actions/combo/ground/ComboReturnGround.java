package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * @version 01/02/16.
 *
 * Class that represents
 */
public class ComboReturnGround extends Combo {
    public ComboReturnGround() {
        this.actions = new ArrayList<>();
    }

    /**
     * Method that set the move_to to return to the ground
     * @param head direction of the head
     * @param firstHead direction of the firts head
     */
    public void defineActions(Direction head, Direction firstHead) {
        actions.add(new MoveTo(firstHead));
        if (head.isHorizontal()) {
            if (head.equals(Direction.EAST))
                actions.add(new MoveTo(Direction.WEST));
            else
                actions.add(new MoveTo(Direction.EAST));
        } else {
            if (head.equals(Direction.NORTH))
                actions.add(new MoveTo(Direction.SOUTH));
            else
                actions.add(new MoveTo(Direction.NORTH));
        }
    }
}
