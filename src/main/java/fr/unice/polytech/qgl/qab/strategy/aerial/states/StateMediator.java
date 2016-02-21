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
    private Direction directionToFindGround;
    private int rangeToGround;

    // return back
    private boolean flyUntilGround;

    private StateMediator() {
        shouldGoToTheCorner = false;
        rangeToTheCorner = 0;
        directionToTheCorner = null;
        rangeToGround = 0;
        flyUntilGround = false;
        directionToFindGround = null;
    }

    public static StateMediator getInstance() {
        if (instance == null)
            instance = new StateMediator();
        return instance;
    }

    /**
     * Judge if should go to the corner
     * @return if should return true, if not return false
     */
    public boolean shouldGoToTheCorner() {
        return this.shouldGoToTheCorner;
    }

    /**
     * set value of shouldGoToTheCorner
     * @param goToTheCorner
     */
    public void setGoToTheCorner(boolean goToTheCorner) {
        this.shouldGoToTheCorner = goToTheCorner;
    }

    /**
     * get the range to the corner
     * @return range to the corner
     */
    public int getRangeToTheCorner() {
        return rangeToTheCorner;
    }

    /**
     * set value of the range to the corner
     * @param rangeToTheCorner
     */
    public void setRangeToTheCorner(int rangeToTheCorner) {
        this.rangeToTheCorner = rangeToTheCorner;
    }

    /**
     * get the direction to the corner
     * @return the direction to the corner
     */
    public Direction getDirectionToTheCorner() {
        return directionToTheCorner;
    }

    /**
     * set the value of the direction to the corner
     * @param directionToTheCorner
     */
    public void setDirectionToTheCorner(Direction directionToTheCorner) {
        this.directionToTheCorner = directionToTheCorner;
    }

    /**
     * get the range to ground
     * @return the range to ground
     */
    public int getRangeToGround() {
        return rangeToGround;
    }

    /**
     * set the value of the range to ground
     * @param rangeToGround
     */
    public void setRangeToGround(int rangeToGround) {
        this.rangeToGround = rangeToGround;
    }

    /**
     * set the value of flyUntilGround
     * @param foundGround
     */
    public void shouldFlyUntilGround(boolean foundGround) {
        this.flyUntilGround = foundGround;
    }

    /**
     * get if should fly until ground
     * @return flyUntilGround
     */
    public boolean shouldFlyUntilGround() { return flyUntilGround; }


    public Direction getDirectionToFindGround() {
        return directionToFindGround;
    }

    public void setDirectionToFindGround(Direction directionToFindGround) {
        this.directionToFindGround = directionToFindGround;
    }
}
