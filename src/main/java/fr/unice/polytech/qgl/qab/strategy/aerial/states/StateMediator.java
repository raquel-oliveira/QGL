package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * @version 17/12/15.
 */
public class StateMediator {
    private static StateMediator instance;

    // initialize - go to the corner
    private boolean shouldGoToTheCorner;
    private int rangeToTheCorner;
    private Direction directionToTheCorner;
    private int rangeToGround;

    // return back
    private boolean flyUntilGround;

    private StateMediator() {
        shouldGoToTheCorner = false;
        rangeToTheCorner = 0;
        directionToTheCorner = null;
        rangeToGround = 0;
        flyUntilGround = false;
    }

    public static StateMediator getInstance() {
        if (instance == null)
            instance = new StateMediator();
        return instance;
    }

    public boolean shouldGoToTheCorner() {
        return shouldGoToTheCorner;
    }

    public void setGoToTheCorner(boolean goToTheCorner) {
        this.shouldGoToTheCorner = goToTheCorner;
    }

    public int getRangeToTheCorner() {
        return rangeToTheCorner;
    }

    public void setRangeToTheCorner(int rangeToTheCorner) {
        this.rangeToTheCorner = rangeToTheCorner;
    }

    public Direction getDirectionToTheCorner() {
        return directionToTheCorner;
    }

    public void setDirectionToTheCorner(Direction directionToTheCorner) {
        this.directionToTheCorner = directionToTheCorner;
    }

    public int getRangeToGround() {
        return rangeToGround;
    }

    public void setRangeToGround(int rangeToGround) {
        this.rangeToGround = rangeToGround;
    }

    public void shouldFlyUntilGround(boolean foundGround) {
        this.flyUntilGround = foundGround;
    }

    public boolean shouldFlyUntilGround() { return flyUntilGround; }
}
