package fr.unice.polytech.qgl.qab.actions.combo.ground;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.MoveTo;
import fr.unice.polytech.qgl.qab.actions.simple.ground.Scout;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * Class that represent the combo of action scout a tile (3 x 3).
 * @version 20/03/16.
 */
public class ComboScoutTile extends Combo {
   public ComboScoutTile() {
       this.actions = new ArrayList<>();
   }

    /**
     * Gave a number of range, this method add in the list of action a set of scout
     */
    @Override
    public void defineActions() {
        actions.add(new Scout(Direction.WEST));
        actions.add(new MoveTo(Direction.WEST));

        actions.add(new Scout(Direction.NORTH));
        actions.add(new MoveTo(Direction.NORTH));

        actions.add(new Scout(Direction.EAST));
        actions.add(new MoveTo(Direction.EAST));
        actions.add(new Scout(Direction.EAST));
        actions.add(new MoveTo(Direction.EAST));

        actions.add(new Scout(Direction.SOUTH));
        actions.add(new MoveTo(Direction.SOUTH));
        actions.add(new Scout(Direction.SOUTH));
        actions.add(new MoveTo(Direction.SOUTH));

        actions.add(new Scout(Direction.WEST));
        actions.add(new MoveTo(Direction.WEST));
        actions.add(new Scout(Direction.WEST));
        actions.add(new MoveTo(Direction.WEST));

        actions.add(new MoveTo(Direction.NORTH));
        actions.add(new MoveTo(Direction.EAST));
    }
}
