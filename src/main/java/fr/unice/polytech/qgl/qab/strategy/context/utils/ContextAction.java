package fr.unice.polytech.qgl.qab.strategy.context.utils;

import fr.unice.polytech.qgl.qab.actions.Action;
import fr.unice.polytech.qgl.qab.actions.combo.Combo;
import fr.unice.polytech.qgl.qab.map.tile.Position;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryResource;
import fr.unice.polytech.qgl.qab.resources.primary.PrimaryType;
import fr.unice.polytech.qgl.qab.strategy.context.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * @version 23/02/16.
 *
 * Class to represent the datas about the action used in the states.
 */
public class ContextAction {
    private Combo comboAction;
    private Combo comboReturnBack;
    private Action simpleAction;
    private Action lastAction;
    private int contScan;
    private int indexAction;

    private List<PrimaryType> resourcesToExploit;
    private int status;
    private Position nextPosition;

    public ContextAction() {
        comboAction = null;
        simpleAction = null;
        lastAction = null;
        contScan = 0;
        indexAction = 0;
        comboReturnBack = null;
        resourcesToExploit = new ArrayList<>();
        status = 0;
        nextPosition = null;
    }

    public Position getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Position nextPosition) {
        this.nextPosition = nextPosition;
    }

    public void setStatus(int b) {
        this.status = b;
    }

    /**
     * Method to return the combo action to return back.
     * @return combo action to return back
     */
    public Combo getComboReturnBack() {
        return comboReturnBack;
    }

    /**
     * Method to set the combo action to return back
     * @param comboReturnBack combo action to return back
     */
    public void setComboReturnBack(Combo comboReturnBack) {
        this.comboReturnBack = comboReturnBack;
    }

    /**
     * Method to get the combo action commum
     * @return combo action
     */
    public Combo getComboAction() {
        return comboAction;
    }

    /**
     * Method to get a simple action
     * @return simple action
     */
    public Action getSimpleAction() {
        return simpleAction;
    }

    /**
     * Method to get the last action made
     * @return last action made
     */
    public Action getLastAction() {
        return lastAction;
    }

    /**
     * Method to get the cont scan (control the list of action in the scan the ground)
     * @return cont scan
     */
    public int getContScan() {
        return contScan;
    }

    /**
     * Get the index action
     * @return index action
     */
    public int getIndexAction() {
        return indexAction;
    }

    /**
     * Set the index action
     * @param indexAction index action
     */
    public void setIndexAction(int indexAction) {
        this.indexAction = indexAction;
    }

    /**
     * Increment the index action in + 1
     */
    public void incrementIndexAction() {
        this.indexAction += 1;
    }

    /**
     * Set value in cont scan
     * @param contScan value to set in cont scan
     */
    public void setContScan(int contScan) {
        this.contScan = contScan;
    }

    /**
     * Set the last action made
     * @param lastAction last action made
     */
    public void setLastAction(Action lastAction) {
        this.lastAction = lastAction;
    }

    /**
     * Set a simple action
     * @param simpleAction simple action
     */
    public void setSimpleAction(Action simpleAction) {
        this.simpleAction = simpleAction;
    }

    /**
     * Set the combo action
     * @param comboAction combo action
     */
    public void setComboAction(Combo comboAction) {
        this.comboAction = comboAction;
    }

    /**
     * Method that returns the resources to exploit
     * @return resources to exploit
     */
    public List<PrimaryType> getResourcesToExploit() { return resourcesToExploit; }

    /**
     * Method that update parameter resourcesToExploit with the resources importants
     * to fill the contract that were find in the tile
     * @param resourcesToExploit resources to exploit
     * @param context current context data
     */
    public void setResourcesToExploit(List<PrimaryType> resourcesToExploit, Context context) {
        java.util.Map<String, Integer> collectedResource = context.getContracts().getCollectedResources();
        while(!resourcesToExploit.isEmpty()){
            PrimaryResource res = new PrimaryResource(resourcesToExploit.get(0));
            String resource = res.getName();
            if(!collectedResource.containsKey(resource)){
                this.resourcesToExploit.add(resourcesToExploit.get(0));
                resourcesToExploit.remove(0);
            }
            else {
                if(collectedResource.get(resource) < context.getContracts().getAmountPrimaryNeeded(res)) {
                    this.resourcesToExploit.add(resourcesToExploit.get(0));
                    resourcesToExploit.remove(0);
                }else{
                    resourcesToExploit.remove(0);
                }

            }
        }
    }

    /**
     * Return the status of the simulation
     * @return status of the simulation
     */
    public int getStatus() {
        return this.status;
    }
}
