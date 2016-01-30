package fr.unice.polytech.qgl.qab.strategy.ground.states;

/**
 * @version 17/01/2016.
 */
public class StateManager {
    private static StateManager instance;

    private boolean decideToExit;

    private StateManager(){
        decideToExit = false;
    }

    public static StateManager getInstance(){
        if (instance == null)
            instance = new StateManager();
        return instance;
    }

    public boolean getDecideToExit(){
        return this.decideToExit;
    }

    public void setDecideToExit(){
        this.decideToExit = true;
    }
}
