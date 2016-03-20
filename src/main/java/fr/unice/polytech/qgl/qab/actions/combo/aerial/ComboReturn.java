package fr.unice.polytech.qgl.qab.actions.combo.aerial;

import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Echo;
import fr.unice.polytech.qgl.qab.actions.simple.aerial.Heading;
import fr.unice.polytech.qgl.qab.util.enums.Direction;

import java.util.ArrayList;

/**
 * @version 9.12.2015
 */
public class ComboReturn extends Combo {

    public ComboReturn() {
        this.actions = new ArrayList<>();
    }

    @Override
    public void defineActions(Direction head, Direction moveTo) {
        if (head.isHorizontal()) {
            turnHorizontal(head, moveTo);
        }
        if (head.isVertical()) {
            turnVertical(head, moveTo);
        }
    }

    private void turnVertical(Direction head, Direction moveTo) {
        if (head.isEquals(Direction.SOUTH)) {
            turnBackHeadSouth(moveTo);
        } else {
            turnBackHeadNorth(moveTo);
        }
    }

    private void turnHorizontal(Direction head, Direction moveTo) {
        if (head.isEquals(Direction.EAST)) {
            turnBackHeadEast(moveTo);
        } else {
            turnBackHeadWest(moveTo);
        }
    }

    private void turnDowntoEast() {
        setDirections(Direction.SOUTH, Direction.EAST);
    }

    private void turnDowntoWest() {
        setDirections(Direction.SOUTH, Direction.WEST);
    }

    private void turnEastUp() {
        setDirections(Direction.EAST, Direction.NORTH);
    }

    private void turnEastDown() {
        setDirections(Direction.EAST, Direction.SOUTH);
    }

    private void turnWestUp() {
        setDirections(Direction.WEST, Direction.NORTH);
    }

    private void turnWestDown() {
        setDirections(Direction.WEST, Direction.SOUTH);
    }

    private void turnUptoWest() {
        setDirections(Direction.NORTH, Direction.WEST);
    }

    private void turnUptoEast() {
        setDirections(Direction.NORTH, Direction.EAST);
    }

    public void turnBackHeadSouth(Direction moveTo) {
        if (moveTo.isEquals(Direction.EAST)) {
            turnEastUp();
            actions.add(new Echo(Direction.NORTH));
            turnEastDown();
            actions.add(new Echo(Direction.SOUTH));
        }
        else if (moveTo.isEquals(Direction.WEST)) {
            turnWestUp();
            actions.add(new Echo(Direction.NORTH));
            turnWestDown();
            actions.add(new Echo(Direction.SOUTH));
        } 
    }

    public void turnBackHeadNorth(Direction moveTo) {
        if (moveTo.isEquals(Direction.EAST)) {
            turnEastDown();
            actions.add(new Echo(Direction.SOUTH));
            turnEastUp();
            actions.add(new Echo(Direction.NORTH));
        }
        else if (moveTo.isEquals(Direction.WEST)) {
            turnWestDown();
            actions.add(new Echo(Direction.SOUTH));
            turnWestUp();
            actions.add(new Echo(Direction.NORTH));
        } 
    }

    public void turnBackHeadEast(Direction moveTo) {
        if (moveTo.isEquals(Direction.SOUTH)) {
            turnDowntoWest();
            actions.add(new Echo(Direction.WEST));
            turnDowntoEast();
            actions.add(new Echo(Direction.EAST));
        }
        else if (moveTo.isEquals(Direction.NORTH)) {
            turnUptoWest();
            actions.add(new Echo(Direction.WEST));
            turnUptoEast();
            actions.add(new Echo(Direction.EAST));
        }
    }

    private void turnBackHeadWest(Direction moveTo) {
        if (moveTo.isEquals(Direction.SOUTH)) {
            turnDowntoEast();
            actions.add(new Echo(Direction.EAST));
            turnDowntoWest();
            actions.add(new Echo(Direction.WEST));
        }
        else if (moveTo.isEquals(Direction.NORTH)) {
            turnUptoEast();
            actions.add(new Echo(Direction.EAST));
            turnUptoWest();
            actions.add(new Echo(Direction.WEST));
        }
    }

    public void setDirections(Direction d1, Direction d2) {
        actions.add(new Heading(d1));
        actions.add(new Heading(d2));
    }
}
