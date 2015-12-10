package fr.unice.polytech.qgl.qab.strategy.context;

import fr.unice.polytech.qgl.qab.actions.Action;

/**
 * @version 10.12.2015
 */
public class ResponseState {
    private boolean status;
    private Action action;

    public ResponseState(Action action, boolean status) {
        this.status = status;
        this.action = action;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
