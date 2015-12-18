package fr.unice.polytech.qgl.qab.strategy.aerial.states;

import fr.unice.polytech.qgl.qab.util.enums.Direction;

/**
 * @version 17/12/15.
 */
public class StateMediator {
    private static StateMediator instance;

    // initialize - go to the corner
    private boolean souldGoToTheCorner;
    private int rangeToTheCorner;
    private Direction directionToTheCorner;
    private int rangeToGround;

    private StateMediator() {
        souldGoToTheCorner = false;
        rangeToTheCorner = 0;
        directionToTheCorner = null;
        rangeToGround = 0;
    }

    public static StateMediator getInstance() {
        if (instance == null)
            instance = new StateMediator();
        return instance;
    }

    public boolean shouldGoToTheCorner() {
        return souldGoToTheCorner;
    }

    public void setGoToTheCorner(boolean goToTheCorner) {
        this.souldGoToTheCorner = goToTheCorner;
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

}
