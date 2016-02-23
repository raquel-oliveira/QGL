package fr.unice.polytech.qgl.qab.strategy.context.utils;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;

/**
 * @version 23/02/16.
 */
public class ContextAction {
    private Combo comboAction;
    private Combo comboReturnBack;
    private Action simpleAction;
    private Action lastAction;
    private int contScan;
    private int indexAction;

    public ContextAction() {
        comboAction = null;
        simpleAction = null;
        lastAction = null;
        contScan = 0;
        indexAction = 0;
        comboReturnBack = null;
    }

    public Combo getComboReturnBack() {
        return comboReturnBack;
    }

    public void setComboReturnBack(Combo comboReturnBack) {
        this.comboReturnBack = comboReturnBack;
    }

    public Combo getComboAction() {
        return comboAction;
    }

    public Action getSimpleAction() {
        return simpleAction;
    }

    public Action getLastAction() {
        return lastAction;
    }

    public int getContScan() {
        return contScan;
    }

    public int getIndexAction() {
        return indexAction;
    }

    public void setIndexAction(int indexAction) {
        this.indexAction = indexAction;
    }

    public void incrementIndexAction() {
        this.indexAction += 1;
    }

    public void setContScan(int contScan) {
        this.contScan = contScan;
    }

    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    public void setSimpleAction(Action simpleAction) {
        this.simpleAction = simpleAction;
    }

    public void setComboAction(Combo comboAction) {
        this.comboAction = comboAction;
    }

}
